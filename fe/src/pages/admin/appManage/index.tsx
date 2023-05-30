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
    width: 100,
    render: (text, record, idx) => {
      return <span>{idx}</span>;
    },
  },
  {
    width: 300,
    title: '应用名',
    dataIndex: 'id',
    align: 'center',
  },
  {
    width: 300,
    title: '应用id',
    dataIndex: 'name',
    align: 'center',
  },
  {
    title: '应用当前投放的广告',
    dataIndex: 'advertisement',
    align: 'center',
  },
  {
    width: 300,
    title: '应用投放广告设置',
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
