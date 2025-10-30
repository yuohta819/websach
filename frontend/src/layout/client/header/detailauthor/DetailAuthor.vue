<script setup>

const url = import.meta.env.VITE_API_URL_FRONTEND
const img = localStorage.getItem("imgAuthor")

const type = [
    'Drama',
    'Biography',
    'Cookbooks'
]

const count = [
    '85%',
    '78%',
    '98%'
]


</script>

<template>
    <div class="pb-50 sm:px-35 px-10" style="background: #F5F5F4;">
        <h2 class=" text-[20px] pt-15 font-[600] text-center sm:pb-0 pb-5"
            style=" font-family: Playfair Display, serif; text-transform: uppercase;">Author</h2>
        <div class=" my-5 sm:block hidden">
            <div  style="border: 1px solid #DEDEDE; width: 5%;"></div>
        </div>
        <div class=" relative place-items-center">
            <div class="flex justify-center gap-15 flex-wrap lg:flex-nowrap ">
                <div class="img" style="width: 50%;">
                    <img :src=img alt="">
                </div>
                <div style="width: 50%;">
                    <div v-for="(item, index) in type" :key="index" class="mb-7">
                        <div class="relative mb-3">
                            <div>
                                <p class="text-[20px] font-[600] " style="font-family: Playfair Display, serif;">{{
                                    type[index] }}</p>
                            </div>
                            <div class="lg:absolute top-[5%] z-1" :style="{ left: `calc(${count[index]} - 5%)` }">
                                <p class="font-[600]" :style="{ width: count[index] }">{{ count[index] }}</p>
                            </div>
                        </div>
                        <div class="" :style="{ width: count[index], height: '7px', background: 'red' }"></div>
                    </div>
                </div>
            </div>
            <div class="content static xl:absolute z-2 p-10 flex gap-10 flex-wrap "
                style="background-color: white;  bottom: -14%; left: 42%;">
                <div class=" items-center ">
                    <p class="box leading-[30px] mb-10">Qonsectetur adipiscing elit, sed do eiusm onsectetur adipiscing elit, sed do eiusm od
                        tempor
                        incididunt
                        ut labore.</p>
                    <p>Consectetur adipiscing elit, sed do eiusm onsectetur adipiscing elit, sed do eiusm od tempor
                        incididunt
                        ut labore.</p>
                </div>
                <div>
                    <p class=" leading-[30px] mb-5">Adipiscing elit, sed do eiusm consectetur aonsectetur sed do eiusm od tempor adipiscing elit, sed
                        do
                        eiusm od tempor do eiusm od tempor adipiscing elit.</p>
                    <p>Consectetur adipiscing elit, sed do eiusm onsectetur adipiscing elit, sed do eiusm od tempor.</p>
                </div>
            </div>
        </div>

    </div>
</template>
<style scoped>
.box::first-letter {
    color: black;
    font-family: Playfair Display, serif;
    font-weight: 400;
    font-size: 50px;
}
.content p {
    color: #797979;
    font-weight: 400;
}
</style>