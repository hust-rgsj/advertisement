import request from "@/api/axios";

const useCart = () => {
  const addCart = (data: any) => {
    return request({
      url: `/customer/shoppingCart/add`,
      data,
      method: "post",
    });
  };

  return addCart;
};

export default useCart;
