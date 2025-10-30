<script setup>
import Header from '../pagehome/header/Header.vue';
import Data from '../PageHome/Middle/Data.vue';
import Search from '../PageHome/Middle/Search.vue'
import CategoryImg from './Img/CategoryImg.vue';
import MiddleImg from './Img/MiddleImg.vue';
import DataImg from './Img/DataImg.vue';
import BackgroundMiddle from './Middle/BackgroundMiddle.vue'
import DetailMiddle from './Middle/DetailMiddle.vue';
import AddMiddle from './Middle/AddMiddle.vue';
import Author from './Middle/Author.vue';
import AuthorMiddle from './Middle/AuthorMiddle.vue';
import Animation from "../PageHome/Box/Animation.vue"
import Taskbar from '../pagehome/header/Taskbar.vue';
import Information from '../PageHome/Box/Information.vue';
import ImgMain from './Img/ImgMain.vue';
import { onMounted } from 'vue';
import axios from 'axios';
const token = localStorage.getItem("token")
// onMounted(async () => {
//     if (token) {
//         const res = await axios.get(`${import.meta.env.VITE_API_URL_BACKEND}/account/token`, {
//             params: {
//                 param: token
//             }
//         })
//         if (res.data === '') {
//             localStorage.clear()
//         }
//     }
// })
</script>
<!-- Trang chinh -->
<template>
    <ImgMain />
    <div  style="background: #F5F5F4">
        <Data />
    </div>
    <Search />
    <div class="sm:px-25 px-0 pt-20 pb-1" style="background: #F5F5F4">
        <div>
            <CategoryImg />
        </div>
    </div>
    <div class="px-25 pt-20 pb-35 pb-1" style="background: white">
        <p class="mb-14 text-center md:text-[60px] text-[24px] " style="font-family: Lora, serif;">The ReadDown</p>
        <div class="flex justify-center">
            <div class="mb-20" style="width: 6%; border: 1px solid #DEDEDE;"></div>
        </div>
        <MiddleImg />
    </div>
    <div class=" pt-20 pb-1" style="background: #F5F5F4">
        <!-- Picked Img -->
        <DataImg />
    </div>
    <BackgroundMiddle />
    <div style=" background-color: white;"
        class=" mt-30 flex md:px-35 px-0 sm:justify-between justify-center flex-wrap">
        <div>
            <h2 class="  text-[24px] font-[400] xl:text-left text-center"
                style="text-transform: uppercase; font-family: Playfair Display, serif;">featured bestsellers</h2>
            <div>
                <div class="mt-3 hidden xl:block " style="width: 6%; border: 1px solid #DEDEDE;"></div>
            </div>
            <DetailMiddle style="background-color: white;" />
        </div>
        <div>
            <AddMiddle />
        </div>
    </div>
    <div style="background-color: #F5F5F4; border: none;">
        <Author />
    </div>
    <AuthorMiddle />
    <Animation />
</template>