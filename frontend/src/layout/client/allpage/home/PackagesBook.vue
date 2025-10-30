<template>
  <div class="min-h-screen bg-gradient-to-br from-amber-50 via-white to-amber-100 p-6">
    <div class="max-w-6xl mx-auto">
      <!-- Header -->
      <header class="text-center mb-10">
        <h1 class="text-3xl md:text-4xl font-extrabold text-amber-800">
          Membership Packages — BookClub VIP
        </h1>
        <p class="mt-2 text-amber-600">
          Choose your plan and enjoy VIP benefits: early access, discounts, unlimited bookmarks.
        </p>
      </header>

      <!-- Packages Grid -->
      <div class="grid grid-cols-1 md:grid-cols-3 gap-6 justify-center place-items-cente">
        <div v-for="pkg in packages" :key="pkg.id"
          class="relative flex flex-col w-full md:w-80 justify-between bg-white rounded-2xl shadow-md p-6 border border-amber-100 hover:shadow-xl hover:-translate-y-1 transition-transform">

          <div>
            <!-- VIP Tag -->
            <div v-if="pkg.purchased"
              class="absolute top-3 right-3 bg-amber-500 text-white px-3 py-1 rounded-full text-sm font-semibold">
              VIP
            </div>

            <!-- Icon + Info -->
            <div class="flex items-center gap-4">
              <div class="p-3 rounded-lg bg-amber-50">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-8 w-8 text-amber-600" fill="none" viewBox="0 0 24 24"
                  stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5"
                    d="M12 6v6l4 2M3 7a4 4 0 014-4h10a4 4 0 014 4v10a4 4 0 01-4 4H7a4 4 0 01-4-4V7z" />
                </svg>
              </div>
              <div>
                <h3 class="text-lg font-bold text-amber-800">{{ pkg.title }}</h3>
                <p class="text-sm text-amber-500">{{ pkg.subtitle }}</p>
              </div>
            </div>

            <!-- Price -->
            <div class="mt-4">
              <p class="text-3xl font-extrabold text-amber-700">{{ formatPrice(pkg.price) }}</p>
              <p class="text-sm text-amber-500 mt-1">{{ pkg.billing }}</p>
            </div>

            <!-- Benefits -->
            <ul class="mt-4 space-y-2 text-amber-700">
              <li v-for="(benefit, i) in pkg.benefits" :key="i" class="flex items-start gap-2">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mt-0.5 text-amber-500 flex-shrink-0"
                  viewBox="0 0 20 20" fill="currentColor">
                  <path fill-rule="evenodd"
                    d="M16.707 5.293a1 1 0 00-1.414 0L8 12.586 4.707 9.293a1 1 0 00-1.414 1.414l4 4a1 1 0 001.414 0l8-8a1 1 0 000-1.414z"
                    clip-rule="evenodd" />
                </svg>
                <span class="text-sm">{{ benefit }}</span>
              </li>
            </ul>
          </div>

          <!-- Buttons -->
          <div class="mt-6 flex gap-3">
            <button :class="[
              'w-1/2 py-2 rounded-lg font-semibold shadow-sm transition text-center',
              pkg.purchased
                ? 'bg-amber-200 text-amber-800 cursor-not-allowed'
                : 'bg-amber-600 text-white hover:scale-[1.02]'
            ]" @click="purchase(pkg, pkg.title)" :disabled="pkg.purchased">
              {{ pkg.purchased ? 'Purchased' : 'Buy Now' }}
            </button>

            <button class="w-1/2 py-2 rounded-lg border border-amber-200 text-amber-700 hover:bg-amber-50"
              @click="openDetails(pkg)">
              Details
            </button>
          </div>

          <!-- Countdown chỉ hiển thị trong gói cao nhất -->
          <div v-if="expiredAt && pkg.purchased && pkg.title === highestPackage"
            class="absolute top-3 left-3 bg-amber-50 border border-amber-200 text-amber-700 text-sm font-semibold rounded-lg px-3 py-1 shadow-sm">
            ⏳ {{ countdown.days }}d {{ countdown.hours }}h {{ countdown.minutes }}m {{ countdown.seconds }}s
          </div>

          <!-- Recommended Tag -->
          <div v-if="pkg.recommended" class="mt-3 text-center text-amber-700 text-sm font-medium italic">
            Recommended for you
          </div>
        </div>
      </div>


      <!-- Modal -->
      <transition name="fade">
        <div v-if="showModal"
          class="fixed inset-0 z-40 flex items-center justify-center bg-black/40 backdrop-blur-sm p-4">
          <div class="bg-white rounded-2xl shadow-xl max-w-lg w-full p-6 relative">
            <button @click="showModal = false"
              class="absolute top-3 right-3 text-amber-400 hover:text-amber-600 text-xl">
              ✕
            </button>

            <h2 class="text-2xl font-bold text-amber-800">{{ activePkg.title }}</h2>
            <p class="text-amber-500 mt-1">{{ activePkg.subtitle }}</p>

            <div class="mt-4 text-amber-700">
              <p class="text-lg font-semibold">Benefits:</p>
              <ul class="list-disc list-inside mt-2 space-y-1">
                <li v-for="(b, i) in activePkg.benefits" :key="i">{{ b }}</li>
              </ul>
              <p class="mt-4">
                Price:
                <span class="font-bold text-amber-700">{{ formatPrice(activePkg.price) }}</span>
                —
                <span class="text-sm text-amber-500">{{ activePkg.billing }}</span>
              </p>
            </div>

            <div class="mt-6 flex justify-end gap-3">
              <button class="px-4 py-2 rounded-lg border border-amber-200 text-amber-700 hover:bg-amber-50"
                @click="showModal = false">
                Close
              </button>
              <button :disabled="activePkg.purchased" @click="purchase(activePkg, activePkg.title)" :class="[
                'px-4 py-2 rounded-lg font-semibold shadow-sm transition',
                activePkg.purchased
                  ? 'bg-amber-200 text-amber-800 cursor-not-allowed'
                  : 'bg-amber-600 text-white hover:scale-[1.03]'
              ]">
                {{ activePkg.purchased ? 'Purchased' : 'Buy this plan' }}
              </button>

            </div>
          </div>
        </div>
      </transition>

      <!-- Toast -->
      <transition name="fade">
        <div v-if="toast.show" class="fixed right-6 bottom-6 bg-amber-700 text-white px-4 py-2 rounded-lg shadow-lg">
          {{ toast.message }}
        </div>
      </transition>
    </div>
  </div>

