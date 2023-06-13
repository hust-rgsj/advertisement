import useGetData from "@/hooks/useGetData";
import "./index.scss";
import { Card, Col, Row } from "antd";
import * as eCharts from "echarts";
import React, { useEffect } from "react";

const Data = () => {
  const data = useGetData();

  const eChartsRef: any = React.createRef();

  const option = {
    title: {
      text: "实时数据",
    },
    tooltip: {
      trigger: "axis",
    },
    legend: {
      data: ["查看", "点击"],
    },
    grid: {
      left: "3%",
      right: "4%",
      bottom: "3%",
      containLabel: true,
    },
    toolbox: {
      feature: {
        saveAsImage: {},
      },
    },
    xAxis: {
      type: "category",
      boundaryGap: false,
      data: ["00:00", "06:00", "12:00", "18:00"],
    },
    yAxis: {
      type: "value",
    },
    series: [
      {
        name: "查看",
        type: "line",
        stack: "Total",
        data: data.view,
      },
      {
        name: "点击",
        type: "line",
        stack: "Total",
        data: data.click,
      },
    ],
  };

  useEffect(() => {
    const myChart = eCharts.init(eChartsRef.current);
    myChart.setOption(option);
  }, []);

  return (
    <div className="data-container">
      <div className="data-title">核心数据</div>
      <Row gutter={16} className="data-content">
        <Col span={8}>
          <Card title="累计查看次数" bordered={false}>
            <h3>{data.totalView}</h3>
            <p>{`日：${data.totalDayView}`}</p>
            <p>{`周：${data.totalWeekView}`}</p>
            <p>{`月：${data.totalMonthView}`}</p>
          </Card>
        </Col>
        <Col span={8}>
          <Card title="累计点击次数" bordered={false}>
            <h3>{data.totalClick}</h3>
            <p>{`日：${data.totalDayClick}`}</p>
            <p>{`周：${data.totalWeekClick}`}</p>
            <p>{`月：${data.totalMonthClick}`}</p>
          </Card>
        </Col>
        <Col span={8}>
          <Card title="点击率" bordered={false}>
            <h3>{`${((data.totalClick / data.totalView) * 100).toFixed(
              2
            )}%`}</h3>
            <p>{`日：${((data.totalDayClick / data.totalDayView) * 100).toFixed(
              2
            )}%`}</p>
            <p>{`周：${(
              (data.totalWeekClick / data.totalWeekView) *
              100
            ).toFixed(2)}%`}</p>
            <p>{`月：${(
              (data.totalMonthClick / data.totalMonthView) *
              100
            ).toFixed(2)}%`}</p>
          </Card>
        </Col>
      </Row>
      <div
        ref={eChartsRef}
        style={{
          width: 600,
          height: 300,
          marginTop: 40,
        }}
      ></div>
      ;
    </div>
  );
};

export default Data;
