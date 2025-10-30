<script setup>
const handleSubmit = () => {
  // Giả lập gửi form — bạn có thể thay bằng API subscribe thực tế
  alert('📬 Thank you for subscribing!');
};
</script>

<template>
  <section class="bg-[#DE3241] py-20 px-6 text-white">
    <div class="max-w-4xl mx-auto text-center">
      <!-- Title -->
      <h2 class="text-4xl sm:text-5xl font-serif mb-4 tracking-wide">
        Stay in Touch with Our Updates
      </h2>

      <!-- Divider -->
      <div class="w-24 h-1 bg-white/60 mx-auto mb-10 rounded-full"></div>

      <!-- Email Input -->
      <form
        @submit.prevent="handleSubmit"
        class="flex flex-wrap justify-center items-center gap-4 mb-8"
      >
        <input
          type="email"
          placeholder="Enter your email address"
          class="w-full sm:w-96 py-3 px-5 rounded-full text-gray-800 placeholder-gray-500 focus:outline-none focus:ring-4 focus:ring-white/40 transition"
          required
        />
        <button
          type="submit"
          class="flex items-center gap-2 px-6 py-3 rounded-full font-semibold bg-black hover:bg-white hover:text-[#DE3241] transition duration-300 shadow-md"
        >
          <i class="fa-solid fa-paper-plane"></i>
          <span>Get in Touch</span>
        </button>
      </form>

      <!-- Privacy Policy -->
      <div class="flex justify-center items-center text-sm text-white/90">
        <input id="agree" type="checkbox" class="w-4 h-4 accent-black cursor-pointer" />
        <label for="agree" class="ml-3 font-serif">
          I agree to the
          <a
            href="#"
            class="underline hover:text-black transition duration-300 font-medium"
            >Privacy Policy</a
          >.
        </label>
      </div>
    </div>
  </section>
</template>

<style scoped>
/* Font style nhẹ nhàng */
h2 {
  font-family: 'Cormorant Garamond', serif;
}
label {
  font-family: 'Cormorant Garamond', serif;
}
</style>
