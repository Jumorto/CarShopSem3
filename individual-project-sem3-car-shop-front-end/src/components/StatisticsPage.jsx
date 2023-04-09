import React, { useEffect, useState } from "react";
import { Chart } from "react-google-charts";
import StatisticsApi from "../apis/StatisticsAPI";
import BrandsApi from "../apis/BrandsAPI";

export const StatisticsPage = () => {
  const [dataTop5Brands, setTop5Brands] = useState([]);
  const [dataAdvertsByKilometers, setAdvertsByKilometers] = useState([]);
  const [dataKilometersByBrand1, setKilometersByBrand1] = useState([]);
  const [dataKilometersByBrand2, setKilometersByBrand2] = useState([]);
  const [optionsBrand, setBrandOptions] = useState([]);
  const [selectedBrandOption1, setSelectedBrandOption1] = useState("");
  const [selectedBrandOption2, setSelectedBrandOption2] = useState("");

  useEffect(() => {
    LoadDropDowns();
    LoadBarChartsInitially();
    //Chart 1
    StatisticsApi.getTop5Brands()
      .then((response) => {
        const chartData = [];
        chartData.push(["Brands", "Count"]);
        response.top5Brands.forEach((item) => {
          chartData.push([item[0], item[1]]);
        });
        setTop5Brands(chartData);
      })
      .catch((error) => {
        console.log(error.message);
      });

    //Chart 2
    StatisticsApi.getChartAdvertsByKilometers()
      .then((response) => {
        const chartData = [];
        chartData.push(["Kilometers", "from - to"]);
        response.advertsByKilometers.forEach((item) => {
          chartData.push([item[0], item[1]]);
        });
        setAdvertsByKilometers(chartData);
      })
      .catch((error) => {
        console.log(error.message);
      });
  }, []);

  const optionsAdvertsByKilometers = {
    title: "Number of advertisemenets by vehicle kilometers",
  };

  const optionsTop5Brands = {
    title: "Top 5 Brands",
  };

  const optionsKilometersByBrand = {
    chart: {
      title: "Cars by brands and kilometers",
      subtitle: "Brands, Kilometers range",
    },
  };

  function LoadDropDowns() {
    BrandsApi.getAllBrands()
      .then((response) => {
        setBrandOptions(response.brands);
      })
      .catch((error) => {
        console.log(error.message);
      });
  }

  function LoadBarChartsInitially() {
    const chartData = [];
    chartData.push([
      "Brand",
      "Kilometers range 0 - 100000",
      "Kilometers range 100001 - 150000",
      "Kilometers range 150001 - 200000",
      "Kilometers range 200000 - ...",
    ]);
    chartData.push([0, 0, 0, 0, 0]);
    setKilometersByBrand1(chartData);
    setKilometersByBrand2(chartData);
  }

  function handleBrandOptionChange1(event) {
    setSelectedBrandOption1(event.target.value);
  }
  function handleBrandOptionChange2(event) {
    setSelectedBrandOption2(event.target.value);
  }

  function handleGetKilometersByBrandChart1(event) {
    event.preventDefault();
    //Chart 3.1
    StatisticsApi.getKilometersByBrand(selectedBrandOption1)
      .then((response) => {
        const chartData = [];
        chartData.push([
          "Brand",
          "Kilometers range 0 - 100000",
          "Kilometers range 100001 - 150000",
          "Kilometers range 150001 - 200000",
          "Kilometers range 200000 - ...",
        ]);
        console.log(response.kilometersByBrand);
        chartData.push(response.kilometersByBrand);
        setKilometersByBrand1(chartData);
      })
      .catch((error) => {
        console.log(error.message);
        const chartData = [];
        chartData.push([
          "Brand",
          "Kilometers range 0 - 100000",
          "Kilometers range 100001 - 150000",
          "Kilometers range 150001 - 200000",
          "Kilometers range 200000 - ...",
        ]);
        chartData.push([0, 0, 0, 0, 0]);
        setKilometersByBrand1(chartData);
      });
  }
  function handleGetKilometersByBrandChart2(event) {
    event.preventDefault();
    //Chart 3.2
    StatisticsApi.getKilometersByBrand(selectedBrandOption2)
      .then((response) => {
        const chartData = [];
        chartData.push([
          "Brand",
          "Kilometers range 0 - 100000",
          "Kilometers range 100001 - 150000",
          "Kilometers range 150001 - 200000",
          "Kilometers range 200000 - ...",
        ]);
        console.log(response.kilometersByBrand);
        chartData.push(response.kilometersByBrand);
        setKilometersByBrand2(chartData);
      })
      .catch((error) => {
        console.log(error.message);
        const chartData = [];
        chartData.push([
          "Brand",
          "Kilometers range 0 - 100000",
          "Kilometers range 100001 - 150000",
          "Kilometers range 150001 - 200000",
          "Kilometers range 200000 - ...",
        ]);
        chartData.push([0, 0, 0, 0, 0]);
        setKilometersByBrand2(chartData);
      });
  }

  return (
    <div className="container mx-auto">
      <Chart
        chartType="PieChart"
        data={dataTop5Brands}
        options={optionsTop5Brands}
        width={"100%"}
        height={"400px"}
      />

      <Chart
        chartType="PieChart"
        data={dataAdvertsByKilometers}
        options={optionsAdvertsByKilometers}
        width={"100%"}
        height={"400px"}
      />

      <div className="row">
        <div className="col-sm-6 mb-5">
          <div className="input-group mb-1">
            <div className="col-sm-2">
              <span className="input-group-text">Brand</span>
            </div>
            <div className="input-group-prepend col-sm-3">
              <select
                className="form-select"
                value={selectedBrandOption1}
                onChange={handleBrandOptionChange1}
              >
                <option value=""></option>
                {optionsBrand.map((optionsBrand) => (
                  <option key={optionsBrand.id} value={optionsBrand.id}>
                    {optionsBrand.brandName}
                  </option>
                ))}
              </select>
            </div>
            <button
              className="btn btn-success mb-4"
              onClick={handleGetKilometersByBrandChart1}
            >
              Get chart
            </button>
          </div>
          <Chart
            chartType="Bar"
            width="100%"
            height="400px"
            data={dataKilometersByBrand1}
            options={optionsKilometersByBrand}
          />
        </div>
        <div className="col-sm-6 mb-5">
          <div className="input-group mb-1">
            <div className="col-sm-2">
              <span className="input-group-text">Brand</span>
            </div>
            <div className="input-group-prepend col-sm-3">
              <select
                className="form-select"
                value={selectedBrandOption2}
                onChange={handleBrandOptionChange2}
              >
                <option value=""></option>
                {optionsBrand.map((optionsBrand) => (
                  <option key={optionsBrand.id} value={optionsBrand.id}>
                    {optionsBrand.brandName}
                  </option>
                ))}
              </select>
            </div>
            <button
              className="btn btn-success mb-4"
              onClick={handleGetKilometersByBrandChart2}
            >
              Get chart
            </button>
          </div>
          <Chart
            chartType="Bar"
            width="100%"
            height="400px"
            data={dataKilometersByBrand2}
            options={optionsKilometersByBrand}
          />
        </div>
      </div>
    </div>
  );
};
export default StatisticsPage;
