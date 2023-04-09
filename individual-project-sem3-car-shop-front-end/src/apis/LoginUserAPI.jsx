import axios from "axios";

const BASE_URL = "http://localhost:8080/login";

const LoginApi = {
  loginUser: (email, password) =>
    axios
      .post(BASE_URL, { email: email, password: password })
      .then((response) => response.data),
};

export default LoginApi;
