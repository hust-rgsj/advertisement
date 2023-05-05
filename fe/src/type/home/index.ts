import { To } from "react-router";

interface SideItem {
  key: number;
  label: string;
  path: To;
}

interface AdvCardData {
  id: number;
  title: string;
  description: string;
  img: string;
  startTime: number | null;
  endTime: number | null;
  status: number;
}

export type { SideItem, AdvCardData };
