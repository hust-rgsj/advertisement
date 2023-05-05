import request from './axios';
import { Login, LoginRes } from '@/type';

export async function login(data: Login): Promise<LoginRes> {
  return request({ url: '/login', data, method: 'post' });
}
export async function register(data: Login) {
  return request({ url: '/register', data, method: 'post' });
}
