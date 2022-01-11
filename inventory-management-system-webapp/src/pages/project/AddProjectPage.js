import React from "react";
import useGet from "../../hooks/Get";
import CircularProgress from "@mui/material/CircularProgress";
import ProjectForm from "./components/ProjectForm";
import { useTheme } from "@emotion/react";
import ErrorPage from "../errorPage/ErrorPage";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";

export default function AddProjectPage() {
  const theme = useTheme();
  const {
    error: errorDistricts,
    isPending: isPendingDistricts,
    data: districts,
  } = useGet("http://localhost:8080/district/basic");

  const project = {
    districtName: "",
    projectCode: "",
    projectName: "",
    projectStatus: "",
  };

  return (
    <React.Fragment>
      {isPendingDistricts && <CircularProgress color="success" />}
      {errorDistricts && <ErrorPage />}
      {districts && (
        <Grid container direction="column" alignItems={"center"}>
          <Grid item marginTop={2} marginBottom={2}>
            <Typography>NOWY PROJEKT</Typography>
          </Grid>
          <Grid item>
            <ProjectForm
              districts={districts}
              project={project}
              status={null}
            />
          </Grid>
        </Grid>
      )}
    </React.Fragment>
  );
}
