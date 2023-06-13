import { Layout } from "antd";
import "./index.scss";
import { Route, Routes } from "react-router";
import { Navigate } from "react-router";
import List from "../List";
import TopBar from "./TopBar";
import SideBar from "./SideBar";
import Create from "../Create";
import Detail from "../Detail";
import Charge from "../Charge";
import Data from "../Data";

const CHome = (): JSX.Element => {
  return (
    <div className="client-home">
      <Layout>
        <TopBar></TopBar>
        <Layout className="client-body">
          <SideBar></SideBar>
          <div className="client-content">
            <Routes>
              <Route path="/" element={<Navigate to="list" />}></Route>
              <Route path="/list" element={<List />}></Route>
              <Route path="/create" element={<Create />}></Route>
              <Route path="/detail" element={<Detail />}></Route>
              <Route path="/charge" element={<Charge />}></Route>
              <Route path="/data" element={<Data />}></Route>
            </Routes>
          </div>
        </Layout>
      </Layout>
    </div>
  );
};

export default CHome;
