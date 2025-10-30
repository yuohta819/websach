<template>
  <div class="min-h-screen bg-gradient-to-br from-amber-50 via-white to-amber-100 py-10 px-6">
    <div class="max-w-5xl mx-auto bg-white shadow-lg rounded-2xl p-8 border border-amber-100">
      <h1 class="text-3xl font-semibold text-amber-700 mb-8 text-center">📚 Thêm Sách Mới</h1>

      <!-- BOOK INFORMATION -->
      <section class="mb-10">
        <h2 class="text-xl font-semibold text-amber-600 mb-4">📘 1️⃣ Thông tin sách</h2>
        <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
          <div>
            <label class="block text-sm font-medium mb-1">Tên sách</label>
            <input v-model="book.name" class="input" placeholder="Nhập tên sách" />
          </div>

          <div>
            <label class="block text-sm font-medium mb-1">Tác giả</label>
            <input v-model="book.author" class="input" placeholder="Tên tác giả" />
          </div>

          <div>
            <label class="block text-sm font-medium mb-1">Giá (VNĐ)</label>
            <input v-model.number="book.price" type="number" class="input" placeholder="Giá sách" />
          </div>

          <div>
            <label class="block text-sm font-medium mb-1">Thể loại</label>
            <select v-model.number="categoryId" class="input">
              <option disabled value="">-- Chọn thể loại --</option>
              <option v-for="cat in categories" :key="cat.categoryid" :value="cat.categoryid">
                {{ cat.name }}
              </option>
            </select>
          </div>

          <!-- 🔹 NEW: Chọn quyền truy cập -->
          <div>
            <label class="block text-sm font-medium mb-1">Quyền truy cập</label>
            <select v-model="book.roles" class="input">
              <option disabled value="">-- Chọn quyền truy cập --</option>
              <option value="member">Member (Miễn phí)</option>
              <option value="pro">Pro (Cao cấp)</option>
              <option value="premium">Premium (VIP)</option>
            </select>
          </div>

          <!-- Upload Ảnh -->
          <div class="md:col-span-2">
            <label class="block text-sm font-medium mb-1">Ảnh bìa</label>
            <div class="flex items-center gap-6">
              <div>
                <input type="file" @change="handleFileChange" class="input" accept="image/*" />
                <p class="text-xs text-gray-500 mt-1">Chọn ảnh mới nếu muốn thay đổi</p>
              </div>

              <div class="flex items-center gap-4">
                <img v-if="previewUrl || book.img" :src="previewUrl || book.img" alt="Preview"
                  class="w-32 h-44 object-cover rounded-lg border border-amber-200 shadow-sm" />
              </div>
            </div>
          </div>

          <div class="md:col-span-2">
            <label class="block text-sm font-medium mb-1">Mô tả</label>
            <textarea v-model="book.describe" class="input h-24" placeholder="Mô tả ngắn về sách"></textarea>
          </div>
        </div>
      </section>

      <!-- DETAIL INFORMATION -->
      <section class="mb-10">
        <h2 class="text-xl font-semibold text-amber-600 mb-4">📖 2️⃣ Chi tiết xuất bản</h2>
        <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
          <div>
            <label class="block text-sm font-medium mb-1">Nhà xuất bản</label>
            <input v-model="detail.publisher" class="input" placeholder="Tên NXB" />
          </div>
          <div>
            <label class="block text-sm font-medium mb-1">Năm xuất bản</label>
            <input v-model.number="detail.publishing" type="number" class="input" placeholder="2025" />
          </div>
          <div>
            <label class="block text-sm font-medium mb-1">Số trang</label>
            <input v-model.number="detail.numberofpage" type="number" class="input" placeholder="350" />
          </div>
          <div>
            <label class="block text-sm font-medium mb-1">ISBN</label>
            <input v-model="detail.isbn" class="input" placeholder="ISBN..." />
          </div>
          <div class="md:col-span-2">
            <label class="block text-sm font-medium mb-1">Định dạng</label>
            <input v-model="detail.format" class="input" placeholder="Bìa mềm / ebook / ..." />
          </div>
        </div>
      </section>

      <!-- CHAPTER INFORMATION -->
      <section>
        <h2 class="text-xl font-semibold text-amber-600 mb-4">📚 3️⃣ Danh sách chương</h2>
        <div v-for="(ch, index) in chapters" :key="index"
          class="bg-amber-50 rounded-xl p-4 mb-4 border border-amber-200">
          <div class="flex justify-between items-center mb-3">
            <h3 class="font-medium text-amber-700">Chương {{ index + 1 }}</h3>
            <button @click="removeChapter(index)" class="text-red-500 hover:underline text-sm">Xóa</button>
          </div>
          <input v-model="ch.chapter_title" class="input mb-3" placeholder="Tiêu đề chương" />
          <textarea v-model="ch.chapter_content" class="input h-24" placeholder="Nội dung chương"></textarea>
        </div>

        <button @click="addChapter" class="mt-2 text-amber-600 font-medium hover:text-amber-700">
          + Thêm chương
        </button>
      </section>

      <!-- SUBMIT BUTTON -->
      <div class="text-center mt-10">
        <button @click="createBook"
          class="bg-amber-600 text-white px-10 py-3 rounded-xl hover:bg-amber-700 transition shadow-md">
          💾 Lưu thay đổi
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

