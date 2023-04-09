import axios from "axios";

axios.interceptors.request.use(
  function (config) {
    // modify the config object as needed
    const token = localStorage.getItem("token");
    if (token != undefined) {
      config.headers["Authorization"] = "Bearer " + token;
    }
    return config;
  },
  function (error) {
    return Promise.reject(error);
  }
);

const BASE_URL = "http://localhost:8080/statistics/";

const StatisticsApi = {
  getTop5Brands: () =>
    axios.get(BASE_URL + "brands").then((response) => response.data),
  getChartAdvertsByKilometers: () =>
    axios.get(BASE_URL + "advertKilometers").then((response) => response.data),
  getKilometersByBrand: (id) =>
    axios
      .get(BASE_URL + "kilometersByBrand/" + id)
      .then((response) => response.data),
};
export default StatisticsApi;
