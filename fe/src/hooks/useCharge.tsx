import request from "@/api/axios";

const useCharge = () => {
  const charge = (num: number) => {
    return request({
      url: `/customer/account/recharge?charge=${num}`,
      method: "post",
    });
  };

  return charge;
};

export default useCharge;
