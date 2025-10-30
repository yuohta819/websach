<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import { useToast } from 'vue-toastification';
import ReadOnline from './ReadOnline.vue';
import AddCart from './AddCart.vue';
const route = useRoute();
const backend = import.meta.env.VITE_API_URL_BACKEND;
const toast = useToast()
const rawData = ref(null);
const viewData = ref({
  bookid: null,
  name: '',
  author: '',
  img: '',
  rate: 0,
  price: 0,
  describe: '',
  publisher: '',
  publishing: '',
  numberOfPages: null,
  isbn: '',
  format: ''
});

const cnt = ref(1);
const id = route.params.id;
function mapToView(obj) {
  if (!obj) return;
  viewData.value.bookid = obj.bookid ?? obj.bookId ?? null;
  viewData.value.name = obj.name ?? '';
  viewData.value.author = obj.author ?? '';
  viewData.value.img = obj.img ?? '';
  viewData.value.rate = Number(obj.rate || 0);
  viewData.value.price = Number(obj.price || 0);
  viewData.value.describe = obj.describe ?? obj.detail?.describe ?? '';
  viewData.value.publisher = obj.detail?.publisher ?? '';
  viewData.value.publishing = obj.detail?.publishing ?? '';
  viewData.value.numberOfPages = obj.detail?.numberofpage ?? obj.detail?.numberOfPage ?? null;
  viewData.value.isbn = obj.detail?.isbn ?? '';
  viewData.value.format = obj.detail?.format ?? '';
  viewData.value.is_roles = obj.is_roles ?? '';
}

onMounted(async () => {
  try {
    const response = await axios.get(`${backend}/book/edit/${id}`);
    rawData.value = Array.isArray(response.data) ? response.data[0] : response.data;
    mapToView(rawData.value);
  } catch (error) {
    console.error('fetch book error:', error);
  }
});

const handleMouseMove = (e) => {
  const img = e.target;
  const rect = img.getBoundingClientRect();
  const x = ((e.clientX - rect.left) / rect.width) * 100;
  const y = ((e.clientY - rect.top) / rect.height) * 100;
  img.style.transformOrigin = `${x}% ${y}%`;
  img.style.transform = 'scale(2)';
};

const handleMouseLeave = (e) => {
  e.target.style.transform = 'scale(1)';
};

function handleClick(e) {
  if (e === 'up') cnt.value++;
  else if (e === 'down' && cnt.value > 1) cnt.value--;
}



</script>

<template>
  <div class="min-h-screen bg-gradient-to-b from-amber-50 to-white py-12 px-6">
    <div class="max-w-6xl mx-auto bg-white rounded-3xl shadow-lg border border-amber-100 overflow-hidden">
      <div class="grid md:grid-cols-2 gap-10 p-8">
        <!-- Image -->
        <div class="flex justify-center items-center bg-amber-50 rounded-2xl p-5 relative group">
          <img :src="viewData.img" alt="Book cover"
            class="rounded-xl shadow-md transition-transform duration-300 ease-in-out group-hover:scale-105 cursor-zoom-in"
            @mousemove="handleMouseMove" @mouseleave="handleMouseLeave" />
        </div>

        <!-- Info -->
        <div>
          <h1 class="text-4xl font-serif text-amber-800 mb-3">{{ viewData.name }}</h1>
          <p class="text-gray-600 text-lg mb-2">
            Author:
            <span class="font-semibold text-amber-700">{{ viewData.author }}</span>
          </p>

          <!-- Rating -->
          <div class="flex mb-4">
            <i v-for="n in 5" :key="n" class="fa-solid fa-star text-xl transition"
              :class="n <= Math.round(viewData.rate) ? 'text-yellow-400' : 'text-gray-300'"></i>
          </div>

          <!-- Price -->
          <p class="text-3xl font-semibold text-red-600 mb-4">
            {{ Number(viewData.price || 0).toLocaleString('vi-VN') }}₫
          </p>

          <!-- Description -->
          <p class="text-gray-700 mb-6 leading-relaxed">{{ viewData.describe }}</p>

          <!-- Book Details -->
          <div class="grid grid-cols-2 gap-y-3 text-gray-600">
            <p><strong>Publisher:</strong> {{ viewData.publisher }}</p>
            <p><strong>Year:</strong> {{ viewData.publishing }}</p>
            <p><strong>Pages:</strong> {{ viewData.numberOfPages }}</p>
            <p><strong>ISBN:</strong> {{ viewData.isbn }}</p>
            <p><strong>Format:</strong> {{ viewData.format }}</p>
          </div>

          <!-- Actions -->
          <div class="mt-8 flex flex-wrap gap-4">
            <AddCart  :book="viewData"/>

            <ReadOnline :book="viewData" />
          </div>

          <!-- Extra Info -->
          <div class="mt-10 border-t pt-5 text-sm text-gray-500">
            <p>📦 Product ID: {{ viewData.bookid }}</p>
            <p>🏷️ Categories: Drama, Novel</p>
            <p>⭐ Tags: Bestseller, Award Winner, New Release</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
img {
  max-height: 500px;
  object-fit: cover;
}
</style>
