<script setup>
const url = import.meta.env.VITE_API_URL_FRONTEND


function handleLink(img) {
    localStorage.setItem("imgAuthor", img)
    window.location.href = `${url}/team`
}
const img = [
    'https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/team9-copyright.jpg',
    'https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/team11-copyright.jpg',
    'https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/team5-copyright.jpg',
    'http://booklovers.ancorathemes.com/wp-content/uploads/2024/06/team13-copyright.jpg',
    'https://booklovers.ancorathemes.com/wp-content/uploads/2024/06/team14-copyright.jpg',
    'https://booklovers.ancorathemes.com/wp-content/uploads/2024/06/team15-copyright.jpg',
    'https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/team2-copyright-570x741.jpg',
    'https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/team1-copyright-570x741.jpg',
]

const name = [
    'Burt Geller',
    'Candy Carson',
    'Carol Foster',
    'Jessica Ford',
    'Ken Clark',
    'Linda Hamilton',
    'Author',
    'Author',
]
</script>
<template>
    <div class="pb-50">
        <h2 class="text-center text-[50px] pt-15" style=" font-family: Playfair Display, serif;">Author</h2>
        <div class="flex justify-center my-10">
            <div style="border: 1px solid #DEDEDE; width: 5%;"></div>
        </div>
        <div class="flex flex-wrap justify-around">
            <div v-for="(item, index) in img" :key="index">
                <div @click="handleLink(img[index])">
                    <div class="img"><img :src=img[index] item alt=""></div>
                    <p class="mt-3 text-center my-5 text-[27px]" style="font-family: Playfair Display, serif;">{{
                        name[index] }}
                    </p>
                </div>
            </div>
        </div>
    </div>

</template>
<style scoped>
img {
    cursor: pointer;
    height: 300px;
    width: 275px;
    object-fit: cover;
}

.img {
    overflow: hidden;
}

.img:hover img {
    transition: transform 0.5s ease;
    transform: scale(1.1);
}

p:hover {
    color: red;
    cursor: pointer;
}
</style>