import React from "react";
import useGet from "../../hooks/Get";
import CircularProgress from "@mui/material/CircularProgress";
import { useLocation } from "react-router-dom";
import MaterialSubgroupForm from "./component/MaterialSubgroupForm";
import ErrorPage from "../errorPage/ErrorPage";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";

export default function EditMaterialSubgroupPage() {
  const location = useLocation();
  const { editedSubgroup } = location.state;

  const {
    error: errorPopcGroups,
    isPending: isPendingPopcGroups,
    data: popcGroups,
  } = useGet("http://localhost:8080/popcGroup/basic");

  const {
    error: errorMaterialsForEdit,
    isPending: isPendingMaterialsForEdit,
    data: materialsForEdit,
  } = useGet(
    "http://localhost:8080/popcMaterial/" +
      editedSubgroup.id +
      "/materialsForEdit"
  );

  return (
    <React.Fragment>
      {!isPendingPopcGroups && isPendingMaterialsForEdit && (
        <CircularProgress color="success" />
      )}
      {errorPopcGroups && errorMaterialsForEdit && <ErrorPage />}
      {popcGroups && materialsForEdit && (
        <Grid container direction="column" alignItems={"center"}>
          <Grid item marginTop={2} marginBottom={2}>
            <Typography>EDYCJA PODGRUPY MATERIAŁOWEJ</Typography>
          </Grid>
          <Grid item>
            <MaterialSubgroupForm
              groups={popcGroups}
              subgroup={editedSubgroup}
              materials={materialsForEdit}
            />
          </Grid>
        </Grid>
      )}
    </React.Fragment>
  );
}
