<template>
  <div
    class="min-h-screen bg-gradient-to-b from-amber-50 via-white to-amber-100 py-10 px-6 flex justify-center"
  >
    <div class="w-full max-w-5xl bg-white shadow-xl rounded-2xl overflow-hidden">
      <!-- Header -->
      <div
        class="bg-gradient-to-r from-amber-100 to-amber-50 px-8 py-6 border-b border-amber-200 flex items-center justify-between"
      >
        <div>
          <h1 class="text-2xl font-semibold text-amber-800 flex items-center gap-2">
            ✏️ Sửa thông tin sách
          </h1>
          <p class="text-sm text-gray-500 mt-1">
            Dữ liệu được tải tự động từ API — bạn có thể chỉnh sửa và lưu lại thay đổi.
          </p>
        </div>
        <div class="text-sm text-gray-600">
          <span class="font-medium text-gray-800">ID:</span> {{ book.bookid || '—' }}
        </div>
      </div>

      <!-- Loading -->
      <div v-if="!loaded" class="py-20 text-center text-gray-500 animate-pulse">
        Đang tải dữ liệu sách...
      </div>

      <!-- Form -->
      <form v-else @submit.prevent="onSave" class="p-8 grid grid-cols-1 lg:grid-cols-2 gap-10">
        <!-- LEFT COLUMN -->
        <div class="space-y-6">
          <!-- Cover preview -->
          <div class="flex items-center gap-6">
            <div
              class="w-36 h-52 border rounded-lg overflow-hidden shadow-sm bg-gray-50 flex justify-center items-center"
            >
              <img
                v-if="coverPreview"
                :src="coverPreview"
                alt="Book cover"
                class="object-cover w-full h-full"
              />
              <div v-else class="text-gray-400 text-xs text-center px-2">Chưa có ảnh</div>
            </div>

            <div>
              <input
                type="file"
                accept="image/*"
                @change="onCoverSelected"
                class="block text-sm text-gray-600 cursor-pointer"
              />
              <button
                v-if="coverPreview"
                type="button"
                @click="removeCover"
                class="mt-3 px-3 py-1 text-sm border border-gray-300 rounded hover:bg-amber-50 transition"
              >
                Xóa ảnh
              </button>
            </div>
          </div>

          <!-- Basic info -->
          <div class="grid grid-cols-1 gap-4">
            <label class="block">
              <span class="font-medium text-gray-700 text-sm">Tiêu đề</span>
              <input
                v-model="book.name"
                type="text"
                class="mt-1 block w-full border rounded-lg px-3 py-2 focus:ring-2 focus:ring-amber-300"
              />
            </label>

            <label class="block">
              <span class="font-medium text-gray-700 text-sm">Tác giả</span>
              <input
                v-model="book.author"
                type="text"
                class="mt-1 block w-full border rounded-lg px-3 py-2 focus:ring-2 focus:ring-amber-300"
              />
            </label>

            <label class="block">
              <span class="font-medium text-gray-700 text-sm">Thể loại</span>
              <input
                v-model="book.category.name"
                type="text"
                class="mt-1 block w-full border rounded-lg px-3 py-2 bg-gray-50 focus:ring-2 focus:ring-amber-300"
                readonly
              />
            </label>

            <label class="block">
              <span class="font-medium text-gray-700 text-sm">Nhà xuất bản</span>
              <input
                v-model="book.detail.publisher"
                type="text"
                class="mt-1 block w-full border rounded-lg px-3 py-2 focus:ring-2 focus:ring-amber-300"
              />
            </label>

            <label class="block">
              <span class="font-medium text-gray-700 text-sm">Nơi & năm xuất bản</span>
              <input
                v-model="book.detail.publishing"
                type="text"
                class="mt-1 block w-full border rounded-lg px-3 py-2 focus:ring-2 focus:ring-amber-300"
              />
            </label>
          </div>
        </div>

        <!-- RIGHT COLUMN -->
        <div class="space-y-6">
          <label class="block">
            <span class="font-medium text-gray-700 text-sm">Mô tả ngắn</span>
            <textarea
              v-model="book.describe"
              rows="5"
              class="mt-1 block w-full border rounded-lg px-3 py-2 focus:ring-2 focus:ring-amber-300"
            ></textarea>
          </label>

          <label class="block">
            <span class="font-medium text-gray-700 text-sm">Giá bán (VNĐ)</span>
            <input
              v-model.number="book.price"
              type="number"
              min="0"
              class="mt-1 block w-full border rounded-lg px-3 py-2 focus:ring-2 focus:ring-amber-300"
            />
          </label>

          <!-- ⭐ Quyền truy cập sách -->
