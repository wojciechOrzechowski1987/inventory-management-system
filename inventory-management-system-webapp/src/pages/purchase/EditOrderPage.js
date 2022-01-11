import React from "react";
import Box from "@mui/material/Box";
import useGet from "../../hooks/Get";
import CircularProgress from "@mui/material/CircularProgress";
import { useParams } from "react-router-dom";
import OrderForm from "./component/OrderForm";
import ErrorPage from "../errorPage/ErrorPage";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";

export default function EditOrderPage() {
  const { demandId } = useParams();

  const {
    error: errorVendors,
    isPending: isPendingVendors,
    data: projects,
  } = useGet("http://localhost:8080/project");

  const {
    error: errorPopcMaterials,
    isPending: isPendingPopcMaterials,
    data: popcMaterials,
  } = useGet("http://localhost:8080/popcMaterial");

  const {
    error: errorDemand,
    isPending: isPendingMaterialsDemand,
    data: demand,
  } = useGet("http://localhost:8080/demand/id/" + demandId);

  return (
    <React.Fragment>
      {isPendingMaterialsDemand &&
        isPendingPopcMaterials &&
        isPendingVendors && <CircularProgress color="success" />}
      {(errorDemand || errorVendors || errorPopcMaterials) && <ErrorPage />}
      {projects && popcMaterials && demand && (
        <Grid container direction="column" alignItems={"center"}>
          <Grid item marginTop={2} marginBottom={2}>
            <Typography>EDYCJA ZAMÓWIENIA</Typography>
          </Grid>
          <Grid item>
            <OrderForm
              projects={projects}
              demand={demand}
              popcMaterials={popcMaterials}
            />
          </Grid>
        </Grid>
      )}
    </React.Fragment>
  );
}
