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
  url: string;
  startTime: number | null;
  endTime: number | null;
  status: number;
}

interface AdvList {
  total: number;
  list: Array<AdvCardData>;
}

interface Adv {
  title: string;
  description: string;
  url: string;
  status: number;
  startTime: string | null;
  endTime: string | null;
}

export type { SideItem, AdvCardData, AdvList, Adv };
