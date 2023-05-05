import { AdvCardData } from "@/type/home";
import "./index.scss";
import AdvCard from "@/components/advCard";

const AdvContainer = () => {
  const advList: Array<AdvCardData> = [
    {
      id: 11,
      title: "广告标题",
      description: "广告摘要广告摘要广告摘要广告摘要广告摘要",
      img: "https://cdn.ray-blog.top/t01e900c82ac25cb7e2.jpg",
      startTime: null,
      endTime: null,
      status: 11,
    },
  ];

  return (
    <div className="adv-container">
      {advList.map((item, index) => {
        return <AdvCard advCardData={item}></AdvCard>;
      })}
    </div>
  );
};

export default AdvContainer;
