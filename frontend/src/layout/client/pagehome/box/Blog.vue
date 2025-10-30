<script setup>

</script>
<template>
    <div class="selection-9 pt-5" style="background-color: #F5F5F4;">
        <h2 class="text-center text-[30px] mt-20 mb-10" style="font-size: 50px; font-family: Playwrite RO, cursive; ">
            Most Recent Articles</h2>
        <div class="box flex gap-5 lg:px-35 px-10 flex-wrap">
            <div class="wrap-1 w-100" style="position: relative;">
                <div class="img">
                    <img src="https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/post11-copyright-890x664.jpg"
                        alt="">
                </div>
                <div class="box-1 px-9 py-6" style="background-color: white;">
                    <p class="my-5 font-semibold text-[20px]">Selection</p>
                    <p style="font-size: 34px;  font-family: Playfair Display, serif;">The quality of life is determined
                        by your books</p>
                    <button
                        style="position: absolute; top: 0; padding: 6px 15px; background: black; color: white; top: 45%; right: 9%;">
                        <p class="text-[30px]">12</p>
                        <p>APRIL</p>
                    </button>
                </div>
            </div>
            <div class="wrap-1 w-100" style="position: relative;">
                <div class="img">
                    <img src="https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/post11-copyright-890x664.jpg"
                        alt="">
                </div>
                <div class="box-1 px-9 py-6" style="background-color: white;">
                    <p class="my-5 font-semibold text-[20px]">Selection</p>
                    <p style="font-size: 34px;  font-family: Playfair Display, serif;">The quality of life is determined
                        by your books</p>
                    <button
                        style="position: absolute; top: 0; padding: 6px 15px; background: black; color: white; top: 45%; right: 9%;">
                        <p class="text-[30px]">12</p>
                        <p>APRIL</p>
                    </button>
                </div>
            </div>
            <div class="wrap-1 w-100" style="position: relative;">
                <div class="img">
                    <img src="https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/post11-copyright-890x664.jpg"
                        alt="">
                </div>
                <div class="box-1 px-9 py-6" style="background-color: white;">
                    <p class="my-5 font-semibold text-[20px]">Selection</p>
                    <p style="font-size: 34px;  font-family: Playfair Display, serif;">The quality of life is determined
                        by your books</p>
                    <button
                        style="position: absolute; top: 0; padding: 6px 15px; background: black; color: white; top: 45%; right: 9%;">
                        <p class="text-[30px]">12</p>
                        <p>APRIL</p>
                    </button>
                </div>
            </div>
        </div>
        <p class="py-17 text-center " >
            <button style="background-color: black; color: white; font-weight: 600;" class="py-5 px-12">VIEW MORE</button>
        </p>
    </div>
</template>
<style>
.selection-9 .box .wrap-1 .img {
    overflow: hidden;
    height: 305px;

}

.selection-9 .wrap-1 img {
    transition: transform 0.3s ease;
    width: 100%;
    height: 100%;
    /* 100% của .wrap-1 */
    object-fit: cover;
    display: block;
    pointer-events: auto;

    /* Đảm bảo chiều cao cố định giống overlay */
}

.selection-9 .wrap-1 img:hover {
    transform: scale(1.1);
}
</style>