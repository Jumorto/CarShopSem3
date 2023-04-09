import React, { Component, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { AuthContext } from "../App";

function NavBar() {
  const { state: authState, dispatch } = React.useContext(AuthContext);

  const handleLogout = (event) => {
    {
      dispatch({
        type: "LOGOUT",
      });
    }
  };

  return (
    <header className="p-3 text-bg-dark">
      <div className="container">
        <div className="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
          {(() => {
            if (
              (authState.isAuthenticated ||
                localStorage.getItem("isAuthenticated")) &&
              ((localStorage.getItem("role") &&
                localStorage.getItem("role") === "normal") ||
                (authState.role[0] != null && authState.role[0] === "normal"))
            ) {
              return (
                <>
                  <Link
                    to="/"
                    className="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none"
                  >
                    <svg
                      className="bi me-2"
                      width="40"
                      height="32"
                      role="img"
                      aria-label="Bootstrap"
                    ></svg>
                  </Link>

                  <ul className="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                    <li>
                      <Link to="/" className="nav-link px-2 text-white">
                        Home
                      </Link>
                    </li>
                    <li>
                      <Link to="/account" className="nav-link px-2 text-white">
                        Account Info
                      </Link>
                    </li>
                    <li>
                      <Link
                        to="/createAdvert"
                        className="nav-link px-2 text-white"
                      >
                        Create Advertisement
                      </Link>
                    </li>
                    <li>
                      <Link
                        to="/myAdvertsPage"
                        className="nav-link px-2 text-white"
                      >
                        My Advertisements
                      </Link>
                    </li>
                  </ul>
                </>
              );
            } else if (
              (authState.isAuthenticated ||
                localStorage.getItem("isAuthenticated")) &&
              ((localStorage.getItem("role") &&
                localStorage.getItem("role") === "admin") ||
                (authState.role[0] != null && authState.role[0] === "admin"))
            ) {
              return (
                <ul className="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                  <li>
                    <Link to="/adminHome" className="nav-link px-2 text-white">
                      Home
                    </Link>
                  </li>
                </ul>
              );
            } else {
              return (
                <ul className="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                  <li>
                    <Link to="/" className="nav-link px-2 text-white">
                      Home
                    </Link>
                  </li>
                  <li>
                    <Link
                      to="/registerPage"
                      className="nav-link px-2 text-white"
                    >
                      Sign-Up
                    </Link>
                  </li>
                </ul>
              );
            }
          })()}

          {!authState.isAuthenticated ? (
            <div className="text-end">
              <Link to="/loginPage">
                <button type="button" className="btn btn-outline-light me-2">
                  Login
                </button>
              </Link>
              <Link to="/registerPage">
                <button type="button" className="btn btn-warning">
                  Register
                </button>
              </Link>
            </div>
          ) : (
            <div>
              <Link to="/">
                <button
                  onClick={handleLogout}
                  type="button"
                  className="btn btn-warning"
                >
                  Logout
                </button>
              </Link>
            </div>
          )}
        </div>
      </div>
    </header>
  );
}

export default NavBar;
