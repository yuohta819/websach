<template>
  <div class="min-h-screen bg-gradient-to-br from-amber-50 via-white to-amber-100 p-8">
    <!-- Header -->
    <header class="flex justify-between items-center mb-10">
      <h1 class="text-3xl font-bold text-amber-700 tracking-tight flex items-center gap-2">
        📚 Quản lý Thư viện
      </h1>

      <div class="flex items-center gap-3">
        <span class="text-gray-700 text-sm">
          Xin chào, <strong class="text-amber-800">Admin</strong> 👋
        </span>
        <button
          class="bg-gradient-to-r from-amber-600 to-orange-500 text-white px-4 py-1.5 rounded-lg text-sm shadow hover:opacity-90 transition">
          Đăng xuất
        </button>
      </div>
    </header>

    <!-- Section Title -->
    <div class="flex justify-between items-center mb-6">
      <h2 class="text-xl font-semibold text-gray-800 flex items-center gap-2">
        <span class="text-amber-600">📖</span> Danh sách Sách
      </h2>

      <a href="/admin/create">
        <button
          class="flex items-center gap-2 px-4 py-2 bg-amber-600 text-white text-sm font-medium rounded-lg shadow hover:bg-amber-700 transition">
          ➕ Thêm sách
        </button>
      </a>
    </div>

    <!-- Grid sách -->
    <div v-if="paginatedBooks.length" class="grid gap-6 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
      <div v-for="book in paginatedBooks" :key="book.bookid"
        class="group relative bg-white rounded-2xl shadow-md hover:shadow-xl transition transform hover:-translate-y-1">
        <img :src="book.img" alt="book cover" class="w-full h-56 object-cover rounded-t-2xl" />

        <div class="p-4">
          <h3 class="text-base font-semibold text-gray-800 group-hover:text-amber-700 transition line-clamp-1">
            {{ book.name }}
          </h3>
          <p class="text-sm text-gray-500 mb-1">{{ book.author }}</p>
          <p class="text-xs text-amber-600 mb-2">
            {{ book.categories?.name || "Không rõ thể loại" }}
          </p>
          <p class="text-sm text-gray-600 mb-3 line-clamp-3">
            {{ book.describe }}
          </p>

          <div class="flex justify-between items-center">
            <span class="text-sm text-amber-700 font-medium">
              {{ formatPrice(book.price) }}₫
            </span>
            <div class="flex gap-2">
              <a :href="`/admin/edit/${book.bookid}`">
                <button class="p-1.5 bg-blue-50 text-blue-600 rounded-lg hover:bg-blue-100 transition"
                  title="Chỉnh sửa">
                  ✏️
                </button>
              </a>
              <button @click="deleteBook(book.bookid)"
                class="p-1.5 bg-red-50 text-red-600 rounded-lg hover:bg-red-100 transition" title="Xóa">
                🗑️
              </button>
            </div>
          </div>
        </div>

        <div class="absolute top-3 right-3 bg-white/90 text-xs text-gray-700 px-2 py-0.5 rounded-full shadow-sm">
          #{{ book.bookid }}
        </div>
      </div>
    </div>

    <!-- Khi chưa có sách -->
    <div v-else class="text-center py-20 text-gray-500 text-sm italic bg-white rounded-xl shadow-sm">
      Chưa có dữ liệu sách nào.
    </div>

    <!-- Pagination -->
    <div class="flex justify-center mt-10 gap-2">
      <button @click="prevPage" :disabled="currentPage === 1"
        class="px-3 py-1.5 border rounded-lg text-sm text-gray-700 hover:bg-amber-50 disabled:opacity-40">
        ← Trước
      </button>
      <span class="px-4 py-1.5 text-gray-600 text-sm bg-white rounded-lg shadow-sm">
        Trang {{ currentPage }} / {{ totalPages }}
      </span>
      <button @click="nextPage" :disabled="currentPage === totalPages"
        class="px-3 py-1.5 border rounded-lg text-sm text-gray-700 hover:bg-amber-50 disabled:opacity-40">
        Sau →
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from "vue";
import axios from "axios";
import { useToast } from "vue-toastification"
const books = ref([]);
const currentPage = ref(1);
const itemsPerPage = 8;
const toast = useToast()
onMounted(async () => {
  try {
    const res = await axios.get(`${import.meta.env.VITE_API_URL_BACKEND}/book/call`);
    books.value = res.data;
  } catch (err) {
    console.error("Lỗi khi tải sách:", err);
  }
});

const totalPages = computed(() => Math.ceil(books.value.length / itemsPerPage));
const paginatedBooks = computed(() => {
  const start = (currentPage.value - 1) * itemsPerPage;
  return books.value.slice(start, start + itemsPerPage);
});

const formatPrice = (price) =>
  price ? price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".") : "0";

const nextPage = () => {
  if (currentPage.value < totalPages.value) currentPage.value++;
};
const prevPage = () => {
  if (currentPage.value > 1) currentPage.value--;
};

const addBook = () => alert("Tính năng thêm sách sắp ra mắt 😄")
const deleteBook = async (id) => {
  if (confirm("Bạn chắc chắn muốn xóa sách này?")) {
    try {
      await axios.get(`${import.meta.env.VITE_API_URL_BACKEND}/book/delete/${id}`);
      books.value = books.value.filter((b) => b.bookid !== id);
      toast.success("Successfully deleted the book")
    } catch (err) {
      console.error("Lỗi khi xóa:", err);
    }
  }
};
</script>

<style scoped>
.line-clamp-1 {
  display: -webkit-box;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

.line-clamp-3 {
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
</style>
