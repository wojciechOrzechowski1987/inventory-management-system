import React from "react";
import useGet from "../../hooks/Get";
import CircularProgress from "@mui/material/CircularProgress";
import VendorForm from "./components/VendorForm";
import ErrorPage from "../errorPage/ErrorPage";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";

export default function AddVendorPage() {
  const {
    error: errorProductItems,
    isPending: isPendingProductItems,
    data: productItems,
  } = useGet("http://localhost:8080/productItem/notAssigned");

  const vendor = {
    vendorName: "",
    productItems: [],
  };

  return (
    <React.Fragment>
      {isPendingProductItems && <CircularProgress color="success" />}
      {errorProductItems && <ErrorPage />}
      {productItems && (
        <Grid container direction="column" alignItems={"center"}>
          <Grid item marginTop={2} marginBottom={2}>
            <Typography>NOWY DOSTAWCA</Typography>
          </Grid>
          <Grid item>
            <VendorForm vendor={vendor} productItems={productItems} />
          </Grid>
        </Grid>
      )}
    </React.Fragment>
  );
}
