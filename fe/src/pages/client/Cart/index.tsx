import useGetCart from "@/hooks/useGetCart";
import "./index.scss";
import { Button, Checkbox, Divider, Radio, Table } from "antd";
import type { ColumnsType } from "antd/es/table";
import TopBar from "../Home/TopBar";

interface DataType {
  title: string;
  count: number;
  time: number;
  price: number;
}

const columns: ColumnsType<DataType> = [
  {
    title: "广告信息",
    dataIndex: "title",
  },
  {
    title: "购买数量",
    dataIndex: "count",
  },
  {
    title: "购买时长",
    dataIndex: "time",
  },
  {
    title: "价格",
    dataIndex: "price",
  },
];

const Cart = () => {
  const data: any = useGetCart();

  return (
    <div className="cart-container">
      <TopBar></TopBar>
      <div className="cart">
        <div className="cart-title">购物车</div>
        <Divider />

        <Table
          rowSelection={{
            type: "checkbox",
          }}
          columns={columns}
          dataSource={data}
        />
      </div>
      <Button className="btn-buy" size="large">
        立即购买
      </Button>
    </div>
  );
};

export default Cart;
