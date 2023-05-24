import React, { useState } from 'react';
import { Space, Table, Tag } from 'antd';
import type { ColumnsType } from 'antd/es/table';
import { EditAdv } from './editAdv';
import { getAdminAdvList } from '@/api';
export interface DataType {
  name: string;
  content: string;
  url: string;
  user: string;
  description: string;
  balance: number;
}

const columns: ColumnsType<DataType> = [
  {
    title: '序号',
    dataIndex: 'index',
    align: 'center',
    width: 60,
    render: (text, record, idx) => {
      return <span>{idx}</span>;
    },
  },
  {
    width: 300,
    title: '广告名称',
    dataIndex: 'name',
    align: 'center',
  },
  {
    title: '广告描述',
    dataIndex: 'content',
    align: 'center',
  },
  {
    title: '广告图片',
    dataIndex: 'url',
    align: 'center',
  },
  {
    title: '广告所属用户',
    dataIndex: 'user',
    align: 'center',
  },
  {
    title: '广告金额',
    dataIndex: 'balance',
    align: 'center',
  },
  {
    title: '广告结束时间',
    dataIndex: 'balance',
    align: 'center',
  },
  {
    width: 300,
    title: '操作广告',
    dataIndex: 'address',
    render: (text, record) => {
      return <EditAdv {...record}></EditAdv>;
    },
    align: 'center',
  },
];

const AdvList = (): JSX.Element => {
  const [tableData, setTableData] = useState<DataType[]>([
    {
      name: '111',
      description: '222',
      balance: 113,
    },
  ]);
  const [totalCnt, setTotalCnt] = useState(100);
  const [tableLoading, setTableLoading] = useState(false);
  async function getList(pageNum: number) {
    setTableLoading(true);
    try {
      const res = await getAdminAdvList({ pageNum });
      console.log(res)
      setTableLoading(false);
    } catch (err) {
      setTableLoading(false);
    }
  }
  return <Table loading={tableLoading} columns={columns} dataSource={tableData} pagination={{ total: totalCnt, pageSize: 10, onChange: getList, showSizeChanger: false }} />;
};
export default AdvList;
