import { useNavigate } from "react-router-dom";
import "./index.scss";
import { AdvCardData } from "@/type/home";
import { Tag } from "antd";

interface AdvCardProps {
  advCardData: AdvCardData;
}

const getFirstImg = (url: string) => {
  let urlList = url ? JSON.parse(url) : [];
  return urlList[0];
};

const AdvCard = (props: AdvCardProps) => {
  const { advCardData } = props;
  const navigate = useNavigate();

  const clickHandler = () => {
    navigate(`../detail?id=${advCardData.id}`);
  };

  return (
    <div className="adv-card" onClick={clickHandler}>
      <div className="adv-title">
        <p className="adv-title">{advCardData.title}</p>
      </div>
      <div className="adv-info">
        <div className="adv-digest">
          <p>{advCardData.description}</p>
        </div>
        <div className="flex-item"></div>
        <div className="adv-img">
          <img src={getFirstImg(advCardData.url)}></img>
        </div>
      </div>
      <div className="adv-time">
        <p>{`广告投放时间：${
          advCardData.startTime && advCardData.endTime
            ? advCardData.startTime.substring(
                0,
                advCardData.startTime.indexOf("T")
              ) +
              " ~ " +
              advCardData.endTime.substring(0, advCardData.endTime.indexOf("T"))
            : "未定"
        }`}</p>
      </div>
      <Tag color={advCardData.status === 2222 ? "#87d068" : "#f50"}>
        {advCardData.status === 2222 ? "正在投放" : "待投放"}
      </Tag>
    </div>
  );
};

export default AdvCard;
