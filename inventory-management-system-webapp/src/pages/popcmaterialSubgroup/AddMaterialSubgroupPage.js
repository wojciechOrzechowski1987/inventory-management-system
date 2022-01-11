import React from "react";
import useGet from "../../hooks/Get";
import CircularProgress from "@mui/material/CircularProgress";
import MaterialSubgroupForm from "./component/MaterialSubgroupForm";
import ErrorPage from "../errorPage/ErrorPage";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";

export default function AddMaterialSubgroupPage() {
  const {
    error: errorPopcSubgroups,
    isPending: isPendingPopcSubgroups,
    data: popcGroups,
  } = useGet("http://localhost:8080/popcGroup/basic");

  const {
    error: errorMaterialsForEdit,
    isPending: isPendingMaterialsForEdit,
    data: materialsForEdit,
  } = useGet("http://localhost:8080/popcMaterial/notAssigned");

  const popcSubgroup = {
    popcSubgroupName: "",
  };

  return (
    <React.Fragment>
      {isPendingPopcSubgroups && isPendingMaterialsForEdit && (
        <CircularProgress color="success" />
      )}
      {(errorPopcSubgroups || errorMaterialsForEdit) && <ErrorPage />}
      {popcGroups && materialsForEdit && (
        <Grid container direction="column" alignItems={"center"}>
          <Grid item marginTop={2} marginBottom={2}>
            <Typography>NOWA GRUPA MATERIAŁOWA</Typography>
          </Grid>
          <Grid item>
            <MaterialSubgroupForm
              groups={popcGroups}
              subgroup={popcSubgroup}
              materials={materialsForEdit}
            />
          </Grid>
        </Grid>
      )}
    </React.Fragment>
  );
}
