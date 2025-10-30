<script setup>
import { ref, onMounted, watch } from "vue";
import axios from "axios";
import { useToast } from "vue-toastification";
import { eventBus } from "../../../components/eventBus"
import { useRoute, useRouter } from "vue-router";
const backend = import.meta.env.VITE_API_URL_BACKEND;
const accountid = localStorage.getItem("accountid");
const token = localStorage.getItem("token");

const favoriteItems = ref([]);
const isLoading = ref(false);
const searchQuery = ref("");
const currentPage = ref(1);
const perPage = 6;
const totalPages = ref(1);
const totalItems = ref(0);
const toast = useToast()
const route = useRouter()
// 📡 Gọi API lấy danh sách (server-side pagination)
const fetchFavorites = async () => {
  if (!token) return;

  isLoading.value = true;
  try {
    const res = await axios.get(`${backend}/cart/call`, {
      headers: {
        Authorization: `Bearer ${token.trim()}`
      },
      params: {
        page: currentPage.value,
        size: perPage,
        search: searchQuery.value || ""
      }
    });

    // ✅ Dữ liệu dạng Spring Data Page { content, totalPages, totalElements }
    if (Array.isArray(res.data)) {
      favoriteItems.value = res.data;
      totalPages.value = 1;
      totalItems.value = res.data.length;
    } else {
      favoriteItems.value = res.data.content || [];
      totalPages.value = res.data.totalPages || 1;
      totalItems.value = res.data.totalElements || favoriteItems.value.length;
    }

  } catch (err) {
    console.error("Error fetching favorites:", err);
  } finally {
    isLoading.value = false;
  }
};
async function readBook(book) {
  if (!token) {
    toast.warning("Please log in to add to favorites!")
    return
  }
  try {
    const account = localStorage.getItem("name")
    const payload = {
      account: account,
      bookid: book.book.bookid, // ✅ ID của sách
      roles: book.book.is_roles // ✅ Role (premium/pro/member)
    }

    const res = await axios.post(
      `${backend}/cart/check/roles`,
      payload,
      {
        headers: { Authorization: `Bearer ${token.trim()}` }
      }
    )
    if (res.data === 'success') {
      route.push(`/favorites/${book.book.bookid}`)
    } else if (res.data === 'error') {
      toast.warning("Invalid token")
    } else if (res.data === 'not roles') {
      toast.warning("You do not have access. Please purchase permission.")
    }
    else {
      toast.warning("Something went wrong")
    }
  } catch (err) {
    console.error('❌ Error adding to favorites:', err)
    toast.error("Server error. Please try again later.")
  }
}
// 🚀 Gọi dữ liệu ban đầu
onMounted(fetchFavorites);

// 🔍 Khi người dùng đổi trang hoặc nhập tìm kiếm
watch([searchQuery, currentPage], fetchFavorites);

// ❌ Xóa khỏi danh sách
const removeFavorite = async (cartid) => {
  try {
    const res = await axios.get(`${backend}/cart/remove`, {
      headers: {
        Authorization: `Bearer ${token.trim()}`
      },
      params: { param: cartid },
    });
    if (res.data === 'success') {
      toast.success("Remove product from cart successfully!!!!")
      eventBus.emit("update-cart") // 👉 Gửi sự kiện cập nhật
    }
    fetchFavorites(); // reload danh sách
  } catch (err) {
    console.error("Error removing favorite:", err);
  }
};

// 📄 Chuyển trang
const changePage = (page) => {
  if (page >= 1 && page <= totalPages.value) currentPage.value = page;
};
</script>

<template>
  <div class="min-h-screen bg-gray-50 py-10 px-4" style="background-color: #F5F5F4;">
    <div class="max-w-6xl mx-auto bg-white rounded-2xl shadow-xl p-8">
      <!-- Tiêu đề -->
      <h1 class="text-3xl font-serif font-semibold mb-8 border-b pb-4 text-gray-800 flex items-center gap-2">
        ❤️ Your Favorite Books
      </h1>

      <!-- Thanh tìm kiếm -->
      <div class="mb-6 flex flex-col sm:flex-row justify-between items-center gap-3">
        <input v-model="searchQuery" type="text" placeholder="Search your favorite books..."
          class="w-full sm:w-1/2 border rounded-md p-2 focus:ring focus:ring-orange-300 outline-none" />
        <p class="text-gray-500 text-sm">
          Showing {{ favoriteItems.length }} of {{ totalItems }} books
        </p>
      </div>

      <!-- Trạng thái tải -->
      <div v-if="isLoading" class="text-center text-gray-500 py-20 text-lg">
        ⏳ Loading your favorites...
      </div>

      <!-- Không có dữ liệu -->
      <div v-else-if="favoriteItems.length === 0" class="text-center text-gray-600 py-20">
        You haven't added any favorite books yet 💔
        <br />
        <a href="/" class="text-blue-600 underline mt-2 inline-block">Discover books</a>
      </div>

      <!-- Danh sách yêu thích -->
      <div v-else class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-8">
        <div v-for="book in favoriteItems" :key="book.bookid"
          class="bg-white border rounded-2xl shadow hover:shadow-lg transition p-4 flex flex-col">
          <img :src="book.book.img" alt="Book cover" class="w-full h-64 object-cover rounded-lg mb-4" />
          <h2 class="text-lg font-semibold text-gray-800 font-serif line-clamp-2">
            {{ book.book.name }}
          </h2>
          <p class="text-gray-600 text-sm mt-1 italic">{{ book.book.author }}</p>

          <div class="flex justify-between items-center mt-4 pt-3 border-t">
            <div @click="readBook(book)" class="text-sm text-blue-600 hover:underline cursor-pointer">
              View Details
            </div>
            <button @click="removeFavorite(book.cartid)" class="text-sm text-red-500 hover:text-red-700 transition">
              Remove ❤️
            </button>
          </div>
        </div>
      </div>

      <!-- Phân trang -->
      <div v-if="totalPages > 1" class="flex justify-center items-center gap-2 mt-10">
        <button @click="changePage(currentPage - 1)" :disabled="currentPage === 1"
          class="px-3 py-1 border rounded-md hover:bg-gray-100 disabled:opacity-50">
          Prev
        </button>

        <button v-for="page in totalPages" :key="page" @click="changePage(page)" class="px-4 py-2 rounded-md border"
          :class="page === currentPage ? 'bg-orange-500 text-white' : 'hover:bg-orange-100'">
          {{ page }}
        </button>

        <button @click="changePage(currentPage + 1)" :disabled="currentPage === totalPages"
          class="px-3 py-1 border rounded-md hover:bg-gray-100 disabled:opacity-50">
          Next
        </button>
      </div>
    </div>
  </div>
</template>
