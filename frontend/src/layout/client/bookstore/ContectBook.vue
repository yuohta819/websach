<script setup>
import { ref, computed, onMounted } from "vue";
import axios from "axios";

const backend = import.meta.env.VITE_API_URL_BACKEND;

// Dữ liệu chính
const books = ref([]);
const search = ref("");
const selectedCategory = ref("all");
const selectedAuthor = ref("all");

// Phân trang
const currentPage = ref(1);
const itemsPerPage = ref(8);

onMounted(async () => {
    try {
        const res = await axios.get(`${backend}/book/call`, { timeout: 10000 });
        console.log("📚 Dữ liệu sách:", res.data);
        books.value = res.data; // API trả mảng sách trực tiếp
    } catch (err) {
        console.error("Lỗi khi tải sách:", err);
    }
});

// Lấy danh mục và tác giả duy nhất
const categories = computed(() => [
    "all",
    ...new Set(
        books.value
            .map((b) => b.categories?.name)
            .filter((c) => c && c !== "")
    ),
]);
const authors = computed(() => [
    "all",
    ...new Set(
        books.value.map((b) => b.author).filter((a) => a && a !== "")
    ),
]);

// Lọc sách
const filteredBooks = computed(() =>
    books.value.filter((b) => {
        const matchSearch = b.name
            .toLowerCase()
            .includes(search.value.toLowerCase());
        const matchCategory =
            selectedCategory.value === "all" ||
            b.categories?.name === selectedCategory.value;
        const matchAuthor =
            selectedAuthor.value === "all" || b.author === selectedAuthor.value;
        return matchSearch && matchCategory && matchAuthor;
    })
);

// Tính phân trang
const totalPages = computed(() =>
    Math.ceil(filteredBooks.value.length / itemsPerPage.value)
);
const paginatedBooks = computed(() => {
    const start = (currentPage.value - 1) * itemsPerPage.value;
    return filteredBooks.value.slice(start, start + itemsPerPage.value);
});

function changePage(page) {
    if (page >= 1 && page <= totalPages.value) currentPage.value = page;
}
</script>

<template>
    <div class="flex flex-col lg:flex-row px-8 py-10 gap-10 bg-[#F8F5F2] min-h-screen">
        <!-- SIDEBAR -->
        <aside class="w-full lg:w-1/4 bg-white p-6 rounded-2xl shadow-lg border border-orange-100">
            <h2 class="text-2xl font-serif mb-5 border-b-2 pb-2 text-[#4B2E05]">
                Filters
            </h2>

            <!-- Ô tìm kiếm -->
            <div class="mb-6">
                <label class="block text-sm font-semibold text-[#6B4E3D] mb-2">
                    Search Books
                </label>
                <input v-model="search" type="text" placeholder="Type book name..."
                    class="w-full px-3 py-2 border border-orange-200 rounded-md focus:ring-2 focus:ring-[#E89B4B] outline-none transition" />
            </div>

            <!-- Lọc theo thể loại -->
            <div class="mb-6">
                <h3 class="text-lg font-serif mb-3 text-[#4B2E05]">Category</h3>
                <ul class="space-y-2">
                    <li v-for="c in categories" :key="c" @click="selectedCategory = c; currentPage = 1" :class="[
                        'cursor-pointer px-3 py-2 rounded-md transition',
                        selectedCategory === c
                            ? 'bg-[#E89B4B] text-white'
                            : 'text-[#4B2E05] hover:bg-[#FFF3E0]',
                    ]">
                        {{ c === 'all' ? 'All' : c }}
                    </li>
                </ul>
            </div>

            <!-- Lọc theo tác giả -->
            <div>
                <h3 class="text-lg font-serif mb-3 text-[#4B2E05]">Author</h3>
                <ul class="space-y-2">
                    <li v-for="a in authors" :key="a" @click="selectedAuthor = a; currentPage = 1" :class="[
                        'cursor-pointer px-3 py-2 rounded-md transition',
                        selectedAuthor === a
                            ? 'bg-[#E89B4B] text-white'
                            : 'text-[#4B2E05] hover:bg-[#FFF3E0]',
                    ]">
                        {{ a === 'all' ? 'All' : a }}
                    </li>
                </ul>
            </div>
        </aside>

        <!-- DANH SÁCH SÁCH -->
        <section class="flex-1">
            <div class="flex justify-between items-center mb-6">
                <h2 class="text-3xl font-serif font-semibold text-[#3C2A1E]">
                    All Books
                </h2>
                <p class="text-sm text-gray-500">
                    Showing {{ paginatedBooks.length }} of {{ filteredBooks.length }}
                </p>
            </div>

            <!-- Lưới hiển thị sách -->
            <div class="grid grid-cols-2 sm:grid-cols-3 lg:grid-cols-4 gap-8 place-items-center">
                <div v-for="item in paginatedBooks" :key="item.bookid"
                    class="book-box bg-white rounded-xl shadow-md overflow-hidden border border-orange-100 hover:shadow-xl hover:-translate-y-1 transition-all duration-300">
                    <a :href="`/${item.name}/${item.categories?.name}/${item.bookid}`">
                        <div class="h-[260px] w-full overflow-hidden flex justify-center items-center bg-gray-50">
                            <img :src="item.img" alt="book"
                                class="object-cover h-full w-full hover:scale-105 transition-transform duration-300" />
                        </div>
                    </a>

                    <div class="p-4 text-center flex flex-col justify-between h-[160px]">
                        <div>
                            <h3 class="font-serif text-lg font-semibold text-[#4B2E05] truncate">
                                {{ item.name }}
                            </h3>
                            <p class="text-sm text-[#E07B39] mt-1 font-medium truncate">
                                {{ item.author }}
                            </p>
                            <div class="flex justify-center gap-1 mt-2">
                                <i v-for="n in 5" :key="n" class="fa-solid fa-star"
                                    :class="n <= item.rate ? 'text-yellow-400' : 'text-gray-300'"></i>
                            </div>
                        </div>
                        <p class="mt-3 text-gray-700 font-semibold">
                            {{ item.price.toLocaleString("vi-VN") }}
                            <span class="text-[#E07B39]">đ</span>
                        </p>

                    </div>
                </div>
            </div>

            <!-- Phân trang -->
            <div class="flex justify-center mt-10 gap-2">
                <button @click="changePage(currentPage - 1)" :disabled="currentPage === 1"
                    class="px-4 py-2 rounded-md bg-[#F0E6DD] text-[#4B2E05] hover:bg-[#E89B4B] hover:text-white disabled:opacity-50 transition">
                    ‹ Prev
                </button>

                <button v-for="p in totalPages" :key="p" @click="changePage(p)" :class="[
                    'px-4 py-2 rounded-md font-medium transition',
                    currentPage === p
                        ? 'bg-[#E89B4B] text-white'
                        : 'bg-[#F0E6DD] text-[#4B2E05] hover:bg-[#E89B4B] hover:text-white',
                ]">
                    {{ p }}
                </button>

                <button @click="changePage(currentPage + 1)" :disabled="currentPage === totalPages"
                    class="px-4 py-2 rounded-md bg-[#F0E6DD] text-[#4B2E05] hover:bg-[#E89B4B] hover:text-white disabled:opacity-50 transition">
                    Next ›
                </button>
            </div>
        </section>
    </div>
</template>

<style scoped>
li {
    transition: all 0.2s ease;
}

.book-box {
    width: 230px;
    /* cố định chiều ngang để layout đều */
    height: 420px;
    /* cố định chiều cao toàn khung */
    display: flex;
    flex-direction: column;
    justify-content: space-between;
}
</style>
