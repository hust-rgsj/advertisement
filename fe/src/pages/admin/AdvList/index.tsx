import React from 'react';
import { Space, Table, Tag } from 'antd';
import type { ColumnsType } from 'antd/es/table';
import { EditAdv } from './editAdv';
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
const data: DataType[] = [
  {
    name: '111',
    description: '222',
    balance: 113,
  },
];

const AdvList = (): JSX.Element => {
  return <Table columns={columns} dataSource={data} />;
};
export default AdvList;
