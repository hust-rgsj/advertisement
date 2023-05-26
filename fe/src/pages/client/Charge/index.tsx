import { useRef, useState } from "react";
import "./index.scss";
import { Button, Input, Layout, Result } from "antd";
import useCharge from "@/hooks/useCharge";
import { useNavigate } from "react-router-dom";

const Charge = () => {
  const [isShowResult, setIsShowResult] = useState<boolean>(false);
  const [balance, setBalance] = useState<number>(0);
  const [value, setValue] = useState<number>(0);
  const inputRef = useRef<any>();

  const navigate = useNavigate();

  const charge = useCharge();

  const changeHandler = (e: any) => {
    setValue(e.target.value);
  };

  const chargeHandler = () => {
    charge(value).then((res: any) => {
      console.log(res);
      if (res.code === 200) {
        setIsShowResult(true);
        setBalance(res.data.balance);
        console.log(isShowResult);
      }
    });
  };

  const backHandler = () => {
    navigate("/home");
  };

  const buyHandler = () => {
    window.location.reload();
  };

  return (
    <div className="charge">
      <Result
        className={isShowResult ? "" : "hidden"}
        status="success"
        title="充值成功"
        subTitle={`已充值${value}元，当前余额为：${balance}元`}
        extra={[
          <Button key="back" onClick={backHandler}>
            返回
          </Button>,
          <Button key="buy" onClick={buyHandler}>
            继续充值
          </Button>,
        ]}
      />
      <div className={isShowResult ? "hidden" : "charge-container"}>
        <Input
          className="charge-input"
          onChange={changeHandler}
          type="number"
        ></Input>
        <Button className="charge-btn" onClick={chargeHandler.bind(this)}>
          充值
        </Button>
      </div>
    </div>
  );
};

export default Charge;
