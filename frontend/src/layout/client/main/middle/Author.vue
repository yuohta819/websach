<script setup>
const url = import.meta.env.VITE_API_URL_FRONTEND

const img = [
    'https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/team2-copyright-570x741.jpg',
    'https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/team1-copyright-570x741.jpg',
    'https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/team3-copyright-570x741.jpg',
    'https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/team4-copyright-570x741.jpg'
]
const name = [
    'Amy Stevens',
    'Steven Moore',
    'Jenny Sanders',
    'Andrew Woods'
]

function handleClick(img) {
    localStorage.setItem("imgAuthor", img)
    window.location.href = `${url}/author/detail`
}
</script>
<template>
    <div class="py-20">
        <p class="text-center text-[50px] " style="font-family: Playfair Display, serif;">Most Popular Authors</p>
        <div class="flex justify-center">
            <div class="mt-3" style="width: 6%; border: 1px solid #DEDEDE;"></div>
        </div>
        <div class="pt-5  flex lg:px-31 px-15 gap-7 flex-wrap">
            <div v-for="(item,index) in img" :key="index">
                <div class="img lg:w-72" @click="handleClick(img[index])"><img :src=item alt=""></div>
                <p class="pt-5 text-[24px] font-[200]" style="font-family: Playfair Display, serif;">{{ name[index] }}</p>
                <p class="pt-1 font-[500]" style="color: #787878;">Author</p>
            </div>
        </div>
        <a :href="`${url}/author`">
            <div class="flex button items-center justify-center mt-10">
            <button class="px-8 py-3" style="box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px; font-weight: 600; ">VIEW MORE</button>
        </div>
        </a>
    </div>
</template>
<style scoped>
.button button {
    background-color: black;
    color: white;
    height: 50px;
}
.button:hover button {
    background-color: white;
    color: black;
    border: 3px solid black;
}
.img {
    overflow: hidden;
    cursor: pointer;
}
img {
    width: 600px;
    height: 400px;
    object-fit: cover;
    transition: transform 0.5s ease;
    display: block;
}
img:hover {
    transform: scale(1.1);
}
</style>