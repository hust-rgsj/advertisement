import './index.scss';
import React, { useState } from 'react';
import { Button, Modal, Form, Input } from 'antd';
import { DataType } from '../index';

const EditAdv = (props: DataType): JSX.Element => {
  const [approveModalOpen, setApproveModalOpen] = useState(false);
  const [refuseModalOpen, setRefuseModelOpen] = useState(false);
  function handleEditAdv() {}

  return (
    <>
      <Button onClick={() => setApproveModalOpen(true)}>通过</Button>
      <Button onClick={() => setRefuseModelOpen(true)}>不通过</Button>
      <Modal title="确认" open={approveModalOpen} onCancel={() => setApproveModalOpen(false)}>
        <div style={{ marginTop: 40, marginBottom: 40 }}>确定审核通过该广告？</div>
      </Modal>
      <Modal title="编辑广告" open={refuseModalOpen} onCancel={() => setRefuseModelOpen(false)}>
        <Form style={{ marginTop: 60, marginBottom: 60 }} onFinish={handleEditAdv} initialValues={props}>
          <Form.Item label="原因" name="reason">
            <Input.TextArea />
          </Form.Item>
        </Form>
      </Modal>
    </>
  );
};

export default EditAdv;
