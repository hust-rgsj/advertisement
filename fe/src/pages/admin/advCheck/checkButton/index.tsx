import './index.scss';
import React, { useState, useRef } from 'react';
import { Button, Modal, Form, Input, message } from 'antd';
import { DataType } from '../index';
import { examineAdv } from '@/api';
import type { FormInstance } from 'antd/es/form';

const EditAdv = (props: DataType): JSX.Element => {
  const [approveModalOpen, setApproveModalOpen] = useState(false);
  const [refuseModalOpen, setRefuseModelOpen] = useState(false);
  const formRef = useRef<FormInstance>(null);
  async function approve() {
    await examineAdv({
      id: props.id,
      status: 22,
    });
    setApproveModalOpen(false);
    message.info('修改成功');
    props.initialize();
  }

  async function submit(form: { reason: string }) {
    console.log(form);
    const { reason } = form;
    await examineAdv({
      id: props.id,
      status: 32,
      reason,
    });
    setRefuseModelOpen(false);
    message.info('修改成功');
    props.initialize();
  }

  return (
    <>
      <Button onClick={() => setApproveModalOpen(true)}>通过</Button>
      <Button onClick={() => setRefuseModelOpen(true)}>不通过</Button>
      <Modal title="确认" open={approveModalOpen} onCancel={() => setApproveModalOpen(false)} onOk={approve}>
        <div style={{ marginTop: 40, marginBottom: 40 }}>确定审核通过该广告？</div>
      </Modal>
      <Modal title="编辑广告" open={refuseModalOpen} onCancel={() => setRefuseModelOpen(false)} onOk={formRef.current?.submit}>
        <Form ref={formRef} style={{ marginTop: 60, marginBottom: 60 }} onFinish={submit} initialValues={props}>
          <Form.Item label="原因" name="reason">
            <Input.TextArea />
          </Form.Item>
        </Form>
      </Modal>
    </>
  );
};

export default EditAdv;
