import React from "react";
import useGet from "../../hooks/Get";
import CircularProgress from "@mui/material/CircularProgress";
import DemandForm from "./component/DemandForm";
import ErrorPage from "../errorPage/ErrorPage";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";

export default function AddDemandPage() {
  const {
    error: errorProjects,
    isPending: isPendingProjects,
    data: projects,
  } = useGet("http://localhost:8080/project/basic");

  const {
    error: errorPopcMaterials,
    isPending: isPendingPopcMaterials,
    data: popcMaterials,
  } = useGet("http://localhost:8080/popcMaterial/basic");

  const demand = {
    projectName: "",
    demandName: "",
    demandPopcMaterials: [],
  };

  return (
    <React.Fragment>
      {isPendingProjects && isPendingPopcMaterials && (
        <CircularProgress color="success" />
      )}
      {errorProjects && errorPopcMaterials && <ErrorPage />}
      {projects && popcMaterials && (
        <Grid container direction="column" alignItems={"center"}>
          <Grid item marginTop={2} marginBottom={2}>
            <Typography>NOWE ZAPOTRZEBOWANIE</Typography>
          </Grid>
          <Grid item>
            <DemandForm
              projects={projects}
              demand={demand}
              popcMaterials={popcMaterials}
            />
          </Grid>
        </Grid>
      )}
    </React.Fragment>
  );
}
