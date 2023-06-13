interface MessageItem {
  title: string;
  desc: string;
}

const useMessage = () => {
  let messageItem = [
    {
      title: "广告审核",
      desc: "你的广告已通过审核",
    },
  ];

  console.log(messageItem);

  return messageItem;
};

export default useMessage;
