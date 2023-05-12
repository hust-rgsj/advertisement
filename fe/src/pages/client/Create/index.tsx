import { Form, Input } from "antd";
import "./index.scss";
import FormItem from "antd/es/form/FormItem";

const Create = () => {
  const onFinish = (value: any) => {
    console.log(value);
  };

  return (
    <div className="create-ad">
      <Form onFinish={onFinish}>
        <FormItem name={"广告标题"}>
          <Input></Input>
        </FormItem>
        <FormItem name={"广告描述"}>
          <Input></Input>
        </FormItem>
        <FormItem></FormItem>
      </Form>
    </div>
  );
};

export default Create;
