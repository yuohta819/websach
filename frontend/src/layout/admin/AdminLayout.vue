<template>
  <div class="flex min-h-screen bg-gray-50 text-gray-800 overflow-hidden">
    <!-- Sidebar -->
    <Sidebar :active="active" @menu-click="handleMenuClick" />

    <!-- Nội dung chính -->
    <main class="flex-1 p-6 overflow-y-auto">
      <router-view />
    </main>
  </div>
</template>

<script setup>
import { ref, computed } from "vue";
import Sidebar from "./sidebar.vue";
import BookMenu from "./library/BookMenu.vue";

// Quản lý menu đang chọn
const active = ref("library");

// Map key → component
const views = {
  library: BookMenu,
};

// Khi click sidebar
function handleMenuClick(key) {
  active.value = key;
}

// Lấy component đang active
const currentView = computed(() => views[active.value] || BookMenu);
</script>
