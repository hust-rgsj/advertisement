import useMessage from "@/hooks/useMessage";
import TopBar from "../Home/TopBar";
import Sider from "antd/es/layout/Sider";
import { Button, Menu } from "antd";
import { MenuInfo } from "rc-menu/lib/interface";
import { SideItem } from "@/type/home";
import "./index.scss";

import { Avatar, List } from "antd";

const sideItem: any = [
  {
    key: 0,
    label: "已读消息",
  },
  {
    key: 1,
    label: "未读消息",
  },
];

const Message = () => {
  const messageItem: any = useMessage();

  return (
    <div className="index">
      <TopBar></TopBar>
      <div className="container">
        <Sider className="client-sider" theme="dark">
          <Menu items={sideItem} defaultSelectedKeys={["0"]}></Menu>
        </Sider>
        <div>
          <List
            className="message-list"
            itemLayout="horizontal"
            dataSource={messageItem}
            renderItem={(item: any, index) => (
              <List.Item>
                <List.Item.Meta
                  title={<div>{item.title}</div>}
                  description={<div>{item.desc}</div>}
                />
              </List.Item>
            )}
          />
          <div className="btn-container">
            <Button>全部已读</Button>
            <Button>全部删除</Button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Message;
