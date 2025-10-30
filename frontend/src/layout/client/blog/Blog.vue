<script setup>
import Header from '../PageHome/header/Header.vue';
import Taskbar from "../PageHome/header/Taskbar.vue"
import BackgroundBlog from "../Blog/BlogCategory/BackgroundBlog.vue"
import DataBlog from "../Blog/BlogCategory/DataBlog.vue"
import FormBlog from './BlogCategory/FormBlog.vue';
import Animation from '../PageHome/Box/Animation.vue'
import Information from "../PageHome/Box/Information.vue"
</script>
<!-- Blog  -->
<template>
    <div class="container " style="box-sizing: border-box; max-width: 100%;">
    </div>
    <BackgroundBlog />
    <div class="flex sm:px-35 gap-10 py-30 flex-wrap lg:flex-nowrap" style="background: #F5F5F4;">
        <div >
            <DataBlog />
        </div>
        <div >
            <FormBlog />
        </div>
    </div>
    <div >
        <Animation />
    </div>
</template>