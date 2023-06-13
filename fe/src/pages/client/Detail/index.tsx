import { useLocation } from "react-router-dom";
import "./index.scss";
import useGetAdv from "@/hooks/useGetAdv";
import { Image, Collapse, Layout, Button, Modal, notification } from "antd";
import useOrder from "@/hooks/useOrder";
import { useState } from "react";
import { NotificationPlacement } from "antd/es/notification/interface";
import Context from "@ant-design/icons/lib/components/Context";
import useCart from "@/hooks/useCart";

const Detail = () => {
  const location = useLocation();
  const query = new URLSearchParams(location.search);
  const id = query.get("id");

  const { adv, loading } = useGetAdv(id);

  const { Panel } = Collapse;

  // 审核
  const Audit = (props: any) => {
    const { status } = props;
    if (status.toString().length > 2) return <p>审核通过</p>;
    switch (status) {
      case 12:
        return <p>审核中</p>;
      case 22:
        return <p>审核通过</p>;
      case 32:
        return <p>审核不通过</p>;
      default:
        return <p>未开始审核</p>;
    }
  };

  // 支付
  const { createOrder, payOrder } = useOrder();
  const [isShowOrderAlert, setIsShowOrderAlert] = useState(false);
  const [orderId, setOrderId] = useState(0);

  const orderHandler = () => {
    createOrder({ adId: id }).then((res) => {
      console.log(res);
      setIsShowOrderAlert(true);
      setOrderId(res.data);
    });
  };

  const payHandler = () => {
    payOrder({ id: orderId }).then((res) => {
      if (res.data) {
        openNotification("topLeft", res.data.balance);
        setTimeout(() => {
          window.location.reload();
        }, 1000);
      }
    });
  };

  const handleCancel = () => {
    setIsShowOrderAlert(false);
  };

  const [api, contextHolder] = notification.useNotification();

  const openNotification = (
    placement: NotificationPlacement,
    balance: number
  ) => {
    api.info({
      message: `Notification ${placement}`,
      description: `支付成功，当前余额为：${balance}`,
      placement,
    });
  };

  // 添加到购物车
  const addCart = useCart();

  const cartHandler = () => {
    addCart({ id }).then((res) => {
      console.log(res);
    });
  };

  return (
    <div className="adv-detail">
      {loading || !adv ? (
        <div>loading...</div>
      ) : (
        <div>
          <Modal
            title="确认支付"
            open={isShowOrderAlert}
            onOk={payHandler}
            onCancel={handleCancel}
            footer={[
              <Button key="back" onClick={handleCancel}>
                取消
              </Button>,
              <Button key="submit" type="primary" onClick={payHandler}>
                确认支付
              </Button>,
            ]}
          >
            <p>{`需要支付：${adv.price}元`}</p>
          </Modal>
          <div className="title">{adv.title}</div>
          <div className="adv-img-title">广告图片预览</div>
          <div className="adv-img">
            {JSON.parse(adv.url).map((item: string, index: number) => {
              return <Image src={item} width={200}></Image>;
            })}
          </div>
          <Collapse accordion expandIconPosition={"end"} className="adv-bar">
            <Panel
              header={
                <div className="panel-header">
                  <div className="panel-icon active">
                    <p>√</p>
                  </div>
                  <p>初始化广告信息</p>
                </div>
              }
              key="1"
              collapsible="icon"
              showArrow={false}
            ></Panel>
            <Panel
              header={
                <div className="panel-header">
                  <div
                    className={`panel-icon ${
                      adv.status.toString().length >= 2 ? "active" : ""
                    }`}
                  >
                    <p>{adv.status.toString().length >= 3 ? "√" : "2"}</p>
                  </div>
                  <p>审核</p>
                </div>
              }
              key="2"
              showArrow={false}
            >
              <Audit status={adv.status}></Audit>
            </Panel>
            <Panel
              header={
                <div className="panel-header">
                  <div
                    className={`panel-icon ${
                      adv.status.toString().length >= 3 ? "active" : ""
                    }`}
                  >
                    <p>{adv.status.toString().length >= 4 ? "√" : "3"}</p>
                  </div>
                  <p>付费</p>
                </div>
              }
              key="3"
              showArrow={false}
            >
              <div>
                {adv.price ? (
                  <div>
                    <div className="pay-price">{`定价：${adv.price}`}</div>
                    <div className="pay-container">
                      <Button onClick={orderHandler.bind(this)}>
                        立即支付
                      </Button>
                      <Button onClick={cartHandler.bind(this)}>
                        加入购物车
                      </Button>
                    </div>
                  </div>
                ) : (
                  <div>该广告尚未定价</div>
                )}
              </div>
            </Panel>
            <Panel
              header={
                <div className="panel-header">
                  <div
                    className={`panel-icon ${
                      adv.status.toString().length >= 4 ? "active" : ""
                    }`}
                  >
                    <p>{adv.status === 2222 ? "√" : "4"}</p>
                  </div>
                  <p>部署</p>
                </div>
              }
              key="4"
              showArrow={false}
            >
              <p>
                {adv.startTime && adv.endTime ? (
                  <p>{`投放时间：${adv.startTime.replace(
                    "T",
                    " "
                  )} ~ ${adv.endTime.replace("T", " ")}`}</p>
                ) : (
                  <p>广告尚未投放</p>
                )}
              </p>
            </Panel>
          </Collapse>
        </div>
      )}
    </div>
  );
};

export default Detail;
