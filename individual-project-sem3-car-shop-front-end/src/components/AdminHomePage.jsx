import React from "react";
import { AuthContext } from "../App";
import { Link } from "react-router-dom";

export const AdminHomePage = () => {
  const { state: authState } = React.useContext(AuthContext);
  return (
    <div className="py-16 lg:py-64 flex justify-center items-center">
      <div className="container text-center grid grid-rows-[1fr,auto,1fr] items-end gap-8">
        <h1 className="card-title justify-center text-3xl">
          Welcome back, {authState.username}!
        </h1>
        <hr />
        <div className="row gutters-sm">
          <div className="col-sm mb-3">
            <h3>Main parameters</h3>
            <Link
              to={"/brandsPage"}
              className="card bg-base-100 p-4 text-base-content transition-colors hover:bg-base-200 items-center flex flex-col rounded-box"
            >
              <h4>BrandsPage</h4>
            </Link>
            <Link
              to={"/vehicleTypesPage"}
              className="card bg-base-100 p-4 text-base-content transition-colors hover:bg-base-200 items-center flex flex-col rounded-box"
            >
              <h4>Vehicle types</h4>
            </Link>
            <Link
              to={"/engineTypesPage"}
              className="card bg-base-100 p-4 text-base-content transition-colors hover:bg-base-200 items-center flex flex-col rounded-box"
            >
              <h4>Engine types</h4>
            </Link>
            <Link
              to={"/gearboxPage"}
              className="card bg-base-100 p-4 text-base-content transition-colors hover:bg-base-200 items-center flex flex-col rounded-box"
            >
              <h4>Gearboxes</h4>
            </Link>
          </div>
          <div className="col-sm mb-3">
            <h3>Statistics for website</h3>
            <Link
              to={"/statisticsPage"}
              className="card bg-base-100 p-4 text-base-content transition-colors hover:bg-base-200 items-center flex flex-col rounded-box"
            >
              <h4>View statistics</h4>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};
export default AdminHomePage;
