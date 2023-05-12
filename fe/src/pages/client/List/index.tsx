import AdvContainer from "./AdvContainer";
import TopBar from "./TopBar";
import "./index.scss";

const CList = (): JSX.Element => {
  return (
    <div className="client-list">
      <TopBar></TopBar>
      <AdvContainer></AdvContainer>
    </div>
  );
};

export default CList;
