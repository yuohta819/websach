<script setup>
</script>
<template>
    <div class="selection-10" style="background: #DE3241;">
        <div class="box flex justify-between items-center px-12 md:px-35 py-15 flex-wrap">
            <div class="inner">
                <span
                    style="font-size: 38px; color: white; font-family: Cormorant Garamond, serif; font-weight: 300;">Get
                    -30% purchase</span>
                <span class="animation" style="font-size: 38px; font-family: Cormorant Garamond, serif;">on order over
                    $299.00</span>
            </div>
            <a :href="`${url}/category/Dramma`">
                <div class="wrap md:px-5 md:py-4 px-0">
                    <button >More Infor</button>
                </div>
            </a>
        </div>
    </div>
</template>
<style>
.selection-10 .wrap button {
    background-color: black;
    color: white;
    height: 60px;
    width: 150px;
    font-weight: 600;
    box-shadow: rgba(0, 0, 0, 0.25) 0px 54px 55px, rgba(0, 0, 0, 0.12) 0px -12px 30px, rgba(0, 0, 0, 0.12) 0px 4px 6px, rgba(0, 0, 0, 0.17) 0px 12px 13px, rgba(0, 0, 0, 0.09) 0px -3px 5px;
}
.selection-10 .wrap:hover button {
    background-color: white;
    color: black;
    border: 3px solid black;
} 
@keyframes fadeInUp {

    0% {
        transform: translateX(0px);
        opacity: 0;
    }

    100% {
        transform: translateX(40px);
        opacity: 1;
    }
}

.selection-10 .box .inner .animation {
    display: inline-block;
    animation: fadeInUp 1.5s ease infinite;
}
</style>