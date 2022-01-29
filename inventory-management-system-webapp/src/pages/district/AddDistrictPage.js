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

  const {
    error: errorUsers,
    isPending: isPendingUsers,
    data: users,
  } = useGet("http://localhost:8080/user/");

  const editedDistrict = {
    districtName: "",
    projects: [],
    owner: "admin",
  };

  return (
    <React.Fragment>
      {(isPendingNoDistrict || isPendingUsers) && (
        <CircularProgress color="success" />
      )}
      {(errorNoDistrict || errorUsers) && <ErrorPage />}
      {projectNoDistrict && users && (
        <Grid container direction="column" alignItems={"center"}>
          <Grid item marginTop={2} marginBottom={2}>
            <Typography>NOWY REGION</Typography>
          </Grid>
          <Grid item>
            <DistrictForm
              selectableProjects={projectNoDistrict}
              district={editedDistrict}
              users={users}
            />
          </Grid>
        </Grid>
      )}
    </React.Fragment>
  );
}
