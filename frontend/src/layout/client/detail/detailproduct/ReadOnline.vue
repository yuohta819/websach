<script setup>
import { useToast } from 'vue-toastification'
import axios from 'axios'
import {useRouter} from 'vue-router'
const props = defineProps({
    book: {
        type: Object,
        required: true
    }
})

const backend = import.meta.env.VITE_API_URL_BACKEND
const token = localStorage.getItem("token")
const toast = useToast()
const route = useRouter()
async function readBook() {
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
            `${backend}/cart/check/roles`,
            payload,
            {
                headers: { Authorization: `Bearer ${token.trim()}` }
            }
        )
        console.log(res.data)
        if (res.data === 'success') {
            route.push(`/favorites/${props.book.bookid}`)
        } else if (res.data === 'error') {
            toast.warning("Invalid token")
        } else if (res.data === 'not roles') {
            toast.warning("You do not have access. Please purchase permission.")
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
    <button @click="readBook"
        class="flex cursor-pointer items-center gap-2 bg-gray-100 hover:bg-gray-200 text-gray-700 font-semibold px-6 py-3 rounded-xl transition">
        <i class="fa-solid fa-book-open"></i> Read Online
    </button>
</template>