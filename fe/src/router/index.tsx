import { Navigate, createBrowserRouter } from "react-router-dom";
import React, { lazy, Suspense } from "react";
const lazyLoad = (Component: React.LazyExoticComponent<() => JSX.Element>) => {
  return (
    <Suspense fallback={null}>
      <Component></Component>
    </Suspense>
  );
};
const Login = lazy(() => import("@/pages/Login/login"));
const CHome = lazy(() => import("@pages/CHome"));
const CList = lazy(() => import("@pages/CList"));

const router = createBrowserRouter([
  {
    path: "/",
    children: [
      {
        path: "/",
        element: <Navigate to="login" />,
      },
      {
        path: "login",
        element: lazyLoad(Login),
      },
    ],
  },
  {
    path: "/home",
    element: lazyLoad(CHome),
    children: [
      {
        path: "",
        element: <Navigate to="list" />,
      },
      {
        path: "list",
        element: lazyLoad(CList),
      },
    ],
  },
]);

export default router;
