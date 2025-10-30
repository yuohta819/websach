<template>
  <div class="custom-scroll">
 <router-view />
  </div>
</template>

<script setup>
</script>

<style scoped>
.custom-scroll {
  height: 100vh;
  overflow-y: auto;
}

/* Thanh cuộn tùy chỉnh chỉ trong khu vực này */
.custom-scroll::-webkit-scrollbar {
  width: 8px;
}
.custom-scroll::-webkit-scrollbar-thumb {
  background: linear-gradient(180deg, #c8a96a, #8b6f3d);
  border-radius: 8px;
}
.custom-scroll::-webkit-scrollbar-track {
  background: #f9f5ee;
}
</style>
