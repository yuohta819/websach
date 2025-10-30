<template>
  <aside
    class="w-72 bg-gradient-to-br from-amber-100 via-amber-50 to-white border-r border-gray-200 shadow-md flex flex-col min-h-screen">
    <!-- Header -->
    <div class="px-5 py-5 border-b border-gray-100 flex items-center justify-between">
      <h1 class="text-lg font-semibold flex items-center gap-2">
        <span class="text-2xl">📚</span>
        <span>BookShelf Admin</span>
      </h1>
    </div>

    <!-- Profile section -->
    <div class="flex items-center gap-3 px-5 py-4 border-b border-gray-100 bg-white/70">
      <img src="https://images.unsplash.com/photo-1512820790803-83ca734da794?q=80&w=80&auto=format&fit=crop"
        alt="avatar" class="w-10 h-10 rounded-lg object-cover shadow-sm" />
      <div class="flex flex-col">
        <span class="text-sm text-gray-600">Xin chào,</span>
        <span class="font-medium text-gray-800">{{ adminName }}</span>
      </div>
    </div>

    <!-- Menu -->
    <nav class="flex-1  mt-2">
      <ul class="px-3">
        <li v-for="item in menu" :key="item.key" class="my-1">
          <router-link :to="item.path"
            class="w-full flex items-center gap-3 px-4 py-2.5 rounded-lg text-sm font-medium transition-all duration-150"
            :class="{
              'bg-amber-100 text-amber-800 shadow-inner': $route.path === item.path,
              'hover:bg-amber-50 text-gray-700': $route.path !== item.path,
            }">
            <span v-html="item.icon" class="w-5 h-5"></span>
            <span>{{ item.title }}</span>
          </router-link>
        </li>
      </ul>
    </nav>


    <!-- Logout -->
    <div class="px-4 py-4 border-t border-gray-100 bg-white/80">
      <button @click="logout"
        class="w-full flex items-center justify-center gap-2 py-2.5 rounded-lg bg-red-500 hover:bg-red-600 text-white text-sm font-medium transition-all">
        <svg xmlns="http://www.w3.org/2000/svg" class="w-4 h-4" fill="none" stroke="currentColor" stroke-width="1.8"
          viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" d="M17 16l4-4m0 0l-4-4m4 4H7" />
          <path stroke-linecap="round" stroke-linejoin="round" d="M3 12a9 9 0 1 0 9-9v3" />
        </svg>
        <span>Đăng xuất</span>
      </button>
    </div>
  </aside>
</template>

<script setup>
import { ref } from "vue";

defineProps({
  active: String,
});

const adminName = ref("Nguyễn Văn A");
const menu = [
  {
    key: "library",
    title: "Thư viện",
    path: "/admin/library",
    icon: `<svg ... />`,
  },
  {
    key: "permissions",
    title: "Quản lý quyền",
    path: "/admin/permissions",
    icon: `<svg ... />`,
  },
  {
    key: "customers",
    title: "Quản lý tài khoản khách hàng",
    path: "/admin/account",
    icon: `<svg ... />`,
  },

  {
    key: "create",
    title: "Tạo tài khoản Admin",
    path: "/admin/createadmin",
    icon: `<svg ... />`,
  },
];



function logout() {
  alert("Đăng xuất thành công (demo)");
  // Thực tế: localStorage.clear(); router.push("/login");
}
</script>

<style scoped>
aside {
  font-family: "Inter", sans-serif;
}
</style>
