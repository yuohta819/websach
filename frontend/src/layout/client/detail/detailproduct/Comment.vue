<script setup>
import { ref } from 'vue';
import axios from 'axios';

const email = ref('');
const name = ref('');
const comment = ref('');
const rating = ref(null);
const backend = import.meta.env.VITE_API_URL_BACKEND;

function handleStarClick(value) {
  rating.value = value;
}

async function handleSubmit() {
  try {
    const res = await axios.post(
      `${backend}/api/comment`,
      {
        email: email.value,
        name: name.value,
        comment: comment.value,
        rating: rating.value,
      },
      { timeout: 10000 }
    );
    console.log('✅ Comment submitted:', res.data);
    alert('🎉 Cảm ơn bạn đã gửi đánh giá!');
    email.value = name.value = comment.value = '';
    rating.value = null;
  } catch (err) {
    console.error('❌ Error submitting comment:', err);
  }
}
</script>

<template>
  <form @submit.prevent="handleSubmit" class="py-12">
    <div class="flex flex-wrap gap-12">
      <!-- Left: Average Rating -->
      <div class="flex-1 min-w-[280px] bg-white rounded-2xl shadow-md p-8 text-center">
        <p class="text-2xl font-serif text-gray-800">Average Rating</p>
        <p class="text-5xl mt-3 text-[#DE3241] font-serif font-semibold">5.00</p>
        <div class="flex justify-center gap-1 mt-3 text-yellow-400">
          <i v-for="n in 5" :key="n" class="fa-solid fa-star text-2xl"></i>
        </div>
        <p class="mt-2 text-gray-500">1 Review</p>

        <div class="mt-6 space-y-2 text-left">
          <div v-for="n in [5,4,3,2,1]" :key="n" class="flex items-center gap-3">
            <p class="text-gray-600 w-10">{{ n }}★</p>
            <div class="w-full h-3 bg-gray-200 rounded-full overflow-hidden">
              <div
                class="h-full bg-[#DE3241]"
                :style="{ width: n === 5 ? '100%' : '0%' }"
              ></div>
            </div>
            <p class="text-gray-600 w-10 text-right">
              {{ n === 5 ? '100%' : '0%' }}
            </p>
          </div>
        </div>
      </div>

      <!-- Right: Add Review -->
      <div class="flex-[2] min-w-[300px] bg-white rounded-2xl shadow-md p-8">
        <h3 class="text-2xl font-serif mb-4">Add a Review</h3>
        <p class="text-gray-500 mb-6 text-sm">
          Your email address will not be published. Required fields are marked *.
        </p>

        <!-- Name -->
        <label class="block mb-2 font-medium text-gray-700">Name *</label>
        <input
          v-model="name"
          type="text"
          required
          class="w-full border border-gray-300 rounded-lg p-3 mb-4 focus:ring-2 focus:ring-[#DE3241] focus:border-[#DE3241] outline-none"
        />

        <!-- Email -->
        <label class="block mb-2 font-medium text-gray-700">Email *</label>
        <input
          v-model="email"
          type="email"
          required
          class="w-full border border-gray-300 rounded-lg p-3 mb-4 focus:ring-2 focus:ring-[#DE3241] focus:border-[#DE3241] outline-none"
        />

        <!-- Rating -->
        <label class="block mb-2 font-medium text-gray-700">Your Rating *</label>
        <div class="flex gap-2 mb-6 text-yellow-400 text-2xl cursor-pointer">
          <i
            v-for="n in 5"
            :key="n"
            class="fa-solid fa-star transition-transform duration-200 hover:scale-110"
            :class="n <= rating ? 'text-yellow-400' : 'text-gray-300'"
            @click="handleStarClick(n)"
          ></i>
        </div>

        <!-- Comment -->
        <label class="block mb-2 font-medium text-gray-700">Your Review *</label>
        <textarea
          v-model="comment"
          required
          rows="5"
          class="w-full border border-gray-300 rounded-lg p-3 focus:ring-2 focus:ring-[#DE3241] focus:border-[#DE3241] outline-none"
        ></textarea>

        <!-- Agreement -->
        <div class="flex items-start gap-3 mt-4">
          <input type="checkbox" class="w-5 h-5 accent-[#DE3241]" />
          <span class="text-sm text-gray-600">
            I agree that my submitted data is being collected and stored.
          </span>
        </div>

        <!-- Submit -->
        <button
          type="submit"
          class="mt-8 bg-black text-white px-8 py-3 rounded-lg hover:bg-[#DE3241] transition-all duration-300 font-semibold tracking-wide"
        >
          SUBMIT
        </button>
      </div>
    </div>
  </form>
</template>
