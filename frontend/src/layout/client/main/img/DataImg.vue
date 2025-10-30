<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";

const books = ref([]);
const backend = import.meta.env.VITE_API_URL_BACKEND;
const frontend = import.meta.env.VITE_API_URL_FRONTEND;

onMounted(async () => {
  try {
    const res = await axios.get(`${backend}/api/all`, { timeout: 10000 });
    // Chỉ map các trường cần thiết
    books.value = res.data.slice(0, 5).map((b) => ({
      id: b.bookid,
      name: b.name,
      author: b.author,
      price: b.price,
      rate: b.rate,
      img: b.img,
      category: b.category?.name || "Unknown"
    }));
  } catch (error) {
    console.error("Lỗi khi tải dữ liệu:", error);
  }
});
</script>

<template>
  <section class="selection-4 py-16 bg-[#F5F5F4]">
    <h2
      class="text-center text-[50px] font-semibold mb-4"
      style="font-family: Playwrite RO, cursive;"
    >
      Picked by Readers
    </h2>
    <div class="mx-auto mb-10" style="width: 80%; border-bottom: 2px solid #7F7F7F;"></div>

    <div class="flex flex-wrap justify-center gap-10 px-8">
      <div
        v-for="item in books"
        :key="item.id"
        class="book-card bg-white rounded-2xl overflow-hidden border border-gray-200 shadow-md hover:shadow-xl hover:-translate-y-2 transition-all duration-300 w-[220px]"
      >
        <a :href="`${frontend}/book/${item.name}/${item.category}/${item.id}`" class="block">
          <div class="overflow-hidden">
            <img
              :src="item.img"
              alt="Book image"
              class="w-full h-[270px] object-cover transition-transform duration-500 hover:scale-110"
            />
          </div>
          <div class="p-4 text-center">
            <p class="font-semibold text-[18px] truncate" style="font-family: Playfair Display, serif;">
              {{ item.name }}
            </p>
            <p class="text-red-600 mt-1" style="font-family: EB Garamond, serif;">{{ item.author }}</p>
            <div class="flex justify-center gap-1 mt-1">
              <i
                v-for="n in 5"
                :key="n"
                class="fa-solid fa-star"
                :class="n <= item.rate ? 'text-yellow-400' : 'text-gray-300'"
              ></i>
            </div>
            <p class="text-red-500 text-[20px] font-semibold mt-3">
              {{ item.price.toLocaleString('vi-VN') }}đ
            </p>
          </div>
        </a>
      </div>
    </div>

    <div class="text-center mt-16">
      <a :href="`${frontend}/book`">
        <button
          class="py-4 px-10 text-white uppercase tracking-wider hover:scale-105 transition-transform duration-300"
          style="border: 3px solid black; background-color: black; box-shadow: rgba(0,0,0,0.25) 0px 14px 28px, rgba(0,0,0,0.22) 0px 10px 10px;"
        >
          Discover More Books
        </button>
      </a>
    </div>
  </section>
</template>

<style scoped>
.truncate {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
</style>
