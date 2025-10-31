<script setup>
import Header from '../pagehome/header/Header.vue';
import Taskbar from '../pagehome/header/Taskbar.vue';
import BackgroundBlog from '../blog/blogcategory/BackgroundBlog.vue';
import DataBlog from '../blog/blogcategory/DataBlog.vue';
import FormBlog from './blogcategory/FormBlog.vue';
import Animation from '../pagehome/box/Animation.vue';
import Information from '../pagehome/box/Information.vue';

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