<div class="border rounded-lg p-4 bg-amber-50">
  <span class="font-medium text-gray-700 text-sm block mb-1">
    Quyền truy cập sách
  </span>
  <p class="text-xs text-gray-500 mb-3">
    Chọn quyền mà người dùng cần để đọc được sách này.
  </p>

  <select
    v-model="book.is_roles"
    class="block w-full border rounded-lg px-3 py-2 focus:ring-2 focus:ring-amber-300 bg-white"
  >
    <!-- Tự động hiển thị giá trị hiện có từ DB -->
    <option :value="book.is_roles" selected>
      {{ book.is_roles }}
    </option>

    <!-- Nếu hiện tại là 'member' thì hiển thị thêm 'pro' và 'premium' -->
    <option v-if="book.is_roles === 'member'" value="pro">pro</option>
    <option v-if="book.is_roles === 'member'" value="premium">premium</option>

    <!-- Nếu hiện tại là 'pro' thì hiển thị thêm 'member' và 'premium' -->
    <option v-if="book.is_roles === 'pro'" value="member">member</option>
    <option v-if="book.is_roles === 'pro'" value="premium">premium</option>

    <!-- Nếu hiện tại là 'premium' thì hiển thị thêm 'member' và 'pro' -->
    <option v-if="book.is_roles === 'premium'" value="member">member</option>
    <option v-if="book.is_roles === 'premium'" value="pro">pro</option>
  </select>
</div>


          <div class="flex items-center gap-3">
            <span class="font-medium text-gray-700 text-sm">Đánh giá:</span>
            <div class="flex items-center text-amber-500">
              <template v-for="i in 5" :key="i">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="currentColor"
                  viewBox="0 0 24 24"
                  :class="[i <= book.rate ? 'text-amber-500' : 'text-gray-300', 'w-5 h-5']"
                >
                  <path
                    d="M12 .587l3.668 7.431 8.2 1.193-5.934 5.785 1.402 8.173L12 18.896l-7.336 3.861 1.402-8.173L.132 9.211l8.2-1.193z"
                  />
                </svg>
              </template>
            </div>
          </div>

          <label class="block">
            <span class="font-medium text-gray-700 text-sm">Tổng số trang</span>
            <input
              v-model.number="book.detail.numberofpage"
              type="number"
              class="mt-1 block w-full border rounded-lg px-3 py-2 focus:ring-2 focus:ring-amber-300"
            />
          </label>

          <div class="flex gap-3 justify-end">
            <button
              type="button"
              @click="onReset"
              class="px-4 py-2 border rounded-lg hover:bg-amber-50 transition"
            >
              Đặt lại
            </button>
            <button
              type="submit"
              class="px-5 py-2 bg-amber-500 text-white rounded-lg hover:bg-amber-600 transition"
            >
              💾 Lưu thay đổi
            </button>
          </div>
        </div>

        <!-- CHAPTERS LIST -->
        <div class="lg:col-span-2 border-t border-gray-200 pt-6">
          <h3 class="text-lg font-semibold text-amber-700 mb-3">
            📚 Danh sách chương ({{ book.bookChapters.length }})
          </h3>

          <div class="max-h-72 overflow-y-auto pr-2 custom-scrollbar">
            <ul class="space-y-3">
              <li
                v-for="(ch, i) in book.bookChapters"
                :key="ch.chapterid"
                class="p-4 rounded-lg border bg-amber-50 hover:bg-amber-100 transition"
              >
                <label class="block font-medium text-gray-800 mb-1">
                  {{ i + 1 }}. Tên chương
                </label>
                <input
                  v-model="ch.chapter_title"
                  type="text"
                  class="w-full border rounded-lg px-3 py-2 mb-2 focus:ring-2 focus:ring-amber-300"
                />

                <label class="block font-medium text-gray-700 mb-1">Nội dung</label>
                <input
                  v-model="ch.chapter_content"
                  rows="3"
                  class="w-full border rounded-lg px-3 py-2 focus:ring-2 focus:ring-amber-300"
                />
              </li>
            </ul>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, watch } from 'vue'
