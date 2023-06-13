import request from "@/api/axios";

const useOrder = () => {
  const createOrder = (data: any) => {
    return request({
      url: "/customer/advertisement/order/submit",
      data,
      method: "post",
    });
  };

  const payOrder = (data: any) => {
    return request({
      url: "/customer/advertisement/order/pay",
      data,
      method: "post",
    });
  };

  return { createOrder, payOrder };
};

export default useOrder;
