import React, { useState, useEffect } from "react";
import { AuthContext } from "../App";
import AdvertisementCard from "./AdvertisementCard";
import AdvertisementApi from "../apis/AdvertisementAPI";
import loadingGIF from "../placeholder_pics/loadingGIF.gif";
import VehicleTypesApi from "../apis/VehicleTypesAPI";
import BrandsApi from "../apis/BrandsAPI";
import EngineTypesApi from "../apis/EngineTypesAPI";
import GearboxApi from "../apis/GearboxAPI";

const initialState = {
  advertisements: [],
  isFetching: false,
  hasError: false,
};

const reducer = (state, action) => {
  switch (action.type) {
    case "FETCH_ADV-REQUEST":
      return {
        ...state,
        isFetching: true,
        hasError: false,
      };
    case "FETCH_ADV_SUCCESS":
      //console.log(action.payload);
      return {
        ...state,
        isFetching: false,
        advertisements: action.payload.adds,
      };
    case "FETCH_ADV_FAILURE":
      return {
        ...state,
        isFetching: false,
        hasError: true,
      };
    default:
      return state;
  }
};
export const Home = () => {
  const { state: authState } = React.useContext(AuthContext);
  const [state, dispatch] = React.useReducer(reducer, initialState);

  //------For loading the dropdown menus--------start------//
  const [optionsVehicleType, setVehicleTypeOptions] = useState([]);
  const [optionsBrand, setBrandOptions] = useState([]);
  const [optionsEngineType, setEngineTypeOptions] = useState([]);
  const [optionsGearbox, setGearboxOptions] = useState([]);

  const [selectedVehicleTypeOption, setSelectedVehicleTypeOption] =
    useState("");
  const [selectedBrandOption, setSelectedBrandOption] = useState("");
  const [selectedEngineTypeOption, setSelectedEngineTypeOption] = useState("");
  const [selectedGearboxOption, setSelectedGearboxOption] = useState("");
  const [priceMin, setPriceMin] = useState("");
  const [priceMax, setPriceMax] = useState("");
  //const [addName, setAddName] = useState("");
  //------For loading the dropdown menus--------end------//

  function LoadDropDowns() {
    VehicleTypesApi.getAllVehicleTypes()
      .then((response) => {
        setVehicleTypeOptions(response.vehicleTypes);
      })
      .catch((error) => {
        // alert(error.message);
        console.log(error.message);
      });
    BrandsApi.getAllBrands()
      .then((response) => {
        setBrandOptions(response.brands);
      })
      .catch((error) => {
        // alert(error.message);
        console.log(error.message);
      });
    EngineTypesApi.getAllEngineTypes()
      .then((response) => {
        setEngineTypeOptions(response.engineTypes);
      })
      .catch((error) => {
        // alert(error.message);
        console.log(error.message);
      });
    GearboxApi.getAllGearboxes()
      .then((response) => {
        setGearboxOptions(response.gearboxes);
      })
      .catch((error) => {
        // alert(error.message);
        console.log(error.message);
      });
  }

  React.useEffect(() => {
    LoadDropDowns();
    //console.log("Starting dispatching!");
    dispatch({ type: "FETCH_ADV-REQUEST" });
    AdvertisementApi.getAllAdvertisements()
      .then((response) => {
        console.log(response);
        dispatch({
          type: "FETCH_ADV_SUCCESS",
          payload: response,
        });
      })
      .catch((error) => {
        console.log(error);
        dispatch({
          type: "FETCH_ADV_FAILURE",
        });
      });
  }, [authState.token]);

  function handleVehicleTypeOptionChange(event) {
    setSelectedVehicleTypeOption(event.target.value);
    console.log(selectedVehicleTypeOption);
  }

  function handleBrandOptionChange(event) {
    setSelectedBrandOption(event.target.value);
    console.log(selectedBrandOption);
  }

  function handleEngineTypeOptionChange(event) {
    setSelectedEngineTypeOption(event.target.value);
    console.log(selectedEngineTypeOption);
  }

  function handleGearboxOptionChange(event) {
    setSelectedGearboxOption(event.target.value);
    console.log(selectedGearboxOption);
  }

  function handlePriceMinChange(event) {
    setPriceMin(event.target.value);
  }

  function handlePriceMaxChange(event) {
    setPriceMax(event.target.value);
  }

  function handleSearchClick(event) {
    event.preventDefault();
    const queryParams = {};

    if (selectedVehicleTypeOption != null) {
      queryParams.vehicleType = selectedVehicleTypeOption;
    }

    if (selectedBrandOption != null) {
      queryParams.brand = selectedBrandOption;
    }

    if (selectedEngineTypeOption != null) {
      queryParams.engine_type = selectedEngineTypeOption;
    }

    if (selectedGearboxOption != null) {
      queryParams.gearbox = selectedGearboxOption;
    }

    if (priceMin.value >= priceMax.value) {
      alert("Minumum price must be lower than maximum price!");
    } else {
      if (priceMin != null) {
        queryParams.priceMin = priceMin;
      }
      if (priceMax != null) {
        queryParams.priceMax = priceMax;
      }
    }

    console.log(queryParams);
    AdvertisementApi.searchAdverts(queryParams)
      .then((response) => {
        console.log(response);
        dispatch({
          type: "FETCH_ADV_SUCCESS",
          payload: response,
        });
      })
      .catch((error) => {
        console.log(error);
        dispatch({
          type: "FETCH_ADV_FAILURE",
        });
      });
  }

  return (
    <React.Fragment>
      <div className="home">
        <div className="row">
          <hr />
          <form role="search">
            <div className="row gutters-sm">
              <div className="row">
                <div className="col-sm-4">
                  <div className="input-group mb-3">
                    <div className="col-sm-4">
                      <span className="input-group-text">Vehicle type</span>
                    </div>
                    <div className="input-group-prepend">
                      <select
                        className="form-select"
                        value={selectedVehicleTypeOption}
                        onChange={handleVehicleTypeOptionChange}
                      >
                        <option value=""></option>
                        {optionsVehicleType.map((optionsVehicleType) => (
                          <option
                            key={optionsVehicleType.id}
                            value={optionsVehicleType.id}
                          >
                            {optionsVehicleType.vehicleType}
                          </option>
                        ))}
                      </select>
                    </div>
                  </div>
                </div>
                <div className="col-sm-4">
                  <div className="input-group mb-3">
                    <div className="col-sm-4">
                      <span className="input-group-text">Brand</span>
                    </div>
                    <div className="input-group-prepend">
                      <select
                        className="form-select"
                        value={selectedBrandOption}
                        onChange={handleBrandOptionChange}
                      >
                        <option value=""></option>
                        {optionsBrand.map((optionsBrand) => (
                          <option key={optionsBrand.id} value={optionsBrand.id}>
                            {optionsBrand.brandName}
                          </option>
                        ))}
                      </select>
                    </div>
                  </div>
                </div>
              </div>
              <div className="row">
                <div className="col-sm-4">
                  <div className="input-group mb-3">
                    <div className="col-sm-4">
                      <span className="input-group-text">Engine type</span>
                    </div>
                    <div className="input-group-prepend">
                      <select
                        className="form-select"
                        value={selectedEngineTypeOption}
                        onChange={handleEngineTypeOptionChange}
                      >
                        <option value=""></option>
                        {optionsEngineType.map((optionsEngineType) => (
                          <option
                            key={optionsEngineType.id}
                            value={optionsEngineType.id}
                          >
                            {optionsEngineType.engineType}
                          </option>
                        ))}
                      </select>
                    </div>
                  </div>
                </div>
                <div className="col-sm-4">
                  <div className="input-group mb-3">
                    <div className="col-sm-4">
                      <span className="input-group-text">Gearbox</span>
                    </div>
                    <div className="input-group-prepend">
                      <select
                        className="form-select"
                        value={selectedGearboxOption}
                        onChange={handleGearboxOptionChange}
                      >
                        <option value=""></option>
                        {optionsGearbox.map((optionsGearbox) => (
                          <option
                            key={optionsGearbox.id}
                            value={optionsGearbox.id}
                          >
                            {optionsGearbox.gearboxType}
                          </option>
                        ))}
                      </select>
                    </div>
                  </div>
                </div>
              </div>
              <div className="row">
                <div className="col-sm-4">
                  <div className="input-group mb-3">
                    <div className="col-sm-4">
                      <span className="input-group-text"> Minimum price</span>
                    </div>
                    <div className="input-group-prepend">
                      <input
                        type="number"
                        className="form-control"
                        defaultValue={priceMin}
                        onChange={handlePriceMinChange}
                        name="priceMin"
                        id="priceMin"
                        min="0"
                      />
                    </div>
                  </div>
                </div>
                <div className="col-sm-4">
                  <div className="input-group mb-3">
                    <div className="col-sm-4">
                      <span className="input-group-text"> Maximum price</span>
                    </div>
                    <div className="input-group-prepend">
                      <input
                        type="number"
                        className="form-control"
                        defaultValue={priceMax}
                        onChange={handlePriceMaxChange}
                        name="priceMax"
                        id="priceMax"
                        min="0"
                      />
                    </div>
                  </div>
                </div>
              </div>
              <div className="col">
                <button
                  type="submit"
                  className="btn btn-outline-dark me-2 "
                  onClick={handleSearchClick}
                >
                  Search
                </button>
              </div>
            </div>
          </form>
        </div>
        {/* {console.log("Starting fetching!")} */}
        {state.isFetching ? (
          <span className="loader">
            {/* Loading... */}
            <img
              src={loadingGIF}
              alt="loading"
              className="rounded-circle"
              width={150}
              height={150}
            />
          </span>
        ) : state.hasError ? (
          <span className="error">
            Error has occured when displaying the advertisements. Sorry for the
            inconvenience!
          </span>
        ) : (
          <>
            {state.advertisements.length > 0 ? (
              state.advertisements.map((adds) => (
                <AdvertisementCard key={adds.id} adds={adds} />
              ))
            ) : (
              <span className="noAdds">
                Sorry we dont have any adds here...
              </span>
            )}
          </>
        )}
      </div>
    </React.Fragment>
  );
};
export default Home;
