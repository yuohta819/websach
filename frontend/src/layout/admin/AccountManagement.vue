<template>
  <div class="min-h-screen bg-gradient-to-br from-amber-50 via-white to-amber-100 p-6">
    <div class="max-w-6xl mx-auto bg-white shadow-xl rounded-2xl p-6 border border-amber-100">
      <!-- Header -->
      <h1 class="text-3xl font-extrabold text-amber-800 mb-8 text-center">
        📚 Quản lý tài khoản người dùng
      </h1>

      <!-- Search -->
      <div class="flex flex-col md:flex-row md:items-center md:justify-between mb-6 gap-4">
        <input v-model="searchQuery" type="text" placeholder="🔍 Tìm kiếm tài khoản..."
          class="w-full md:w-1/2 p-3 border border-amber-200 rounded-xl focus:ring-2 focus:ring-amber-400 outline-none" />

        <select v-model="filterRole"
          class="w-full md:w-1/4 p-3 border border-amber-200 rounded-xl focus:ring-2 focus:ring-amber-400 outline-none">
          <option value="">Tất cả vai trò</option>
          <option value="ADMIN">ADMIN</option>
          <option value="CLIENT">CLIENT</option>
        </select>
      </div>

      <!-- Table -->
      <div class="overflow-x-auto">
        <table class="min-w-full border-collapse rounded-xl overflow-hidden">
          <thead>
            <tr class="bg-amber-100 text-amber-800">
              <th class="p-3 text-left">#</th>
              <th class="p-3 text-left">Tài khoản</th>
              <th class="p-3 text-left">Email</th>
              <th class="p-3 text-left">Vai trò</th>
              <th class="p-3 text-left">Ngày tạo</th>
              <th class="p-3 text-left">Số dư (VNĐ)</th>
              <th class="p-3 text-left">Trạng thái</th>
              <th class="p-3 text-center">Thao tác</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(acc, index) in filteredAccounts" :key="acc.accountid"
              class="hover:bg-amber-50 transition duration-150">
              <td class="p-3">{{ index + 1 }}</td>
              <td class="p-3 font-semibold text-gray-800">{{ acc.account }}</td>
              <td class="p-3 text-gray-600">{{ acc.email }}</td>
              <td>
                <span :class="acc.role === 'ADMIN' ? 'bg-red-100 text-red-600' : 'bg-green-100 text-green-600'"
                  class="px-3 py-1 rounded-full text-sm font-semibold">
                  {{ acc.role }}
                </span>
              </td>
              <td class="p-3 text-gray-500">{{ formatDate(acc.created_at) }}</td>
              <td class="p-3 text-right font-semibold text-amber-700">
                {{ acc.money.toLocaleString() }}
              </td>
              <td class="p-3">
                <span :class="acc.is_deleted ? 'bg-gray-300 text-gray-600' : 'bg-blue-100 text-blue-600'"
                  class="px-3 py-1 rounded-full text-sm font-semibold">
                  {{ acc.is_deleted ? "Đã xóa" : "Hoạt động" }}
                </span>
              </td>
              <td class="p-3 text-center">
                <button @click="toggleStatus(acc)"
                  class="px-3 py-1 bg-amber-500 hover:bg-amber-600 text-white rounded-lg text-sm font-semibold transition">
                  {{ acc.is_deleted ? "Khôi phục" : "Xóa" }}
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <!-- Footer -->
      <p class="text-sm text-gray-500 mt-6 text-center italic">
        📖 Trang quản lý tài khoản - Chủ đề Sách & Tri thức
      </p>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from "vue"
import axios from "axios"
import { useToast } from "vue-toastification" // nếu bạn có cài toast

const backend = import.meta.env.VITE_API_URL_BACKEND // ví dụ: https://backend.onrender.com
const toast = useToast()

// Biến trạng thái
const accounts = ref([])
const searchQuery = ref("")
const filterRole = ref("")
const isLoading = ref(false)
const error = ref(null)

// 🔹 Hàm lấy danh sách tài khoản từ backend
const fetchAccounts = async () => {
  try {
    isLoading.value = true
    const res = await axios.get(`${backend}/account/call`)
    accounts.value = res.data // backend trả JSON danh sách tài khoản
  } catch (err) {
    console.error(err)
    error.value = "Không thể tải danh sách tài khoản!"
    toast.error("Lỗi khi tải dữ liệu!") // nếu bạn có dùng vue-toastification
  } finally {
    isLoading.value = false
  }
}

// 🔹 Gọi API khi component được mount
onMounted(() => {
  fetchAccounts()
})

// 🔹 Lọc tài khoản theo vai trò + từ khóa
const filteredAccounts = computed(() => {
  return accounts.value.filter(acc => {
    const matchRole = !filterRole.value || acc.role === filterRole.value
    const matchSearch = acc.account.toLowerCase().includes(searchQuery.value.toLowerCase())
    return matchRole && matchSearch
  })
})

// 🔹 Chuyển đổi trạng thái (xóa / khôi phục)
const toggleStatus = async (acc) => {
  try {
    const newStatus = !acc.is_deleted
    await axios.post(`${backend}/account/delete/${acc.accountid}`, { is_deleted: newStatus })
    acc.is_deleted = newStatus
    toast.success(newStatus ? "Đã xóa tài khoản!" : "Đã khôi phục tài khoản!")
  } catch (err) {
    console.error(err)
    toast.error("Không thể cập nhật trạng thái!")
  }
}

// 🔹 Định dạng ngày
const formatDate = (date) => new Date(date).toLocaleDateString("vi-VN")
</script>
