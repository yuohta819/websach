<script setup>
import { onMounted, ref } from 'vue'
const currentIndex = ref(0)
const scale = ref(0)

function handleClick(index) {
    scale.value = index
    currentIndex.value = index
}
const img = [
    'https://booklovers.ancorathemes.com/wp-content/uploads/2021/02/bg-test1-copyright.jpg',
    'https://booklovers.ancorathemes.com/wp-content/uploads/2021/02/bg-test1-copyright.jpg',
    'https://booklovers.ancorathemes.com/wp-content/uploads/2021/02/bg-test1-copyright.jpg'
]
const conntent = [
    'When it comes to immediate financial help, I know who I can count on. Being an accountant, you sometimes need a fresh eye to check the reports. Great job! I look forward to meeting tou again soon!',
    'If it wasn’t for your young team of professionals, I would have lost my business. We have developed a re-branding strategy that has been working to my advantage so far. Thank you and good luck!',
    'This is one of the most exciting parts where we get to express our appreciation as clients of this amazing creative agency. Thank you for all your hard work regarding our financial aspect!'
]
const author = [
    'Jennifer Lee',
    'George Gordon',
    'Linda Moore'
]
const type = [
    'Accountant',
    'Business Owner',
    'Office Manager'
]
const imgauthor = [
    'https://booklovers.ancorathemes.com/wp-content/uploads/2021/02/post28-copyright-120x120.jpg',
    'https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/team8-copyright-120x120.jpg',
    'https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/team10-copyright-120x120.jpg'
]
function prev() {
    currentIndex.value = (currentIndex.value - 1 + img.length) % img.length
    scale.value = currentIndex.value
}

function next() {
    currentIndex.value = (currentIndex.value + 1) % img.length
    scale.value = currentIndex.value
}
</script>
<template>
    <div class="relative">
        <div class="overlay"></div>
        <img :key="currentIndex" :src="img[currentIndex]" alt="" class="w-full h-[559px] object-cover" />
        <div @click="prev()" class="box-3 absolute top-[50%] left-[5%] z-2 text-[30px]" style="color: white;"><i
                class="fa-solid fa-arrow-left-long"></i></div>
        <p :key="currentIndex" class=" box-2 cursor-pointer font-[500] text-[18px] absolute left-1/2 -translate-x-1/2 top-[16%]  z-2"
            style="color: white;"><i class="text-[40px] fa-solid fa-comment-dots"></i>
        </p>
        <h2 :key="currentIndex"
            class="box-2 font-[500] text-[14px] lg:text-[20px] absolute left-1/2 -translate-x-1/2 top-[25%] sm:top-[30%] z-2 text-center font-[500] leading-[30px]"
            style="width: 47%; color: white; font-family: Merriweather, serif;">
            {{ conntent[currentIndex] }}</h2>
        <p :key="currentIndex"
            class="box-2 font-[500] text-[20px] absolute left-1/2 -translate-x-1/2 top-[65%] lg:top-[51%] z-2 text-center font-[500] leading-[30px]"
            style="width: 47%; color: white; font-family: Merriweather, serif;">
            {{ author[currentIndex] }}</p>
        <p :key="currentIndex"
            class="box-2 font-[500] text-[20px] absolute left-1/2 -translate-x-1/2 top-[70%] lg:top-[56%] z-2 text-center font-[500] leading-[30px]"
            style="width: 47%; color: white; font-family: Merriweather, serif;">
            {{ type[currentIndex] }}</p>
        <div class="absolute left-1/2 -translate-x-1/2 lg:top-[66%] top-[80%] z-2 flex gap-10 "  >
            <div v-for="(item,index) in imgauthor" :key="index">
                <img :class="{active: scale === index}" @click="handleClick(index)" style="width: 80px; border-radius: 50%;" :src=item alt="">
            </div>
        </div>
        <div @click="next()" class="box-1 cursor-pointer absolute top-[50%] right-[5%] z-2 text-[30px]" style="color: white;"><i
                class="fa-solid fa-arrow-right"></i></div>
    </div>
</template>

<style scoped>
img {
    cursor: pointer;
}
.overlay {
    position: absolute;
    width: 100%;
    height: 559px;
    top: 0;
    background-color: rgba(0, 0, 0, 0.4);
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
.box-2 {
    animation: slide 0.8s ease-in-out;
}
@keyframes slide {
    0% {
        opacity: 1;
        transform: translateX(0);
    }
    100% {
        opacity: 0;
        transform: translateX(-1000px);
    }
}
</style>