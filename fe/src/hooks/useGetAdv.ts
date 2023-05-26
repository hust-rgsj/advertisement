import request from "@/api/axios";
import { Adv } from "@/type/home";
import { useEffect, useState } from "react";

const useGetAdv = (id: any) => {
  if (!id) return { adv: null, loading: true };

  let isRequest = false;

  const [adv, setAdv] = useState<Adv | null>(null);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    if (isRequest) return;
    isRequest = true;
    request({
      url: `/customer/advertisement/adDetail/${id}`,
      method: "get",
    }).then((res: any) => {
      console.log(res.data);
      setLoading(false);
      setAdv(res.data);
    });
  }, []);

  return { adv, loading };
};

export default useGetAdv;
