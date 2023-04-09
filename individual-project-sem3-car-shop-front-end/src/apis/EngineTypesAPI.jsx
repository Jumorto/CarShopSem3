import axios from "axios";

const BASE_URL = "http://localhost:8080/engineType/";

const EngineTypesApi = {
  getAllEngineTypes: () =>
    axios.get(BASE_URL).then((response) => response.data),
  createNewEngineType: (engineType) =>
    axios.post(BASE_URL, { engineType: engineType }),
  editEngineType: (id, engineType) =>
    axios
      .put(BASE_URL + id, {
        id: id,
        engineType: engineType,
      })
      .then((response) => response.data),
  deleteEngineType: (id) =>
    axios
      .delete(BASE_URL, { params: { id } })
      .then((response) => response.data),
};
export default EngineTypesApi;
