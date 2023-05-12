import { Button, Form, Input, Modal, Upload, UploadFile } from "antd";
import "./index.scss";
import FormItem from "antd/es/form/FormItem";
import TextArea from "antd/es/input/TextArea";
import { useState } from "react";
import { RcFile, UploadProps } from "antd/es/upload/interface";
import { PlusOutlined } from "@ant-design/icons";
import useUploadImg from "@/hooks/useUploadImg";
import axios from "axios";
import { useForm } from "antd/es/form/Form";
import useCreateAdv from "@/hooks/useCreateAdv";
import { useNavigate } from "react-router-dom";

const Create = () => {
  const [form] = useForm();
  const createAdv = useCreateAdv();
  const navigate = useNavigate();

  const onFinish = (value: any) => {
    createAdv({ ...value, url: JSON.stringify(imgList) }).then((res: any) => {
      if (res.code === 200) {
        navigate("../list");
      }
    });
  };

  const uploadButton = (
    <div>
      <PlusOutlined />
      <div style={{ marginTop: 8 }}>Upload</div>
    </div>
  );

  const [fileList, setFileList] = useState<UploadFile[]>([]);
  const [imgList, setImgList] = useState<string[]>([]);

  const uploadRequest = async (options: any) => {
    const { onSuccess, onError, file, onProgress } = options;

    const fmData = new FormData();
    const token = localStorage.getItem("token");
    const config = {
      headers: {
        "content-type": "multipart/form-data",
        Authorization: `${token}`,
      },
    };
    fmData.append("image", file);
    try {
      const res: any = await axios.post(
        "/api/customer/advertisement/upload",
        fmData,
        config
      );
      onSuccess(res);
      console.log("server res: ", res);
      setImgList(() => imgList.concat([res.data.data]));
    } catch (err) {
      console.log("Error: ", err);
      const error = new Error("Some error");
      onError({ err });
    }
  };

  const handleOnChange = ({ file, fileList, event }: any) => {
    setFileList(fileList);
    console.log(file, fileList);
  };

  return (
    <div className="create-ad">
      <div className="top-bar">
        <div className="top-title">创建广告</div>
        <div className="flex-item"></div>
      </div>
      <Form
        className="ad-form"
        layout="vertical"
        onFinish={onFinish}
        form={form}
      >
        <FormItem className="ad-title" name={"title"} label={"广告标题"}>
          <Input></Input>
        </FormItem>
        <FormItem className="ad-desc" name={"description"} label={"广告描述"}>
          <TextArea></TextArea>
        </FormItem>
        <FormItem></FormItem>
      </Form>
      <div className="ad-upload-title">
        <p>上传广告图片</p>
      </div>
      <div className="ad-upload">
        <Upload
          className="ad-upload-img"
          listType="picture-card"
          fileList={fileList}
          onChange={handleOnChange}
          customRequest={uploadRequest}
        >
          {fileList.length >= 3 ? null : uploadButton}
        </Upload>
      </div>
      <div className="ad-btn">
        <Button type="primary" onClick={form.submit.bind(this)}>
          创建
        </Button>
      </div>
    </div>
  );
};

export default Create;
