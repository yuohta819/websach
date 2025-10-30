<script setup>
import { ref, computed, onMounted } from "vue";
import axios from "axios";

const apiUrl = import.meta.env.VITE_API_URL_BACKEND + "/category/call";

const categories = ref([]);
const searchQuery = ref("");
const currentPage = ref(1);
const perPage = 8; // số danh mục mỗi trang

// Gọi API khi trang load
onMounted(async () => {
    try {
        const res = await axios.get(apiUrl);
        categories.value = res.data;
    } catch (err) {
        console.error("Error loading categories:", err);
    }
});

// Lọc theo tìm kiếm
const filteredCategories = computed(() => {
    if (!searchQuery.value) return categories.value;
    return categories.value.filter((cat) =>
        cat.name.toLowerCase().includes(searchQuery.value.toLowerCase())
    );
});

// Tính tổng số trang
const totalPages = computed(() =>
    Math.ceil(filteredCategories.value.length / perPage)
);

// Dữ liệu hiển thị trên trang hiện tại
const paginatedCategories = computed(() => {
    const start = (currentPage.value - 1) * perPage;
    return filteredCategories.value.slice(start, start + perPage);
});

// Chuyển trang
const goToPage = (page) => {
    if (page >= 1 && page <= totalPages.value) {
        currentPage.value = page;
        window.scrollTo({ top: 0, behavior: "smooth" });
    }
};
</script>

<template>
    <section class="bg-[#F5F5F4] min-h-screen py-15 px-6 lg:px-35">
        <!-- Tiêu đề -->
        <div class="text-center mb-10">
            <h1 class="text-4xl md:text-5xl font-serif text-gray-800">
                Explore Book Categories
            </h1>
            <div class="w-24 h-[2px] bg-gray-400 mx-auto mt-4"></div>
            <p class="text-gray-600 mt-3">Find the genre that inspires your next read.</p>
        </div>

        <!-- Thanh tìm kiếm -->
        <div class="max-w-xl mx-auto mb-12">
            <input v-model="searchQuery" type="text" placeholder="Search categories..."
                class="w-full px-5 py-3 border border-gray-300 rounded-md shadow-sm focus:ring-2 focus:ring-gray-600 focus:outline-none bg-white placeholder-gray-400" />
        </div>

        <!-- Lưới danh mục -->
        <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-10 place-items-center">
            <div v-for="cat in paginatedCategories" :key="cat.categoryid"
                class="relative group cursor-pointer overflow-hidden rounded-2xl shadow-md hover:shadow-2xl transition-all duration-500 bg-white w-[260px] h-[380px]">
                <a :href="`/category/${cat.categoryid}`">
                    <!-- Ảnh -->
                    <img :src="cat.img" :alt="cat.name"
                        class="w-full h-full object-cover group-hover:scale-110 transition-transform duration-700" />

                    <!-- Overlay -->
                    <div
                        class="absolute inset-0 bg-gradient-to-t from-black/70 via-black/40 to-transparent opacity-70 group-hover:opacity-90 transition-all duration-500">
                    </div>

                    <!-- Tên loại sách -->
                    <div class="absolute bottom-6 left-6 text-white z-10">
                        <h3 class="text-2xl font-semibold mb-1 transition-all duration-300 group-hover:-translate-y-1">
                            {{ cat.name }}
                        </h3>
                        <p
                            class="opacity-0 group-hover:opacity-100 text-sm tracking-wide transition-opacity duration-300">
                            Discover Now →
                        </p>
                    </div>
                </a>
            </div>
        </div>

        <!-- Phân trang -->
        <div class="flex justify-center items-center mt-16 space-x-2">
            <button @click="goToPage(currentPage - 1)" :disabled="currentPage === 1"
                class="px-4 py-2 border border-gray-500 rounded-md hover:bg-gray-200 disabled:opacity-40">
                Prev
            </button>

            <button v-for="page in totalPages" :key="page" @click="goToPage(page)" :class="[
                'px-4 py-2 rounded-md font-medium',
                currentPage === page
                    ? 'bg-gray-800 text-white'
                    : 'border border-gray-400 hover:bg-gray-100',
            ]">
                {{ page }}
            </button>

            <button @click="goToPage(currentPage + 1)" :disabled="currentPage === totalPages"
                class="px-4 py-2 border border-gray-500 rounded-md hover:bg-gray-200 disabled:opacity-40">
                Next
            </button>
        </div>
    </section>
</template>

<style scoped>
h1 {
    font-family: "EB Garamond", serif;
}
</style>
