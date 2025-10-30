import { createApp } from 'vue'
import { createRouter, createWebHistory } from "vue-router"
import axios from 'axios'
import Toast from 'vue-toastification'
import 'vue-toastification/dist/index.css'
import './style.css'
import App from "./App.vue"
import Client from './routes/Client'
import Admin from './routes/Admin'
const routes = [...Client, ...Admin]
const router = createRouter({
  history: createWebHistory(),
  routes,
})
router.beforeEach(async (to, from, next) => {
  const token = localStorage.getItem("adminToken")
  const backend = import.meta.env.VITE_API_URL_BACKEND

  // 🔹 Nếu là trang đăng nhập admin, không kiểm tra token
  if (to.path === "/admin/login") {
    // Nếu đã có token (đã login admin) → tự động chuyển đến dashboard
    if (token) {
      try {
        const res = await axios.get(`${backend}/account/decode`, {
          headers: { Authorization: `Bearer ${token}` },
        })
        if (res.data.role === "ADMIN") {
          return next("/admin/dashboard")
        }
      } catch {
        localStorage.removeItem("adminToken")
      }
    }
    return next()
  }

  // 🔹 Nếu là trang admin khác (ví dụ /admin/dashboard)
  if (to.path.startsWith("/admin")) {
    if (!token) {
      return next("/admin/login") // Chưa đăng nhập
    }

    try {
      const res = await axios.get(`${backend}/account/decode`, {
        headers: { Authorization: `Bearer ${token}` },
      })

      if (res.data === "not_admin" || res.data.role !== "ADMIN") {
        localStorage.clear()
        return next("/admin/login")
      }

      return next() // ✅ token hợp lệ
    } catch (err) {
      console.error("Decode lỗi:", err)
      localStorage.clear()
      return next("/admin/login")
    }
  }

  // 🔹 Các route còn lại (client)
  return next()
})
const app = createApp(App)

app.use(Toast, {
  position: 'top-right',
  timeout: 2500,
})

app.use(router)
app.mount('#app')
