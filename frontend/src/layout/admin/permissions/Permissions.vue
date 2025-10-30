<template>
  <div class="min-h-screen bg-gradient-to-br from-amber-50 via-white to-amber-100 p-6">
    <div class="max-w-6xl mx-auto bg-white shadow-xl rounded-2xl p-6 border border-amber-100">
      <!-- Header -->
      <h1 class="text-3xl font-extrabold text-amber-800 mb-8 text-center">
        📚 Quản lý quyền người dùng
      </h1>

      <!-- Bộ lọc -->
      <div class="flex flex-col md:flex-row md:items-center md:justify-between mb-6 gap-3">
        <input
          v-model="searchQuery"
          type="text"
          placeholder="🔍 Tìm kiếm tài khoản..."
          class="w-full md:w-1/3 border border-amber-200 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-amber-400 transition"
        />

        <div class="text-sm text-gray-600 text-center md:text-right">
          Tổng cộng: <span class="font-semibold text-amber-700">{{ filteredPermissions.length }}</span> người dùng
        </div>
      </div>

      <!-- Loading -->
      <div v-if="isLoading" class="text-center py-10 text-amber-600 text-lg animate-pulse">
        Đang tải dữ liệu...
      </div>

      <!-- Table -->
      <div v-else class="overflow-x-auto">
        <table class="w-full border-collapse text-sm md:text-base">
          <thead>
            <tr class="bg-amber-100 text-amber-800 uppercase text-sm font-semibold">
              <th class="p-3 text-left rounded-tl-lg">#</th>
              <th class="p-3 text-left">Tên tài khoản</th>
              <th class="p-3 text-left">Quyền</th>
              <th class="p-3 text-left">Gói hiện tại</th>
              <th class="p-3 text-left rounded-tr-lg">Ngày hết hạn</th>
            </tr>
          </thead>
          <tbody>
            <tr
              v-for="(user, index) in paginatedData"
              :key="user.accountid"
              class="border-b hover:bg-amber-50 transition-colors"
            >
              <td class="p-3 font-medium text-gray-700">
                {{ (currentPage - 1) * itemsPerPage + index + 1 }}
              </td>
              <td class="p-3 font-semibold text-gray-800">{{ user.username }}</td>
              <td class="p-3">
                <span
                  :class="{
                    'bg-gray-200 text-gray-700 px-2 py-1 rounded-md text-xs font-semibold': user.role === 'USER',
                    'bg-green-100 text-green-700 px-2 py-1 rounded-md text-xs font-semibold': user.role === 'ADMIN',
                    'bg-purple-100 text-purple-700 px-2 py-1 rounded-md text-xs font-semibold': user.role === 'MOD',
                  }"
                >
                  {{ user.role }}
                </span>
              </td>
              <td class="p-3">
                <span
                  :class="{
                    'bg-gray-200 text-gray-700 px-2 py-1 rounded-md text-xs font-semibold':
                      user.plan === 'Member',
                    'bg-blue-100 text-blue-700 px-2 py-1 rounded-md text-xs font-semibold':
                      user.plan === 'Pro',
                    'bg-yellow-100 text-yellow-700 px-2 py-1 rounded-md text-xs font-semibold':
                      user.plan === 'Premium',
                  }"
                >
                  {{ user.plan }}
                </span>
              </td>
              <td class="p-3 text-gray-700">
                {{ formatDate(user.expired_date) }}
              </td>
            </tr>

            <tr v-if="paginatedData.length === 0">
              <td colspan="5" class="text-center py-6 text-gray-500 italic">
                Không có dữ liệu phù hợp
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <!-- Phân trang -->
      <div
        v-if="totalPages > 1"
        class="flex justify-center items-center mt-6 space-x-2 text-sm md:text-base"
      >
        <button
          @click="prevPage"
          :disabled="currentPage === 1"
          class="px-3 py-1 rounded-md border border-amber-300 text-amber-700 hover:bg-amber-100 disabled:opacity-40"
        >
          ← Trước
        </button>
        <span class="font-semibold text-amber-800">
          Trang {{ currentPage }} / {{ totalPages }}
        </span>
        <button
          @click="nextPage"
          :disabled="currentPage === totalPages"
          class="px-3 py-1 rounded-md border border-amber-300 text-amber-700 hover:bg-amber-100 disabled:opacity-40"
        >
          Sau →
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from "vue";
import axios from "axios";
import { useToast } from "vue-toastification";

const toast = useToast();
const backend = import.meta.env.VITE_API_URL_BACKEND;
const permissions = ref([]);
const isLoading = ref(true);
const token = localStorage.getItem("token");

const searchQuery = ref("");
const currentPage = ref(1);
const itemsPerPage = 5; // Số dòng mỗi trang

onMounted(async () => {
  await fetchPermissions();
});

async function fetchPermissions() {
  try {
    const res = await axios.get(`${backend}/account/admin/permissions`, {
      headers: { Authorization: `Bearer ${token}` },
    });

    permissions.value = res.data.map((u) => {
      let plan = "Member";
      if (u.premium) plan = "Premium";
      else if (u.pro) plan = "Pro";

      return {
        accountid: u.account.accountid,
        username: u.account.account,
        role: u.account.role || "USER", // 👈 Lấy role từ backend
        plan,
        expired_date: u.expired_date,
      };
    });
  } catch (err) {
    toast.error("Không thể tải danh sách quyền");
  } finally {
    isLoading.value = false;
  }
}

// --- Bộ lọc ---
const filteredPermissions = computed(() => {
  const query = searchQuery.value.toLowerCase().trim();
  return permissions.value.filter((u) =>
    u.username.toLowerCase().includes(query)
  );
});

// --- Phân trang ---
const totalPages = computed(() =>
  Math.ceil(filteredPermissions.value.length / itemsPerPage)
);

const paginatedData = computed(() => {
  const start = (currentPage.value - 1) * itemsPerPage;
  return filteredPermissions.value.slice(start, start + itemsPerPage);
});

function nextPage() {
  if (currentPage.value < totalPages.value) currentPage.value++;
}

function prevPage() {
  if (currentPage.value > 1) currentPage.value--;
}

// --- Đổi ngày hết hạn ra "Còn X ngày" ---
function formatDate(dateStr) {
  if (!dateStr) return "—";

  const now = new Date();
  const expired = new Date(dateStr);
  const diffMillis = expired - now;
  const diffDays = Math.ceil(diffMillis / (1000 * 60 * 60 * 24));

  if (diffDays > 0) {
    return `Còn ${diffDays} ngày`;
  } else {
    return "Đã hết hạn";
  }
}
</script>


<style scoped>
table {
  width: 100%;
  border-spacing: 0;
  border-radius: 10px;
  overflow: hidden;
}
</style>
