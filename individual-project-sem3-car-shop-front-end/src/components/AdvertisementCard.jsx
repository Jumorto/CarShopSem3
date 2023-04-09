import React, { useEffect, useState } from "react";
import AdvertisementApi from "../apis/AdvertisementAPI";
import AdvertisementPage from "./AdvertisementPage";
import { useNavigate } from "react-router-dom";
import AliceCarousel from "react-alice-carousel";
import "react-alice-carousel/lib/alice-carousel.css";
import car_placeholder from "../placeholder_pics/car_placeholder.png";
import { Card, Image, Button } from "react-bootstrap";

export const AdvertisementCard = ({ adds }) => {
  const navigate = useNavigate();

  const [advPhotos, setAdvPhotos] = useState([]);

  const GetAdvertPhotos = () => {
    AdvertisementApi.getAdvertPhotos(adds.id)
      .then((response) => {
        setAdvPhotos(response.advertPhotoList);
      })
      .catch((error) => {
        console.log(error.message);
      });
  };

  useEffect(() => {
    GetAdvertPhotos();
  }, []);

  const handleClick = (event) => {
    event.preventDefault();
    navigate("/advertPage", { state: { id: adds.id } });
  };

  return (
    <Card>
      <div className="card-img">
        <div className="d-flex flex-column align-items-center text-center mt-2">
          {advPhotos.length > 0 ? (
            <AliceCarousel>
              {advPhotos.map((ph) => (
                <Image src={ph.photo} alt={ph.photo} width={250} />
              ))}
            </AliceCarousel>
          ) : (
            <AliceCarousel>
              <Image src={car_placeholder} alt="icon" width={250} />
            </AliceCarousel>
          )}
        </div>
      </div>
      <Card.Body>
        <div className="d-flex flex-column align-items-center text-center">
          <Card.Title>{adds.name}</Card.Title>
          <Card.Subtitle className="mb-2 text-muted">
            Published: {adds.date_publish.substring(0, 10)}
          </Card.Subtitle>
        </div>
        <div className="mx-4 mb-3">
          <Card.Text>
            Brand: {adds.brand}
            <br />
            Vehicle type: {adds.vehicleType}
            <br />
            Engine: {adds.engine_type}
            <br />
            Gearbox: {adds.gearbox}
            <br />
            Doors: {adds.num_doors}
            <br />
            Manufactured: {adds.date_manufacture.substring(0, 10)}
            <br />
            Kilometers: {adds.kilometers}
          </Card.Text>
        </div>
        <Card.Footer>
          <div className="d-flex justify-content-center">
            <h3>Price: {adds.price}$</h3>
          </div>
          <div className="d-flex justify-content-center">
            <Button variant="primary" onClick={handleClick}>
              View Offer
            </Button>
          </div>
        </Card.Footer>
      </Card.Body>
    </Card>
  );
};
export default AdvertisementCard;
