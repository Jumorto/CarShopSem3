import React, {
  useEffect,
  useMemo,
  useRef,
  useState,
  useCallback,
} from "react";
import BrandsApi from "../apis/BrandsAPI";
import { useLocation } from "react-router-dom";
import { AgGridReact } from "ag-grid-react";
import "ag-grid-community/dist/styles/ag-grid.css";
import "ag-grid-community/dist/styles/ag-theme-alpine.css";

export const BrandsPage = () => {
  const gridRef = useRef();
  const gridStyle = useMemo(() => ({ height: 800, width: 492 }));
  const [selectedItem, setItem] = useState({ id: null, brandName: null });

  const [brands, setBrands] = useState([]);

  const [id, setId] = useState(null);

  useEffect(() => {
    BrandsApi.getAllBrands()
      .then((response) => {
        setBrands(response.brands);
        setId(0);
      })
      .catch((error) => {
        // alert(error.message);
        console.log(error.message);
      });
  }, [id]);

  const [columnDefs] = useState([
    { headerName: "#", valueGetter: "node.id", width: 90 },
    {
      headerName: "Brands",
      field: "brandName",
      width: 400,
      resizable: true,
      filter: true,
    },
    { headerName: "ID", field: "id", hide: "true" },
  ]);

  const getRowId = useMemo(() => {
    return (params) => params.data.id;
  }, []);

  const onSelectionChanged = useCallback(() => {
    const selectedRows = gridRef.current.api.getSelectedRows();
    if (selectedRows.length === 1) {
      setItem({
        id: selectedRows[0].id,
        brandName: selectedRows[0].brandName,
      });
    }
  }, []);

  const handleInputChange = (event) => {
    setItem({
      ...selectedItem,
      [event.target.name]: event.target.value,
    });
  };

  const handleSave = (event) => {
    event.preventDefault();
    if (selectedItem.id != null) {
      BrandsApi.editBrand(selectedItem.id, selectedItem.brandName).then(
        (response) => {
          console.log(response);
          setId(1);
        }
      );
    } else {
      console.log(selectedItem.brandName);
      BrandsApi.createNewBrand(selectedItem.brandName).then((response) => {
        console.log(response);
        setId(1);
      });
    }
  };

  const handleDelete = (event) => {
    event.preventDefault();
    if (selectedItem.id != null) {
      BrandsApi.deleteBrand(selectedItem.id).then((response) => {
        console.log(response);
        setId(1);
      });
    } else {
      alert("Select brand to delete!");
    }
  };

  const handleNewBrand = (event) => {
    event.preventDefault();
    setItem({ id: null, brandName: null });
  };

  return (
    <div className="container">
      <div className="d-flex justify-content-center align-items-center">
        <div className="row gutters-sm">
          <div className="col-sm mb-3">
            <div style={gridStyle} className="ag-theme-alpine">
              <h2>Brands</h2>
              {/* <div>
            Selection:
            <span id="selectedRows"></span>
          </div> */}

              <AgGridReact
                ref={gridRef}
                rowData={brands}
                columnDefs={columnDefs}
                rowSelection={"single"}
                getRowId={getRowId}
                paginationPageSize={15}
                pagination={true}
                onSelectionChanged={onSelectionChanged}
              ></AgGridReact>
            </div>
          </div>
          <div className="col-sm mb-3">
            <form>
              <div>
                <button
                  onClick={handleNewBrand}
                  className="btn btn-info mb-4 m-1"
                  type="submit"
                >
                  New Brand
                </button>

                <div className="d-flex flex-column">
                  <label className="form-label">Enter brand: </label>
                  <input
                    type="text"
                    className="form-control"
                    value={
                      selectedItem.brandName != null
                        ? selectedItem.brandName
                        : ""
                    }
                    onChange={handleInputChange}
                    name="brandName"
                    id="brandName"
                    required
                  />
                </div>

                <div className="d-flex flex-row justify-content-center align-items-center">
                  <button
                    onClick={handleSave}
                    className="btn btn-success mb-4 m-1"
                    type="submit"
                  >
                    Save
                  </button>
                  <button
                    onClick={handleDelete}
                    className="btn btn-danger mb-4 m-1"
                    type="submit"
                  >
                    Delete
                  </button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};
export default BrandsPage;
