import "./index.scss";
import { AdvCardData } from "@/type/home";

interface AdvCardProps {
  advCardData: AdvCardData;
}

const AdvCard = (props: AdvCardProps) => {
  const { advCardData } = props;

  return (
    <div className="adv-card">
      <div className="adv-title">
        <p className="adv-title">{advCardData.title}</p>
      </div>
      <div className="adv-info">
        <div className="adv-digest">
          <p>{advCardData.description}</p>
        </div>
        <div className="flex-item"></div>
        <div className="adv-img">
          <img src={advCardData.url}></img>
        </div>
      </div>
      <div className="adv-time">
        <p></p>
      </div>
      <div className="adv-tag"></div>
    </div>
  );
};

export default AdvCard;
