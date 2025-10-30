<script setup>
const url = import.meta.env.VITE_API_URL_FRONTEND
import { useRoute } from 'vue-router';
const route = useRoute()

const img = localStorage.getItem("img")
const name = route.params.name
</script>
<template>
    <div class="relative">
        <div class="overlay"></div>
        <img :key="currentIndex" :src=img alt="" class="w-full h-[559px] object-cover" />
        <p :key="currentIndex"
            class=" box-2 cursor-pointer font-[500] text-[13px] absolute left-1/2 -translate-x-1/2 top-[28%]  z-2 "
            style="color: white;"><button class="px-3 py-1 font-[500] "
                style="background-color: #D02735; color: white;">MODERN</button>
        </p>
        <div class="flex-wrap flex box-2 font-[500] w-[100%] justify-center text-[55px] absolute left-1/2 -translate-x-1/2 top-[37%] z-2 text-center font-[500] leading-[50px]">
            <h2 :key="currentIndex"
                class=""
                style=" color: white; font-family: Merriweather, serif;">
                {{ name }}</h2>
        </div>
        <div class="absolute left-1/2 items-center gap-4 flex-wrap -translate-x-1/2 flex top-[60%] z-2">
            <div :key="currentIndex" class="box-2  font-[200] text-[20px]"
                style=" color: white; font-family: Merriweather, serif;">
                Jenna Moore</div>
            <div :key="currentIndex" class="box-2 font-[100] text-[16px]  "
                style=" color: white; font-family: Merriweather, serif;">
                April 21, 2020</div>
        </div>
    </div>
</template>

<style scoped>
.overlay {
    position: absolute;
    width: 100%;
    height: 559px;
    top: 0;
    background-color: rgba(0, 0, 0, 0.6);
    /* Màu nền phủ */
    z-index: 1;
    overflow: hidden;
}

.active {
    transition: transform 0.5s ease;
    transform: scale(1.3);
}

.box-3:hover i {
    cursor: pointer;
    color: red;
}

.box-1:hover i {
    cursor: pointer;
    color: red;
}
</style>