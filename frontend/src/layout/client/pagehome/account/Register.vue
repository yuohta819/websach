<script setup>
import { ref, watch } from 'vue';
import axios from "axios";
import { useToast } from "vue-toastification";

const emit = defineEmits(['close', 'switch']);
const toast = useToast();
const backend = import.meta.env.VITE_API_URL_BACKEND;

// Form data
const name = ref('');
const email = ref('');
const pass = ref('');
const confirmPass = ref('');
const errorMsg = ref('');

// Regex for validation
const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{8,}$/;

// Watch email
watch(email, (value) => {
  if (value && !emailRegex.test(value)) {
    errorMsg.value = "Invalid email format!";
  } else {
    errorMsg.value = "";
  }
});

// Watch password
watch(pass, (value) => {
  if (value && !passwordRegex.test(value)) {
    errorMsg.value = "Password must be at least 8 characters long and include uppercase, lowercase, number, and special character!";
  } else {
    errorMsg.value = "";
  }
});

// Close and switch
function handleClose() {
  emit('close');
}
function handleSwitchToLogin() {
  emit('switch');
}

// Register
async function handleRegister() {
  errorMsg.value = '';

  // if (!name.value || !email.value || !pass.value || !confirmPass.value) {
  //   errorMsg.value = "Please fill in all fields!";
  //   return;
  // }

  // if (!emailRegex.test(email.value)) {
  //   errorMsg.value = "Invalid email format!";
  //   return;
  // }

  // if (pass.value !== confirmPass.value) {
  //   errorMsg.value = "Passwords do not match!";
  //   return;
  // }

  try {
    const res = await axios.post(`${backend}/account/register/save`, {
      // name: name.value,
      // email: email.value,
      // password: pass.value
      name: "yuohta",
      email: "loctramcam12@gmail.com",
      password: "Loc@1234509867"
    });

    if (res.data === 'same') {
      toast.warning("Email already exists!");
      return;
    }

    toast.success("Registration successful!");
    emit('switch');
  } catch (error) {
    errorMsg.value = "Registration failed. Please try again!";
    console.error(error);
  }
}
</script>

<template>
  <div class="fixed inset-0 bg-black/60 z-40 flex justify-center items-center" @click.self="handleClose">
    <div class="bg-[#F5F5F4] shadow-lg w-[430px] rounded overflow-hidden">
      <!-- Header -->
      <div class="flex border-b border-gray-300">
        <div class="flex-1 text-center py-3 bg-gray-100 cursor-pointer hover:bg-gray-200 transition"
             @click="handleSwitchToLogin">
          <span><i class="fa-brands fa-hive"></i> Login</span>
        </div>
        <div class="flex-1 text-center py-3 bg-white border-t-4 border-black">
          <span><i class="fa-brands fa-accessible-icon"></i> Register</span>
        </div>
        <div class="py-3 px-5 bg-gray-100 cursor-pointer hover:bg-gray-200" @click="handleClose">
          <i class="fa-solid fa-xmark"></i>
        </div>
      </div>

      <!-- Content -->
      <div class="px-10 py-8">
        <input class="w-full p-3 mb-3 bg-white border-none outline-none rounded" placeholder="Name" v-model="name" />
        <input class="w-full p-3 mb-3 bg-white border-none outline-none rounded" placeholder="Email" v-model="email" />
        <input class="w-full p-3 mb-3 bg-white border-none outline-none rounded" type="password"
               placeholder="Password" v-model="pass" />
        <input class="w-full p-3 mb-3 bg-white border-none outline-none rounded" type="password"
               placeholder="Confirm Password" v-model="confirmPass" />

        <p v-if="errorMsg" class="text-red-500 text-sm mb-3 text-center">{{ errorMsg }}</p>

        <button type="button" @click="handleRegister"
                class="mt-3 w-full py-3 bg-black text-white font-semibold hover:bg-gray-800 transition">
          REGISTER
        </button>
      </div>
    </div>
  </div>
</template>
