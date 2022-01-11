import React from "react";
import useGet from "../../hooks/Get";
import { CircularProgress } from "@mui/material";
import DistrictForm from "./components/DistrictForm";
import ErrorPage from "../errorPage/ErrorPage";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";

export default function AddDistrictPage() {
  const {
    error: errorNoDistrict,
    isPending: isPendingNoDistrict,
    data: projectNoDistrict,
  } = useGet("http://localhost:8080/project/noDistrict");

  const editedDistrict = {
    districtName: "",
    projects: [],
  };

  return (
    <React.Fragment>
      {isPendingNoDistrict && <CircularProgress color="success" />}
      {errorNoDistrict && <ErrorPage />}
      {projectNoDistrict && (
        <Grid container direction="column" alignItems={"center"}>
          <Grid item marginTop={2} marginBottom={2}>
            <Typography>NOWY REGION</Typography>
          </Grid>
          <Grid item>
            <DistrictForm
              selectableProjects={projectNoDistrict}
              district={editedDistrict}
            />
          </Grid>
        </Grid>
      )}
    </React.Fragment>
  );
}
