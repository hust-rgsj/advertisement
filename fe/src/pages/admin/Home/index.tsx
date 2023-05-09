import { Layout, Menu, Breadcrumb } from 'antd';
import './index.scss';
import { useNavigate } from 'react-router';
import { SideItem } from '@/type/home';
import { MenuInfo } from 'rc-menu/lib/interface';
import { Navigate, useLocation } from 'react-router';
import { Outlet } from 'react-router-dom';

const { Header, Sider } = Layout;

const sideItem: Array<SideItem> = [
  {
    key: 0,
    label: '广告浏览',
    path: '/admin/advList',
  },
  {
    key: 1,
    label: '客户浏览',
    path: '/admin/users',
  },
  {
    key: 2,
    label: '广告审核',
    path: '/admin/advCheck',
  },
  {
    key: 3,
    label: '广告定价',
    path: '/admin/advValue',
  },
  {
    key: 4,
    label: '应用管理',
    path: '/admin/appManage',
  },
];

const UHome = (): JSX.Element => {
  const navigate = useNavigate();

  const siderOnClick = (item: MenuInfo) => {
    navigate(sideItem[Number(item.key)].path);
  };

  const pathname = useLocation().pathname.slice(1).split('/');
  console.log(pathname)
  return (
    <Layout className="client-home">
      <Header className="client-header">
        <div className="logo" />
      </Header>
      <Layout className="client-body">
        <Sider className="client-sider">
          <Menu items={sideItem} defaultSelectedKeys={['0']} onClick={siderOnClick} theme="dark"></Menu>
        </Sider>
        <div className="client-content">
          <Breadcrumb style={{ margin: '16px' }}>
            <Breadcrumb.Item>{pathname[0]}</Breadcrumb.Item>
            <Breadcrumb.Item>{pathname[1]}</Breadcrumb.Item>
          </Breadcrumb>
          <Outlet />
        </div>
      </Layout>
    </Layout>
  );
};

export default UHome;
