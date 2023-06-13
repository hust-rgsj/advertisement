import { useNavigate } from "react-router-dom";
import "./index.scss";
import refreshImg from "@assets/img/refresh.png";

const TopBar = () => {
  const navigate = useNavigate();

  const clickHandler = () => {
    navigate("../create");
  };

  const refreshHandler = () => {
    window.location.reload();
  };

  return (
    <div className="top-bar">
      <p className="top-title">我的广告</p>
      <div className="flex-item"></div>
      <div className="top-refresh" onClick={refreshHandler}>
        <img src={refreshImg}></img>
      </div>
      <div className="top-btn" onClick={clickHandler}>
        <p>创建广告</p>
      </div>
    </div>
  );
};

export default TopBar;
