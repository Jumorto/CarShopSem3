import React, { useState, useEffect } from "react";
import { AuthContext } from "../App";
import AdvertisementApi from "../apis/AdvertisementAPI";
import MyAdvertCard from "./MyAdvertCard";

export const MyAdvertsPage = () => {
  const { state: authState } = React.useContext(AuthContext);
  const [myAdverts, setMyAdverts] = useState([]);

  const GetMyAdverts = () => {
    if (authState.userId != null) {
      AdvertisementApi.getAdvertisementsByIdUser(authState.userId)
        .then((response) => {
          setMyAdverts(response.adds);
        })
        .catch((error) => {
          alert(error.message);
          console.log(error.message);
        });
    } else {
      AdvertisementApi.getAdvertisementsByIdUser(localStorage.getItem("id"))
        .then((response) => {
          setMyAdverts(response.adds);
        })
        .catch((error) => {
          alert(error.message);
          console.log(error.message);
        });
    }
  };

  useEffect(() => {
    GetMyAdverts();
  }, []);

  return (
    <React.Fragment>
      <div className="home">
        <>
          {myAdverts.length > 0 ? (
            myAdverts.map((adverts) => (
              <MyAdvertCard key={adverts.id} adds={adverts} />
            ))
          ) : (
            <span className="noAdds">
              You have not created any advertisements. To create an
              advertisement go to Create Advertisement Page.
            </span>
          )}
        </>
      </div>
    </React.Fragment>
  );
};
export default MyAdvertsPage;
