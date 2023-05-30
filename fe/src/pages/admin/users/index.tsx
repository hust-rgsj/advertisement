import React, { useEffect } from 'react';
import { Space, Table, Tag } from 'antd';
import type { ColumnsType } from 'antd/es/table';
import { useState } from 'react';
import { getUserList } from '@/api';
import dayjs from 'dayjs';

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
    title: '注册时间',
    dataIndex: 'advertisement',
    align: 'center',
    render: (text) => {
      return <span>{dayjs(text).format('YYYY-MM-DD')}</span>;
    },
  },
  {
    width: 300,
    title: '客户电话',
    dataIndex: 'phone',
    align: 'center',
  },
  {
    width: 300,
    title: '客户电话',
    dataIndex: 'phone',
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
  useEffect(() => {
    getList(1);
  }, []);
  async function getList(pageNum: number) {
    setTableLoading(true);
    try {
      const res = await getUserList({ pageNum });
      console.log(res);
      setTableData(res.list);
      setTotalCnt(res.total);
      setTableLoading(false);
    } catch (err) {
      setTableLoading(false);
    }
  }
  return <Table loading={tableLoading} columns={columns} dataSource={tableData} pagination={{ total: totalCnt, pageSize: 10, onChange: getList, showSizeChanger: false }} />;
};
export default Users;
