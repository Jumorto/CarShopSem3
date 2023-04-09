import React, { useState } from "react";
import RegisterApi from "../apis/RegisterUserAPI";
import { useNavigate } from "react-router-dom";

function RegisterPage() {
  const [newUser, setNewUser] = useState({
    id: "",
    type: "0",
    username: "",
    email: "",
    password: "",
  });

  const RegisterUser = (newUser) => {
    RegisterApi.registerUser(newUser)
      .then((userId) => {
        console.log(`New user created ID: ${userId}`);
        routeChange();
      })
      .catch((error) => {
        if (
          error.response &&
          error.response.status === 400 &&
          error.response.data === "USER_DUPLICATED"
        ) {
          alert("Sorry, a user with this name already exists.");
        } else {
          alert("Sorry, something went wrong. Please try again later.");
        }
      });
  };

  const handleSubmit = (event) => {
    //Prevent page reload
    event.preventDefault();

    RegisterUser(newUser);
  };

  //to redirect from registerPage to login page
  let navigate = useNavigate();
  const routeChange = () => {
    let path = "/loginPage";
    navigate(path);
  };

  return (
    <div className="d-flex justify-content-center align-items-center m-5">
      <div>
        <div>
          <h1>Register</h1>
        </div>
        <form onSubmit={handleSubmit}>
          <div className="form-outline mb-4">
            {}
            <label>Username: </label>
            <input
              type="text"
              name="username"
              className="form-control"
              onChange={(e) =>
                setNewUser({ ...newUser, username: e.target.value })
              }
              value={newUser.username}
              required
            />
            <label>Email: </label>
            <input
              type="text"
              name="email"
              className="form-control"
              onChange={(e) =>
                setNewUser({ ...newUser, email: e.target.value })
              }
              value={newUser.email}
              required
            />
            <label>Password: </label>
            <input
              type="password"
              name="password"
              className="form-control"
              onChange={(e) =>
                setNewUser({ ...newUser, password: e.target.value })
              }
              value={newUser.password}
              required
            />
          </div>
          <div className="d-flex justify-content-center align-items-center">
            <button className="btn btn-warning mb-4 m-1" type="submit">
              Register
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default RegisterPage;
