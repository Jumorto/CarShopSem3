import React, {
  useEffect,
  useMemo,
  useRef,
  useState,
  useCallback,
} from "react";
import VehicleTypesApi from "../apis/VehicleTypesAPI";
import { AgGridReact } from "ag-grid-react";
import "ag-grid-community/dist/styles/ag-grid.css";
import "ag-grid-community/dist/styles/ag-theme-alpine.css";

export const VehicleTypesPage = () => {
  const gridRef = useRef();
  const gridStyle = useMemo(() => ({ height: 800, width: 492 }));
  const [selectedItem, setItem] = useState({ id: null, vehicleType: null });

  const [vehicleTypes, setVehicles] = useState([]);

  const [id, setId] = useState(null);

  useEffect(() => {
    VehicleTypesApi.getAllVehicleTypes()
      .then((response) => {
        setVehicles(response.vehicleTypes);
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
      headerName: "Vehicle types",
      field: "vehicleType",
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
        vehicleType: selectedRows[0].vehicleType,
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
      VehicleTypesApi.editVehicleType(
        selectedItem.id,
        selectedItem.vehicleType
      ).then((response) => {
        console.log(response);
        setId(1);
      });
    } else {
      console.log(selectedItem.vehicleType);
      VehicleTypesApi.createNewVehicleType(selectedItem.vehicleType).then(
        (response) => {
          console.log(response);
          setId(1);
        }
      );
    }
  };

  const handleDelete = (event) => {
    event.preventDefault();
    if (selectedItem.id != null) {
      VehicleTypesApi.deleteVehicleType(selectedItem.id).then((response) => {
        console.log(response);
        setId(1);
      });
    } else {
      alert("Select vehicle type to delete!");
    }
  };

  const handleNewVehicleType = (event) => {
    event.preventDefault();
    setItem({ id: null, vehicleType: null });
  };

  return (
    <div className="container">
      <div className="d-flex justify-content-center align-items-center">
        <div className="row gutters-sm">
          <div className="col-sm mb-3">
            <div style={gridStyle} className="ag-theme-alpine">
              <h2>Vehicle types</h2>
              <AgGridReact
                ref={gridRef}
                rowData={vehicleTypes}
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
                  onClick={handleNewVehicleType}
                  className="btn btn-info mb-4 m-1"
                  type="submit"
                >
                  New vehicle type
                </button>

                <div className="d-flex flex-column">
                  <label className="form-label">Enter vehicle type: </label>
                  <input
                    type="text"
                    className="form-control"
                    value={
                      selectedItem.vehicleType != null
                        ? selectedItem.vehicleType
                        : ""
                    }
                    onChange={handleInputChange}
                    name="vehicleType"
                    id="vehicleType"
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
export default VehicleTypesPage;
