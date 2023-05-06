import request from "@/api/axios";
import { AdvCardData } from "@/type/home";
import { useEffect, useState } from "react";

type advList = Array<AdvCardData>;

const useGetAdvList = () => {
  const [advList, setAdvList] = useState<advList | null>(null);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    request({ url: "/customer/advertisement/page", method: "get" }).then(
      (res: any) => {
        setLoading(false);
        setAdvList(res);
      }
    );
  }, []);

  return { advList, loading };
};

export default useGetAdvList;
