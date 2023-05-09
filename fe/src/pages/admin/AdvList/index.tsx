import React from 'react';
import { Space, Table, Tag } from 'antd';
import type { ColumnsType } from 'antd/es/table';
import { editAdv } from '@/components';
interface DataType {
  key: string;
  content: string;
  balance: number;
}

const columns: ColumnsType<DataType> = [
  {
    title: '广告名称',
    dataIndex: 'name',
    key: 'name',
    render: (text) => <a>{text}</a>,
  },
  {
    title: '广告内容',
    dataIndex: 'content',
    key: 'content',
  },
  {
    title: '广告余额',
    dataIndex: 'balance',
    key: 'balance',
  },
  {
    title: '操作广告',
    dataIndex: 'address',
    key: 'address',
    render: () => {
      return <editAdv></editAdv>;
    },
  },
];
const data: DataType[] = [
  {
    key: '1',
    name: 'John Brown',
    age: 32,
    address: 'New York No. 1 Lake Park',
    tags: ['nice', 'developer'],
  },
  {
    key: '2',
    name: 'Jim Green',
    age: 42,
    address: 'London No. 1 Lake Park',
    tags: ['loser'],
  },
  {
    key: '3',
    name: 'Joe Black',
    age: 32,
    address: 'Sydney No. 1 Lake Park',
    tags: ['cool', 'teacher'],
  },
];

const AdvList = (): JSX.Element => {
  return <Table columns={columns} dataSource={data} />;
};
export default AdvList;
