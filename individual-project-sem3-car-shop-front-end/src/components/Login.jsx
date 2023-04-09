import React from "react";
import logo from "../logo.svg";
import LoginApi from "../apis/LoginUserAPI";
import jwtDecode from "jwt-decode";
import { AuthContext } from "../App";
import { Link, useNavigate } from "react-router-dom";

export const Login = () => {
  const { dispatch } = React.useContext(AuthContext);
  const navigate = useNavigate();
  const initailState = {
    email: "",
    password: "",
    isSubmitting: false,
    errorMessage: null,
  };

  const [data, setData] = React.useState(initailState);
  const handleInputChange = (event) => {
    setData({
      ...data,
      [event.target.name]: event.target.value,
    });
  };

  const handleFormSubmit = (event) => {
    event.preventDefault();
    setData({
      ...data,
      isSubmitting: true,
      errorMessage: null,
    });

    LoginApi.loginUser(data.email, data.password)
      .then((response) => {
        //console.log(response);

        // console.log("...........token.........");
        // console.log(response.accessToken);

        dispatch({
          type: "LOGIN",
          payload: response.accessToken,
        });
        const decodedTokenRole = jwtDecode(response.accessToken);
        if (decodedTokenRole.role == "admin") {
          navigate("/adminHome");
        } else {
          navigate("/");
        }
      })
      .catch((error) => {
        setData({
          ...data,
          isSubmitting: false,
          errorMessage: error.message || error.statusText,
        });
      });
  };

  return (
    <div className="d-flex justify-content-center align-items-center m-5">
      <div>
        <h3>Login</h3>
        <form onSubmit={handleFormSubmit}>
          <div>
            <div className="d-flex flex-column">
              <label className="form-label">Email: </label>
              <input
                type="text"
                className="form-control"
                value={data.email}
                onChange={handleInputChange}
                name="email"
                id="email"
                required
              />
            </div>
            <div className="d-flex flex-column mb-3">
              <label className="form-label">Password: </label>
              <input
                type="password"
                className="form-control"
                value={data.password}
                onChange={handleInputChange}
                name="password"
                id="password"
                required
              />
            </div>

            {data.errorMessage && (
              <span className="form-error">{data.errorMessage}</span>
            )}

            <div className="d-flex flex-column justify-content-center align-items-center">
              <button
                disabled={data.isSubmitting}
                className="btn btn-success mb-4 m-1"
                type="submit"
              >
                {data.isSubmitting ? "Loading..." : "Login"}
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
};
export default Login;
