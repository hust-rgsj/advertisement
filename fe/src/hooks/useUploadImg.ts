import request from "@/api/axios";

const useUploadImg = () => {
  const uploadImg = (img: any) => {
    let forms = new FormData();
    forms.append("image", img);
    return request({ url: "/upload", data: forms, method: "post" });
  };

  return uploadImg;
};

export default useUploadImg;
