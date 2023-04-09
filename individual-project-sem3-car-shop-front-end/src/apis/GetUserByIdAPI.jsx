import axios from "axios";

const BASE_URL = "http://localhost:8080/users/userForAdvert/";

const GetUserByIdApi = {
  getUserById: (idUser) =>
    axios.get(BASE_URL + idUser).then((response) => response.data),
};

export default GetUserByIdApi;
