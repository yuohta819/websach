<script setup>
import Middle from './header/Middle.vue'
import Data from './middle/Data.vue'
import BoxImg from './middle/BoxImg.vue'
import Search from './middle/Search.vue'
import Blog from './box/Blog.vue'
import CategoryImg from "../main/img/CategoryImg.vue"
import Animation from './box/Animation.vue'
import DataImg from "../main/Img/DataImg.vue"
import Finnaly from './box/Finnaly.vue'
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
