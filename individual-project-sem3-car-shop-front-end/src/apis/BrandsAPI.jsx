import axios from "axios";

const BASE_URL = "http://localhost:8080/brand/";

const BrandsApi = {
  getAllBrands: () => axios.get(BASE_URL).then((response) => response.data),
  createNewBrand: (brandName) => axios.post(BASE_URL, { brandName: brandName }),
  editBrand: (id, brandName) =>
    axios
      .put(BASE_URL + id, {
        id: id,
        brandName: brandName,
      })
      .then((response) => response.data),
  deleteBrand: (id) =>
    axios
      .delete(BASE_URL, { params: { id } })
      .then((response) => response.data),
};
export default BrandsApi;
