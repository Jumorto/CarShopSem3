import axios from "axios";

const BASE_URL = "http://localhost:8080/users";

const RegisterApi = {
  registerUser: (newUser) =>
    axios.post(BASE_URL, newUser).then((response) => response.data.userId),
};

export default RegisterApi;
