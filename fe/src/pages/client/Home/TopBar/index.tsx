import "./index.scss";
import { Header } from "antd/es/layout/layout";
import shoppingImg from "@assets/img/shopping.png";
import messageImg from "@assets/img/message.png";
import userImg from "@assets/img/user.png";
import { useNavigate } from "react-router-dom";

const TopBar = () => {
  const navigate = useNavigate();

  return (
    <Header className="client-header">
      <div className="header-logo">
        <p>Logo</p>
      </div>
      <div className="flex-item"></div>
      <div className="header-icon">
        <img src={shoppingImg}></img>
      </div>
      <div className="header-icon">
        <img src={messageImg}></img>
      </div>
      <div className="header-avatar">
        <img src={userImg}></img>
      </div>
    </Header>
  );
};

export default TopBar;
