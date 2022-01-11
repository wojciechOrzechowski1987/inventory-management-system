import React from "react";
import CircularProgress from "@mui/material/CircularProgress";
import useGet from "../../hooks/Get";
import PopcMaterialTable from "./component/PopcMaterialTable";
import Grid from "@mui/material/Grid";
import ErrorPage from "../errorPage/ErrorPage";
import Typography from "@mui/material/Typography";

const headCells = [
  {
    id: "popcMaterialCode",
    label: "KOD",
    sortable: true,
  },
  {
    id: "popcMaterialName",
    label: "NAZWA",
    sortable: true,
  },
  {
    id: "popcSubgroupName",
    label: "GRUPA",
    sortable: true,
  },
  {
    id: "action",
    label: "AKCJE",
    sortable: false,
  },
];

const AllPopcMaterialPage = () => {
  const {
    error: errorMaterials,
    isPending: isPendingMaterials,
    data: materials,
  } = useGet("http://localhost:8080/popcMaterial");

  return (
    <React.Fragment>
      {isPendingMaterials && <CircularProgress color="success" />}
      {errorMaterials && <ErrorPage />}
      {materials &&
        materials.forEach((a) => {
          a.search = true;
        })}
      {materials && (
        <Grid container direction="column" alignItems={"center"}>
          <Grid item marginTop={2} marginBottom={2}>
            <Typography>MATERIAŁY</Typography>
          </Grid>
          <Grid item>
            <PopcMaterialTable
              tableTitle="MATERIAŁY"
              headCells={headCells}
              rows={materials}
              url="http://localhost:8080/popcMaterial/"
            />
          </Grid>
        </Grid>
      )}
    </React.Fragment>
  );
};

export default AllPopcMaterialPage;
