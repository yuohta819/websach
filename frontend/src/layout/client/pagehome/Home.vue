<script setup>
import Header from "./header/Header.vue"
import Taskbar from "./header/Taskbar.vue"
import Middle from './header/Middle.vue'
import Data from './Middle/Data.vue'
import BoxImg from './Middle/BoxImg.vue'
import Search from './Middle/Search.vue'
import Blog from './Box/Blog.vue'
import CategoryImg from "../Main/Img/CategoryImg.vue"
import Animation from './Box/Animation.vue'
import DataImg from "../Main/Img/DataImg.vue"
import Finnaly from './Box/Finnaly.vue'
</script>

<template>
  <div class="container " style="box-sizing: border-box; max-width: 100%;">
    <div class="selection relative px-25" style="background: white;">
      <Middle />
      <Data />
      <CategoryImg />
      <BoxImg />
      <Search />
      <DataImg />
      <Blog />
      <Finnaly />
    </div>
  </div>
  <Animation />
  
</template>
<style>
</style>
