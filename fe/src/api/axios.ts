import axios, { AxiosInstance, InternalAxiosRequestConfig, AxiosResponse } from 'axios';

const service: AxiosInstance = axios.create({
  baseURL: '/api'
});
// 请求拦截器
service.interceptors.request.use(
  (config: InternalAxiosRequestConfig) => {
    const token = localStorage.getItem('token');
    const { headers } = config;
    if (token && headers) headers.Authorization = `{token}`;
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);
service.interceptors.response.use((response: AxiosResponse) => {
  const { data, headers } = response;
  return response.data;
});

export default service
