<template>
  <div class="flex">
    <Sidebar />
      <router-view />
  </div>
</template>

<script setup>
import Sidebar from './Sidebar.vue';
</script>
