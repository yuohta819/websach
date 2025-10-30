<template>
  <footer
    class="bg-gradient-to-br from-amber-50 via-white to-stone-100 border-t border-amber-200 font-sans"
  >
    <!-- Phần trên -->
    <div
      class="max-w-7xl mx-auto py-16 px-6 md:px-12 lg:px-4 grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-10 text-stone-700"
    >
      <!-- Logo + mô tả -->
      <div class="flex flex-col items-center md:items-start">
        <div class="flex items-center gap-3 mb-4">
          <img
            src="https://cdn-icons-png.flaticon.com/512/2232/2232688.png"
            alt="logo"
            class="w-14 md:w-16"
          />
          <h2 class="text-3xl font-bold tracking-wide text-amber-700">BOOKSTORE</h2>
        </div>
        <p class="text-stone-600 text-center md:text-left leading-relaxed">
          Explore the world of books — discover, learn, and enjoy stories that inspire your imagination.
        </p>
      </div>

      <!-- What's New -->
      <div>
        <h2
          class="text-xl font-semibold mb-5 text-amber-800 border-b-2 border-amber-400 pb-2 inline-block"
        >
          What’s New
        </h2>
        <div
          v-for="item in books"
          :key="item.id"
          class="flex items-center mb-5 group cursor-pointer hover:scale-[1.02] transition-transform duration-300"
        >
          <a
            :href="`/book/${item.name}/${item.category}/${item.id}`"
            class="flex items-center"
          >
            <img
              :src="item.img"
              alt="book"
              class="w-16 h-20 object-cover rounded-lg shadow-md mr-4 group-hover:shadow-lg group-hover:shadow-amber-200 transition-all"
            />
            <div>
              <p
                class="font-semibold text-stone-800 line-clamp-1 group-hover:text-amber-700 transition"
              >
                {{ item.name }}
              </p>
              <p class="text-amber-600 text-sm">{{ item.author }}</p>
              <div class="flex items-center mt-1">
                <i
                  v-for="n in 5"
                  :key="n"
                  class="fa-solid fa-star text-xs"
                  :class="n <= item.rate ? 'text-yellow-400' : 'text-stone-300'"
                ></i>
              </div>
              <p class="text-red-500 font-semibold mt-1">{{ item.price }} $</p>
            </div>
          </a>
        </div>
      </div>

      <!-- Quick Links -->
      <div>
        <h2
          class="text-xl font-semibold mb-5 text-amber-800 border-b-2 border-amber-400 pb-2 inline-block"
        >
          Quick Links
        </h2>
        <ul class="space-y-3">
          <li
            v-for="link in [
              { name: 'Home', href: '/' },
              { name: 'About Us', href: '/About/aboutus' },
              { name: 'Our Team', href: '/our/team' },
              { name: 'Events', href: '/service/Event/Dramma' },
              { name: 'Store', href: '/book' },
              { name: 'Contacts', href: '/contact/form' },
            ]"
            :key="link.name"
          >
            <a
              :href="link.href"
              class="flex items-center gap-3 text-stone-700 hover:text-amber-600 font-medium transition"
            >
              <span
                class="w-2.5 h-2.5 rounded-full bg-amber-400 opacity-70 group-hover:opacity-100 transition-all duration-300"
              ></span>
              {{ link.name }}
            </a>
          </li>
        </ul>
      </div>

      <!-- Get in Touch -->
      <div>
        <h2
          class="text-xl font-semibold mb-5 text-amber-800 border-b-2 border-amber-400 pb-2 inline-block"
        >
          Get in Touch
        </h2>
        <p class="text-stone-600 leading-relaxed mb-3">
          Germany — 785 15h Street, Office 478<br />
          Berlin, DE 81566
        </p>
        <a
          href="mailto:info@email.com"
          class="text-amber-700 font-medium hover:text-amber-600 transition-colors cursor-pointer border-b border-amber-500 inline-block mb-3"
        >
          info@email.com
        </a>
        <p class="text-amber-700 font-semibold text-lg">+235 841 25 69</p>
      </div>
    </div>

    <!-- Phần dưới -->
    <div class="bg-amber-100 border-t border-amber-300 text-center py-5">
      <p class="text-stone-700 text-sm">
        <span class="text-amber-700 font-semibold">Yuohta © 2025.</span> All Rights Reserved.
      </p>
    </div>
  </footer>
</template>
