import axios from "axios";

const BASE_URL = "http://localhost:8080/gearbox/";

const GearboxApi = {
  getAllGearboxes: () => axios.get(BASE_URL).then((response) => response.data),
  createNewGearbox: (gearboxType) =>
    axios.post(BASE_URL, { gearboxType: gearboxType }),
  editGearbox: (id, gearboxType) =>
    axios
      .put(BASE_URL + id, {
        id: id,
        gearboxType: gearboxType,
      })
      .then((response) => response.data),
  deleteGearbox: (id) =>
    axios
      .delete(BASE_URL, { params: { id } })
      .then((response) => response.data),
};
export default GearboxApi;
