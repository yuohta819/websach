<script setup>
import Header from '../pagehome/header/Header.vue';
import Taskbar from '../pagehome/header/Taskbar.vue';
import Background from './detailproduct/Background.vue';
import DetailBook from './detailproduct/DetailBook.vue';
import Descibe from './detailproduct/Descibe.vue';
import Data from '../pagehome/middle/Data.vue';
import Animation from '../pagehome/box/Animation.vue';
import Information from '../pagehome/box/Information.vue';
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