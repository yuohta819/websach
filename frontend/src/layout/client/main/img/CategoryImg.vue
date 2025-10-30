<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";
import { useRouter } from "vue-router";

const router = useRouter();
const categories = ref([]);
const apiUrl = import.meta.env.VITE_API_URL_BACKEND + "/category/call";

onMounted(async () => {
  try {
    const res = await axios.get(apiUrl);
    // Lấy 5 loại đầu tiên
    categories.value = res.data.slice(0, 5);
  } catch (err) {
    console.error("Error loading categories:", err);
  }
});

const goToCategory = (name) => {
  router.push(`/category/${name}`);
};
</script>

<template>
  <section class="bg-[#F5F5F4] py-20 px-6 lg:px-12">
    <!-- Tiêu đề -->
    <div class="text-center mb-14">
      <h2 class="text-4xl md:text-5xl font-serif text-gray-800 tracking-wide">
        Choose Your Book!
      </h2>
      <div class="w-24 h-[2px] bg-gray-400 mx-auto mt-4"></div>
    </div>

    <!-- Lưới danh mục -->
    <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-5 gap-8 place-items-center">
      <div v-for="cat in categories" :key="cat.categoryid" @click="goToCategory(cat.categoryid)"
        class="relative group cursor-pointer overflow-hidden rounded-2xl shadow-md hover:shadow-2xl transition-all duration-500 bg-white w-[220px] h-[340px]">
        <!-- Ảnh -->
        <img :src="cat.img" :alt="cat.name"
          class="w-full h-full object-cover group-hover:scale-110 transition-transform duration-700" />

        <!-- Lớp phủ mờ -->
        <div
          class="absolute inset-0 bg-gradient-to-t from-black/70 via-black/40 to-transparent opacity-70 group-hover:opacity-90 transition-all duration-500">
        </div>

        <!-- Tên loại sách -->
        <div class="absolute bottom-5 left-5 text-white z-10">
          <h3 class="text-xl font-semibold mb-1 transition-all duration-300 group-hover:-translate-y-1">
            {{ cat.name }}
          </h3>
          <p class="opacity-0 group-hover:opacity-100 text-sm tracking-wide transition-opacity duration-300">
            Shop Now →
          </p>
        </div>
      </div>
    </div>

    <!-- Nút xem thêm -->
    <a href="/category">
      <div class="text-center mt-16">
        <button 
          class="py-3 px-8 border-2 border-black font-medium bg-black text-white hover:bg-transparent hover:text-black transition-all duration-300 shadow-md rounded-md">
          DISCOVER MORE CATEGORIES
        </button>
      </div>
    </a>
  </section>
</template>

<style scoped>
h2 {
  font-family: "EB Garamond", serif;
}
</style>
