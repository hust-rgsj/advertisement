import { UploadFile } from "antd";
import request from "@/api/axios";

interface createAdvData {
  title: string;
  description: string;
  url: string;
}

const useCreateAdv = () => {
  const createAdv = (data: createAdvData) => {
    return request({
      url: "/customer/advertisement/add",
      data,
      method: "post",
    });
  };
  return createAdv;
};

export default useCreateAdv;
