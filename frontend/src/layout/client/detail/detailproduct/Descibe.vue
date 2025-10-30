<script setup>
import { ref } from 'vue';
import Comment from './Comment.vue';

const activeTab = ref('description');

function switchTab(tab) {
  activeTab.value = tab;
}
</script>

<template>
  <section class="px-6 sm:px-20 py-12 bg-[#FAFAF9] rounded-2xl shadow-inner">
    <!-- Tab Header -->
    <div class="flex flex-wrap gap-2 border-b border-gray-200 mb-8">
      <button
        @click="switchTab('description')"
        class="px-6 py-3 text-lg font-serif transition-all duration-300 rounded-t-lg"
        :class="
          activeTab === 'description'
            ? 'bg-white text-[#DE3241] border border-b-0 border-gray-200 font-semibold'
            : 'bg-[#F5F5F4] text-gray-700 hover:bg-white/80'
        "
      >
        Description
      </button>
    </div>

    <!-- Tab Content -->
    <div class="bg-white rounded-b-xl shadow-sm p-8 transition-all duration-300">
      <div v-if="activeTab === 'description'">
        <p class="text-gray-700 leading-relaxed font-[500] text-[16px]">
          Dicta sunt explicabo. Nemo enim ipsam voluptatem voluptas sit odit aut fugit,
          sed quia consequuntur. Lorem ipsum dolor sit amet, elitr, sed diam nonumy eirmod
          tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At
          vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren,
          no sea takimata sanctus est Lorem ipsum dolor sit amet.
        </p>
      </div>

    </div>
  </section>
</template>

<style scoped>
button {
  font-family: 'Playfair Display', serif;
}
</style>
