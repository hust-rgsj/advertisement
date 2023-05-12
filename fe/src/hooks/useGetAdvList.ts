import request from "@/api/axios";
import { AdvCardData, AdvList } from "@/type/home";
import { useEffect, useState } from "react";

const useGetAdvList = () => {
  const [advList, setAdvList] = useState<AdvList | null>(null);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    request({ url: "/customer/advertisement/page", method: "get" }).then(
      (res: any) => {
        console.log(res);
        setLoading(false);
        setAdvList(res);
      }
    );
  }, []);

  return { advList, loading };
};

export default useGetAdvList;
