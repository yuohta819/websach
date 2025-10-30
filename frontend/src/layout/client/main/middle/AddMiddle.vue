<script setup>
import { ref } from 'vue';
import { onMounted } from 'vue';
import axios from "axios";
const data = ref([]);
const fill = ref([]);
const isActive = ref(null)
const backend = import.meta.env.VITE_API_URL_BACKEND
const url = import.meta.env.VITE_API_URL_FRONTEND
onMounted(async () => {
    const savedata = await axios.get(`${backend}/api/all`,{ timeout: 10000 })
    fill.value = savedata
    data.value = savedata.data.slice(0, 3)
})
</script>
<template>
    <div class="selection-11  " style=" border-bottom: 1px solid black;">
        <div class="box-1 ">
            <p class="text-[24px] font-[400] " style="text-transform: uppercase; font-family: Playfair Display, serif;">
                What’s New</p>
            <div>
                <div class="mt-3" style="width: 20%; border: 1px solid #DEDEDE;"></div>
            </div>
            <div class="wrap mt-7  gap-20  text-center justify-center">
                <div class="box flex mb-5" v-for="item in data">
                        <div class="img">
                            <a :href="`${url}/${item.name}/${item.category.name}/${item.id}`">
                                <img class="w-20" :src="item.img" alt="">
                            </a>
                        </div>
                        <div class="inner-2 ">
                            <p class=" text-[15px] font-[600]" style=" font-family: Playfair Display, serif">{{
                                item.name }}</p>
                            <p class="pt-1 text-red-600" style="font-family: EB Garamond, serif;">{{ item.author }}</p>
                            <div class="flex gap-3 px-5">
                                <div v-for="n in 5" :key="n" class="rate pt-1">
                                    <div :class="n <= item.rate ? 'text-black-500' : 'text-yellow-200'">
                                        <i class="fa-solid fa-star"></i>
                                    </div>
                                </div>
                            </div>
                            <p class=" text-red-500 text-[20px]">{{ item.price }} $</p>
                        </div>
                    
                </div>
            </div>
        </div>
    </div>
    <div class="mt-5 flex items-center text-red-600" style="font-family: Lora, serif;"><a href="">VIEW MORE</a><i
            class="ml-3 fa-solid fa-arrow-right"></i></div>
</template>
<style>
.selection-11 .box-2 li {
    margin-bottom: 21px;
    cursor: pointer;
    color: #797979;
}

.selection-11 .box-2 li:hover {
    color: red;
    border-bottom: none;
}
.selection-11 p {
    cursor: pointer;
}
.selection-11 .box-3 .content p {
    font-size: 20px;
    color: #797979;
}
</style>