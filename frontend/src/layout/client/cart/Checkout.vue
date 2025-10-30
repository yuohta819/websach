<script setup>
import { ref, computed, onMounted } from "vue";

const cartItems = ref([]);
const user = ref({
  name: "",
  phone: "",
  address: "",
  note: "",
});

// Pagination
const currentPage = ref(1);
const itemsPerPage = 5;

// Lấy dữ liệu từ localStorage
onMounted(() => {
  const localData = localStorage.getItem("checkoutItems");
  if (localData) {
    try {
      const parsed = JSON.parse(localData);

      // Map dữ liệu sang format cần dùng
      cartItems.value = parsed.map((item) => ({
        id: item.book.bookid,
        name: item.book.name.trim(),
        price: item.book.price,
        img: item.book.img,
        quantity: item.quantity,
      }));
    } catch (err) {
      console.error("Error parsing cart data:", err);
    }
  } else {
    console.warn("No cart data found in localStorage!");
  }
});

// Phân trang
const totalPages = computed(() =>
  Math.ceil(cartItems.value.length / itemsPerPage)
);

const paginatedItems = computed(() => {
  const start = (currentPage.value - 1) * itemsPerPage;
  return cartItems.value.slice(start, start + itemsPerPage);
});

const nextPage = () => {
  if (currentPage.value < totalPages.value) currentPage.value++;
};
const prevPage = () => {
  if (currentPage.value > 1) currentPage.value--;
};

// Tổng tiền toàn bộ giỏ
const totalPrice = computed(() =>
  cartItems.value.reduce((sum, item) => sum + item.price * item.quantity, 0)
);

// Đặt hàng
const checkout = () => {
  if (!user.value.name || !user.value.phone || !user.value.address) {
    alert("Please fill in all required shipping information!");
    return;
  }
  alert("Order placed successfully! (Simulated)");
};
</script>

<template>
  <div class="max-w-6xl mx-auto px-4 py-10">
    <h2
      class="text-3xl font-bold text-center mb-10"
      style="font-family: 'EB Garamond', serif;"
    >
      Checkout
    </h2>

    <div class="grid grid-cols-1 lg:grid-cols-3 gap-8">
      <!-- Cart Items -->
      <div class="lg:col-span-2 bg-white shadow-md rounded-2xl p-6 border">
        <h3 class="text-xl font-semibold mb-4 text-[#b85c38]">
          Items in Your Cart
        </h3>

        <div v-if="cartItems.length" class="divide-y">
          <div
            v-for="item in paginatedItems"
            :key="item.id"
            class="flex items-center justify-between py-4"
          >
            <div class="flex items-center gap-4">
              <img
                :src="item.img"
                alt="Book"
                class="w-16 h-20 object-cover rounded-md border"
              />
              <div>
                <h4 class="text-gray-800 font-medium">{{ item.name }}</h4>
                <p class="text-[#b85c38] font-semibold">
                  {{ item.price.toLocaleString() }}₫
                </p>
              </div>
            </div>
            <div class="text-gray-700 text-sm">
              Qty:
              <input
                type="number"
                v-model.number="item.quantity"
                min="1"
                class="w-14 border rounded text-center"
              />
            </div>
          </div>
        </div>
        <p v-else class="text-gray-500 italic text-center py-10">
          Your cart is empty.
        </p>

        <!-- Pagination -->
        <div
          v-if="cartItems.length > itemsPerPage"
          class="flex justify-center items-center space-x-4 mt-6"
        >
          <button
            @click="prevPage"
            :disabled="currentPage === 1"
            class="px-4 py-2 rounded-lg bg-gray-200 hover:bg-gray-300 disabled:opacity-50 transition"
          >
            Previous
          </button>
          <span class="text-gray-700 font-medium">
            Page {{ currentPage }} of {{ totalPages }}
          </span>
          <button
            @click="nextPage"
            :disabled="currentPage === totalPages"
            class="px-4 py-2 rounded-lg bg-gray-200 hover:bg-gray-300 disabled:opacity-50 transition"
          >
            Next
          </button>
        </div>
      </div>

      <!-- Shipping Info -->
      <div class="bg-white shadow-md rounded-2xl p-6 border">
        <h3 class="text-xl font-semibold mb-4 text-[#b85c38]">
          Shipping Information
        </h3>

        <div class="space-y-4">
          <input
            v-model="user.name"
            type="text"
            placeholder="Full Name"
            class="w-full border rounded-lg p-2 focus:ring-2 focus:ring-[#b85c38] outline-none"
          />
          <input
            v-model="user.phone"
            type="text"
            placeholder="Phone Number"
            class="w-full border rounded-lg p-2 focus:ring-2 focus:ring-[#b85c38] outline-none"
          />
          <input
            v-model="user.address"
            type="text"
            placeholder="Delivery Address"
            class="w-full border rounded-lg p-2 focus:ring-2 focus:ring-[#b85c38] outline-none"
          />
          <textarea
            v-model="user.note"
            placeholder="Additional Notes (optional)"
            class="w-full border rounded-lg p-2 focus:ring-2 focus:ring-[#b85c38] outline-none"
            rows="3"
          ></textarea>
        </div>

        <div class="border-t mt-6 pt-4 text-right">
          <p class="text-gray-700 font-semibold mb-2">
            Total:
            <span class="text-[#b85c38] text-lg font-bold">
              {{ totalPrice.toLocaleString() }}₫
            </span>
          </p>
          <button
            @click="checkout"
            class="w-full bg-[#b85c38] text-white py-3 rounded-lg font-semibold hover:bg-[#a34e2f] transition"
          >
            Place Order
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
