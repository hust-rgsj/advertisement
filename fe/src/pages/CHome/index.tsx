import { Layout, Menu } from "antd";
import "./index.scss";
import { Route, Routes, useNavigate } from "react-router";
import { SideItem } from "@/type/home";
import { MenuInfo } from "rc-menu/lib/interface";
import { Navigate } from "react-router";
import CList from "../CList";

const { Header, Sider } = Layout;

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

const CHome = (): JSX.Element => {
  const navigate = useNavigate();

  const siderOnClick = (item: MenuInfo) => {
    navigate(sideItem[Number(item.key)].path);
  };

  return (
    <div className="client-home">
      <Layout>
        <Header className="client-header"></Header>
        <Layout className="client-body">
          <Sider className="client-sider" theme="light">
            <Menu
              items={sideItem}
              defaultSelectedKeys={["0"]}
              onClick={siderOnClick}
              theme="light"
            ></Menu>
          </Sider>
          <div className="client-content">
            <Routes>
              <Route path="/" element={<Navigate to="list" />}></Route>
              <Route path="/list" element={<CList />}></Route>
            </Routes>
          </div>
        </Layout>
      </Layout>
    </div>
  );
};

export default CHome;
