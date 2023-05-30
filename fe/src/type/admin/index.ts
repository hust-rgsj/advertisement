export interface AdvItem {
  createTime: string;
  customerId: number;
  description: string;
  endTime: string;
  id: number;
  price: number;
  reason: string;
  startTime: string;
  status: number;
  title: string;
  updateTime: string;
  url: string[];
}

export interface PageRes {
  endRow: number;
  list: AdvItem[];
  pageNum: number;
  pageSize: number;
  pages: number;
  total: number;
  size: number;
}
