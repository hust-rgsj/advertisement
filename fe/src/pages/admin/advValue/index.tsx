import './index.scss';
import React, { Fragment, useEffect, useState } from 'react';
import { Space, Table, Tag } from 'antd';
import type { ColumnsType } from 'antd/es/table';
import { EditAdv } from './editAdv';
import { getAdminAdvList } from '@/api';
import { AdvItem, PageRes } from '@/type/admin';
import dayjs from 'dayjs';

const AdvList = (): JSX.Element => {
  const [tableData, setTableData] = useState<AdvItem[]>();
  const [totalCnt, setTotalCnt] = useState(100);
  const [tableLoading, setTableLoading] = useState(false);
  const columns: ColumnsType<AdvItem> = [
    {
      title: '序号',
      dataIndex: 'index',
      align: 'center',
      width: 100,
      render: (text, record, idx) => {
        return <span>{idx + 1}</span>;
      },
    },
    {
      width: 300,
      title: '广告名称',
      dataIndex: 'title',
      align: 'center',
    },
    {
      width: 300,
      title: '广告描述',
      dataIndex: 'description',
      align: 'center',
    },
    {
      title: '广告图片',
      dataIndex: 'url',
      render: (text, record) => {
        return text ? <img className="adv-image" src={JSON.parse(text)[0]}></img> : <Fragment></Fragment>;
      },
    },
    {
      title: '广告金额',
      dataIndex: 'price',
      align: 'center',
    },
    {
      title: '广告开始时间',
      dataIndex: 'startTime',
      align: 'center',
      render: (text) => {
        return <span>{dayjs(text).format('YYYY-MM-DD')}</span>;
      },
    },
    {
      title: '广告结束时间',
      dataIndex: 'endTime',
      align: 'center',
      render: (text) => {
        return <span>{dayjs(text).format('YYYY-MM-DD')}</span>;
      },
    },
    {
      width: 300,
      title: '操作广告',
      dataIndex: 'address',
      render: (text, record) => {
        return <EditAdv {...record} initialize={initialize}></EditAdv>;
      },
      align: 'center',
    },
  ];
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
  return <Table loading={tableLoading} columns={columns} dataSource={tableData} pagination={{ total: totalCnt, pageSize: 6, onChange: getList, showSizeChanger: false }} rowKey="id" />;
};
export default AdvList;
