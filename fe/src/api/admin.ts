import request from './axios';
import { AdvItem, PageRes } from '@/type/admin';

interface BaseParam {
  pageNum?: number;
  msg?: string;
}

interface SetPriceParams {
  id: number;
  price: number;
  startTime: string;
  endTime: string;
}

interface ExamineParams {
  id: number;
  status: number;
  reason?: string;
}

interface CheckAdvParams {
  id: number;
  status: number;
  reason?: string;
}

interface SetPriceParams {
  id: number;
  price: number;
  startTime: string;
  endTime: string;
}

export async function getAdminAdvList(params: BaseParam): Promise<PageRes> {
  return request({ url: '/admin/pageAd', params, method: 'get' });
}

export async function getUserList(params: BaseParam) {
  return request({ url: '/admin/customer/page', params, method: 'get' });
}

export async function examineAdv(data: ExamineParams) {
  return request({ url: '/admin/advertisement/examine', data, method: 'post' });
}

export async function setAdv(data: SetPriceParams) {
  return request({ url: '/admin/advertisement/set', data, method: 'post' });
}
