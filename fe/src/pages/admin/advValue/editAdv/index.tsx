import './index.scss';
import React, { useEffect, useState } from 'react';
import { Button, Modal, Form, Input, DatePicker, message } from 'antd';
import { DataType } from '../../AdvList/index';
import type { FormInstance } from 'antd/es/form';
import { setAdv } from '@/api';
import dayjs from 'dayjs';

export const EditAdv = (props: DataType): JSX.Element => {
  const [modalOpen, setModelOpen] = useState(false);
  const formRef = React.useRef<FormInstance>(null);
  async function handleEditAdv(data: { price: number; startTime: dayjs.Dayjs; endTime: dayjs.Dayjs }) {
    const formData = {
      id: props.id,
      price: data.price,
      startTime: data.startTime.format('YYYY-MM-DD HH:mm:ss'),
      endTime: data.endTime.format('YYYY-MM-DD HH:mm:ss'),
    };
    try {
      await setAdv(formData);
      message.info('修改成功!');
      props.initialize();
      setModelOpen(false);
    } catch (e) {
      message.info('修改失败!');
    }
  }
  dayjs(new Date());
  let defaultVal = {
    ...props,
    startTime: dayjs(props.startTime ?? new Date()),
    endTime: dayjs(props.endTime ?? new Date()),
  };
  return (
    <>
      <Button onClick={() => setModelOpen(true)}>修改广告</Button>
      <Modal
        title="编辑广告"
        open={modalOpen}
        onCancel={() => setModelOpen(false)}
        onOk={() => {
          formRef.current?.submit();
        }}
      >
        <Form ref={formRef} onFinish={handleEditAdv} initialValues={defaultVal}>
          <Form.Item label="广告价格" name="price">
            <Input></Input>
          </Form.Item>
          <Form.Item label="开始时间" name="startTime">
            <DatePicker></DatePicker>
          </Form.Item>
          <Form.Item label="结束时间" name="endTime">
            <DatePicker></DatePicker>
          </Form.Item>
        </Form>
      </Modal>
    </>
  );
};
