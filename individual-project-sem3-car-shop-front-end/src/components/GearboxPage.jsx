import React, {
  useEffect,
  useMemo,
  useRef,
  useState,
  useCallback,
} from "react";
import GearboxApi from "../apis/GearboxAPI";
import { AgGridReact } from "ag-grid-react";
import "ag-grid-community/dist/styles/ag-grid.css";
import "ag-grid-community/dist/styles/ag-theme-alpine.css";

export const GearboxPage = () => {
  const gridRef = useRef();
  const gridStyle = useMemo(() => ({ height: 800, width: 492 }));
  const [selectedItem, setItem] = useState({ id: null, gearboxType: null });

  const [gearboxes, setGearboxes] = useState([]);

  const [id, setId] = useState(null);

  useEffect(() => {
    GearboxApi.getAllGearboxes()
      .then((response) => {
        setGearboxes(response.gearboxes);
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
      headerName: "Gearboxes",
      field: "gearboxType",
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
        gearboxType: selectedRows[0].gearboxType,
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
      GearboxApi.editGearbox(selectedItem.id, selectedItem.gearboxType).then(
        (response) => {
          console.log(response);
          setId(1);
        }
      );
    } else {
      console.log(selectedItem.engineType);
      GearboxApi.createNewGearbox(selectedItem.gearboxType).then((response) => {
        console.log(response);
        setId(1);
      });
    }
  };

  const handleDelete = (event) => {
    event.preventDefault();
    if (selectedItem.id != null) {
      GearboxApi.deleteGearbox(selectedItem.id).then((response) => {
        console.log(response);
        setId(1);
      });
    } else {
      alert("Select gearbox to delete!");
    }
  };

  const handleNewGearbox = (event) => {
    event.preventDefault();
    setItem({ id: null, gearboxType: null });
  };

  return (
    <div className="container">
      <div className="d-flex justify-content-center align-items-center">
        <div className="row gutters-sm">
          <div className="col-sm mb-3">
            <div style={gridStyle} className="ag-theme-alpine">
              <h2>Gearboxes</h2>
              <AgGridReact
                ref={gridRef}
                rowData={gearboxes}
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
                  onClick={handleNewGearbox}
                  className="btn btn-info mb-4 m-1"
                  type="submit"
                >
                  New gearbox
                </button>

                <div className="d-flex flex-column">
                  <label className="form-label">Enter gearbox: </label>
                  <input
                    type="text"
                    className="form-control"
                    value={
                      selectedItem.gearboxType != null
                        ? selectedItem.gearboxType
                        : ""
                    }
                    onChange={handleInputChange}
                    name="gearboxType"
                    id="gearboxType"
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
export default GearboxPage;
