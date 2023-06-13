import "./index.scss";
import { Header } from "antd/es/layout/layout";
import shoppingImg from "@assets/img/shopping.png";
import messageImg from "@assets/img/message.png";
import userImg from "@assets/img/user.png";
import { useNavigate } from "react-router-dom";

const TopBar = () => {
  const navigate = useNavigate();

  const homeHandler = () => {
    navigate("/home");
  };

  const cartHandler = () => {
    navigate("/cart");
  };

  const messageHandler = () => {
    navigate("/message");
  };

  const userHandler = () => {
    navigate("/user");
  };

  return (
    <Header className="client-header">
      <div className="header-logo" onClick={homeHandler}>
        <p>Logo</p>
      </div>
      <div className="flex-item"></div>
      <div className="header-icon" onClick={cartHandler}>
        <img src={shoppingImg}></img>
      </div>
      <div className="header-icon" onClick={messageHandler}>
        <img src={messageImg}></img>
      </div>
      <div className="header-avatar" onClick={userHandler}>
        <img src={userImg}></img>
      </div>
    </Header>
  );
};

export default TopBar;
