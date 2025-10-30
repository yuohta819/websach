<script setup>
import { ref } from 'vue';
import { onMounted } from 'vue';
const data = ref(false)
const play = ref(null)
function handleClick() {
    data.value = !data.value
    play.value.play()
}
</script>
<template>
    <div class="selection-blog">
        <img style="width: 100%;"
            src="https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/post8-copyright.jpg" alt="">
        <p class="leading-[30px] pt-10 font-medium" style="font-family: Playfair Display, serif; color: #797979;">Mauris
            eu nisi eget nisi imperdiet vestibulum. Nunc sodales vehicula risus. Suspendisse id mauris sodales, blandit
            tortor eu, sodales justo. Morbi tincidunt, ante vel suscipit volutpat, turpis enim volutpSectetur adipiscing
            elit, sed do eiusm onsectetur adipiscing elit, sed do eiusm od tempor incididunt ut labore. Ut vel placerat
            eros, eu tincidunt velit. Consectetur adipiscing elit, adipiscing elit, sed do.</p>
        <p class="mt-5 text-[24px]" style="font-family: Lora, serif;">Sed ut perspiciatis unde omnis iste natus et</p>
        <p class="mt-5 leading-[30px] " style="font-family: Playfair Display, serif; color: #797979;">Lorem ipsum dolor
            sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna
            aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd
            gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.</p>
        <p class="mt-5 leading-[30px] " style="font-family: Playfair Display, serif; color: #797979;">Aliquam laoreet
            sed neque ac vehicula. Cras congue eros nec quam laoreet, in viverra erat bibendum. Cras turpis urna,
            vulputate at est vitae, posuere lobortis erat.</p>
        <div class="flex gap-3 mt-10 flex-wrap justify-center" >
            <img class="w-100" 
                src="https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/post1-copyright-1536x1149.jpg"
                alt="">
            <img class="w-100" 
                src="https://booklovers.ancorathemes.com/wp-content/uploads/2020/05/post2-copyright-1536x1149.jpg"
                alt="">
        </div>
        <p class="mt-5 leading-[30px] " style="font-family: Playfair Display, serif; color: #797979;">Lorem ipsum dolor
            sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna
            aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd
            gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.</p>
        <p class="mt-5 text-[24px]" style="font-family: Lora, serif;">Aliquam quis lobortis quam</p>
        <p class="mt-5 leading-[30px] " style="font-family: Playfair Display, serif; color: #797979;">Curabitur
            pellentesque odio magna, id malesuada arcu sodales ut. Sed sed quam ut ex bibendum commodo id id magna.
            Aliquam sed ligula sed ante blandit volutpat. Ut bibendum, nisi et mattis vulputate, odio arcu aliquet
            metus, nec dapibus risus risus quis lectus.</p>
        <p class="mt-5 leading-[30px] " style="font-family: Playfair Display, serif; color: #797979;">Lorem ipsum dolor
            sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna
            aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd
            gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.</p>
        <div class="video relative mt-8">
            <video controls ref="play" poster="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_SdZWIMacymfJuZ19Zxc-vwmlbHZCSOzCLg&s"  style="width: 100%;">
                <source src="/video.mp4" type="video/mp4">
                Trình duyệt không hỗ trợ video.
            </video>
            <div class="start absolute text-[50px] top-[40%] z-[2] right-[50%] text-red-600" :class="{active: data}" @click="handleClick">
                <i class="fa-brands fa-youtube"></i>
            </div>
        </div>
        <p class="mt-5 leading-[30px] " style="font-family: Playfair Display, serif; color: #797979;">At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr.</p>

    </div>

</template>

<style scoped> 
.start {
    cursor: pointer;
}
.active {
    display: none;
}
</style>