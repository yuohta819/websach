<script setup>


</script>

<template>
    <div class=" selection-categorybackground px-0 md:px-20 py-20" style="position: relative;">
        <div class="img">
            <div class="overlay"></div>
            <h2 class="pt-30 text-[50px] pb-10" style="font-family: Playfair Display, serif;" >Inspire Daily Reading</h2>
            <div style="border: 1px solid #797979; height: 1px; width: 5%;"></div>
            <p class="pt-10 text-[20px] lg:w-120 w-60 " style="font-weight: 400; font-family: Playfair Display, serif;">Visit Our Blog and Page Find Out Daily Inspiration Quotes from the best Authors</p>
            <div class="pt-20 pb-30">
                <button class="py-5 px-16 font-[500]" style="background-color: black;">VISIT OUR BLOG</button>
            </div>
        </div>
    </div>

</template>
<style scoped>
.img {
    background-image: url("https://booklovers.ancorathemes.com/wp-content/uploads/2020/04/post32-copyright.jpg");
    background-size: cover;
    background-position: center;
    position: relative;
    width: 100%;
    overflow: hidden;
    display: flex;
    flex-direction: column;
    align-items: center;
    color: white;
    text-align: center;
}

.overlay {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.4); /* Lớp phủ đen mờ */
    z-index: 1;
}

.img h2,
.img p,
.img button {
    z-index: 2; /* Đảm bảo hiển thị trên lớp overlay */
    position: relative;
}

</style>