// --- Dữ liệu sách ---
const book = ref({
  name: '',
  author: '',
  rate: 0,
  price: null,
  describe: '',
  roles: '' // ✅ quyền truy cập
})

// --- Ảnh & preview ---
const selectedFile = ref(null)
const previewUrl = ref(null)

const handleFileChange = (event) => {
  selectedFile.value = event.target.files[0]
  if (selectedFile.value) {
    previewUrl.value = URL.createObjectURL(selectedFile.value)
  }
}

// --- Chi tiết sách ---
const detail = ref({
  publisher: '',
  publishing: null,
  numberofpage: null,
  isbn: '',
  format: ''
})

// --- Thể loại và chương ---
const categories = ref([])
const categoryId = ref('')
const chapters = ref([{ chapter_title: '', chapter_content: '' }])

const addChapter = () => chapters.value.push({ chapter_title: '', chapter_content: '' })
const removeChapter = (index) => chapters.value.splice(index, 1)

// --- Gọi API lấy thể loại ---
onMounted(async () => {
  try {
    const res = await axios.get('http://localhost:8080/category/call')
    categories.value = res.data
  } catch (error) {
    console.error('❌ Lỗi khi tải thể loại:', error)
  }
})

// --- Gửi dữ liệu lên backend ---
const createBook = async () => {
  try {
    // ✅ Gộp toàn bộ dữ liệu thành 1 object "bookData"
    const bookData = {
      name: book.value.name,
      author: book.value.author,
      rate: book.value.rate,
      price: book.value.price,
      describe: book.value.describe,
      is_roles: book.value.roles,
      detail: detail.value,
      category: { categoryid: categoryId.value },
      bookChapters: chapters.value
    }

    // ✅ Tạo FormData đúng chuẩn
    const formData = new FormData()
    formData.append('data', JSON.stringify(bookData)) // 👈 Backend nhận @RequestPart("data")
    if (selectedFile.value) {
      formData.append('file', selectedFile.value) // 👈 Backend nhận @RequestPart("file")
    }

    // ✅ Log kiểm tra trước khi gửi
    for (let [key, value] of formData.entries()) {
      console.log(key, value)
    }

    // ✅ Gửi request
    const response = await axios.post('http://localhost:8080/book/create', formData, {
      headers: { 'Content-Type': 'multipart/form-data' }
    })

    alert('✅ Thêm sách mới thành công!')
    console.log('📚 Book Created:', response.data)
  } catch (error) {
    console.error('❌ Lỗi khi thêm sách:', error)
    alert('❌ Thêm sách thất bại!')
  }
}
</script>


<style scoped></style>
