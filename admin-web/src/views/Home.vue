<template>
  <div class="home-container">
    <el-card class="box-card">
      <div class="text-center">
        <h1>A05 管理后台前端已启动</h1>
        <p class="subtitle">Vue 3 + Vite + Element Plus 骨架搭建完成</p>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { onMounted } from 'vue'
import { ElMessage } from 'element-plus'

onMounted(() => {
  ElMessage.success('前端服务加载成功！')
})
</script>

<style scoped>
.home-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f5f7fa;
}

.box-card {
  width: 600px;
  padding: 40px;
}

.text-center {
  text-align: center;
}

h1 {
  color: #409eff;
  margin-bottom: 20px;
}

.subtitle {
  color: #909399;
  font-size: 16px;
}
</style>
