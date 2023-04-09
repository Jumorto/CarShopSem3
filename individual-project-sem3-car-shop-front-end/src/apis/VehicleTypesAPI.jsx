import axios from "axios";

const BASE_URL = "http://localhost:8080/vehicleType/";

const VehicleTypesApi = {
  getAllVehicleTypes: () =>
    axios.get(BASE_URL).then((response) => response.data),
  createNewVehicleType: (vehicleType) =>
    axios.post(BASE_URL, { vehicleType: vehicleType }),
  editVehicleType: (id, vehicleType) =>
    axios
      .put(BASE_URL + id, {
        id: id,
        vehicleType: vehicleType,
      })
      .then((response) => response.data),
  deleteVehicleType: (id) =>
    axios
      .delete(BASE_URL, { params: { id } })
      .then((response) => response.data),
};
export default VehicleTypesApi;
