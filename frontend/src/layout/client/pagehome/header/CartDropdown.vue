<script setup>
import { ref, onMounted,onBeforeUnmount } from "vue";
import axios from "axios";
import { useRouter } from "vue-router"
import { useToast } from "vue-toastification"
import { eventBus } from "../../../../components/eventBus"
const backend = import.meta.env.VITE_API_URL_BACKEND;
const favoriteItems = ref([]);
const isHovering = ref(false);
const accountid = localStorage.getItem("accountid");
const router = useRouter()
const toast = useToast()
const token = localStorage.getItem("token")
function handleViewFavorites() {
  // Kiểm tra localStorage có token không

  if (!token) {
    // ⚠️ Chưa đăng nhập
    toast.warning("Please login to view your favorite books!")

    // Nếu bạn có popup login:
    // emit('openLogin') hoặc mở modal login ở đây

    return
  } else {
    router.push("/favorites")
  }
}
onMounted(() => {
  if (token) fetchCart()

  // 🔄 Khi có sự kiện update-cart thì gọi lại API
  eventBus.on("update-cart", fetchCart)
})

function fetchCart() {
  axios.get(`${backend}/cart/call`, {
    headers: { Authorization: `Bearer ${token.trim()}` }
  }).then(res => {
    favoriteItems.value = res.data
  }).catch(err => {
    console.error(err)
  })
}

// 🧹 Hủy lắng nghe khi component bị huỷ
onBeforeUnmount(() => {
  eventBus.off("update-cart")
})
</script>

<template>
  <div class="relative" @mouseenter="isHovering = true" @mouseleave="isHovering = false">
    <!-- Icon trái tim -->
    <div class="relative cursor-pointer">
      <i class="fa-solid fa-heart text-xl hover:text-[#b85c38]"></i>
      <span v-if="favoriteItems.length"
        class="absolute -top-2 -right-3 bg-[#b85c38] text-white text-xs px-1.5 rounded-full">
        {{ favoriteItems.length }}
      </span>
    </div>

    <!-- Dropdown danh sách yêu thích -->
    <transition name="fade">
      <div v-if="isHovering" class="absolute right-0 w-96 bg-white shadow-xl border border-gray-100 rounded-xl z-50">
        <!-- Danh sách sách yêu thích -->
        <div v-if="favoriteItems.length" class="max-h-64 overflow-y-auto divide-y">
          <div v-for="item in favoriteItems" :key="item.book.bookid"
            class="flex items-center gap-3 p-3 hover:bg-[#fdf6ee] transition">
            <img :src="item.book.img" alt="book" class="w-14 h-16 object-cover rounded-md" />
            <div class="flex-1">
              <h3 class="text-sm font-semibold text-gray-700 line-clamp-2 leading-snug">
                {{ item.book.name }}
              </h3>
              <p class="text-xs text-gray-500 mt-1">by {{ item.book.author }}</p>
              <p class="text-[14px] font-medium text-red-500 mt-1">
                {{ item.book.price.toLocaleString("vi-VN") }}₫
              </p>
            </div>
          </div>
        </div>

        <!-- Nếu chưa có sách -->
        <div v-else class="p-5 text-center text-gray-400 text-sm">
          You haven't liked any books yet 💔
        </div>

        <!-- Nút xem toàn bộ -->
        <div class="p-4 border-t text-center">
          <button @click="handleViewFavorites"
            class="inline-block w-full bg-[#b85c38] text-white py-2 rounded-lg hover:bg-[#a34e2f] transition">
            View My Favorite Books
          </button>
        </div>

      </div>
    </transition>
  </div>
</template>

<style scoped>
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.2s ease, transform 0.2s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
  transform: translateY(-5px);
}
</style>
