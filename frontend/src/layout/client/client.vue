<script setup>
import { ref, reactive, onMounted, onBeforeUnmount, onUnmounted } from 'vue'
import axios from 'axios'
import { useToast } from 'vue-toastification'
import { eventBus } from '../../components/eventBus'
import Header from './pagehome/header/Header.vue' 
import Taskbar from './pagehome/header/Taskbar.vue'
import Information from './PageHome/Box/Information.vue'

// ===== Scroll header effect =====
const isScrolled = ref(false)
function handleScroll() {
  isScrolled.value = window.scrollY > 20
}
onMounted(() => window.addEventListener('scroll', handleScroll))
onBeforeUnmount(() => window.removeEventListener('scroll', handleScroll))

// ===== Toast setup =====
const toastt = useToast()

// ===== Package data =====
const token = localStorage.getItem("token")
const id = ref(0)
const expiredAt = ref(null)
const visiblePkg = ref(null)
const userType = ref('')

// Countdown
const countdown = reactive({
  days: 0,
  hours: 0,
  minutes: 0,
  seconds: 0
})
let timer = null

// ===== API: Lấy package khi vào trang =====
onMounted(async () => {
  if (!token) return

  try {
    const res = await axios.get(`${import.meta.env.VITE_API_URL_BACKEND}/account/package`, {
      headers: { Authorization: `Bearer ${token.trim()}` },
    })

    const data = res.data
    id.value = data.account.accountid
    expiredAt.value = new Date(data.expired_date)

    // Gửi trạng thái package lên Header (qua eventBus)
    eventBus.emit("package-updated1", { type: userType.value })
    console.log(expiredAt.value)
    if (expiredAt.value) startCountdown()
  } catch (error) {
    console.error('❌ Lỗi lấy gói:', error)
  }
})

// ===== Countdown =====
async function updateCountdown() {
  if (!expiredAt.value) return
  const now = new Date()
  const diff = expiredAt.value - now

  if (diff <= 0) {
    console.log(diff)
    clearInterval(timer)
    countdown.days = countdown.hours = countdown.minutes = countdown.seconds = 0
    await axios.get(`${import.meta.env.VITE_API_URL_BACKEND}/account/package/change`, {
      params: { param: id.value }
    })
    eventBus.emit("package-updated", { type: "member" })
    return
  }

  countdown.days = Math.floor(diff / (1000 * 60 * 60 * 24))
  countdown.hours = Math.floor((diff / (1000 * 60 * 60)) % 24)
  countdown.minutes = Math.floor((diff / (1000 * 60)) % 60)
  countdown.seconds = Math.floor((diff / 1000) % 60)
}

function startCountdown() {
  updateCountdown()
  timer = setInterval(updateCountdown, 10000)
}

onUnmounted(() => clearInterval(timer))
</script>

<template>
  <Header />
  <Taskbar />
  <router-view />
  <Information />
</template>
