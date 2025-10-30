<script setup>
import { ref } from 'vue';

const data = ref([]);
const url = import.meta.env.VITE_API_URL_FRONTEND
// Zoom theo chuột
const handleMouseMove = (event) => {
    const img = event.target;
    const rect = img.getBoundingClientRect();
    const x = ((event.clientX - rect.left) / rect.width) * 100;
    const y = ((event.clientY - rect.top) / rect.height) * 100;
    img.style.transformOrigin = `${x}% ${y}%`;
    img.style.transform = 'scale(2)';
};

const handleMouseLeave = (event) => {
    const img = event.target;
    img.style.transformOrigin = 'center center';
    img.style.transform = 'scale(1)';
};


</script>

<template>
    <div class="selection-book" style="background: #F5F5F4;">
        <div class="flex gap-20 mt-8 flex-wrap justify-center">
            <div class="img">
                <img class="w-84 zoom h-[469px]"
                    src="https://booklovers.ancorathemes.com/wp-content/uploads/2021/02/book16-copyright.jpg" alt=""
                    @mousemove="handleMouseMove" @mouseleave="handleMouseLeave">
            </div>
            <div class="box md:px-0 px-10">
                <h2 class="text-[50px] w-[290px] leading-[50px]" style="font-family: Playfair Display, serif;">Pride and
                    Privilege and Zombies
                </h2>
                <div class="mt-5">
                    <span class="font-[700]" style="color: red;">Author: </span>
                    <span class="font-[500]" style="color: #798CBA;">Steven Gerrard</span>
                </div>
                <p class="mt-5 text-[17px] md:w-[400px]" style="color: #797979;">This delightful book is the latest in the
                    series of
                    Ladybird books which have been specially planned to help grown-ups with the world about them…</p>
                <div class="flex items-center mt-3">
                    <p class="font-[500]" style="color: #797979;">PRICE: </p>
                    <p class="ml-3 text-[30px] font-[600] text-red-600" >$14.99</p>
                </div>

                <div class="mt-25 h-15 flex items-center pb-20 " >
                    <a :href="`${url}/book`">
                        <button class=" px-10 py-4 "
                        style="font-weight: 600; color: white; background-color: black; border: none;">SHOP ONLINE</button>
                    </a>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.img {
    height: 100%;
    overflow: hidden;
    cursor: zoom-in;
    position: relative;
}

.zoom {
    transition: transform 0.3s ease;
}


.button .test {
    border: 1px solid transparent;
    cursor: pointer;
}

.button .test:hover {
    border: 1px solid black;
}

i {
    cursor: pointer;
}
</style>
