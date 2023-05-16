import "./index.scss";
import { Card, Form, Input, Button, message } from "antd";
import { Link } from "react-router-dom";
import { LoginBackGround } from "@/components";
import { useState, useRef } from "react";
import { Login } from "@/type";
import { login, register } from "@/api";
import { useNavigate } from "react-router-dom";

const LoginPage = (): JSX.Element => {
  const [isRegister, setIsRegister] = useState(false);
  const [isPasswdRig, setIsPasswdRig] = useState(true);
  const [isReinputRig, setIsReinputRig] = useState(false);
  const navigate = useNavigate();
  async function onFinish(values: Login) {
    const { username, password } = values;
    if (isRegister)
      register({ username, password }).then((res: any) => {
        if (res.code === 200) {
          message.info("注册成功！");
          setIsRegister(false);
        } else {
          message.info(res.msg);
        }
      });
    else {
      handlerLogin({ username, password });
    }
  }
  // 这里就不写双向绑定了，只是一个获取值
  let passwd = useRef("");
  let rePasswd = useRef("");
  function changePasswd(e: any) {
    passwd.current = e.target.value;
    if (rePasswd.current != passwd.current && rePasswd.current !== "")
      setIsPasswdRig(false);
    else setIsPasswdRig(true);
  }
  function changeConfirmPasswd(e: any) {
    rePasswd.current = e.target.value;
    if (rePasswd.current != passwd.current && rePasswd.current !== "")
      setIsPasswdRig(false);
    else setIsPasswdRig(true);
  }

  async function handlerLogin(data: Login) {
    const res = await login(data);
    if (res.code === 200) {
      localStorage.setItem("token", res.data.token);
      message.info("登录成功！");
      navigate("/home");
    } else {
      message.warning(res.msg);
    }
  }

  return (
    <div className="container">
      <Card title={isRegister ? "账户注册" : "账户登录"} className="login-card">
        <Form onFinish={onFinish} autoComplete="off">
          <div>
            用户名
            <Form.Item name="username" rules={[{ required: true }]}>
              <Input id="username" size="large" placeholder="请输入用户名" />
            </Form.Item>
          </div>
          <Form.Item name="password" rules={[{ required: true }]}>
            <div>
              密码
              <Input.Password
                id="password"
                onChange={changePasswd}
                size="large"
                placeholder="请输入密码"
              />
            </div>
          </Form.Item>
          {isRegister && (
            <Form.Item
              name="repasswd"
              rules={[
                {
                  validator: () => {
                    console.log("validate", isPasswdRig);
                    if (!isPasswdRig)
                      return Promise.reject(new Error("两次密码不同"));
                    else return Promise.resolve();
                  },
                },
              ]}
            >
              <div>
                确认密码
                <Input.Password
                  id="confirm-password"
                  onChange={changeConfirmPasswd}
                  size="large"
                  placeholder="请确认密码"
                />
              </div>
            </Form.Item>
          )}
          <div className="forget-passwd">忘记密码</div>
          <div className="button-group">
            <Button id="submit" type="primary" size="large" htmlType="submit">
              {isRegister ? "注册" : "登录"}
            </Button>
          </div>
        </Form>
        <div
          onClick={() => {
            setIsRegister(!isRegister);
          }}
          className="register"
        >
          <div className="bg"></div>
          <span>{isRegister ? "登录" : "注册"}</span>
        </div>
      </Card>
    </div>
  );
};

export default LoginPage;
