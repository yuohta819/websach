<script setup>
import Background from '../../detail/detailproduct/Background.vue';
import BoxImg from '../../pagehome/middle/BoxImg.vue';
import Author from '../../main/middle/Author.vue';
import AuthorMiddle from '../../main/middle/AuthorMiddle.vue';
import Animation from '../../pagehome/box/Animation.vue';

</script>
<template>
    <Background />
    <div style="background-color: #F5F5F4;">
        <div class="py-30">
            <p class="text-center text-[50px] pb-10">Our Mission</p>
            <div class="flex justify-center">
                <div style="border: 1px solid #797979; width: 5%; font-family: Playfair Display, serif;"></div>
            </div>
            <div class="text-center flex justify-center pt-10">
                <p class="font-[600] mt-5 text-[17px]"
                    style="width: 800px;  font-family: Playfair Display, serif; color: #797979;">Our mission is to bring
                    the passion and love for reading books back. whether it's a regular paper book, or an online
                    edition, we want our readers to know that we appreciate quality and artful storytelling. Join our
                    community and enjoy multiple genres, modern and classic authors, reviews, critics and more!</p>
            </div>
        </div>
        <div class="video relative mt-8  px-31">
            <video controls ref="play"
                poster="https://booklovers.ancorathemes.com/wp-content/uploads/2021/02/bg-test1-copyright.jpg"
                style="width: 100%;">
                <source src="/video.mp4" type="video/mp4">
                Trình duyệt không hỗ trợ video.
            </video>
            <div class="start absolute text-[50px] top-[40%] z-[2] right-[50%] text-red-600" :class="{ active: data }"
                @click="handleClick">
                <i class="fa-brands fa-youtube"></i>
            </div>
        </div>
        <div class="px-10">
            <BoxImg />
        </div>
        <div class="px-5">
            <Author />
        </div>
        <AuthorMiddle />
        <ContentCategory />
        <Animation />
    </div>
</template>