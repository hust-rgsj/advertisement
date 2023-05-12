import React from 'react';
import { Space, Table, Tag } from 'antd';
import type { ColumnsType } from 'antd/es/table';
import CheckButton from './checkButton';

export interface DataType {
  id: number;
  name: string;
  advertisement: string;
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
    width: 300,
    title: '审核',
    render: () => <CheckButton />,
    align: 'center',
  },
];
const data: DataType[] = [
  {
    name: '111',
    id: 222,
    advertisement: '广告1',
    balance: 113,
  },
];

const Users = (): JSX.Element => {
  return <Table columns={columns} dataSource={data} />;
};
export default Users;
