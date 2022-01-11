import React from "react";
import CircularProgress from "@mui/material/CircularProgress";
import useGet from "../../hooks/Get";
import MaterialSubgroupTable from "./component/MaterialSubgroupTable";
import Grid from "@mui/material/Grid";
import ErrorPage from "../errorPage/ErrorPage";
import Typography from "@mui/material/Typography";

const headCells = [
  {
    id: "popcSubgroupName",
    label: "PODGRUPA",
    sortable: true,
  },
  {
    id: "popcGroupName",
    label: "GRUPA MATERIAŁOWA",
    sortable: true,
  },
  {
    id: "materials",
    label: "MATERIAŁY",
    sortable: false,
  },
  {
    id: "action",
    label: "AKCJE",
  },
];

const AllMaterialSubgroupPage = () => {
  const {
    error: errorPopcSubgroups,
    isPending: isPendingPopcSubgroups,
    data: popcSubgroups,
  } = useGet("http://localhost:8080/popcSubgroup");

  return (
    <React.Fragment>
      {isPendingPopcSubgroups && <CircularProgress color="success" />}
      {errorPopcSubgroups && <ErrorPage />}
      {popcSubgroups &&
        popcSubgroups.forEach((a) => {
          a.search = true;
        })}
      {popcSubgroups && (
        <Grid
          container
          direction="column"
          justifyContent={"center"}
          alignItems={"center"}
        >
          <Grid item marginTop={2} marginBottom={2}>
            <Typography>PODGRUPY MATERIAŁOWE</Typography>
          </Grid>
          <Grid item>
            <MaterialSubgroupTable
              tableTitle="PODGRUPY MATERIAŁOWE"
              headCells={headCells}
              rows={popcSubgroups}
              url="http://localhost:8080/popcSubgroup/"
            />
          </Grid>
        </Grid>
      )}
    </React.Fragment>
  );
};

export default AllMaterialSubgroupPage;
