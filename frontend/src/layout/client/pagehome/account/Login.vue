<script setup>
import { ref } from 'vue'
import axios from "axios"
import { useToast } from 'vue-toastification'

const emit = defineEmits(['close', 'switch'])
const name = ref('')
const pass = ref('')
const backend = import.meta.env.VITE_API_URL_BACKEND
const toast = useToast()

// Đóng modal
function handleClose() {
  emit('close')
}

// Chuyển sang Register
function handleSwitchToRegister() {
  emit('switch')
}

// Đăng nhập thông thường
async function handleSubmit() {
  try {
    const res = await axios.post(`${backend}/account/login/check`, {
      // account: name.value,
      // password: pass.value
      account: "loctramcam12@gmail.com",
      password: "Loc@1234509867"
    })
    if (res.data === 'account') return toast.warning("Wrong account")
    if (res.data === 'password') return toast.warning("Wrong password")

    const data = res.data
    console.log(data)
    localStorage.setItem("accountid", data.accountid)
    localStorage.setItem("name", data.account)
    localStorage.setItem("roles", data.role)
    localStorage.setItem("token", data.token)
    localStorage.setItem("type", data.type)
    axios.defaults.headers.common["Authorization"] = `Bearer ${data.token}`
    location.reload()
  } catch (err) {
    console.error(err)
    toast.error("Login failed!")
  }
}

// 🔥 Đăng nhập bằng MetaMask
import { ethers } from "ethers";

async function handleMetaMaskLogin() {
  try {
    if (!window.ethereum) {
      toast.warning("⚠️ Vui lòng cài đặt MetaMask trước khi đăng nhập.");
      return;
    }

    // 1️⃣ Yêu cầu MetaMask cấp quyền
    const provider = new ethers.BrowserProvider(window.ethereum);
    const accounts = await provider.send("eth_requestAccounts", []);
    const walletAddress = accounts[0];

    // 2️⃣ Gọi API để lấy nonce từ backend
    const nonceRes = await axios.get(`${backend}/metamask/nonce/${walletAddress}`);
    const nonce = nonceRes.data.nonce;

    // 3️⃣ Ký message bằng MetaMask
    const signer = await provider.getSigner();
    const message = `${nonce}`;
    const signature = await signer.signMessage(message);
    // 4️⃣ Gửi chữ ký về backend để xác minh và nhận token
    const verifyRes = await axios.post(`${backend}/metamask/verify`, {
      walletAddress,
      signature,
    });
    console.log(verifyRes.data)
    const data = verifyRes.data;

    // 5️⃣ Lưu token và thông tin ví vào localStorage
    localStorage.setItem("walletAddress", walletAddress);
    localStorage.setItem("token", data.token);
    localStorage.setItem("name", data.role || "user");
    toast.success("🎉 Đăng nhập MetaMask thành công!");
    location.reload();
  } catch (err) {
    console.error(err);
    toast.error("❌ Đăng nhập MetaMask thất bại.");
  }
}

</script>

<template>
  <div
    class="fixed inset-0 bg-black/60 z-40 flex justify-center items-center"
    @click.self="handleClose"
  >
    <div class="bg-[#F5F5F4] shadow-lg w-[430px] rounded-xl overflow-hidden">
      <!-- Header -->
      <div class="flex border-b border-gray-300">
        <div class="flex-1 text-center py-3 bg-white border-t-4 border-black font-semibold">
          <i class="fa-brands fa-hive mr-1"></i> Login
        </div>
        <div
          class="flex-1 text-center py-3 bg-gray-100 cursor-pointer hover:bg-gray-200 transition"
          @click="handleSwitchToRegister"
        >
          <i class="fa-brands fa-accessible-icon mr-1"></i> Register
        </div>
        <div
          class="py-3 px-5 bg-gray-100 cursor-pointer hover:bg-gray-200 transition"
          @click="handleClose"
        >
          <i class="fa-solid fa-xmark"></i>
        </div>
      </div>

      <!-- Content -->
      <div class="px-10 py-8">
        <input
          v-model="name"
          class="w-full p-3 mb-3 bg-white border border-gray-300 rounded focus:ring-2 focus:ring-amber-400 outline-none"
          placeholder="Email"
        />
        <input
          v-model="pass"
          type="password"
          class="w-full p-3 mb-3 bg-white border border-gray-300 rounded focus:ring-2 focus:ring-amber-400 outline-none"
          placeholder="Password"
        />

        <div class="flex items-center justify-between text-sm mb-5">
          <span class="text-red-600 cursor-pointer hover:underline">Forgot password?</span>
          <label class="flex items-center space-x-1 text-gray-700">
            <input type="checkbox" />
            <span>Remember me</span>
          </label>
        </div>

        <!-- Nút LOGIN -->
        <button
          type="button"
          @click="handleSubmit"
          class="w-full py-3 bg-black text-white font-semibold rounded-lg hover:bg-gray-800 transition"
        >
          LOGIN
        </button>

        <!-- Hoặc -->
        <div class="flex items-center my-6">
          <div class="flex-1 border-t border-gray-300"></div>
          <span class="px-3 text-gray-500 text-sm">or</span>
          <div class="flex-1 border-t border-gray-300"></div>
        </div>

        <!-- Nút MetaMask -->
        <button
          type="button"
          @click="handleMetaMaskLogin"
          class="w-full py-3 bg-gradient-to-r from-orange-500 to-yellow-400 text-white font-semibold rounded-lg hover:opacity-90 transition flex items-center justify-center gap-3"
        >
          <img
            src="https://upload.wikimedia.org/wikipedia/commons/3/36/MetaMask_Fox.svg"
            alt="MetaMask"
            class="w-6 h-6"
          />
          Continue with MetaMask
        </button>
      </div>
    </div>
  </div>
</template>
