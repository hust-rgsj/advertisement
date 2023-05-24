import request from './axios';

interface BaseParam {
  pageNum?: number;
  msg?: string;
}

interface SetPriceParams {
  adId: number;
  price: number;
}

interface ExamineParams {
  id: number;
  status: number;
  reason?: string;
}

export async function getAdminAdvList(data: BaseParam) {
  return request({ url: '/admin/advertisement', data, method: 'get' });
}

export async function getUserList(data: BaseParam) {
  return request({ url: '/admin/customer/page', data, method: 'get' });
}

export async function examineAdv(data: ExamineParams) {
  return request({ url: '/admin/advertisement/examine', data, method: 'post' });
}

export async function setAdvPrice(data: SetPriceParams) {
  return request({ url: '/admin/advertisement/setPrice', data, method: 'post' });
}
