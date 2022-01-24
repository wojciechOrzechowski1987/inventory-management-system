import React from "react";
import { useLocation } from "react-router-dom";
import useGet from "../../hooks/Get";
import { CircularProgress } from "@mui/material";
import DistrictForm from "./components/DistrictForm";
import ErrorPage from "../errorPage/ErrorPage";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";

export default function EditDistrictPage() {
  const location = useLocation();
  const { editedDistrict } = location.state ? location.state : [];

  const {
    error: errorProjectsForEdit,
    isPending: isProjectsForEdit,
    data: projectsForEdit,
  } = useGet(
    "http://localhost:8080/project/" + editedDistrict.id + "/projectsForEdit"
  );

  const {
    error: errorUsers,
    isPending: isPendingUsers,
    data: users,
  } = useGet("http://localhost:8080/user/");

  return (
    <React.Fragment>
      {(isProjectsForEdit || isPendingUsers) && (
        <CircularProgress color="success" />
      )}
      {(errorProjectsForEdit || errorUsers) && <ErrorPage />}
      {projectsForEdit && users && (
        <Grid container direction="column" alignItems={"center"}>
          <Grid item marginTop={2} marginBottom={2}>
            <Typography>EDYCJA REGIONU</Typography>
          </Grid>
          <Grid item>
            <DistrictForm
              selectableProjects={projectsForEdit}
              district={editedDistrict}
              users={users}
            />
          </Grid>
        </Grid>
      )}
    </React.Fragment>
  );
}
