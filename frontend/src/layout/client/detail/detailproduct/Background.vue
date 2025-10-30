<script setup>
import { useRoute } from 'vue-router';


const route = useRoute()
const name = route.params.name
const type = route.params.type
</script>

<template>
  <div class="w-full px-0 text-center py-10" style="background: black;">
    <h2 class="text-[40px] mb-4" style="color: white; font-weight: 500;">{{type}}</h2>
    <ul style="color: #A8A1B5;" class="flex justify-center">
      <li>Home</li>
      <li>›</li>
      <li>Shop</li>
      <li>›</li>
      <li>{{type}}</li>
      <li>›</li>
      <li>{{ name }}</li>
    </ul>
  </div>
</template>

<style scoped>
li {
  position: relative;
  margin-left: 5px;
  margin-right: 5px;
  cursor: pointer;
  transition: all 0.3s ease;
}

li::after {
  content: '';
  position: absolute;
  bottom: -2px; /* Khoảng cách dưới */
  left: 50%;
  transform: translateX(-50%) scaleX(0);
  transform-origin: center;
  width: 100%;
  height: 1px;
  background-color: #A8A1B5;
  transition: transform 0.3s ease;
}

li:hover::after {
  transform: translateX(-50%) scaleX(1);
}
</style>
