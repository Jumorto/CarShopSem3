import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import AdvertisementApi from "../apis/AdvertisementAPI";
import { toast } from "react-toastify";
import { Card, Image, Button } from "react-bootstrap";
import AliceCarousel from "react-alice-carousel";
import "react-alice-carousel/lib/alice-carousel.css";
import car_placeholder from "../placeholder_pics/car_placeholder.png";

export const MyAdvertCard = ({ adds }) => {
  const navigate = useNavigate();

  const [advPhotos, setAdvPhotos] = useState([]);

  const GetAdvertPhotos = () => {
    AdvertisementApi.getAdvertPhotos(adds.id)
      .then((response) => {
        //console.log(response);
        setAdvPhotos(response.advertPhotoList);
      })
      .catch((error) => {
        console.log(error.message);
      });
  };

  useEffect(() => {
    GetAdvertPhotos();
  }, []);

  const handleEditClick = (event) => {
    event.preventDefault();
    navigate("/createAdvert", { state: { id: adds.id } });
  };

  const handleDeleteClick = (event) => {
    event.preventDefault();
    const confirmation = window.confirm(
      "Are you sure you want to delete this advert?"
    );

    // If the user clicks 'OK', delete the item
    if (confirmation) {
      AdvertisementApi.deleteAdvertPhotos(adds.id)
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          alert(error.message);
          console.log(error.message);
        });
      AdvertisementApi.deleteAdvert(adds.id)
        .then((response) => {
          console.log(response);
          toast.success("Advertisement deleted!", {
            position: toast.POSITION.TOP_RIGHT,
          });
        })
        .catch((error) => {
          alert(error.message);
          console.log(error.message);
        });
      window.location.reload();
    }
  };

  return (
    <Card>
      <div className="card-img">
        <div className="d-flex flex-column align-items-center text-center mt-2">
          {advPhotos.length > 0 ? (
            <AliceCarousel className="carousel-container">
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
          <Card.Subtitle className="mb-1 text-muted">
            Published: {adds.date_publish.substring(0, 10)}
          </Card.Subtitle>
        </div>
        <div className="mx-3">
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
          <div className="d-flex justify-content-center">
            <h3>Price: {adds.price}$</h3>
          </div>
        </div>
        <Card.Footer>
          <div className="d-flex justify-content-center">
            <button className="btn btn-success mx-3" onClick={handleEditClick}>
              Edit
            </button>
            <button className="btn btn-danger mx-3" onClick={handleDeleteClick}>
              Delete
            </button>
          </div>
        </Card.Footer>
      </Card.Body>
    </Card>
  );
};
export default MyAdvertCard;
