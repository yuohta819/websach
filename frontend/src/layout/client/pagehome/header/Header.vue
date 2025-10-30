<script setup>
import { onMounted, ref, onUnmounted } from 'vue'
import Login from '../account/Login.vue'
import Register from '../account/Register.vue'
import axios from "axios"
import { useToast } from 'vue-toastification'
import { eventBus } from '../../../../components/eventBus'
import { useRouter } from 'vue-router'
const check = ref(false)
const mode = ref('login')
const name = ref('Login')
const item = ref(null)
const role = ref('') // ✅ thêm biến role để hiển thị vai trò
const token = localStorage.getItem("token")
const toast = useToast()
const router = useRouter()
const balance = ref(0)
const currentPackage = ref("member");
function openLogin() {
  mode.value = 'login'
  check.value = true
}

function openRegister() {
  mode.value = 'register'
  check.value = true
}

function handleClose() {
  check.value = false
}

function handleOut() {
  localStorage.clear()
  location.reload()
}
onMounted(async () => {
  if (token) {
    const account = localStorage.getItem("name")
    const response = await axios.get(`${import.meta.env.VITE_API_URL_BACKEND}/token/only`, {
      headers: {
        Authorization: `Bearer ${token.trim()}`
      }
    })
    if (response.data === 'error') {
      localStorage.clear()
    }
    try {
      const res = await axios.get(`${import.meta.env.VITE_API_URL_BACKEND}/token/check`, {
        headers: {
          Authorization: `Bearer ${token.trim()}`
        },
        params: {
          account: account
        }

      })
      name.value = res.data.account
      item.value = res.data.account
      check.value = false
    } catch (error) {
      router.push("/")
      toast.warning(error.response.data.error)
      localStorage.clear()
    }
  } 
})
onMounted(async () => {
  if (token) {
    const res = await axios.get(`${import.meta.env.VITE_API_URL_BACKEND}/account/package`, {
      headers: {
        Authorization: `Bearer ${token.trim()}`
      },
    })

    const data = res.data
    console.log(data)
    let type = ''
    if (data.premium) type = 'Premium'
    else if (data.pro) type = 'Pro'
    else if (data.member) type = 'Member'
    else type = 'Guest'
    // ✅ Thêm giá trị tiền từ backend
    if (data.account && data.account.money != null) {
      balance.value = data.account.money
    }
    // Gộp 2 thông tin lại cho hiển thị
    role.value = `${type} | ${data.account.role}`
  }
})
onMounted(() => {
  eventBus.on("package-updated", (data) => {
    currentPackage.value = data.type

    // ✅ Cập nhật luôn vào role hiển thị
    const currentRole = role.value.split(" | ")[1] || "User"
    role.value = `${data.type.toUpperCase()} | ${currentRole}`
  })
})



onUnmounted(() => {
  eventBus.off("package-updated1");
});
</script>

<template>
  <header class="bg-[#efe9dd] text-[#5a4636] border-b border-[#d8cfc0]">
    <div class="max-w-7xl mx-auto flex flex-col md:flex-row justify-between items-center py-2 px-4 text-sm">
      <!-- Left text -->
      <p class="hidden md:block italic text-[#7b6a58]">
        “Welcome to our Publishing House — where stories come alive.”
      </p>

      <!-- Right section -->
      <div class="flex items-center gap-6 mt-1 md:mt-0">
        <!-- Login/Register -->
        <div v-if="item" class="flex items-center gap-2">
          <span class="text-[#7b6a58] font-medium">
            Hi, {{ name }}
            <span class="ml-2 text-xs bg-[#b85c38] text-white px-2 py-0.5 rounded">
              {{ role }}
            </span>
          </span>
          <!-- Balance -->
          <div class="hidden lg:flex items-center gap-2 text-[#7b6a58]">
            <i class="fa-solid fa-coins text-[#b85c38]"></i>
            <span class="font-semibold">
              {{ new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(balance) }}
            </span>
          </div>
          <button @click="handleOut" class="text-red-600 hover:text-red-700 text-sm font-semibold transition-colors">
            Logout
          </button>
        </div>

        <div v-else class="flex items-center gap-2 text-[#7b6a58]">
          <button @click="openLogin" class="hover:text-[#b85c38] font-semibold transition-colors">
            Login
          </button>
          <span>or</span>
          <button @click="openRegister" class="text-[#b85c38] font-semibold hover:underline transition-colors">
            Register
          </button>
        </div>




        <!-- Hotline -->
        <div class="hidden xl:flex items-center border-l border-[#d8cfc0] pl-4">
          <i class="fa-solid fa-phone text-[#b85c38]"></i>
          <span class="ml-2 text-sm">Free Call:</span>
          <span class="ml-1 font-semibold text-[#b85c38]">123-456-7890</span>
        </div>
      </div>
    </div>

    <!-- Modal -->
    <div v-if="check">
      <Login v-if="mode === 'login'" @close="handleClose" @switch="mode = 'register'" />
      <Register v-if="mode === 'register'" @close="handleClose" @switch="mode = 'login'" />
    </div>
  </header>
</template>
