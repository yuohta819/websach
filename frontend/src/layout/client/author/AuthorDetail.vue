<script setup>
import BackgroundMiddle from '../Main/Middle/BackgroundMiddle.vue'
import ContentCategory from '../Category/InforCategory/ContentCategory.vue';
import Animation from '../PageHome/Box/Animation.vue';
const img = localStorage.getItem("imgAuthor")
</script>
<template>
    <BackgroundMiddle />
    <div style="background-color: #F5F5F4;">
<div class="py-30">
        <div class="flex justify-center">
            <img style="width: 100px; border-radius: 50%;" :src=img alt="">
        </div>
        <div class="text-center flex justify-center">
            <p class="font-[600] mt-5 text-[16px]"
                style="width: 1000px;  font-family: Playfair Display, serif; color: #797979;">Lorem ipsum dolor sit
                amet, consectetur adipiscing elit. Proin fermentum magna dolor.
                Pellentesque fermentum fermentum felis, nec luctus dui interdum ac. Morbi odio ex, vestibulum vel quam
                non,
                viverra laoreet est.</p>
        </div>
        <div class="text-center flex justify-center">
            <p class="font-[600] mt-3 text-[16px]"
                style="width: 1000px;  font-family: Playfair Display, serif; color: #797979;">Nullam dictum maximus
                nulla tristique euismod. Suspendisse arcu ligula, gravida sit amet dolor ac,
                ultrices consequat velit. In ante sem, imperdiet et eleifend eu, finibus nec enim. Phasellus magna
                lorem,
                sagittis ac odio eget, varius vestibulum dolor.</p>
        </div>
    </div>
    <ContentCategory />
    <Animation />
    </div>
    
</template>