import React, { useState, useEffect } from "react";
import ReactDOM from "react-dom";
import jwtDecode from "jwt-decode";
import NavBar from "./components/navbar";
import RegisterPage from "./pages/RegisterPage";
import Login from "./components/Login";
import Home from "./components/Home";
import AccountDetails from "./components/AccountDetails";
import { Link, useNavigate } from "react-router-dom";
import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { act } from "react-dom/test-utils";
import AdvertisementPage from "./components/AdvertisementPage";
import CreateAdvertisementPage from "./components/CreateAdvertisementPage";
import { ToastContainer, toast } from "react-toastify";
import SockJS from "sockjs-client";
import Stomp from "stompjs";
import AdminHomePage from "./components/AdminHomePage";
import BrandsPage from "./components/BrandsPage";
import VehicleTypesPage from "./components/VehilceTypesPage";
import EngineTypesPage from "./components/EngineTypesPage";
import GearboxPage from "./components/GearboxPage";
import StatisticsPage from "./components/StatisticsPage";
import MyAdvertsPage from "./components/MyAdvertsPage";
export const AuthContext = React.createContext();

const initialState = {
  isAuthenticated: false,
  email: null,
  username: null,
  userId: null,
  description: null,
  phone: null,
  token: null,
  role: null,
};

const reducer = (state, action) => {
  switch (action.type) {
    case "LOGIN":
      //console.log("Action: " + action.type);
      const decodedToken = jwtDecode(action.payload);

      localStorage.setItem("isAuthenticated", true);
      localStorage.setItem("email", decodedToken.email);
      //console.log(localStorage.getItem("email"));
      localStorage.setItem("username", decodedToken.username);
      //console.log(localStorage.getItem("username"));
      localStorage.setItem("id", decodedToken.userId);
      // console.log(localStorage.getItem("id"));
      localStorage.setItem("description", decodedToken.description);
      //console.log(localStorage.getItem("description"));
      localStorage.setItem("phone", decodedToken.phone);
      //console.log(localStorage.getItem("phone"));
      localStorage.setItem("role", decodedToken.role);
      //console.log(localStorage.getItem("role"));
      localStorage.setItem("token", action.payload);
      //console.log(localStorage.getItem("token"));

      return {
        ...state,
        isAuthenticated: true,
        username: decodedToken.username,
        email: decodedToken.email,
        userId: decodedToken.userId,
        description: decodedToken.description,
        phone: decodedToken.phone,
        token: action.payload.token,
        role: decodedToken.role,
      };
    case "LOGOUT":
      localStorage.clear();
      return {
        ...state,
        isAuthenticated: false,
        email: null,
        username: null,
        userId: null,
        description: null,
        phone: null,
        token: null,
        role: null,
      };
    default:
      return state;
  }
};

function App() {
  const [state, dispatch] = React.useReducer(reducer, initialState);
  //<-----For the notifications----START---------->
  // Set the backend location
  const ENDPOINT = "http://localhost:8080/ws";
  const [stompClient, setStompClient] = useState();
  useEffect(() => {
    console.log("------Recieving notific-----");
    console.log(state.isAuthenticated);
    if (state.isAuthenticated) {
      // use SockJS as the websocket client
      const socket = SockJS(ENDPOINT);
      // Set stomp to use websockets
      const stompClient = Stomp.over(socket);
      // connect to the backend
      stompClient.connect({}, () => {
        // subscribe to the backend
        stompClient.subscribe("/topic/publicmessages", (data) => {
          console.log(data);
          onMessageReceived(data);
        });
      });
      // maintain the client for sending and receiving
      setStompClient(stompClient);
    }
  }, [state.isAuthenticated]);

  const onMessageReceived = (data) => {
    const message = JSON.parse(data.body);
    console.log(message.text);
    toast.success(message.text, {
      position: toast.POSITION.TOP_RIGHT,
      data: {
        title: "Notification",
      },
    });
    console.log("<---END OF NOTIFICATION---->");
  };
  //<-----For the notifications----END---------->
  //<----- For keeping the logged user logged after page reload --------->
  useEffect(() => {
    if (!state.isAuthenticated && localStorage.getItem("token") != null) {
      state.isAuthenticated = localStorage.getItem("isAuthenticated");
      state.username = localStorage.getItem("username");
      state.email = localStorage.getItem("email");
      state.userId = localStorage.getItem("id");
      state.description = localStorage.getItem("description");
      state.phone = localStorage.getItem("phone");
      state.token = localStorage.getItem("token");
      state.role = localStorage.getItem("role");
    }
  }, []);

  //Clearing local storage on page close
  // window.onunload = () => {
  //   localStorage.clear();
  // };
  // window.addEventListener("beforeunload", (ev) => {
  //   ev.preventDefault();
  //   return (
  //     (ev.returnValue = "Are you sure you want to close?")
  //   );
  // });

  return (
    <AuthContext.Provider value={{ state, dispatch }}>
      <Router>
        <NavBar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/loginPage" element={<Login />} />
          <Route path="/registerPage" element={<RegisterPage />} />
          <Route path="/account" element={<AccountDetails />} />
          <Route path="/advertPage" element={<AdvertisementPage />} />
          <Route path="/createAdvert" element={<CreateAdvertisementPage />} />
          <Route path="/adminHome" element={<AdminHomePage />} />
          <Route path="/brandsPage" element={<BrandsPage />} />
          <Route path="/vehicleTypesPage" element={<VehicleTypesPage />} />
          <Route path="/engineTypesPage" element={<EngineTypesPage />} />
          <Route path="/gearboxPage" element={<GearboxPage />} />
          <Route path="/statisticsPage" element={<StatisticsPage />} />
          <Route path="/myAdvertsPage" element={<MyAdvertsPage />} />
        </Routes>
      </Router>
      <ToastContainer />
    </AuthContext.Provider>
  );
}

export default App;
