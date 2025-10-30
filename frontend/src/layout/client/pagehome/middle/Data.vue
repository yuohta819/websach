<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const books = ref([])
const url = import.meta.env.VITE_API_URL_FRONTEND
const backend = import.meta.env.VITE_API_URL_BACKEND

onMounted(async () => {
  try {
    const res = await axios.get(`${backend}/api/all`, { timeout: 10000 })
    books.value = res.data.slice(-5).reverse() // lấy 5 sách mới nhất
  } catch (error) {
    console.error("Lỗi khi tải dữ liệu sách:", error)
  }
})
</script>

<template>
  <section class="selection-4 py-20 bg-gradient-to-b from-amber-50 via-white to-amber-100">
    <div class="text-center mb-10">
      <h2 class="text-[40px] sm:text-[50px] font-semibold text-gray-800" style="font-family: 'Playwrite RO', cursive;">
        Discover Your Next Book
      </h2>
      <div class="mx-auto mt-4" style="width: 80%; border-bottom: 2px solid #DEDEDE;"></div>
    </div>

    <div class="flex flex-wrap justify-center gap-8 px-6 sm:px-20">
      <div
        v-for="item in books"
        :key="item.bookid"
        class="book-card bg-white rounded-2xl overflow-hidden border border-gray-200 shadow-md hover:shadow-2xl transition-all duration-300 w-[220px] sm:w-[223px]"
      >
        <a :href="`/book/${item.name}/${item.category.name}/${item.bookid}`" class="block">
          <div class="overflow-hidden rounded-t-xl">
            <img
              :src="item.img"
              alt="Book cover"
              class="book-img w-full h-[280px] object-cover transition-transform duration-500 hover:scale-110"
            />
          </div>
          <div class="p-4">
            <h3 class="truncate-name text-[18px] font-[600] text-gray-800" style="font-family: 'Playfair Display', serif;">
              {{ item.name }}
            </h3>
            <p class="pt-1 text-red-600 italic" style="font-family: 'EB Garamond', serif;">{{ item.author }}</p>

            <div class="flex justify-center gap-1 mt-2">
              <i
                v-for="n in 5"
                :key="n"
                class="fa-solid fa-star"
                :class="n <= item.rate ? 'text-yellow-400' : 'text-gray-300'"
              ></i>
            </div>

            <p class="pt-3 text-red-500 text-[20px] font-semibold">
              {{ item.price.toLocaleString("vi-VN") }}đ
            </p>
          </div>
        </a>
      </div>
    </div>

    <div class="text-center mt-16">
      <a :href="`${url}/book`">
        <button
          class="px-10 py-4 text-white font-semibold uppercase tracking-wider rounded-full transition-transform duration-300 hover:scale-105"
          style="background: linear-gradient(90deg, black, #DE3241); box-shadow: rgba(0,0,0,0.25) 0px 8px 20px;"
        >
          Discover More Books
        </button>
      </a>
    </div>
  </section>
</template>

<style scoped>
.truncate-name {
  display: -webkit-box;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
  overflow: hidden;
  text-overflow: ellipsis;
}

.book-card {
  transition: transform 0.4s ease, box-shadow 0.4s ease;
}
.book-card:hover {
  transform: translateY(-6px);
  box-shadow: rgba(0, 0, 0, 0.25) 0px 10px 25px;
}
.book-img {
  transition: transform 0.4s ease;
}
.book-card:hover .book-img {
  transform: scale(1.08);
}
</style>
