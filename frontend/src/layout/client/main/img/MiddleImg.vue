<script setup>
const url = import.meta.env.VITE_API_URL_FRONTEND
</script>
<template>
    <div class="selection-3 flex gap-7 px-10 flex-wrap justify-center">
        <div class="box-1 w-200px">
            <a :href="`${url}/service/20/To%20Help%20You%20Bring%20Up%20a%20Child`">
                <div class="img" style="height: 500px;">
                    <img style="width: 100%; height: 250px; "
                        src="https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/post8-copyright-840x560.jpg"
                        alt="">
                    <div style="background-color: #F5BF56; width: 100%; text-align: center;">
                        <p class="pt-8 text-[24px] font-[300]"
                            style="font-family: Dancing Script, cursive; color: white;">
                            20 Books</p>
                        <div class="flex justify-center pt-4">
                            <p class="text-[24px]" style=" color: white;width: 200px; font-family: EB Garamond, serif;">
                                To
                                Help You Brings Up a Child</p>
                        </div>
                        <div class="read flex items-center justify-center">
                            <p class=" py-4 font-[500]" style="font-family: Arvo, serif;">Read More </p>
                            <i class="pt-1 pl-2 fa-solid fa-arrow-right"></i>
                        </div>
                    </div>
                </div>
            </a>
        </div>

        <div class="box-1 w-200px">
            <a :href="`${url}/service/20/To%20Help%20You%20Bring%20Up%20a%20Cooking`">
                <div class="img" style="height: 500px;">
                    <img style="width: 100%; height: 250px; "
                        src="https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/post13-copyright-840x560.jpg"
                        alt="">
                    <div style="background-color: #53877A; width: 100%; text-align: center;">
                        <p class="pt-8 text-[24px] font-[300]"
                            style="font-family: Dancing Script, cursive; color: white;">
                            20 Books</p>
                        <div class="flex justify-center pt-4">
                            <p class="text-[24px]" style=" color: white;width: 200px; font-family: EB Garamond, serif;">
                                To
                                Help You Brings Up a Cooking</p>
                        </div>
                        <div class="read flex items-center justify-center">
                            <p class=" py-4 font-[500]" style="font-family: Arvo, serif;">Read More </p>
                            <i class="pt-1 pl-2 fa-solid fa-arrow-right"></i>
                        </div>
                    </div>
                </div>
            </a>
        </div>

        <div class="box-1 w-200px">
            <a :href="`${url}/service/20/To%20Help%20You%20Bring%20Up%20a%20Positive`">
                <div class="img" style="height: 500px;">
                    <img style="width: 100%; height: 250px; "
                        src="https://booklovers.ancorathemes.com/wp-content/uploads/2021/02/post28-copyright-840x560.jpg"
                        alt="">
                    <div style="background-color: #DE3241; width: 100%; text-align: center;">
                        <p class="pt-8 text-[24px] font-[300]"
                            style="font-family: Dancing Script, cursive; color: white;">
                            20 Books</p>
                        <div class="flex justify-center pt-4">
                            <p class="text-[24px]" style=" color: white;width: 200px; font-family: EB Garamond, serif;">
                                To
                                Help You Brings Up a Positive</p>
                        </div>
                        <div class="read flex items-center justify-center">
                            <p class=" py-4 font-[500]" style="font-family: Arvo, serif;">Read More </p>
                            <i class="pt-1 pl-2 fa-solid fa-arrow-right"></i>
                        </div>
                    </div>
                </div>
            </a>
        </div>
    </div>

</template>

<style>
.selection-3 .box-1 {
    position: relative;
    cursor: pointer;
}

.selection-3 .box-1 .wrap-1 {
    top: 35%;
    right: 19%;
    position: absolute;
    text-align: center;
    font-family: "Playwrite RO", cursive;
    color: white;
    font-size: 24px;
    z-index: 2;

}

.selection-3 .img {
    overflow: hidden;
    /* Giữ ảnh không bị tràn khỏi khung */
    height: 303px;
    /* Đảm bảo chiều cao cố định giống overlay */
    position: relative;
}

.selection-3 .img img {
    transition: transform 0.5s ease;
    width: 100%;
    height: 100%;
    object-fit: cover;
    /* Giúp ảnh lấp đầy khung mà không méo */
    display: block;
}

.selection-3 .wrap-1 {
    pointer-events: none;
}

.selection-3 .box-1:hover .img img {
    transform: scale(1.1);
}

.selection-3 .box-1 .read p,
.selection-3 .box-1 .read i {
    color: white;
}

.selection-3 .box-1:hover .read p,
.selection-3 .box-1:hover .read i {
    color: black;
}

.selection-3 .button button {
    position: absolute;
    padding: 10px 15px;
    background: red;
    bottom: 0;
    right: 50%;
    z-index: 4;
    font-size: 24px;
    color: white;
    cursor: pointer;
}

.selection-3 .box-1 .button {
    opacity: 0;
    transition: opacity 0.3s ease;
    pointer-events: none;
}

.selection-3 .box-1:hover .button {
    opacity: 1;
    pointer-events: auto;
}
</style>