import axios from 'axios'
import { useRoute } from 'vue-router'

const route = useRoute()

const book = reactive({
  bookid: '',
  name: '',
  author: '',
  describe: '',
  rate: 0,
  price: 0,
  img: '',
  is_roles: 'pro', // mặc định
  category: { categoryid: '', name: '' },
  detail: {
    publisher: '',
    publishing: '',
    numberofpage: 0,
  },
  bookChapters: [],
})

const coverFile = ref(null)
const coverPreview = ref('')
const loaded = ref(false)

onMounted(async () => {
  try {
    const id = route.params.id
    const response = await axios.get(`http://localhost:8080/book/edit/${id}`)
    console.log('📘 Dữ liệu API:', response.data)

    Object.assign(book, response.data)
    coverPreview.value = response.data.img || ''
    loaded.value = true
  } catch (err) {
    console.error('❌ Không thể tải sách:', err)
    alert('Lỗi khi tải dữ liệu sách!')
  }
})

function onCoverSelected(e) {
  const f = e.target.files?.[0]
  if (!f) return
  coverFile.value = f
  coverPreview.value = URL.createObjectURL(f)
}

function removeCover() {
  coverFile.value = null
  coverPreview.value = ''
}

async function onSave() {
  try {
    const formData = new FormData()
    console.log(book.is_roles)
    formData.append('name', book.name)
    formData.append('author', book.author)
    formData.append('describe', book.describe)
    formData.append('price', book.price)
    formData.append('rate', book.rate)
    formData.append('publisher', book.detail.publisher)
    formData.append('publishing', book.detail.publishing)
    formData.append('numberOfPages', book.detail.numberofpage)

    formData.append('categoryid', book.category.categoryid)
    formData.append('is_roles', book.is_roles) // "pro" hoặc "premium"

    if (coverFile.value) formData.append('image', coverFile.value)

    for (let [k, v] of formData.entries()) {
      console.log('🟡', k, ':', v)
    }

    const res = await axios.post(`http://localhost:8080/book/update/${book.bookid}`, formData, {
      headers: { 'Content-Type': 'multipart/form-data' },
    })

    console.log('✅ PUT response:', res.data)
    alert('Đã lưu thay đổi sách!')
  } catch (err) {
    console.error('❌ Lỗi khi cập nhật:', err)
    alert('Cập nhật thất bại!')
  }
}

async function onReset() {
  loaded.value = false
  await onMounted()
}

watch(coverPreview, (v, old) => {
  if (old && old.startsWith('blob:')) URL.revokeObjectURL(old)
})
</script>

<style scoped>
.custom-scrollbar::-webkit-scrollbar {
  width: 8px;
}
.custom-scrollbar::-webkit-scrollbar-track {
  background: #fef3c7;
  border-radius: 8px;
}
.custom-scrollbar::-webkit-scrollbar-thumb {
  background: #f59e0b;
  border-radius: 8px;
}
.custom-scrollbar::-webkit-scrollbar-thumb:hover {
  background: #d97706;
}
</style>
