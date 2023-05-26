import { useLocation } from "react-router-dom";
import "./index.scss";
import useGetAdv from "@/hooks/useGetAdv";
import { Image, Collapse } from "antd";

const Detail = () => {
  const location = useLocation();
  const query = new URLSearchParams(location.search);
  const id = query.get("id");

  const { adv, loading } = useGetAdv(id);

  const { Panel } = Collapse;

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

  return (
    <div className="adv-detail">
      {loading || !adv ? (
        <div>loading...</div>
      ) : (
        <div>
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
              <p>text</p>
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
              <p>text</p>
            </Panel>
          </Collapse>
        </div>
      )}
    </div>
  );
};

export default Detail;
