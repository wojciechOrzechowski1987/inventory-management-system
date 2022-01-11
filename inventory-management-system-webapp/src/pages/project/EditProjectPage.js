import React from "react";
import useGet from "../../hooks/Get";
import CircularProgress from "@mui/material/CircularProgress";
import ProjectForm from "./components/ProjectForm";
import { useLocation } from "react-router-dom";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import ErrorPage from "../errorPage/ErrorPage";

export default function EditProjectPage() {
  const location = useLocation();
  const { editedProject } = location.state;

  const {
    error: errorDistricts,
    isPending: isPendingDistricts,
    data: districts,
  } = useGet("http://localhost:8080/district/basic");

  const {
    error: errorStatus,
    isPending: isPendingStatus,
    data: status,
  } = useGet("http://localhost:8080/project/enums");

  return (
    <React.Fragment>
      {isPendingDistricts && isPendingStatus && (
        <CircularProgress color="success" />
      )}
      {(errorDistricts || errorStatus) && <ErrorPage />}
      {districts && status && (
        <Grid container direction="column" alignItems={"center"}>
          <Grid item marginTop={2} marginBottom={2}>
            <Typography>EDYCJA PROJEKTU</Typography>
          </Grid>
          <Grid item>
            <ProjectForm
              districts={districts}
              project={editedProject}
              status={status}
            />
          </Grid>
        </Grid>
      )}
    </React.Fragment>
  );
}
