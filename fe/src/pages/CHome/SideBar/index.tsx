import "./index.scss";
import Sider from "antd/es/layout/Sider";
import { Menu } from "antd";
import { MenuInfo } from "rc-menu/lib/interface";
import { SideItem } from "@/type/home";
import { Route, Routes, useNavigate } from "react-router";

const sideItem: Array<SideItem> = [
  {
    key: 0,
    label: "广告列表",
    path: "/home/list",
  },
  {
    key: 1,
    label: "nav 2",
    path: "/home/nav",
  },
];

const SideBar = () => {
  const navigate = useNavigate();

  const siderOnClick = (item: MenuInfo) => {
    navigate(sideItem[Number(item.key)].path);
  };

  return (
    <Sider className="client-sider" theme="dark">
      <Menu
        items={sideItem}
        defaultSelectedKeys={["0"]}
        onClick={siderOnClick}
        theme="dark"
      ></Menu>
    </Sider>
  );
};

export default SideBar;
