<script setup>

import axios from 'axios';
import { ref } from 'vue';

const name = ref('')
const phone = ref('')
const email = ref('')
const subject = ref('')
const infor = ref('')

function handleSubmit() {
    console.log(infor.value)
    axios.post('http://localhost:8080/blog/update', {
        name: name.value,
        phone: phone.value,
        email: email.value,
        subject: subject.value,
        information: infor.value
    },
        {
            timeout: 10000 // <- đúng chỗ
        }

    )
}
</script>
<template>
    <form action="" @submit.prevent="handleSubmit" style="position: sticky; top: 0;">
        <div class="px-13 pt-15" style="background-color: white; position: sticky; top: 0;">
            <div class="header flex justify-between items-center ">
                <p class="text-[19px] font-[700]" style="font-family: Playfair Display, serif;">GET IN TOUCH</p>
                <i class="fa-solid fa-caret-down"></i>
            </div>
            <div class="w-[20%] h-[1px] mt-5"></div>
            <div class=" mt-10 flex items-center pb-1" style="border-bottom: 1px solid #DEDEDE;">
                <i class="  fa-solid fa-user-large"></i>
                <input style="font-family: Noto Serif, serif;" v-model="name" class="ml-5 text-[16px]" required
                    placeholder="Name">
            </div>
            <div class="w-[100%] relative group">
                <div class="box"></div>
            </div>
            <div class=" mt-10 flex items-center pb-1" style="border-bottom: 1px solid #DEDEDE;">
                <i class="fa-solid fa-phone-volume"></i>
                <input style="font-family: Noto Serif, serif;" class="ml-5 text-[16px]" v-model="phone" required
                    placeholder="Phone">
            </div>
            <div class="w-[100%] relative group">
                <div class="box"></div>
            </div>
            <div class=" mt-10 flex items-center pb-1" style="border-bottom: 1px solid #DEDEDE;">
                <i class="fa-solid fa-envelope"></i>
                <input style="font-family: Noto Serif, serif;" class="ml-5 text-[16px]" v-model="email" required
                    placeholder="Email">
            </div>
            <div class="w-[100%] relative group">
                <div class="box"></div>
            </div>
            <div class=" mt-10 flex items-center pb-1" style="border-bottom: 1px solid #DEDEDE;">
                <i class="fa-solid fa-circle-info"></i>
                <input style="font-family: Noto Serif, serif;" class="ml-5 text-[16px]" v-model="subject" required
                    placeholder="Subject">
            </div>
            <div class="w-[100%] relative group">
                <div class="box"></div>
            </div>
            <div class=" mt-10 flex items-center pb-1" style="border-bottom: 1px solid #DEDEDE;">
                <i class="fa-solid fa-pen"></i>
                <textarea style="width: 100%;font-family: Noto Serif, serif;" class="ml-5 text-[16px]" v-model="infor"
                    required placeholder="How can we help you? Feel free to get in touch!"></textarea>
            </div>
            <div class="w-[100%] relative group">
                <div class="box"></div>
            </div>
            <div class="flex items-center mt-7">
                <input type="checkbox" style="border: 1px solid #7F7F7F; transform: scale(1.5); " required>
                <p style="width: 100%;font-family: Noto Serif, serif; color: #7F7F7F;" class="ml-5 text-[16px]">I agree
                    that my submitted
                    data is being collected and stored.</p>
            </div>
            <div class="my-10 pb-10">
                <button class="px-9 py-4 font-[600] " type="submit"
                    style="box-shadow: rgba(50, 50, 93, 0.25) 0px 13px 27px -5px, rgba(0, 0, 0, 0.3) 0px 8px 16px -8px;;background-color: #D02735; color: white;  font-family: Sansation, sans-serif;">GET
                    IN TOUCH</button>
            </div>
        </div>
    </form>
</template>

<style scoped>
.box {
    position: relative;
    height: 1px;
    background-color: transparent;
    overflow: hidden;
}

.box::after {
    content: "";
    position: absolute;
    left: 0;
    bottom: 0;
    height: 1px;
    width: 0;
    background-color: black;
    transition: width 0.5s ease;
}

textarea:focus {
    border: none;
    overflow: hidden;
    outline: none;
}

.group:hover .box::after {
    width: 100%;
}
</style>