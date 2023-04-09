import React, {
  useEffect,
  useMemo,
  useRef,
  useState,
  useCallback,
} from "react";
import EngineTypesApi from "../apis/EngineTypesAPI";
import { AgGridReact } from "ag-grid-react";
import "ag-grid-community/dist/styles/ag-grid.css";
import "ag-grid-community/dist/styles/ag-theme-alpine.css";

export const EngineTypesPage = () => {
  const gridRef = useRef();
  const gridStyle = useMemo(() => ({ height: 800, width: 492 }));
  const [selectedItem, setItem] = useState({ id: null, engineType: null });

  const [engineTypes, setEngineTypes] = useState([]);

  const [id, setId] = useState(null);

  useEffect(() => {
    EngineTypesApi.getAllEngineTypes()
      .then((response) => {
        setEngineTypes(response.engineTypes);
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
      headerName: "Engine types",
      field: "engineType",
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
        engineType: selectedRows[0].engineType,
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
      EngineTypesApi.editEngineType(
        selectedItem.id,
        selectedItem.engineType
      ).then((response) => {
        console.log(response);
        setId(1);
      });
    } else {
      console.log(selectedItem.engineType);
      EngineTypesApi.createNewEngineType(selectedItem.engineType).then(
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
      EngineTypesApi.deleteEngineType(selectedItem.id).then((response) => {
        console.log(response);
        setId(1);
      });
    } else {
      alert("Select engine type to delete!");
    }
  };

  const handleNewEngineType = (event) => {
    event.preventDefault();
    setItem({ id: null, engineType: null });
  };

  return (
    <div className="container">
      <div className="d-flex justify-content-center align-items-center">
        <div className="row gutters-sm">
          <div className="col-sm mb-3">
            <div style={gridStyle} className="ag-theme-alpine">
              <h2>Engine types</h2>
              <AgGridReact
                ref={gridRef}
                rowData={engineTypes}
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
                  onClick={handleNewEngineType}
                  className="btn btn-info mb-4 m-1"
                  type="submit"
                >
                  New engine type
                </button>

                <div className="d-flex flex-column">
                  <label className="form-label">Enter engine type: </label>
                  <input
                    type="text"
                    className="form-control"
                    value={
                      selectedItem.engineType != null
                        ? selectedItem.engineType
                        : ""
                    }
                    onChange={handleInputChange}
                    name="engineType"
                    id="engineType"
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
export default EngineTypesPage;
