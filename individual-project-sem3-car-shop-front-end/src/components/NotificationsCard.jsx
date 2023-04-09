const MessageReceived = (props) => {
  return (
    <div>
      <b>{props.from}</b>: {props.text} {props.direct ? <b>(direct)</b> : ""}
    </div>
  );
};

const NotificationsCard = (props) => {
  return (
    <div className="container d-flex justify-content-center">
      <div className="card mt-5 p-3">
        <div className="media">
          <h2>Messages:</h2>
          <div className="media-body">
            <h6 className="mt-2 mb-0">
              {props.messagesReceived
                .filter((message) => message.from !== props.username)
                .map((message) => (
                  <MessageReceived
                    key={message.id}
                    from={message.from}
                    direct={message.to === props.username}
                    text={message.text}
                  />
                ))}
            </h6>
            <small className="text">Mar 20,2020</small>
          </div>
        </div>
      </div>
    </div>
  );
};

export default NotificationsCard;
