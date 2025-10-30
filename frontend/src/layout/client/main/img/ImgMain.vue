<script setup>
import { onMounted, ref } from 'vue'
const currentIndex = ref(0)

const img = [
    'https://booklovers.ancorathemes.com/wp-content/uploads/2021/02/bg-team-copyright.jpg',
    'https://booklovers.ancorathemes.com/wp-content/uploads/2021/02/bg-test1-copyright.jpg',
    'https://www.cleanipedia.com/images/5iwkm8ckyw6v/5gyV3Ps4pHjoxO40B1FrBL/9b4d4cf98009d7415fb90da945accd8f/amV3LXJlYWRpbmc2LTQ2Mi5qcGVn/600w/ng%C6%B0%E1%BB%9Di-%C4%91ang-%C4%91%E1%BB%8Dc-s%C3%A1ch-trong-%C3%A1nh-s%C3%A1ng-m%E1%BA%B7t-tr%E1%BB%9Di..jpg'
]
const conntent = [
    'Choose Your Books!',
    'Book that Really Matter',
    'Meet Favorite Authors'
]
function prev() {
    currentIndex.value = (currentIndex.value - 1 + img.length) % img.length
}

function next() {
    currentIndex.value = (currentIndex.value + 1) % img.length
}
onMounted(() => {
    setInterval(() => {
        currentIndex.value = (currentIndex.value + 1) % img.length
    }, 5000)
})

function hanldeScroll() {
    const target = document.querySelector('.scroll');
      if (target) {
        target.scrollIntoView({ behavior: 'smooth' });
      }
}
</script>
<template>
    <div class="relative">
        <div class="overlay"></div>
        <FadeInOut :duration="500" entry="left" exit="right">
            <img :key="currentIndex" :src="img[currentIndex]" alt="" class="w-full h-[559px] object-cover" />
        </FadeInOut>
        <div @click="prev()" class="absolute top-[50%] left-[5%] z-2 text-[30px]" style="color: white;"><i
                class="fa-solid fa-arrow-left-long"></i></div>
        <p :key="currentIndex" class="box-1 font-[500] text-[18px] absolute left-1/2 -translate-x-1/2 top-[16%]  z-2"
            style="color: white;">Choose Your Books!
        </p>
        <h2 :key="currentIndex"
            class="box-2 font-[500] text-[60px] absolute left-1/2 -translate-x-1/2 top-[30%] z-2 text-center font-[500] leading-[70px]"
            style="width: 450px; color: white; font-family: Merriweather, serif;">
            {{ conntent[currentIndex] }}</h2>
        <div :key="currentIndex" class=" box-3 absolute top-[0] left-1/2 -translate-x-1/2 top-[60%] z-2 font-[600]"
            style="background-color: black; color: white;">
            <button @click="hanldeScroll()" class="px-10 py-5">DISCOVER YOUR NEXT BOOK</button>
        </div>
        <div @click="next()" class="absolute top-[50%] right-[5%] z-2 text-[30px]" style="color: white;"><i
                class="fa-solid fa-arrow-right"></i></div>
    </div>
</template>

<style scoped>
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

.box-1 {
    animation: slideUp 0.8s ease-out forwards;
}

@keyframes slideUp {
    from {
        transform: translateY(30px);
        opacity: 0;
    }

    to {
        transform: translateY(0);
        opacity: 1;
    }
}

.box-2 {
    animation: form 0.8s ease-out forwards;
}

@keyframes form {
    from {
        transform: translateX(-40px);
        opacity: 0;
    }

    to {
        transform: translateX(0);
        opacity: 1;
    }
}

.box-3 {
    animation: button 0.8s ease;
}

@keyframes button {
    from {
        transform: translateY(30px);
        opacity: 0;
    }

    to {
        transform: translateY(0);
        opacity: 1;
    }
}

</style>