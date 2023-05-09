import React from 'react';
import { Space, Table, Tag } from 'antd';
import type { ColumnsType } from 'antd/es/table';
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
    title: '客户id',
    dataIndex: 'id',
    align: 'center',
  },
  {
    title: '客户名称',
    dataIndex: 'name',
    align: 'center',
  },
  {
    width: 300,
    title: '客户广告',
    dataIndex: 'advertisement',
    align: 'center',
  },
  {
    width: 300,
    title: '客户余额',
    dataIndex: 'balance',
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
