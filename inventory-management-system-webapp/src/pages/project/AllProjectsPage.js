import React from "react";
import CircularProgress from "@mui/material/CircularProgress";
import useGet from "../../hooks/Get";
import Grid from "@mui/material/Grid";
import ErrorPage from "../errorPage/ErrorPage";
import ProjectTable from "./components/ProjectTable";
import Typography from "@mui/material/Typography";

let headCells = [
  {
    id: "projectName",
    label: "NAZWA PROJEKTU",
    sortable: true,
  },
  {
    id: "projectCode",
    label: "KOD",
    sortable: true,
  },
  {
    id: "projectStatus",
    label: "STATUS",
    sortable: true,
  },
  {
    id: "district",
    label: "REGION",
    sortable: true,
  },
  {
    id: "action",
    label: "AKCJE",
    sortable: false,
  },
];

const AllProjectPage = () => {
  const {
    error: errorProjects,
    isPending: isPendingProjects,
    data: projects,
  } = useGet("http://localhost:8080/project");

  return (
    <React.Fragment>
      {isPendingProjects && <CircularProgress color="success" />}
      {errorProjects && <ErrorPage />}
      {projects &&
        projects.forEach((a) => {
          a.search = true;
        })}
      {projects && (
        <Grid container direction="column" alignItems={"center"}>
          <Grid item marginTop={2} marginBottom={2}>
            <Typography>STATUS - PROJEKTY</Typography>
          </Grid>
          <Grid item>
            <ProjectTable
              tableTitle="PROJEKTY"
              headCells={headCells}
              rows={projects}
              url="http://localhost:8080/project/"
            />
          </Grid>
        </Grid>
      )}
    </React.Fragment>
  );
};

export default AllProjectPage;
