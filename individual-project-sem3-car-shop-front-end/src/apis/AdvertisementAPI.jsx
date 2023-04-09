import axios from "axios";

const BASE_URL = "http://localhost:8080/advertisement/";

const AdvertisementApi = {
  getAllAdvertisements: () =>
    axios.get(BASE_URL).then((response) => response.data),
  getAdvertisementsByIdUser: (idUserCreate) =>
    axios
      .get(BASE_URL, {
        params: { idUserCreate },
      })
      .then((response) => response.data),
  searchAdverts: (queryParams) =>
    axios
      .get(BASE_URL, { params: queryParams })
      .then((response) => response.data),
  getAdvertById: (id) =>
    axios.get(BASE_URL + id).then((response) => response.data),
  createNewAdvert: (
    idUserCreate,
    name,
    description,
    photos,
    vehicleType,
    brand,
    engineType,
    gearbox,
    numOfDoors,
    kilometers,
    dateManufacture,
    price
  ) =>
    axios
      .post(BASE_URL, {
        idUserCreate: idUserCreate,
        name: name,
        description: description,
        photos: photos,
        vehicleType: vehicleType,
        brand: brand,
        engineType: engineType,
        gearbox: gearbox,
        numOfDoors: numOfDoors,
        kilometers: kilometers,
        dateManufacture: dateManufacture,
        price: price,
      })
      .then((response) => response.data),
  uploadPhotos: (files, idAdvert) =>
    axios
      .post(BASE_URL + idAdvert, { photos: files })
      .then((response) => response.data),
  updateAdvert: (
    idAdvert,
    name,
    description,
    photos,
    vehicleType,
    brand,
    engineType,
    gearbox,
    numOfDoors,
    kilometers,
    dateManufacture,
    price
  ) =>
    axios
      .put(BASE_URL + idAdvert, {
        name: name,
        description: description,
        photos: photos,
        vehicleType: vehicleType,
        brand: brand,
        engineType: engineType,
        gearbox: gearbox,
        numOfDoors: numOfDoors,
        kilometers: kilometers,
        dateManufacture: dateManufacture,
        price: price,
      })
      .then((response) => response.data),
  deleteAdvert: (id) =>
    axios
      .delete(BASE_URL, { params: { id } })
      .then((response) => response.data),
  getAdvertPhotos: (id) =>
    axios.get(BASE_URL + "photos/" + id).then((response) => response.data),
  deleteAdvertPhotos: (id) =>
    axios.delete(BASE_URL + "photos/" + id).then((response) => response.data),
};

export default AdvertisementApi;
