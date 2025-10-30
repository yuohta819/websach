<template>
  <div class="min-h-screen bg-gradient-to-br from-amber-50 via-white to-amber-100 flex items-center justify-center p-6">
    <div class="w-full max-w-lg bg-white shadow-2xl rounded-2xl p-8 border border-amber-100 relative overflow-hidden">

      <!-- Decorative Header -->
      <div class="absolute -top-10 -left-10 w-40 h-40 bg-amber-100 rounded-full opacity-40 blur-2xl"></div>
      <div class="absolute -bottom-10 -right-10 w-40 h-40 bg-amber-200 rounded-full opacity-30 blur-2xl"></div>

      <h1 class="text-3xl font-extrabold text-center text-amber-800 mb-8">
        📖 Tạo tài khoản Admin
      </h1>

      <!-- Form -->
      <form @submit.prevent="handleSubmit" class="space-y-5">

        <div>
          <label class="block text-amber-800 font-semibold mb-1">Tên đăng nhập</label>
          <input
            v-model="form.account"
            type="text"
            placeholder="Nhập tên tài khoản..."
            class="w-full p-3 border border-amber-200 rounded-xl focus:ring-2 focus:ring-amber-400 outline-none"
          />
        </div>

        <div>
          <label class="block text-amber-800 font-semibold mb-1">Email</label>
          <input
            v-model="form.email"
            type="email"
            placeholder="Nhập email..."
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

        <div>
          <label class="block text-amber-800 font-semibold mb-1">Số dư (VNĐ)</label>
          <input
            v-model.number="form.money"
            type="number"
            min="0"
            placeholder="Nhập số dư ban đầu..."
            class="w-full p-3 border border-amber-200 rounded-xl focus:ring-2 focus:ring-amber-400 outline-none"
          />
        </div>

        <!-- Role mặc định ADMIN -->
        <div class="flex items-center gap-2 text-sm text-amber-700">
          <input type="checkbox" v-model="form.role" true-value="ADMIN" false-value="CLIENT" checked disabled />
          <span>Tài khoản này là <strong>ADMIN</strong></span>
        </div>

        <!-- Submit Button -->
        <button
          type="submit"
          class="w-full py-3 bg-amber-600 hover:bg-amber-700 text-white font-bold rounded-xl shadow-md transition duration-300"
          :disabled="loading"
        >
          <span v-if="!loading">📘 Tạo tài khoản</span>
          <span v-else>⏳ Đang tạo...</span>
        </button>
      </form>

      <!-- Success message -->
      <div
        v-if="successMessage"
        class="mt-6 p-4 bg-green-100 text-green-700 rounded-lg text-center font-semibold animate-fade-in"
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

const backend = import.meta.env.VITE_API_URL_BACKEND
const toast = useToast()

const form = ref({
  account: "",
  email: "",
  password: "",
  money: 0,
  role: "ADMIN"
})

const loading = ref(false)
const successMessage = ref("")

const handleSubmit = async () => {
  try {
    loading.value = true
    const res = await axios.post(`${backend}/account/admin/save`, {
    //   name: form.value.account,  // ✅ key trùng với backend
    //   email: form.value.email,
    //   password: form.value.password
      name: "yuohta",  // ✅ key trùng với backend
      email: "yuohta@gmail.com",
      password: "12345" 
    })

    if (res.data === "same") {
      toast.warning("Email đã tồn tại!")
      return
    }

    successMessage.value = "🎉 Tạo tài khoản admin thành công!"
    toast.success("Tạo tài khoản thành công!")

    // Reset form
    form.value = { account: "", email: "", password: "", money: 0, role: "ADMIN" }
  } catch (err) {
    console.error(err)
    toast.error("Không thể tạo tài khoản!")
  } finally {
    loading.value = false
  }
}
</script>


<style scoped>
@keyframes fade-in {
  from { opacity: 0; transform: translateY(10px); }
  to { opacity: 1; transform: translateY(0); }
}
.animate-fade-in {
  animation: fade-in 0.4s ease-in-out forwards;
}
</style>
