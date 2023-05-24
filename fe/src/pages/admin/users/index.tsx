import React from 'react';
import { Space, Table, Tag } from 'antd';
import type { ColumnsType } from 'antd/es/table';
import { useState } from 'react';
import { getUserList } from '@/api';

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
const data: DataType[] = [];

const Users = (): JSX.Element => {
  const [tableData, setTableData] = useState<DataType[]>([
    {
      name: '111',
      id: 222,
      advertisement: '广告1',
      balance: 113,
    },
  ]);
  const [totalCnt, setTotalCnt] = useState(100);
  const [tableLoading, setTableLoading] = useState(false);
  async function getList(pageNum: number) {
    setTableLoading(true);
    try {
      const res = await getUserList({ pageNum });
      console.log(res);
      setTableLoading(false);
    } catch (err) {
      setTableLoading(false);
    }
  }
  return <Table loading={tableLoading} columns={columns} dataSource={tableData} pagination={{ total: totalCnt, pageSize: 10, onChange: getList, showSizeChanger: false }} />;
};
export default Users;
