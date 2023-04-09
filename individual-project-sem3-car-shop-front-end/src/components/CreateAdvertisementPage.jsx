import React, { useState, useEffect } from "react";
import NotificationsApi from "../apis/NotificationsAPI";
import { AuthContext } from "../App";
import "react-toastify/dist/ReactToastify.css";
import { v4 as uuidv4 } from "uuid";
import VehicleTypesApi from "../apis/VehicleTypesAPI";
import BrandsApi from "../apis/BrandsAPI";
import EngineTypesApi from "../apis/EngineTypesAPI";
import GearboxApi from "../apis/GearboxAPI";
import Calendar from "react-calendar";
import "react-calendar/dist/Calendar.css";
import AdvertisementApi from "../apis/AdvertisementAPI";
import AliceCarousel from "react-alice-carousel";
import "react-alice-carousel/lib/alice-carousel.css";
import car_placeholder from "../placeholder_pics/car_placeholder.png";
import { toast } from "react-toastify";
import { useLocation } from "react-router-dom";

export const CreateAdvertisementPage = () => {
  const { state: authState } = React.useContext(AuthContext);
  const { state } = useLocation();
  const [idAdvert, setIdAdvert] = useState();

  //Notifications-----------start-------------------//
  const showToastMessage = (event) => {
    event.preventDefault();
    console.log("<---SENDING NOTIFICATION---->");
    const textMesBrand = optionsBrand.find((x) => x.id == selectedBrandOption);
    console.log(textMesBrand);
    const textMess =
      "Advertisement for " + textMesBrand.brandName + " has been created!";
    NotificationsApi.sendNotification(uuidv4(), authState.username, textMess);
  };
  //Notifications------------end------------------//

  //------For loading the dropdown menus--------start------//
  const [optionsVehicleType, setVehicleTypeOptions] = useState([]);
  const [optionsBrand, setBrandOptions] = useState([]);
  const [optionsEngineType, setEngineTypeOptions] = useState([]);
  const [optionsGearbox, setGearboxOptions] = useState([]);
  //------For loading the dropdown menus--------end------//

  //------ Main data saved --------start--------//
  const [advertName, setAdvertName] = useState("");
  const [selectedVehicleTypeOption, setSelectedVehicleTypeOption] =
    useState("");
  const [selectedBrandOption, setSelectedBrandOption] = useState("");
  const [selectedEngineTypeOption, setSelectedEngineTypeOption] = useState("");
  const [selectedGearboxOption, setSelectedGearboxOption] = useState("");
  const [numDoors, setNumDoors] = useState("");
  const [kilometers, setKilometers] = useState("");
  const [selectedManufactureDate, setSelectedManufactureDate] = useState(
    new Date()
  );
  const [price, setPrice] = useState("");
  const [description, setDescription] = useState("");
  //------ Main data saved --------end--------//

  //-----Calendar toggle and set-----start-----//
  const [showManufactureCalendar, setShowManufactureCalendar] = useState(false);
  function toggleManufactureCalendar(event) {
    event.preventDefault();
    setShowManufactureCalendar(!showManufactureCalendar);
  }
  //-----Calendar toggle and set-----end-----//

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

  useEffect(() => {
    LoadDropDowns();

    //Load if we are edditing an existing advert
    if (state != null) {
      GetAdvert();
      console.log(state.id);
    }
  }, []);

  function handleAdvertNameChange(event) {
    setAdvertName(event.target.value);
  }

  function handleVehicleTypeOptionChange(event) {
    setSelectedVehicleTypeOption(event.target.value);
  }

  function handleBrandOptionChange(event) {
    setSelectedBrandOption(event.target.value);
  }

  function handleEngineTypeOptionChange(event) {
    setSelectedEngineTypeOption(event.target.value);
  }

  function handleGearboxOptionChange(event) {
    setSelectedGearboxOption(event.target.value);
  }

  function handleNumDoorsChange(event) {
    setNumDoors(event.target.value);
  }

  function handleKilometersChange(event) {
    setKilometers(event.target.value);
  }

  function handlePriceChange(event) {
    setPrice(event.target.value);
  }

  function handleDescriptionChange(event) {
    setDescription(event.target.value);
  }

  function handleFormSubmit(event) {
    event.preventDefault();

    // console.log(advertName);
    // console.log(selectedVehicleTypeOption);
    // console.log(selectedBrandOption);
    // console.log(selectedEngineTypeOption);
    // console.log(selectedGearboxOption);
    // console.log(numDoors);
    // console.log(kilometers);
    // console.log(selectedManufactureDate);
    // console.log(price);
    // console.log(description);

    if (idAdvert == null) {
      AdvertisementApi.createNewAdvert(
        authState.userId,
        advertName,
        description,
        null,
        selectedVehicleTypeOption,
        selectedBrandOption,
        selectedEngineTypeOption,
        selectedGearboxOption,
        numDoors,
        kilometers,
        selectedManufactureDate,
        price
      )
        .then((response) => {
          console.log(response);
          setIdAdvert(response.addId);
        })
        .catch((error) => {
          console.log(error.message);
          alert(
            "Creating new advertisement failed! Please, check for any unselected fields!"
          );
        });
      showToastMessage(event);
    } else {
      AdvertisementApi.updateAdvert(
        idAdvert,
        advertName,
        description,
        null,
        selectedVehicleTypeOption,
        selectedBrandOption,
        selectedEngineTypeOption,
        selectedGearboxOption,
        numDoors,
        kilometers,
        selectedManufactureDate,
        price
      )
        .then((response) => {
          console.log(response);
          toast.success("Update saved!", {
            position: toast.POSITION.TOP_RIGHT,
          });
        })
        .catch((error) => {
          console.log(error.message);
          alert(
            "Creating new advertisement failed! Please, check for any unselected fields!"
          );
        });
    }
  }

  const [photoBase64, setPhotosBase64] = useState([]);
  const [favImages, setFavImages] = useState([]);

  function handleFileChange(event) {
    const files = Array.from(event.target.files);

    files.forEach((file) => {
      const fileReader = new FileReader();
      fileReader.readAsDataURL(file);

      fileReader.onloadend = () => {
        setFavImages((prevFavImages) => [
          ...prevFavImages,
          { photo: fileReader.result },
        ]);
        console.log("Setting files---------------");
        console.log(fileReader.result);

        setPhotosBase64((prevBase64Strings) => [
          ...prevBase64Strings,
          fileReader.result,
        ]);
      };
    });
  }

  function handleFilesUpload(event) {
    event.preventDefault();
    if (idAdvert != undefined) {
      if (photoBase64.length > 0) {
        AdvertisementApi.uploadPhotos(photoBase64, idAdvert)
          .then((response) => {
            console.log(response);
            toast.success("Images uploaded!", {
              position: toast.POSITION.TOP_RIGHT,
            });
          })
          .catch((error) => {
            console.log(error.message);
            alert("Uploading images failed! Select images to be uploaded.");
          });
      } else {
        toast.error("Uploading images failed! Select images to be uploaded.", {
          position: toast.POSITION.TOP_RIGHT,
        });
      }
    } else {
      alert("You must first create an advertisement before you upload images!");
    }
  }

  //------------ Getting the data of an existing already advert for updating ------------//

  function GetAdvert() {
    AdvertisementApi.getAdvertById(state.id)
      .then((response) => {
        console.log("-----------START ADVERT DATA--------------");
        console.log(response);

        setIdAdvert(response.advert.id);
        setAdvertName(response.advert.name);
        setDescription(response.advert.description);
        setSelectedBrandOption(response.advert.idBrand);
        setSelectedVehicleTypeOption(response.advert.idVehicleType);
        setSelectedEngineTypeOption(response.advert.idEngineType);
        setSelectedGearboxOption(response.advert.idGearbox);
        setSelectedManufactureDate(new Date(response.advert.date_manufacture));
        setKilometers(Number(response.advert.kilometers));
        setNumDoors(Number(response.advert.num_doors));
        setPrice(Number(response.advert.price));

        console.log("-----------END ADVERT DATA--------------");
      })
      .catch((error) => {
        alert(error.message);
        console.log(error.message);
      });
    //-----Getting the photos for the advert------
    AdvertisementApi.getAdvertPhotos(state.id)
      .then((response) => {
        // console.log("--------------ADV PHOTOS-----------");
        // console.log("--------------response----------");
        // console.log(response);
        setFavImages(response.advertPhotoList);
      })
      .catch((error) => {
        alert(error.message);
        console.log(error.message);
      });
  }

  function handleDeletePhotos(event) {
    event.preventDefault();
    if (idAdvert != null) {
      AdvertisementApi.deleteAdvertPhotos(idAdvert)
        .then((response) => {
          console.log(response);
          toast.success("Images deleted!", {
            position: toast.POSITION.TOP_RIGHT,
          });
          setFavImages([]);
          setPhotosBase64([]);
        })
        .catch((error) => {
          alert(error.message);
          console.log(error.message);
        });
    }
  }

  return (
    <div>
      <div className="container">
        <div className="main-body">
          <h2 className="text-center">Advertisement editor</h2>
          <hr />
          <form onSubmit={handleFilesUpload}>
            <div className="row">
              <div className="col-md-4 mb-3">
                {favImages.length > 0 ? (
                  <AliceCarousel>
                    {favImages.map((ph) => (
                      <img src={ph.photo} alt="icons" width={250} />
                    ))}
                  </AliceCarousel>
                ) : (
                  <img
                    src={car_placeholder}
                    alt="icons"
                    width={250}
                    role="presentation"
                  />
                )}
                <input
                  type="file"
                  id="upload-multiple-images"
                  multiple
                  onChange={handleFileChange}
                />
              </div>
              <div className="col-md-4 mb-3">
                <button className="btn btn-success mb-4 m-1" type="submit">
                  Upload pictures
                </button>
                <button
                  className="btn btn-danger mb-4 m-1"
                  onClick={handleDeletePhotos}
                >
                  Delete photos
                </button>
              </div>
            </div>
          </form>
          <form onSubmit={handleFormSubmit}>
            <div className="row gutters-sm">
              <div className="col-md-8">
                <div className="card mb-3">
                  <div className="card-body">
                    <div className="row">
                      <div className="input-group mb-3">
                        <div className="input-group-prepend">
                          <span className="input-group-text" id="basic-addon1">
                            Name:
                          </span>
                        </div>
                        <input
                          type="text"
                          className="form-control"
                          defaultValue={advertName}
                          onChange={handleAdvertNameChange}
                          name="advertName"
                          id="advertName"
                        />
                      </div>
                    </div>
                    <hr />
                    <div className="row">
                      <div className="col-sm">
                        <div className="input-group mb-3">
                          <div className="col-sm-4">
                            <span className="input-group-text">
                              Vehicle type
                            </span>
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
                      <div className="col-sm">
                        <div className="input-group mb-3">
                          <div className="col-sm-3">
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
                                <option
                                  key={optionsBrand.id}
                                  value={optionsBrand.id}
                                >
                                  {optionsBrand.brandName}
                                </option>
                              ))}
                            </select>
                          </div>
                        </div>
                      </div>
                    </div>
                    <hr />
                    <div className="row">
                      <div className="col-sm">
                        <div className="input-group mb-3">
                          <div className="col-sm-4">
                            <span className="input-group-text">
                              Engine type
                            </span>
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
                      <div className="col-sm">
                        <div className="input-group mb-3">
                          <div className="col-sm-3">
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
                    <hr />
                    <div className="row">
                      <div className="input-group mb-3">
                        <div className="input-group-prepend">
                          <span className="input-group-text" id="basic-addon1">
                            Number of doors:
                          </span>
                        </div>
                        <input
                          type="number"
                          className="form-control"
                          defaultValue={numDoors}
                          onChange={handleNumDoorsChange}
                          name="numDoors"
                          id="numDoors"
                        />
                      </div>
                    </div>
                    <hr />
                    <div className="row">
                      <div className="input-group mb-3">
                        <div className="input-group-prepend">
                          <span className="input-group-text" id="basic-addon1">
                            Kilometers:
                          </span>
                        </div>
                        <input
                          type="number"
                          className="form-control"
                          defaultValue={kilometers}
                          onChange={handleKilometersChange}
                          name="kilometers"
                          id="kilometers"
                        />
                      </div>
                    </div>
                    <hr />
                    <div className="row">
                      <div className="input-group-prepend">
                        <span className="input-group-text" id="basic-addon1">
                          Manufacture date:
                          <button
                            className="btn btn-outline-secondary"
                            onClick={toggleManufactureCalendar}
                          >
                            {showManufactureCalendar
                              ? "Hide Calendar"
                              : "Show Calendar"}
                          </button>
                        </span>
                      </div>
                      {showManufactureCalendar && (
                        <Calendar
                          onChange={(date) => setSelectedManufactureDate(date)}
                          value={selectedManufactureDate}
                        />
                      )}
                    </div>
                    <hr />
                    <div className="row">
                      <div className="input-group mb-3">
                        <div className="input-group-prepend">
                          <span className="input-group-text" id="basic-addon1">
                            Price:
                          </span>
                        </div>
                        <input
                          type="number"
                          className="form-control"
                          defaultValue={price}
                          onChange={handlePriceChange}
                          name="price"
                          id="price"
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
                defaultValue={description}
                onChange={handleDescriptionChange}
                name="description"
                id="description"
                rows="7"
              />
            </div>
            <br />
            <div className="d-flex flex-column justify-content-center align-items-center">
              <button
                className="btn btn-success mb-4 m-1"
                type="submit"
                onSubmit={showToastMessage}
              >
                <h2>Save</h2>
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};
export default CreateAdvertisementPage;
