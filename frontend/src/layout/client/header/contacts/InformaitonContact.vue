<script setup>
</script>

<template>
  <section class="min-h-screen bg-gradient-to-br from-amber-50 via-white to-stone-100 py-20 px-6">
    <div
      class="max-w-5xl mx-auto bg-white shadow-lg rounded-2xl p-10 border border-stone-200 flex flex-col lg:flex-row gap-12"
    >
      <!-- Thông tin liên hệ -->
      <div class="lg:w-1/3 space-y-3">
        <h2 class="text-4xl md:text-5xl font-bold text-stone-800 font-[Playfair Display]">
          Contact Us
        </h2>
        <div class="w-20 h-1 bg-amber-500 rounded my-4"></div>

        <p class="text-stone-600 font-[Playfair Display]">19 Muriel St Freeport, NY</p>
        <p class="text-stone-600 font-[Playfair Display]">11520 USA</p>
        <p class="text-red-600 font-semibold text-lg font-[Playfair Display]">+8 800 841 25 69</p>
        <p class="text-stone-600 font-[Playfair Display]">info@email.com</p>

        <div class="mt-6 flex gap-4">
          <a href="#" class="text-stone-700 hover:text-amber-600 transition text-xl">
            <i class="fa-brands fa-facebook-f"></i>
          </a>
          <a href="#" class="text-stone-700 hover:text-amber-600 transition text-xl">
            <i class="fa-brands fa-twitter"></i>
          </a>
          <a href="#" class="text-stone-700 hover:text-amber-600 transition text-xl">
            <i class="fa-brands fa-instagram"></i>
          </a>
        </div>
      </div>

      <!-- Form liên hệ -->
      <div class="lg:w-2/3">
        <form class="space-y-6">
          <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
            <input
              type="text"
              placeholder="Name"
              class="w-full bg-stone-100 border border-stone-300 rounded-lg py-3 px-4 focus:ring-2 focus:ring-amber-400 outline-none font-[Playfair Display]"
            />
            <input
              type="email"
              placeholder="Email Address"
              class="w-full bg-stone-100 border border-stone-300 rounded-lg py-3 px-4 focus:ring-2 focus:ring-amber-400 outline-none font-[Playfair Display]"
            />
          </div>

          <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
            <input
              type="text"
              placeholder="Phone"
              class="w-full bg-stone-100 border border-stone-300 rounded-lg py-3 px-4 focus:ring-2 focus:ring-amber-400 outline-none font-[Playfair Display]"
            />
            <input
              type="text"
              placeholder="Subject"
              class="w-full bg-stone-100 border border-stone-300 rounded-lg py-3 px-4 focus:ring-2 focus:ring-amber-400 outline-none font-[Playfair Display]"
            />
          </div>

          <textarea
            placeholder="Message"
            rows="6"
            class="w-full bg-stone-100 border border-stone-300 rounded-lg py-3 px-4 focus:ring-2 focus:ring-amber-400 outline-none resize-none font-[Playfair Display]"
          ></textarea>

          <button
            type="submit"
            class="px-8 py-3 bg-stone-900 text-white font-semibold rounded-lg shadow-md hover:bg-white hover:text-stone-900 hover:border hover:border-stone-900 transition-all duration-300 font-[Playfair Display]"
          >
            SEND MESSAGE
          </button>
        </form>
      </div>
    </div>
  </section>
</template>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;600;700&display=swap");
</style>
