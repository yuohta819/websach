<script setup>

import { useRouter } from 'vue-router';



const route = useRouter()
const url = import.meta.env.VITE_API_URL_FRONTEND
function handleLink(content, img) {
    localStorage.setItem("img", img)
    window.location.href = `${url}/content/${content}`
}
const img = [
    'https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/post13-copyright-890x664.jpg',
    'https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/post17-copyright-890x664.jpg',
    'https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/post25-copyright-890x664.jpg',
    'https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/post24-copyright-890x664.jpg',
    'https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/post23-copyright-890x664.jpg',
    'https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/post22-copyright-890x664.jpg',
    'https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/post21-copyright-890x664.jpg',
    'https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/post20-copyright-890x664.jpg',
    'https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/post17-copyright-890x664.jpg',
    'https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/post10-copyright-890x664.jpg',
    'https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/post9-copyright-890x664.jpg',
    'https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/post1-copyright-890x664.jpg',
]
const content = [
    'Middle East cuisine and its best recipes',
    'Why do we love our libraries so much',
    'What’s the main challenge for a young author?',
    'Top books for studying: a wide range of textbooks',
    'How to avoid boring books and stories',
    'A new collection of funny stories to lift your spirits',
    'Using books to reduce stress and soften anxiety',
    'Fantastic authors who have been neglected',
    'Why ladies prefer detective stories over fantasy?',
    'Searching for inspiration and finding it in books',
    'A new collection of funny stories to lift your spirits',
    'Using books to reduce stress and soften anxiety',
]
</script>
<template>
    <div class="flex px-34 flex-wrap my-35 justify-between ">
        <div v-for="(item, index) in img" :key="index" class="relative containerr">
            <div @click="handleLink(content[index], img[index])">
                <div class="img ">
                    <img class="pb-5" style="" :src=img[index] alt="">
                </div>
                <div class="overlay"></div>
                <div class="content flex items-center top-[47%] absolute z-2 left-[10%] " style="color: white;">
                    <p style="text-transform: uppercase; font-family: Lora, sans-serif;" class="font-[600]">Modern</p>
                    <p class="ml-5 font-[300]" style="">April 21, 2020</p>
                </div>
                <p class="absolute top-[60%] left-[10%] text-[20px] z-2" style="color: white;">{{ content[index] }}</p>
            </div>
        </div>
    </div>
</template>
<style scoped>
.img {
    overflow: hidden;

}

.containerr {
    width: 290px;
    height: 265px;
    overflow: hidden;
    position: relative;
    margin-bottom: 30px;
    cursor: pointer;
}

.overlay {
    position: absolute;
    top: 0;
    background-color: rgba(0, 0, 0, 0.3);
    width: 290px;
    height: 265px;
    z-index: 1;

}

.img img {
    width: 290px;
    height: 285px;
    object-fit: cover;
    transition: transform 0.5s ease;
}

.containerr:hover img {
    transform: scale(1.1);
}
</style>