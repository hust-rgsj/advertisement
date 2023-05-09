import './index.scss';
import React, { useState } from 'react';
import { Button, Modal, Form, Input } from 'antd';
import { DataType } from '../index';

export const EditAdv = (props: DataType): JSX.Element => {
  const [modalOpen, setModelOpen] = useState(false);
  console.log(props)
  function handleEditAdv() {}

  return (
    <>
      <Button onClick={() => setModelOpen(true)}>修改广告</Button>
      <Modal title="编辑广告" open={modalOpen} onCancel={() => setModelOpen(false)}>
        <Form onFinish={handleEditAdv} initialValues={props}>
          <Form.Item label="广告名称" name="name">
            <Input></Input>
          </Form.Item>
          <Form.Item label="广告内容" name="content">
            <Input></Input>
          </Form.Item>
          <Form.Item label="广告余额" name="balance" rules={[{ pattern: /^-?\d+$/, message: '需输入数字' }]}>
            <Input></Input>
          </Form.Item>
        </Form>
      </Modal>
    </>
  );
};
