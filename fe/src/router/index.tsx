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
]);

export default router;
