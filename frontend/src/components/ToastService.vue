<template></template>

<script setup>
import { useToast } from 'vue-toastification'
import { defineExpose } from 'vue'

const toast = useToast()

function success(msg) {
  toast.success(msg)
}

function error(msg) {
  toast.error(msg)
}

function info(msg) {
  toast.info(msg)
}

function warning(msg) {
  toast.warning(msg)
}

// Cho phép component cha gọi
defineExpose({
  success,
  error,
  info,
  warning,
})
</script>
