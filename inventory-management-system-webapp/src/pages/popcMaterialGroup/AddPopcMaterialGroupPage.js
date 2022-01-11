import React from "react";
import useGet from "../../hooks/Get";
import CircularProgress from "@mui/material/CircularProgress";
import PopcMaterialGroupForm from "./components/PopcMaterialGroupForm";
import ErrorPage from "../errorPage/ErrorPage";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";

export default function AddPopcMaterialGroupPage() {
  const {
    error: errorPopcSubgroups,
    isPending: isPendingPopcSubgroups,
    data: popcSubgroups,
  } = useGet("http://localhost:8080/popcSubgroup/notAssigned");

  const group = {
    popcGroupName: "",
    popcSubgroup: [],
  };

  return (
    <React.Fragment>
      {isPendingPopcSubgroups && <CircularProgress color="success" />}
      {errorPopcSubgroups && <ErrorPage />}
      {popcSubgroups && (
        <Grid container direction="column" alignItems={"center"}>
          <Grid item marginTop={2} marginBottom={2}>
            <Typography>NOWA GRUPA MATERIAŁOWA</Typography>
          </Grid>
          <Grid item>
            <PopcMaterialGroupForm group={group} subgroups={popcSubgroups} />
          </Grid>
        </Grid>
      )}
    </React.Fragment>
  );
}
