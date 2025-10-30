<script setup>


</script>
<template>
    <div class="selection-6 flex justify-around py-20 flex-wrap" style="background: #F5F5F4;">
        <div class="box-1">
            <p style="font-size: 75px;"><i class="fa-brands fa-dropbox box"></i></p>
            <p class="text-[30px] cursor-pointer" style="font-family: EB Garamond, serif;">Make the Best Present</p>
            <div class="wrap-1">
                <p>A book is still one of the best presents </p>
                <p>for anyone on any occasion.</p>
            </div>
            <div class="icon flex items-center justify-center">
                <span class="cursor-pointer">Read More</span>
                <i class="fa-solid fa-arrow-right-long mt-1 ml-2"></i>
            </div>
        </div>
        <div class="box-1">
            <p style="font-size: 75px;"><i class="fa-brands fa-envira box"></i></p>
            <p class="text-[30px] cursor-pointer" style="font-family: EB Garamond, serif;">Make the Best Present</p>
            <div class="wrap-1">
                <p>A book is still one of the best presents </p>
                <p>for anyone on any occasion.</p>
            </div>
            <div class="icon flex items-center justify-center">
                <span class="cursor-pointer">Read More</span>
                <i class="fa-solid fa-arrow-right-long mt-1 ml-2"></i>
            </div>
        </div>
        <div class="box-1">
            <p style="font-size: 75px;"><i class="box fa-brands fa-bilibili"></i></p>
            <p class="text-[30px] cursor-pointer" style="font-family: EB Garamond, serif;">Make the Best Present</p>
            <div class="wrap-1">
                <p>A book is still one of the best presents </p>
                <p>for anyone on any occasion.</p>
            </div>
            <div class="icon flex items-center justify-center">
                <span class="cursor-pointer">Read More</span>
                <i class="fa-solid fa-arrow-right-long mt-1 ml-2"></i>
            </div>
        </div>
    </div>
</template>

<style>
.selection-6 p {
    text-align: center;
    margin-bottom: 10px;
}

.selection-6 .wrap-1 p {
    font-size: 14px;
    font-weight: 500;
    color: #797979;
}

.selection-6 .icon {
    text-align: center;
    position: relative;
}

.selection-6 .icon span {
    opacity: 0;
    transform: translateX(-10px);
    transition: all 0.5s ease;
    display: inline-block;
    position: absolute;
}

.selection-6 .icon i {
    transition: transform 0.4s ease;
}

.selection-6 .box-1:hover .icon span, i {
    display: inline-block;
    opacity: 1;
    transform: translateX(0);
  
}
.selection-6 .box-1:hover .icon i {
    transform: translateX(50px); 
  
}
.selection-6 .box-1:hover .icon i {
    transform: translateX(50px); 
  
}

.selection-6 .box-1:hover .box {
    color: red;
}
</style>