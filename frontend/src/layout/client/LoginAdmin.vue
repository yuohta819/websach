<template>
  <div class="min-h-screen bg-gradient-to-br from-amber-50 via-white to-amber-100 flex items-center justify-center p-6">
    <div class="w-full max-w-md bg-white rounded-2xl shadow-2xl border border-amber-100 relative overflow-hidden p-8">

      <!-- Hiệu ứng nền -->
      <div class="absolute -top-12 -left-12 w-40 h-40 bg-amber-100 rounded-full opacity-40 blur-2xl"></div>
      <div class="absolute -bottom-12 -right-12 w-40 h-40 bg-amber-200 rounded-full opacity-30 blur-2xl"></div>

      <!-- Tiêu đề -->
      <h1 class="text-3xl font-extrabold text-center text-amber-800 mb-8">
        📚 Đăng nhập Quản trị viên
      </h1>

      <!-- Form đăng nhập -->
      <form @submit.prevent="handleLogin" class="space-y-5 relative z-10">
        <div>
          <label class="block text-amber-800 font-semibold mb-1">Tài khoản</label>
          <input
            v-model="form.account"
            type="text"
            placeholder="Nhập tài khoản admin..."
            class="w-full p-3 border border-amber-200 rounded-xl focus:ring-2 focus:ring-amber-400 outline-none"
          />
        </div>

        <div>
          <label class="block text-amber-800 font-semibold mb-1">Mật khẩu</label>
          <input
            v-model="form.password"
            type="password"
            placeholder="Nhập mật khẩu..."
            class="w-full p-3 border border-amber-200 rounded-xl focus:ring-2 focus:ring-amber-400 outline-none"
          />
        </div>

        <!-- Nút đăng nhập -->
        <button
          type="submit"
          class="w-full py-3 bg-amber-600 hover:bg-amber-700 text-white font-bold rounded-xl shadow-md transition duration-300"
          :disabled="loading"
        >
          <span v-if="!loading">📖 Đăng nhập</span>
          <span v-else>⏳ Đang kiểm tra...</span>
        </button>
      </form>

      <!-- Thông báo lỗi -->
      <div
        v-if="errorMessage"
        class="mt-6 p-3 bg-red-100 text-red-700 rounded-lg text-center font-semibold animate-fade-in"
      >
        {{ errorMessage }}
      </div>

      <!-- Thông báo thành công -->
      <div
        v-if="successMessage"
        class="mt-6 p-3 bg-green-100 text-green-700 rounded-lg text-center font-semibold animate-fade-in"
      >
        {{ successMessage }}
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue"
import axios from "axios"
import { useToast } from "vue-toastification"
import { useRouter } from "vue-router"

const toast = useToast()
const router = useRouter()
const backend = import.meta.env.VITE_API_URL_BACKEND

const form = ref({
  account: "",
  password: ""
})
const loading = ref(false)
const errorMessage = ref("")
const successMessage = ref("")

const handleLogin = async () => {
  errorMessage.value = ""
  successMessage.value = ""
  loading.value = true

  try {
    const res = await axios.post(`${backend}/account/admin/login`, {
    //   account: form.value.account,
    //   password: form.value.password
      account: "yuohta@gmail.com",
      password: "12345"
    })

    // Nếu response thành công (status 200)
    const data = res.data
    if (data.token) {
      // ✅ Lưu token & thông tin vào localStorage
      localStorage.setItem("adminToken", data.token)
      localStorage.setItem("adminId", data.accountid)
      localStorage.setItem("adminName", data.account)
      localStorage.setItem("adminRole", data.role)

      successMessage.value = "✅ Đăng nhập thành công!"
      toast.success("Chào mừng trở lại, quản trị viên!")
      // Điều hướng sau 1.2s
      setTimeout(() => router.push("/admin/library"), 1200)
    } else {
      errorMessage.value = "Phản hồi không hợp lệ từ server!"
      toast.error("Server không trả về token.")
    }

  } catch (err) {
    if (err.response && err.response.status === 401) {
      const type = err.response.data
      if (type === "account") {
        errorMessage.value = "❌ Tài khoản không tồn tại!"
        toast.error("Tài khoản không tồn tại!")
      } else if (type === "password") {
        errorMessage.value = "⚠️ Mật khẩu không chính xác!"
        toast.warning("Sai mật khẩu!")
      } else {
        errorMessage.value = "🚫 Không có quyền truy cập!"
        toast.error("Từ chối truy cập!")
      }
    } else {
      console.error(err)
      errorMessage.value = "Lỗi máy chủ!"
      toast.error("Không thể kết nối server!")
    }
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
@keyframes fade-in {
  from { opacity: 0; transform: translateY(8px); }
  to { opacity: 1; transform: translateY(0); }
}
.animate-fade-in {
  animation: fade-in 0.4s ease-in-out forwards;
}
</style>
