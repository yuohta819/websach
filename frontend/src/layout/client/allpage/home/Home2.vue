<script setup>

import ImgMain from '../../Main/Img/ImgMain.vue';
import BoxImg from '../../PageHome/Middle/BoxImg.vue';
import MiddleImg from '../../Main/Img/MiddleImg.vue';
import Data from '../../PageHome/Middle/Data.vue';
import Author from '../../Main/Middle/Author.vue';
import Animation from '../../PageHome/Box/Animation.vue';
import CategoryImg from '../../Main/Img/CategoryImg.vue';
</script>
<template>
    <ImgMain />
    <div class="sm:px-25 px-0 pt-20 pb-1" style="background: #F5F5F4">
        <div>
            <CategoryImg />
        </div>
    </div>
    <div>
        <BoxImg />
    </div>
    <div class="px-25 pt-20 pb-35 pb-1" style="background: white">
        <p class="mb-14 text-center md:text-[60px] text-[24px] " style="font-family: Lora, serif;">The ReadDown</p>
        <div class="flex justify-center">
            <div class="mb-20" style="width: 6%; border: 1px solid #DEDEDE;"></div>
        </div>
        <MiddleImg />
    </div>
    <div class="px-25" style="background: #F5F5F4">
        <Data />
    </div>
    <Author />
    <Animation />
</template>