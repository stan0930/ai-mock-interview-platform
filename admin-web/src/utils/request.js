import axios from 'axios'
import { ElMessage } from 'element-plus'

// 创建 axios 实例
const request = axios.create({
  baseURL: 'http://localhost:8081',
  timeout: 10000 // 请求超时时间
})

// request 拦截器
request.interceptors.request.use(
  config => {
    // 可以在这里设置统一的请求头
    config.headers['Content-Type'] = 'application/json;charset=utf-8'
    return config
  },
  error => {
    console.error('request error: ', error)
    return Promise.reject(error)
  }
)

// response 拦截器
request.interceptors.response.use(
  response => {
    let res = response.data
    // 如果是返回的文件
    if (response.config.responseType === 'blob') {
      return res
    }
    // 兼容服务端返回的字符串数据
    if (typeof res === 'string') {
      res = res ? JSON.parse(res) : res
    }
    
    // 如果后端定义状态码不为 200，则提示错误信息
    if (res.code !== 200 && res.code != null) {
      ElMessage.error(res.message || '系统异常')
      return Promise.reject(new Error(res.message || 'Error'))
    }
    return res
  },
  error => {
    console.error('response error: ', error)
    // 根据 http 状态码给出不同的提示
    const status = error.response ? error.response.status : null
    let message = '请求错误'
    if (status === 401) {
      message = '未授权，请登录'
      // 可以在此处添加路由跳转到登录页的逻辑
    } else if (status === 403) {
      message = '拒绝访问'
    } else if (status === 404) {
      message = `请求地址出错: ${error.response.config.url}`
    } else if (status === 500) {
      message = '服务器内部错误'
    }
    ElMessage.error(message)
    return Promise.reject(error)
  }
)

export default request
