<script setup>
import { ref, computed, onMounted } from "vue";
import axios from "axios";
import { useRoute } from "vue-router";

const route = useRoute();
const backend = import.meta.env.VITE_API_URL_BACKEND;

// Data
const booksRaw = ref([]);
const searchQuery = ref("");
const selectedCategory = ref("");
const selectedAuthor = ref("");
const priceRange = ref([0, 1000000]);

// Pagination
const currentPage = ref(1);
const itemsPerPage = 8;

// Fetch data
onMounted(async () => {
  try {
    const res = await axios.get(`${backend}/book/${route.params.type}`);
    booksRaw.value = Array.isArray(res.data) ? res.data : [];
  } catch (e) {
    console.error("Error loading books:", e);
  }
});

// Unique authors & categories
const authors = computed(() =>
  Array.from(new Set(booksRaw.value.map((b) => b.author).filter(Boolean)))
);
const categories = computed(() =>
  Array.from(
    new Set(booksRaw.value.map((b) => b.categories?.name).filter(Boolean))
  )
);

// Filtered books
const filteredBooks = computed(() => {
  return booksRaw.value.filter((b) => {
    const matchesName = (b.name || "")
      .toLowerCase()
      .includes(searchQuery.value.toLowerCase());
    const matchesCategory =
      !selectedCategory.value || b.categories?.name === selectedCategory.value;
    const matchesAuthor =
      !selectedAuthor.value || b.author === selectedAuthor.value;
    const matchesPrice =
      b.price >= priceRange.value[0] && b.price <= priceRange.value[1];
    return matchesName && matchesCategory && matchesAuthor && matchesPrice;
  });
});

// Pagination
const totalPages = computed(() =>
  Math.max(1, Math.ceil(filteredBooks.value.length / itemsPerPage))
);

const paginatedBooks = computed(() =>
  filteredBooks.value.slice(
    (currentPage.value - 1) * itemsPerPage,
    currentPage.value * itemsPerPage
  )
);

function goToPage(page) {
  if (page >= 1 && page <= totalPages.value) {
    currentPage.value = page;
    window.scrollTo({ top: 0, behavior: "smooth" });
  }
}
</script>

<template>
  <div class="flex flex-col lg:flex-row px-6 lg:px-16 py-16 bg-[#FAF9F6] gap-10">
    <!-- Sidebar -->
    <aside
      class="w-full lg:w-1/4 bg-white rounded-2xl shadow-md p-6 h-fit sticky top-10"
    >
      <h3 class="text-2xl font-serif text-gray-800 mb-6">Filter Books</h3>

      <!-- Search -->
      <div class="mb-5">
        <p class="text-gray-600 font-medium mb-2">Search</p>
        <input
          v-model="searchQuery"
          type="text"
          placeholder="Search by name..."
          class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:ring-2 focus:ring-gray-400 outline-none"
        />
      </div>

      <!-- Category Filter -->
      <div class="mb-5">
        <p class="text-gray-600 font-medium mb-2">Category</p>
        <select
          v-model="selectedCategory"
          class="w-full border border-gray-300 rounded-lg px-4 py-2 bg-white focus:ring-2 focus:ring-gray-400"
        >
          <option value="">All</option>
          <option v-for="c in categories" :key="c" :value="c">{{ c }}</option>
        </select>
      </div>

      <!-- Author Filter -->
      <div class="mb-5">
        <p class="text-gray-600 font-medium mb-2">Author</p>
        <select
          v-model="selectedAuthor"
          class="w-full border border-gray-300 rounded-lg px-4 py-2 bg-white focus:ring-2 focus:ring-gray-400"
        >
          <option value="">All</option>
          <option v-for="a in authors" :key="a" :value="a">{{ a }}</option>
        </select>
      </div>

      <button
        @click="
          selectedCategory = '';
          selectedAuthor = '';
          priceRange = [0, 1000000];
          searchQuery = '';
        "
        class="mt-6 w-full py-2 bg-gray-800 text-white rounded-lg hover:bg-gray-700 transition"
      >
        Reset Filters
      </button>
    </aside>

    <!-- Main Content -->
    <section class="flex-1">
      <!-- Header -->
      <div class="text-center mb-10">
        <h2 class="text-4xl font-serif text-gray-800">
          Explore {{ route.params.type }} Books
        </h2>
        <p class="text-gray-500 mt-2">Discover stories that inspire you</p>
      </div>

      <!-- Books Grid -->
      <div
        v-if="paginatedBooks.length"
        class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-8"
      >
        <div
          v-for="book in paginatedBooks"
          :key="book.bookid"
          class="group relative bg-white rounded-2xl shadow-md hover:shadow-2xl transition-all duration-500 overflow-hidden border border-gray-100"
        >
          <!-- Image -->
          <div class="overflow-hidden">
            <img
              :src="book.img"
              :alt="book.name"
              class="w-full h-64 object-cover group-hover:scale-110 transition-transform duration-700"
            />
          </div>

          <!-- Info -->
          <div class="p-4">
            <h3
              class="text-lg font-semibold text-gray-800 truncate font-[Playfair Display]"
            >
              {{ book.name }}
            </h3>
            <p class="text-sm text-gray-500 mt-1">{{ book.author }}</p>

            <div class="flex items-center mt-2">
              <i
                v-for="n in 5"
                :key="n"
                class="fa-solid fa-star"
                :class="n <= (book.rate || 0) ? 'text-yellow-400' : 'text-gray-300'"
              ></i>
            </div>

            <p class="mt-3 text-lg font-semibold text-gray-800">
              {{ new Intl.NumberFormat().format(book.price) }} ₫
            </p>
          </div>

          <!-- Hover Overlay -->
          <div
            class="absolute inset-0 bg-black/60 flex items-center justify-center opacity-0 group-hover:opacity-100 transition-opacity"
          >
            <a
              :href="`/${book.name}/${book.categories?.name}/${book.bookid}`"
              class="px-5 py-2 bg-white text-gray-900 font-semibold rounded-lg shadow hover:bg-gray-100"
            >
              View Details
            </a>
          </div>
        </div>
      </div>

      <!-- Empty -->
      <div v-else class="text-center text-gray-500 italic py-20">
        No books found.
      </div>

      <!-- Pagination -->
      <div v-if="totalPages > 1" class="flex justify-center mt-10 space-x-2">
        <button
          v-for="p in totalPages"
          :key="p"
          @click="goToPage(p)"
          class="px-4 py-2 rounded-lg font-semibold transition-all duration-300"
          :class="p === currentPage ? 'bg-gray-800 text-white' : 'bg-gray-200 hover:bg-gray-300'"
        >
          {{ p }}
        </button>
      </div>
    </section>
  </div>
</template>

<style scoped>
h2,
h3 {
  font-family: "EB Garamond", serif;
}
</style>
