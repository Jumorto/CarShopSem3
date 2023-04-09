import React, { useState, useEffect } from "react";
import { AuthContext } from "../App";
import placeholder_user from "../placeholder_pics/placeholder_user.jpg";
import EditUserApi from "../apis/EditAccountAPI";

export const AccountDetails = () => {
  const { state: authState } = React.useContext(AuthContext);
  const [editVar, setEditVar] = useState({ editVar: false });

  const initailState = {
    username: "",
    description: "",
    phone: "",
    errorMessage: null,
  };

  const [data, setData] = React.useState(initailState);

  const [init, setInit] = useState();

  const handleInputChange = (event) => {
    setData({
      ...data,
      [event.target.name]: event.target.value,
    });
  };

  const handleEditClick = (event) => {
    event.preventDefault();
    setEditVar({ editVar: true });
    console.log(editVar);
  };

  const handleFormSubmit = (event) => {
    event.preventDefault();
    setEditVar({ editVar: false });
    console.log(editVar);

    setData({
      ...data,
    });

    EditUserApi.editUser(
      authState.userId,
      data.username,
      data.description,
      data.phone
    )
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        setData({
          ...data,
          errorMessage: error.message || error.statusText,
        });
      });
  };

  return (
    <div className="container">
      <div className="main-body">
        <hr />
        <form onSubmit={handleFormSubmit}>
          <div className="row gutters-sm">
            <div className="col-md-4 mb-3">
              <div className="card">
                <div className="card-body">
                  <div className="d-flex flex-column align-items-center text-center">
                    <div className="mt-3">
                      <h4>{authState.email}</h4>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div className="col-md-8">
              <div className="card mb-3">
                <div className="card-body">
                  <div className="row">
                    <div className="input-group mb-3">
                      <div className="input-group-prepend">
                        <span className="input-group-text" id="basic-addon1">
                          User name:
                        </span>
                      </div>
                      <input
                        type="text"
                        className="form-control"
                        defaultValue={authState.username}
                        onChange={handleInputChange}
                        name="username"
                        id="username"
                        disabled={!editVar.editVar}
                      />
                    </div>
                  </div>
                  <hr />
                  <div className="row">
                    <div className="input-group mb-3">
                      <div className="input-group-prepend">
                        <span className="input-group-text" id="basic-addon1">
                          Phone:
                        </span>
                      </div>
                      <input
                        type="text"
                        className="form-control"
                        defaultValue={authState.phone}
                        onChange={handleInputChange}
                        name="phone"
                        id="phone"
                        disabled={!editVar.editVar}
                      />
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className="row gutters-sm">
            <textarea
              className="form-control"
              defaultValue={authState.description}
              onChange={handleInputChange}
              name="description"
              id="description"
              rows="7"
              disabled={!editVar.editVar}
            />
          </div>
          <br />

          {!editVar.editVar ? (
            <div className="col-sm-12">
              <button className="btn btn-info " onClick={handleEditClick}>
                Edit Account Info
              </button>
            </div>
          ) : (
            <div className="col-sm-12">
              <button type="submit" className="btn btn-success ">
                Save account info
              </button>
            </div>
          )}
        </form>
      </div>
    </div>
  );
};
export default AccountDetails;
