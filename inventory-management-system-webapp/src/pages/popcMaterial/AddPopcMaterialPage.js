import React from "react";
import useGet from "../../hooks/Get";
import CircularProgress from "@mui/material/CircularProgress";
import PopcMaterialForm from "./component/PopcMaterialForm";
import ErrorPage from "../errorPage/ErrorPage";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";

export default function AddPopcMaterialPage() {
  const {
    error: errorSubgroups,
    isPending: isPendingSubgroups,
    data: subgroups,
  } = useGet("http://localhost:8080/popcSubgroup/basic");

  const {
    error: errorProductItems,
    isPending: isPendingProductItems,
    data: productItems,
  } = useGet("http://localhost:8080/productItem/noPopcMaterial");

  const material = {
    popcMaterialCode: "",
    popcMaterialName: "",
    popcMaterialDescription: "",
    popcSubgroupName: "",
    productItems: [],
  };

  return (
    <React.Fragment>
      {isPendingSubgroups && isPendingProductItems && (
        <CircularProgress color="success" />
      )}
      {(errorSubgroups || errorProductItems) && <ErrorPage />}
      {subgroups && productItems && (
        <Grid container direction="column" alignItems={"center"}>
          <Grid item marginTop={2} marginBottom={2}>
            <Typography>NOWY MATERIAŁ</Typography>
          </Grid>
          <Grid item>
            <PopcMaterialForm
              material={material}
              subgroups={subgroups}
              productItems={productItems}
            />
          </Grid>
        </Grid>
      )}
    </React.Fragment>
  );
}
