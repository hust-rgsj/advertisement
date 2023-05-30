import React from 'react';
import { Space, Table, Tag } from 'antd';
import type { ColumnsType } from 'antd/es/table';
import CheckButton from './checkButton';
import { useState, useEffect, Fragment } from 'react';
import { getAdminAdvList } from '@/api';

export interface DataType {
  id: number;
  name: string;
  advertisement: string;
  balance: number;
}

const data: DataType[] = [
  {
    name: '111',
    id: 222,
    advertisement: '广告1',
    balance: 113,
  },
];

const Users = (): JSX.Element => {
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
      align: 'center',
      dataIndex: 'url',
      render: (text, record) => {
        return text ? <img style={{ height: '150px', width: '150px' }} src={JSON.parse(text)[0]}></img> : <Fragment></Fragment>;
      },
    },
    {
      title: '广告当前状态',
      dataIndex: 'status',
      align: 'center',
      render(text) {
        return <span>{text == 22 ? '审核通过' : text == 32 ? '审核不通过' : '未审核'}</span>;
      },
    },
    {
      title: '说明',
      dataIndex: 'reason',
      align: 'center',
    },
    {
      width: 300,
      title: '审核',
      render: (text, more) => <CheckButton {...more} initialize={initialize} />,
      align: 'center',
    },
  ];
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
  function initialize() {
    getList(1);
  }
  useEffect(() => {
    initialize();
  }, []);
  async function getList(pageNum: number) {
    setTableLoading(true);
    try {
      const res = await getAdminAdvList({ pageNum });
      setTableData(res.list);
      setTotalCnt(res.size);
      setTableLoading(false);
    } catch (err) {
      setTableLoading(false);
    }
  }
  return <Table loading={tableLoading} columns={columns} dataSource={tableData} pagination={{ total: totalCnt, pageSize: 10, onChange: getList, showSizeChanger: false }} />;
};
export default Users;
