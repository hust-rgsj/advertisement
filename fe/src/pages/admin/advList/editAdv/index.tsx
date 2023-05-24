import './index.scss';
import React, { useState } from 'react';
import { Button, Modal, Form, Input, DatePicker } from 'antd';
import { DataType } from '../index';
import type { FormInstance } from 'antd/es/form';

export const EditAdv = (props: DataType): JSX.Element => {
  const [modalOpen, setModelOpen] = useState(false);
  const formRef = React.useRef<FormInstance>(null);
  function handleEditAdv(data: { price: number; startTime: string; endTime: string }) {
    console.log(data);
  }

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
        <Form ref={formRef} onFinish={handleEditAdv} initialValues={props}>
          <Form.Item label="广告价格" name="price">
            <Input></Input>
          </Form.Item>
          <Form.Item label="开始时间" name="statTime">
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
