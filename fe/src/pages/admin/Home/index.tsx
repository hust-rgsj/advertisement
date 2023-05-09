import { Layout, Menu } from 'antd';
import './index.scss';
import { useNavigate } from 'react-router';
import { SideItem } from '@/type/home';
import { MenuInfo } from 'rc-menu/lib/interface';
import { Navigate } from 'react-router';
import { Outlet } from 'react-router-dom';

const { Header, Sider } = Layout;

const sideItem: Array<SideItem> = [
  {
    key: 0,
    label: '广告浏览',
    path: '/home/list',
  },
  {
    key: 1,
    label: '客户浏览',
    path: '/home/nav',
  },
  {
    key: 3,
    label: '广告审核',
    path: '/home/list',
  },
  {
    key: 4,
    label: '广告定价',
    path: '/home/nav',
  },
  {
    key: 5,
    label: '应用管理',
    path: '/home/list',
  },
];

const UHome = (): JSX.Element => {
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
            <Menu items={sideItem} defaultSelectedKeys={['0']} onClick={siderOnClick} theme="light"></Menu>
          </Sider>
          <div className="client-content">
            <Outlet />
          </div>
        </Layout>
      </Layout>
    </div>
  );
};

export default UHome;
