import { Navigate, createBrowserRouter } from 'react-router-dom';
import React, { lazy, Suspense } from 'react';
const lazyLoad = (Component: React.LazyExoticComponent<() => JSX.Element>) => {
  return (
    <Suspense fallback={null}>
      <Component></Component>
    </Suspense>
  );
};
const Login = lazy(() => import('@/pages/Login/login'));
const CHome = lazy(() => import('@pages/CHome'));
const CList = lazy(() => import('@pages/CList'));
const AHome = lazy(() => import('@/pages/admin/home'));
const AAdvList = lazy(() => import('@pages/admin/advList'));
const AUsers = lazy(() => import('@/pages/admin/users'));
const AAdvCheck = lazy(() => import('@/pages/admin/advCheck'));
const AAdvValue = lazy(() => import('@/pages/admin/advValue'));
const AAppManage = lazy(() => import('@/pages/admin/appManage'));

const router = createBrowserRouter([
  {
    path: '/',
    children: [
      {
        path: '/',
        element: <Navigate to="login" />,
      },
      {
        path: 'login',
        element: lazyLoad(Login),
      },
    ],
  },
  {
    path: '/home',
    element: lazyLoad(CHome),
    children: [
      {
        path: '',
        element: <Navigate to="list" />,
      },
      {
        path: 'list',
        element: lazyLoad(CList),
      },
    ],
  },
  {
    path: '/admin',
    element: lazyLoad(AHome),
    children: [
      {
        path: '',
        element: <Navigate to="advList" />,
      },
      {
        path: 'advList',
        element: lazyLoad(AAdvList),
      },
      {
        path: 'users',
        element: lazyLoad(AUsers),
      },
      {
        path: 'advCheck',
        element: lazyLoad(AAdvCheck),
      },
      {
        path: 'advValue',
        element: lazyLoad(AAdvValue),
      },
      {
        path: 'appManage',
        element: lazyLoad(AAppManage),
      },
    ],
  },
]);

export default router;
