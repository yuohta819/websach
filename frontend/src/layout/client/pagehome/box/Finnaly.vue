<script setup>
</script>
<template>
    <div class="selection-13" style="position: fixed;">
        <div class="button">
            <button><i class="fa-solid fa-cart-shopping"></i></button>
        </div>
        <div class="button">
            <button><i class="fa-solid fa-images"></i></button>
        </div>
        <div class="button">
            <button><i class="fa-solid fa-address-card"></i></button>
        </div>
    </div>
    <div class="selection-14" style="position: fixed;">
        <div class="arrow">
            <i class="fa-solid fa-arrow-up"></i>
        </div>
    </div>
</template>
<style scoped>
.selection-13 {
    top: 200px;
    right: 16px;
    z-index: 4;

}
.selection-14 {
    bottom: 50px;
    right: 16px;
    z-index: 4;

}
.arrow {
    padding: 23px;
    background: black;
    color: white;
    cursor: pointer;
}
.button {
    margin-bottom: 5px;

}

button {
    padding: 20px;
    background: black;
    color: white;
    cursor: pointer;
}
</style>