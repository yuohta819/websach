<template>
  <div style="background-color: #F5F5F4;">
    <div class="max-w-5xl mx-auto py-10 px-6 font-serif relative">
      <button @click="goBack" class="text-blue-600 hover:underline mb-5 flex items-center">
        ← Quay lại thư viện
      </button>

      <!-- Tiêu đề sách -->
      <div class="mb-10 text-center">
        <h1 class="text-4xl font-bold text-gray-800 mb-2">
          📖 Bài học cuộc sống
        </h1>
        <p class="text-gray-500 italic">
          “Những câu chuyện nhỏ dạy ta bài học lớn trong cuộc đời.”
        </p>
      </div>

      <!-- Nếu đang tải -->
      <div v-if="isLoading" class="text-center text-gray-500 py-20 animate-pulse">
        ⏳ Đang tải nội dung sách...
      </div>

      <!-- Nếu đã tải xong -->
      <div v-else>
        <!-- Danh sách chương -->
        <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-5 mb-10">
          <div v-for="chapter in chapters" :key="chapter.chapterid" @click="selectChapter(chapter)"
            class="cursor-pointer bg-white border border-gray-200 rounded-xl shadow-sm hover:shadow-xl hover:-translate-y-1 transition-all duration-300 p-5">
            <h3 class="text-lg font-semibold text-gray-800 mb-2">
              {{ chapter.chapter_title }}
            </h3>
            <p class="text-gray-500 line-clamp-2 text-sm">
              {{ chapter.chapter_content }}
            </p>
          </div>
        </div>
      </div>

      <!-- 🩵 Modal hiển thị nội dung chương -->
      <transition name="fade">
        <div v-if="currentChapter"
          class="fixed inset-0 bg-black/40 backdrop-blur-sm flex justify-center items-center z-50">
          <div
            class="relative bg-gradient-to-b from-white to-blue-50 w-full max-w-3xl shadow-2xl rounded-2xl border border-gray-100 p-8 animate-scaleUp overflow-y-auto max-h-[90vh]">
            <!-- Nút đóng -->
            <button @click="closeModal" class="absolute top-4 right-4 text-gray-400 hover:text-red-500 text-2xl">
              ✕
            </button>

            <!-- Tiêu đề chương -->
            <h2 class="text-2xl font-bold mb-4 text-gray-800 text-center">
              {{ currentChapter.chapter_title }}
            </h2>

            <!-- Nội dung -->
            <transition name="page-turn" mode="out-in">
              <div :key="pageIndex" class="leading-relaxed text-gray-800 text-lg whitespace-pre-line px-2">
                <div v-if="isTranslating" class="text-center text-gray-500 italic py-10">
                  ⏳ Đang dịch nội dung...
                </div>
                <div v-else>
                  {{ translatedText || pagedContent }}
                </div>
              </div>
            </transition>

            <!-- Thanh điều hướng -->
            <div class="flex justify-between items-center mt-6 border-t pt-4 text-sm">
              <button @click="prevPage" :disabled="pageIndex === 0"
                class="px-4 py-2 bg-gray-100 rounded-lg hover:bg-gray-200 disabled:opacity-50">
                ◀ Trang trước
              </button>

              <div class="text-gray-500">
                Trang {{ pageIndex + 1 }} / {{ totalPages }}
              </div>

              <button @click="nextPage" :disabled="pageIndex === totalPages - 1"
                class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 disabled:opacity-50">
                Trang sau ▶
              </button>
            </div>
          </div>
        </div>
      </transition>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import axios from "axios";

const route = useRoute();
const router = useRouter();
const backend = import.meta.env.VITE_API_URL_BACKEND;

const chapters = ref([]);
const currentChapter = ref(null);
const pageIndex = ref(0);
const isLoading = ref(false);
const pageLength = 400;
const token = localStorage.getItem("token")
// 🔹 Biến liên quan đến dịch
const translatedText = ref("");
const isTranslating = ref(false);

const goBack = () => router.push("/");
onMounted(async () => {
  if (token) {
    const account = localStorage.getItem("name")
    try {
      const res = await axios.get(`${backend}/chapter/${route.params.id}`, {
        headers: {
          Authorization: `Bearer ${token.trim()}`
        },
        params: {
          account: account
        }
      })
      chapters.value = res.data;
    } catch (error) {
      toast.warning(error.response.data.error)
      localStorage.clear()
    }
  }
})

// Khi chọn chương
const selectChapter = (chapter) => {
  currentChapter.value = chapter;
  pageIndex.value = 0;
  translatedText.value = "";
};

// Đóng modal
const closeModal = () => {
  currentChapter.value = null;
  translatedText.value = "";
};


// Tách nội dung thành trang
const pages = computed(() => {
  if (!currentChapter.value) return [];
  const text = currentChapter.value.chapter_content;
  return text.match(new RegExp(`.{1,${pageLength}}`, "g")) || [];
});

const pagedContent = computed(() => pages.value[pageIndex.value]);
const totalPages = computed(() => pages.value.length);

const nextPage = () => {
  if (pageIndex.value < totalPages.value - 1) pageIndex.value++;
};
const prevPage = () => {
  if (pageIndex.value > 0) pageIndex.value--;
};
</script>

<style scoped>
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.4s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}

@keyframes scaleUp {
  from {
    transform: scale(0.9);
    opacity: 0;
  }

  to {
    transform: scale(1);
    opacity: 1;
  }
}

.animate-scaleUp {
  animation: scaleUp 0.4s ease-out;
}

.page-turn-enter-active,
.page-turn-leave-active {
  transition: all 0.6s ease;
}

.page-turn-enter-from {
  transform: rotateY(-90deg);
  opacity: 0;
}

.page-turn-leave-to {
  transform: rotateY(90deg);
  opacity: 0;
}
</style>