</template>

<script setup>
import axios from 'axios'
import { reactive, ref, onMounted, onUnmounted } from 'vue'
import { useToast } from 'vue-toastification'
import { eventBus } from '../../../../components/eventBus'
const packages = reactive([
  {
    id: 1,
    title: 'Member',
    subtitle: 'For new readers',
    price: 0,
    billing: 'Free — Limited access',
    benefits: ['Read 5 books/month', 'Bookmark up to 10 items', 'Join the community'],
    purchased: false,
    recommended: false,
  },
  {
    id: 2,
    title: 'Pro',
    subtitle: 'Read more, save more',
    price: 49900,
    billing: 'Monthly',
    benefits: [
      'Unlimited reading',
      'Unlimited bookmarks',
      '10% off paid books',
      '48-hour early access',
    ],
    purchased: false,
    recommended: false,
  },
  {
    id: 3,
    title: 'Premium',
    subtitle: 'Full VIP experience',
    price: 129900,
    billing: 'Monthly — Includes gifts',
    benefits: [
      'All Pro benefits',
      'Monthly gifts',
      'Author workshops',
      'VIP badge on profile',
    ],
    purchased: false,
    recommended: false,
  },
])

const toastt = useToast()
const showModal = ref(false)
const activePkg = ref(packages[0])
const toast = reactive({ show: false, message: '' })
const userType = ref('')
const token = localStorage.getItem("token")
const id = ref(0)
const highestPackage = ref('')
// ⏳ Countdown data
const countdown = reactive({
  days: 0,
  hours: 0,
  minutes: 0,
  seconds: 0
})
let timer = null
const expiredAt = ref(null)

