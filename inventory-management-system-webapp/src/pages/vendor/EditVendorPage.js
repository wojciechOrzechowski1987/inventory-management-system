import React from "react";
import useGet from "../../hooks/Get";
import CircularProgress from "@mui/material/CircularProgress";
import { useLocation } from "react-router-dom";
import VendorForm from "./components/VendorForm";
import { useTheme } from "@emotion/react";
import ErrorPage from "../errorPage/ErrorPage";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";

export default function EditVendorPage() {
  const theme = useTheme();
  const location = useLocation();
  const { editedVendor } = location.state;

  const {
    error: errorProductItems,
    isPending: isPendingProductItems,
    data: productItems,
  } = useGet(
    "http://localhost:8080/productItem/" +
      editedVendor.id +
      "/productItemsForEdit"
  );

  const {
    error: errorVendor,
    isPending: isPendingVendor,
    data: vendor,
  } = useGet("http://localhost:8080/vendor/id/" + editedVendor.id);

  return (
    <React.Fragment>
      {isPendingProductItems && isPendingVendor && (
        <CircularProgress color="success" />
      )}
      {(errorVendor || errorProductItems) && <ErrorPage />}
      {productItems && vendor && (
        <Grid container direction="column" alignItems={"center"}>
          <Grid item marginTop={2} marginBottom={2}>
            <Typography>EDYCJA DOSTAWCY</Typography>
          </Grid>
          <Grid item>
            <VendorForm vendor={vendor} productItems={productItems} />
          </Grid>
        </Grid>
      )}
    </React.Fragment>
  );
}
