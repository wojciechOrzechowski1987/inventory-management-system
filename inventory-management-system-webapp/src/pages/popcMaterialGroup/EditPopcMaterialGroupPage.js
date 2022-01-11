import React from "react";
import useGet from "../../hooks/Get";
import CircularProgress from "@mui/material/CircularProgress";
import PopcMaterialGroupForm from "./components/PopcMaterialGroupForm";
import { useLocation } from "react-router-dom";
import { useTheme } from "@emotion/react";
import ErrorPage from "../errorPage/ErrorPage";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";

export default function EditPopcMaterialGroupPage() {
  const theme = useTheme();
  const location = useLocation();
  const { editedGroup } = location.state;
  const {
    error: errorPopcSubgroups,
    isPending: isPendingPopcSubgroups,
    data: popcSubgroups,
  } = useGet(
    "http://localhost:8080/popcSubgroup/" + editedGroup.id + "/subgroupsForEdit"
  );

  return (
    <React.Fragment>
      {isPendingPopcSubgroups && <CircularProgress color="success" />}
      {errorPopcSubgroups && <ErrorPage />}
      {popcSubgroups && (
        <Grid container direction="column" alignItems={"center"}>
          <Grid item marginTop={2} marginBottom={2}>
            <Typography>EDYCJA GRUPY MATERIAŁOWEJ</Typography>
          </Grid>
          <Grid item>
            <PopcMaterialGroupForm
              group={editedGroup}
              subgroups={popcSubgroups}
            />
          </Grid>
        </Grid>
      )}
    </React.Fragment>
  );
}