// Khi component mount, lấy thông tin gói từ backend
onMounted(async () => {
  if (token) {

    const res = await axios.get(`${import.meta.env.VITE_API_URL_BACKEND}/account/package`, {
      headers: { Authorization: `Bearer ${token.trim()}` },
    })
    const data = res.data
    id.value = data.account.accountid

    expiredAt.value = new Date(data.expired_date)

    if (data.member) packages[0].purchased = true
    if (data.pro) packages[1].purchased = true
    if (data.premium) packages[2].purchased = true
    if (data.premium) {
      highestPackage.value = 'Premium'
    } else if (data.pro) {
      highestPackage.value = 'Pro'
    } else if (data.member) {
      highestPackage.value = 'Member'
    } else {
      highestPackage.value = ''
    }

    // ✅ Chỉ hiển thị gói tương ứng
    if (data.premium) {
      userType.value = 'premium'
    } else if (data.pro) {
      userType.value = 'pro'
    } else if (data.member) {
      userType.value = 'member'
    } else {
      userType.value = 'guest'
    }

    if (expiredAt.value && data.expired_date) startCountdown()
  }
})


// Hàm tính toán countdown
async function updateCountdown() {
  if (!expiredAt.value) return
  const now = new Date()
  const diff = expiredAt.value - now
  // const diff = 0
  if (diff <= 0) {
    clearInterval(timer)
    countdown.days = countdown.hours = countdown.minutes = countdown.seconds = 0
    await axios.get(`${import.meta.env.VITE_API_URL_BACKEND}/account/package/change`, {
      params: {
        param: id.value
      }
    })
    toastt.info('Your package has expired.')
    return
  }

  countdown.days = Math.floor(diff / (1000 * 60 * 60 * 24))
  countdown.hours = Math.floor((diff / (1000 * 60 * 60)) % 24)
  countdown.minutes = Math.floor((diff / (1000 * 60)) % 60)
  countdown.seconds = Math.floor((diff / 1000) % 60)
}

function startCountdown() {
  updateCountdown()
  timer = setInterval(updateCountdown, 1000)
}

onUnmounted(() => clearInterval(timer))

function formatPrice(v) {
  if (v === 0) return 'Free'
  return new Intl.NumberFormat('en-US', { style: 'currency', currency: 'VND' }).format(v)
}

function openDetails(pkg) {
  activePkg.value = pkg
  showModal.value = true
}

async function purchase(pkg, type) {
  if (!token) {
    toastt.warning("Please log in to your account")
    return
  }

  try {
    // 🔹 1️⃣ Lấy tiền hiện tại trong tài khoản
    const moneyRes = await axios.get(`${import.meta.env.VITE_API_URL_BACKEND}/account/money`, {
      headers: { Authorization: `Bearer ${token.trim()}` },
      params: { id: id.value },
    })

    const money = moneyRes.data || 0
    // 🔹 2️⃣ Kiểm tra tiền có đủ không
    console.log(pkg.price)
      console.log(money)

    if (money < pkg.price) {
      console.log(money)
      toastt.warning("Insufficient balance! Please top up your account.")
      return
    }

    await axios.get(`${import.meta.env.VITE_API_URL_BACKEND}/account/package/save`, {
      headers: { Authorization: `Bearer ${token.trim()}` },
      params: { param: pkg.title }
    })

    // 🔄 Gọi lại API để lấy expired_date mới
    const refreshed = await axios.get(`${import.meta.env.VITE_API_URL_BACKEND}/account/package`, {
      headers: { Authorization: `Bearer ${token.trim()}` },
    })

    const data = refreshed.data
    expiredAt.value = new Date(data.expired_date)
    clearInterval(timer)
    // startCountdown()

    pkg.purchased = true
    showModal.value = false

    eventBus.emit("package-updated", { type })
    showToast(`You have purchased the "${pkg.title}" plan — Congratulations, you’re now VIP!`)
  } catch (error) {
    console.error(error)
    toastt.error('Purchase failed! Please try again.')
  }
}



function showToast(msg) {
  toast.message = msg
  toast.show = true
  setTimeout(() => (toast.show = false), 3000)
}
</script>

<style scoped>
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.25s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}

/* ✅ Nút đếm ngược nổi góc trên phải màn hình */
.countdown-floating {
  position: fixed;
  top: 1rem;
  right: 1rem;
  background: rgba(255, 237, 213, 0.95);
  border: 1px solid #fcd34d;
  color: #92400e;
  font-weight: 600;
  padding: 0.5rem 1rem;
  border-radius: 9999px;
  font-size: 0.875rem;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
  z-index: 50;
}
</style>
