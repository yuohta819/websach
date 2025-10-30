<script setup>
const url = import.meta.env.VITE_API_URL_FRONTEND;
</script>

<template>
  <section class="relative w-full">
    <img
      class="w-full h-[400px] md:h-[500px] lg:h-[600px] object-cover"
      src="https://booklovers.ancorathemes.com/wp-content/uploads/2021/02/bg-team-copyright.jpg"
      alt="Inspire Reading Banner"
    />
    <div class="overlay"></div>

    <!-- Nội dung -->
    <div class="absolute inset-0 flex flex-col justify-center items-start px-10 md:px-20 z-10 text-white">
      <h2
        class="font-[400] text-[26px] sm:text-[34px] md:text-[46px] lg:text-[56px]"
        style="font-family: 'Playfair Display', serif;"
      >
        Inspire Daily Reading
      </h2>

      <div class="w-20 border-t-2 border-[#ABABAA] my-5"></div>

      <p
        class="max-w-[600px] text-[12px] sm:text-[16px] md:text-[18px] font-[300] leading-relaxed"
        style="font-family: 'Playfair Display', serif;"
      >
        Visit our Blog and Page to find out daily inspirational quotes from the best authors.
      </p>

      <a :href="`${url}/blog/All%20Posts/Blog%20Portfolio%204%20Columns`" class="mt-6">
        <button
          class="px-6 py-3 md:px-10 md:py-5 text-[12px] md:text-[16px] font-[600] uppercase tracking-wider transition-transform duration-300 hover:scale-105"
          style="background-color: black; color: white; box-shadow: rgba(0, 0, 0, 0.25) 0px 8px 20px;"
        >
          View Our Blog
        </button>
      </a>
    </div>
  </section>
</template>

<style scoped>
.overlay {
  position: absolute;
  inset: 0;
  background: rgba(0, 0, 0, 0.45);
  z-index: 5;
  animation: fadeIn 1.2s ease-in-out;
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
</style>
