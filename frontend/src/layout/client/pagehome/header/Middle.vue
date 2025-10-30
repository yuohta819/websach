<script setup>
const url = import.meta.env.VITE_API_URL_FRONTEND
</script>
<template>
    <div class="selection-3 md:flex block">
        <div class="box-1 md:w-[33%]">
            <div class="overlay"></div> <!-- Thêm lớp phủ -->
            <div class="img">
                <img style="width: 100%;"
                    src="https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/post8-copyright-840x560.jpg"
                    alt="">
            </div>

            <div class="wrap-1 ">
                <p>20 Books</p>
                <p>To Help You Brings</p>
                <p>Up a Child</p>
            </div>
            <a :href="`${url}/service/20/To Help You Bring Up a Child`">
                <div class="button">
                    <button>+</button>
                </div>
            </a>
        </div>
        <div class="box-1 md:w-[33%]">
            <div class="img">
                <img style="width: 100%;"
                    src="https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/post13-copyright-840x560.jpg"
                    alt="">
            </div>
            <div class="overlay"></div> <!-- Thêm lớp phủ -->
            <div class="wrap-1">
                <p>20 Books</p>
                <p>To Help You Brings</p>
                <p>Up a Cooking</p>
            </div>
            <a :href="`${url}/service/20/To Help You Bring Up a Cooking`">
                <div class="button">
                    <button>+</button>
                </div>
            </a>
        </div>
        <div class="box-1 md:w-[33%]">
            <div class="img">
                <img style="width: 100%;"
                    src="https://booklovers.ancorathemes.com/wp-content/uploads/2021/02/post28-copyright-840x560.jpg"
                    alt="">
            </div>
            <div class="overlay"></div> <!-- Thêm lớp phủ -->
            <div class="wrap-1 ">
                <p >20 Books</p>
                <p >To Help You Brings</p>
                <p >Up a Positive</p>
            </div>
            <a :href="`${url}/service/20/To Help You Bring Up a Positive`">
                <div class="button">
                    <button>+</button>
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
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    position: absolute;
    text-align: center;
    font-family: "Playwrite RO", cursive;
    color: white;
    font-size: 24px;
    z-index: 2;

}
.selection-3 p {
    font-size: 20px;
}
.selection-3 .overlay {
    position: absolute;
    width: 100%;
    height: 303px;
    top: 0;
    background-color: rgba(0, 0, 0, 0.4);
    /* Màu nền phủ */
    z-index: 1;
    overflow: hidden;
}

.selection-3 .overlay:hover {
    position: absolute;
    width: 100%;
    height: 303px;
    top: 0;
    background-color: rgba(0, 0, 0, 0.8);
    /* Màu nền phủ */
    z-index: 1;
    overflow: hidden;
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