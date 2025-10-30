<script setup>
import { useToast } from 'vue-toastification'
import axios from 'axios'

const props = defineProps({
  book: {
    type: Object,
    required: true
  }
})

const backend = import.meta.env.VITE_API_URL_BACKEND
const token = localStorage.getItem("token")
const toast = useToast()

async function addToCart() {
  if (!token) {
    toast.warning("Please log in to add to favorites!")
    return
  }

  try {
    const account = localStorage.getItem("name")
    const payload = {
      account: account,
      bookid: props.book.bookid, // ✅ Dùng props thay vì viewData
      roles: props.book.is_roles
    }

    const res = await axios.post(
      `${backend}/cart/save`,
      payload,
      {
        headers: { Authorization: `Bearer ${token.trim()}` }
      }
    )
    if (res.data === 'success') {
      toast.success("Added to favorites list successfully")
    } else if (res.data === 'error') {
      toast.warning("Invalid token")
    } else if (res.data === 'same') {
      toast.warning("This book is already in your favorites list.")
    } 
    else {
      toast.warning("Something went wrong")
    }
  } catch (err) {
    console.error('❌ Error adding to favorites:', err)
    toast.error("Server error. Please try again later.")
  }
}
</script>

<template>
  <button
    class="flex cursor-pointer items-center gap-2 bg-amber-600 hover:bg-amber-700 text-white font-semibold px-6 py-3 rounded-xl shadow-md transition"
    @click="addToCart">
    <i class="fa-solid fa-cart-plus"></i> Add to Cart
  </button>
</template>
