import React, { useEffect, useState } from "react";
import { AuthContext } from "../App";
import car_placeholder from "../placeholder_pics/car_placeholder.png";
import AdvertisementApi from "../apis/AdvertisementAPI";
import GetUserByIdApi from "../apis/GetUserByIdAPI";
import { useNavigate, useLocation } from "react-router-dom";
import AliceCarousel from "react-alice-carousel";
import "react-alice-carousel/lib/alice-carousel.css";

export const AdvertisementPage = () => {
  const navigate = useNavigate();
  const { state } = useLocation();
  const [advert, setAdvert] = useState({
    name: null,
    userCreate: null,
    description: null,
    photos: null,
    brand: null,
    vehicleType: null,
    engine: null,
    gearbox: null,
    doors: null,
    dateManufacture: null,
    kilometers: null,
    price: null,
    datePublish: null,
  });
  const [userCreate, setUserCreate] = useState({
    id: null,
    username: null,
    email: null,
    phone: null,
  });

  const [advPhotos, setAdvPhotos] = useState([]);

  const [id, setId] = useState(null);

  const GetAdvert = () => {
    AdvertisementApi.getAdvertById(state.id)
      .then((response) => {
        setAdvert({
          name: response.advert.name,
          userCreate: response.advert.idUserCreate,
          description: response.advert.description,
          photos: response.advert.add_photos,
          brand: response.advert.brand,
          vehicleType: response.advert.vehicleType,
          engine: response.advert.engine_type,
          gearbox: response.advert.gearbox,
          doors: response.advert.num_doors,
          dateManufacture: response.advert.date_manufacture.substring(0, 10),
          kilometers: response.advert.kilometers,
          price: response.advert.price,
          datePublish: response.advert.date_publish.substring(0, 10),
        });
        setId(state.id);
        console.log("--------SAVED---------");
        console.log(advert);
      })
      .catch((error) => {
        alert(error.message);
        console.log(error.message);
        navigate("/");
      });
    AdvertisementApi.getAdvertPhotos(state.id)
      .then((response) => {
        // console.log("--------------ADV PHOTOS-----------");
        // console.log("--------------response----------");
        // console.log(response);
        setAdvPhotos(response.advertPhotoList);
      })
      .catch((error) => {
        alert(error.message);
        console.log(error.message);
        navigate("/");
      });
    GetUserByIdApi.getUserById(advert.userCreate).then((response) => {
      console.log(response);
      setUserCreate({
        id: response.id,
        username: response.username,
        email: response.email,
        phone: response.phone,
      });
    });
  };

  useEffect(() => {
    GetAdvert();
    console.log(id);
  }, [id]);

  return (
    <div className="container">
      <div className="main-body">
        <hr />
        <div className="row gutters-sm">
          <div className="col-md-4 mb-3">
            <div className="card">
              <div className="card-body">
                <div className="d-flex flex-column align-items-center text-center">
                  {advPhotos.length > 0 ? (
                    <AliceCarousel>
                      {advPhotos.map((ph) => (
                        <img src={ph.photo} alt={ph.photo} width={250} />
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
                  <div className="mt-3">
                    <h3>{advert.name}</h3>
                  </div>
                  <hr />
                  <div className="mt-3">
                    <h5>Published on: {advert.datePublish}</h5>
                  </div>
                </div>
              </div>
            </div>
            <div className="card">
              <div className="card-body">
                <div className="d-flex flex-column align-items-center text-left">
                  <div className="mt-3">
                    <div className="mt-3 ">
                      <h5>Created by user:</h5>
                      <h6>Username: {userCreate.username}</h6>
                      <h6>Email: {userCreate.email}</h6>
                      <h6>Phone: {userCreate.phone}</h6>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div className="card">
              <div className="card-body">
                <div className="d-flex flex-column align-items-center text-center">
                  <div className="mt-3">
                    <h4>Price: {advert.price}$</h4>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className="col-md-8">
            <div className="card mb-3">
              <div className="card-body">
                <div className="row">
                  <div className="col-sm-3">
                    <h6 className="mb-0">Brand: </h6>
                  </div>
                  <div className="col-sm-9 text-secondary">{advert.brand}</div>
                </div>
                <hr />
                <div className="row">
                  <div className="col-sm-3">
                    <h6 className="mb-0">Vehicle type: </h6>
                  </div>
                  <div className="col-sm-9 text-secondary">
                    {advert.vehicleType}
                  </div>
                </div>
                <hr />
                <div className="row">
                  <div className="col-sm-3">
                    <h6 className="mb-0">Engine: </h6>
                  </div>
                  <div className="col-sm-9 text-secondary">{advert.engine}</div>
                </div>
                <hr />
                <div className="row">
                  <div className="col-sm-3">
                    <h6 className="mb-0">Gearbox: </h6>
                  </div>
                  <div className="col-sm-9 text-secondary">
                    {advert.gearbox}
                  </div>
                </div>
                <hr />
                <div className="row">
                  <div className="col-sm-3">
                    <h6 className="mb-0">Doors: </h6>
                  </div>
                  <div className="col-sm-9 text-secondary">{advert.doors}</div>
                </div>
                <hr />
                <div className="row">
                  <div className="col-sm-3">
                    <h6 className="mb-0">Manufactured: </h6>
                  </div>
                  <div className="col-sm-9 text-secondary">
                    {advert.dateManufacture}
                  </div>
                </div>
                <hr />
                <div className="row">
                  <div className="col-sm-3">
                    <h6 className="mb-0">Kilometers: </h6>
                  </div>
                  <div className="col-sm-9 text-secondary">
                    {advert.kilometers}
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <hr />
        <div className="row gutters-sm mb-5">
          <div className="card">
            <div className="card-body">
              <p>{advert.description}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
export default AdvertisementPage;
