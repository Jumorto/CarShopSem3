import axios from "axios";

const BASE_URL = "http://localhost:8080/users/";

const EditUserApi = {
  editUser: (id, username, description, phone) =>
    axios
      .put(BASE_URL + id, {
        id: id,
        username: username,
        description: description,
        phone: phone,
      })
      .then((response) => response.data),
};
export default EditUserApi;
