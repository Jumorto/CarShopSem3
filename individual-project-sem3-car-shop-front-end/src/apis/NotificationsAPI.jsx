import axios from "axios";

const BASE_URL = "http://localhost:8080/notifications";

const NotificationsApi = {
  sendNotification: (id, from, text) =>
    axios
      .post(BASE_URL, { id: id, from: from, text: text })
      .then((response) => response.data),
};

export default NotificationsApi;
