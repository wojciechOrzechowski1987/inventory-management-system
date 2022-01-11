import React from "react";
import useGet from "../../hooks/Get";
import CircularProgress from "@mui/material/CircularProgress";
import { useLocation } from "react-router-dom";
import ProductItemForm from "./components/ProductItemForm";
import ErrorPage from "../errorPage/ErrorPage";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";

export default function EditProductItemPage() {
  const location = useLocation();
  const { editedProductItem } = location.state;

  const {
    error: errorVendors,
    isPending: isPendingVendors,
    data: vendors,
  } = useGet("http://localhost:8080/vendor/basic");

  const {
    error: errorPopcMaterials,
    isPending: isPendingPopcMaterials,
    data: popcMaterials,
  } = useGet("http://localhost:8080/popcMaterial/basic");

  return (
    <React.Fragment>
      {isPendingVendors && isPendingPopcMaterials && (
        <CircularProgress color="success" />
      )}
      {(errorVendors || errorPopcMaterials) && <ErrorPage />}
      {vendors && popcMaterials && (
        <Grid container direction="column" alignItems={"center"}>
          <Grid item marginTop={2} marginBottom={2}>
            <Typography>EDYCJA PRODUKTU</Typography>
          </Grid>
          <Grid item>
            <ProductItemForm
              vendors={vendors}
              popcMaterials={popcMaterials}
              productItem={editedProductItem}
            />
          </Grid>
        </Grid>
      )}
    </React.Fragment>
  );
}
