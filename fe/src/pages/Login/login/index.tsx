import './index.scss';
import { Card, Form, Input, Button } from 'antd';
import { Link } from 'react-router-dom';
import { LoginBackGround } from '@/components';

const Login = (): JSX.Element => {
  function onFinish(values: Login) {}

  return (
    <div className="container">
      <Card title="账户登录" className="login-card">
        <Form onFinish={onFinish}>
          <Form.Item name="username">
            <Input size="large" prefix="账户" placeholder="请输入用户名" />
          </Form.Item>
          <Form.Item name="password">
            <Input.Password size="large" prefix="密码" placeholder="请输入密码" />
          </Form.Item>
          <div className="button-group">
            <Button size="large">
              <Link to="/register">注册</Link>
            </Button>
            <Button type="primary" size="large">
              登录
            </Button>
          </div>
        </Form>
      </Card>
    </div>
  );
};

export default Login;
