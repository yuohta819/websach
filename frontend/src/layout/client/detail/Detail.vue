<script setup>
import Header from '../PageHome/header/Header.vue';
import Taskbar from '../PageHome/header/Taskbar.vue'
import Background from './DetailProduct/Background.vue';
import DetailBook from './DetailProduct/DetailBook.vue';
import Descibe from './DetailProduct/Descibe.vue';
import Data from '../PageHome/Middle/Data.vue';
import Animation from '../PageHome/Box/Animation.vue';
import Information from '../PageHome/Box/Information.vue';
</script>
<!-- Chi Tiet 1 Quyen Sach -->
<template>
    <div class="selection-detail">
        <Background />
        <div style="background: #F5F5F4;">
            <DetailBook />
            <Descibe />
        </div>
        <Data />
        <div style="box-sizing: border-box">
            <div class="selection relative" style="background: white;">
                
                <Animation />
            </div>
        </div>
        

    </div>
</template>