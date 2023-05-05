import "./index.scss";
import refreshImg from "@assets/img/refresh.png";

const TopBar = () => {
  return (
    <div className="top-bar">
      <p className="top-title">我的广告</p>
      <div className="flex-item"></div>
      <div className="top-refresh">
        <img src={refreshImg}></img>
      </div>
      <div className="top-btn">
        <p>创建广告</p>
      </div>
    </div>
  );
};

export default TopBar;
