import React, { useContext } from "react";
import useGet from "../../hooks/Get";
import CircularProgress from "@mui/material/CircularProgress";
import { useLocation } from "react-router-dom";
import DemandForm from "./component/DemandForm";
import ErrorPage from "../errorPage/ErrorPage";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import authContex from "../../auth/AuthContex";

export default function EditDemandPage() {
  const location = useLocation();
  const { editedDemand } = location.state;
  const authCtx = useContext(authContex);

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

  return (
    <React.Fragment>
      {isPendingProjects && isPendingPopcMaterials && (
        <CircularProgress color="success" />
      )}
      {(errorProjects || errorPopcMaterials) && <ErrorPage />}
      {projects && popcMaterials && (
        <Grid container direction="column" alignItems={"center"}>
          <Grid item marginTop={2} marginBottom={2}>
            {authCtx.authorities.includes("ROLE_ADMIN") ? (
              <Typography>EDYCJA PROJEKTU</Typography>
            ) : (
              <Typography>PODGLĄD PROJEKTU</Typography>
            )}
          </Grid>
          <Grid item>
            <DemandForm
              projects={projects}
              demand={editedDemand}
              popcMaterials={popcMaterials}
            />
          </Grid>
        </Grid>
      )}
    </React.Fragment>
  );
}
