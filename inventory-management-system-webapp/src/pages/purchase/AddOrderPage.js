import React from "react";
import Box from "@mui/material/Box";
import useGet from "../../hooks/Get";
import CircularProgress from "@mui/material/CircularProgress";
import OrderForm from "./component/OrderForm";
import ErrorPage from "../errorPage/ErrorPage";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";

export default function AddOrderPage() {
  const {
    error: errorProjects,
    isPending: isPendingProjects,
    data: projects,
  } = useGet("http://localhost:8080/project/demanded");

  const {
    error: errorPopcMaterials,
    isPending: isPendingPopcMaterials,
    data: popcMaterials,
  } = useGet("http://localhost:8080/popcMaterial/basic");

  const {
    error: errorProductItems,
    isPending: isPendingProductItems,
    data: productItems,
  } = useGet("http://localhost:8080/productItem");

  const {
    error: errorVendos,
    isPending: isPendingVendors,
    data: vendors,
  } = useGet("http://localhost:8080/vendor/purchase");

  /*
  const {
    error: errorDemand,
    isPending: isPendingDemand,
    data: demand,
  } = useGet("http://localhost:8080/demand/id/" + 1);
*/

  return (
    <React.Fragment>
      {isPendingProjects &&
        isPendingVendors &&
        isPendingPopcMaterials &&
        isPendingProductItems && <CircularProgress color="success" />}
      {(errorProjects ||
        errorProductItems ||
        errorVendos ||
        errorPopcMaterials) && <ErrorPage />}
      {projects && vendors && popcMaterials && productItems && (
        <Grid container direction="column" alignItems={"center"}>
          <Grid item marginTop={2} marginBottom={2}>
            <Typography>NOWE ZAMÓWIENIE</Typography>
          </Grid>
          <Grid item>
            <OrderForm
              projects={projects}
              vendors={vendors}
              popcMaterials={popcMaterials}
              productItems={productItems}
            />
          </Grid>
        </Grid>
      )}
    </React.Fragment>
  );
}
