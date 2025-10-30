<script setup>
import { ref, onMounted, onBeforeUnmount } from "vue";
import { useRouter } from "vue-router";
import CartDropdown from "./CartDropdown.vue";

const router = useRouter();
const isOpen = ref(false);
const isMobile = ref(false);

function toggleMenu() {
  isOpen.value = !isOpen.value;
}
function updateIsMobile() {
  isMobile.value = window.innerWidth < 1024;
}

onMounted(() => {
  updateIsMobile();
  window.addEventListener("resize", updateIsMobile);
});
onBeforeUnmount(() => {
  window.removeEventListener("resize", updateIsMobile);
});
</script>

<template>
  <header class="bg-[#f9f5ee] shadow-md sticky top-0 left-0 w-full z-9">
    <div class="max-w-7xl mx-auto flex items-center justify-between py-3 px-4">
      <!-- Logo -->
      <a href="/" class="flex items-center gap-2">
        <img
          src="https://cdn-icons-png.flaticon.com/512/2232/2232688.png"
          alt="Bookstore Logo"
          class="w-10 h-10"
        />
        <span class="text-2xl font-bold text-[#5a4636]">BookStore</span>
      </a>

      <!-- Nút mở menu mobile -->
      <button
        @click="toggleMenu"
        class="lg:hidden text-[#5a4636] text-2xl focus:outline-none"
      >
        <i :class="isOpen ? 'fa-solid fa-xmark' : 'fa-solid fa-bars'"></i>
      </button>

      <!-- Menu -->
      <nav
        :class="[ 
          'flex flex-col lg:flex-row gap-4 lg:gap-10 lg:items-center absolute lg:static top-full left-0 w-full lg:w-auto bg-[#f9f5ee] lg:bg-transparent shadow-md lg:shadow-none transition-all duration-300 ease-in-out',
          isMobile ? (isOpen ? 'opacity-100 visible' : 'opacity-0 invisible') : '',
        ]"
      >
        <ul
          class="flex flex-col lg:flex-row lg:gap-8 text-[#5a4636] font-semibold"
        >
          <!-- HOME -->
          <li class="group relative px-4 py-2 cursor-pointer hover:text-[#b85c38]">
            HOME
            <div
              class="absolute left-0 top-full bg-white shadow-lg rounded-md opacity-0 group-hover:opacity-100 invisible group-hover:visible transition-all duration-300"
            >
              <a href="/" class="block px-5 py-2 hover:bg-[#f2e8dc]">Home Classic</a>
            </div>
          </li>

          <!-- PAGES -->
          <li class="group relative px-4 py-2 cursor-pointer hover:text-[#b85c38]">
            PAGES
            <div
              class="absolute left-0 top-full bg-white shadow-lg rounded-md opacity-0 group-hover:opacity-100 invisible group-hover:visible transition-all duration-300"
            >
              <a href="/About/aboutus" class="block px-5 py-2 hover:bg-[#f2e8dc]">About Us</a>
              <a href="/our/team" class="block px-5 py-2 hover:bg-[#f2e8dc]">Our Team</a>
              <a href="/team" class="block px-5 py-2 hover:bg-[#f2e8dc]">Author Detail</a>
              <a href="/contact" class="block px-5 py-2 hover:bg-[#f2e8dc]">Contacts</a>
            </div>
          </li>

          <!-- EVENTS -->
          <li class="px-4 py-2 cursor-pointer hover:text-[#b85c38]">
            <a href="/service/Event/Dramma">EVENTS</a>
          </li>

          <!-- BLOG -->
          <li class="group relative px-4 py-2 cursor-pointer hover:text-[#b85c38]">
            BLOG
            <div
              class="absolute left-0 top-full bg-white shadow-lg rounded-md opacity-0 group-hover:opacity-100 invisible group-hover:visible transition-all duration-300"
            >
              <a href="/blog/All Posts/Blog Portfolio 4 Columns" class="block px-5 py-2 hover:bg-[#f2e8dc]">All Posts</a>
              <a href="/content/inspiration" class="block px-5 py-2 hover:bg-[#f2e8dc]">Inspiration</a>
              <a href="/service/Quote/Blog" class="block px-5 py-2 hover:bg-[#f2e8dc]">Blog Service</a>
            </div>
          </li>

          <!-- ABOUT -->
          <li class="px-4 py-2 cursor-pointer hover:text-[#b85c38]">
            <a href="/About/aboutus">ABOUT</a>
          </li>

          <!-- STORE -->
          <li class="group relative px-4 py-2 cursor-pointer hover:text-[#b85c38]">
            STORE
            <div
              class="absolute left-0 top-full bg-white shadow-lg rounded-md opacity-0 group-hover:opacity-100 invisible group-hover:visible transition-all duration-300"
            >
              <a href="/book" class="block px-5 py-2 hover:bg-[#f2e8dc]">All Books</a>
              <a href="/favorites" class="block px-5 py-2 hover:bg-[#f2e8dc]">Favorites</a>
              <!-- ✅ Thêm nút Mua gói VIP -->
              <a href="/packages" class="block px-5 py-2 hover:bg-[#f2e8dc] text-[#b85c38] font-semibold">
                Buy Packages ⭐
              </a>
            </div>
          </li>
        </ul>

        <!-- Icons -->
        <div class="flex justify-center lg:justify-end gap-5 py-3 border-t border-[#d8cfc0] lg:border-none">
          <CartDropdown />
          <i class="fa-solid fa-magnifying-glass text-xl cursor-pointer hover:text-[#b85c38]"></i>
        </div>
      </nav>
    </div>
  </header>
</template>

<style scoped>
.group div {
  transform: translateY(10px);
  transition: all 0.3s ease;
}
.group:hover div {
  transform: translateY(0);
}
a {
  transition: color 0.3s ease;
  white-space: nowrap;
}
a:hover {
  color: #b85c38;
}
li {
  white-space: nowrap;
}
.group div a {
  display: block;
  white-space: nowrap;
}
nav ul {
  flex-wrap: nowrap;
}
</style>
