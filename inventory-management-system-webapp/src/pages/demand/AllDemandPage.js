import React from "react";
import CircularProgress from "@mui/material/CircularProgress";
import useGet from "../../hooks/Get";
import Grid from "@mui/material/Grid";
import ErrorPage from "../errorPage/ErrorPage";
import DemandTable from "./component/DemandTable";
import Typography from "@mui/material/Typography";

const headCells = [
  {
    id: "createDate",
    label: "DATA DODANIA",
    sortable: true,
  },
  {
    id: "projectCode",
    label: "KOD PROJEKTU",
    sortable: true,
  },
  {
    id: "projectName",
    label: "NAZWA PROJEKT",
    sortable: true,
  },
  {
    id: "demandName",
    label: "IDENTYFIKATOR",
    sortable: true,
  },
  {
    id: "demandStatus",
    label: "STATUS",
    sortable: true,
  },
  {
    id: "action",
    label: "AKCJE",
  },
];

const AllDemandPage = () => {
  const {
    error: errorDemands,
    isPending: isPendingDemands,
    data: demands,
  } = useGet("http://localhost:8080/demand");

  return (
    <React.Fragment>
      {isPendingDemands && <CircularProgress color="success" />}
      {errorDemands && <ErrorPage />}
      {demands &&
        demands.forEach((a) => {
          a.search = true;
          console.log(a);
        })}
      {demands && (
        <Grid container direction="column" alignItems={"center"}>
          <Grid item marginTop={2} marginBottom={2}>
            <Typography>ZAPOTRZEBOWANIA</Typography>
          </Grid>
          <Grid item>
            <DemandTable
              tableTitle=""
              headCells={headCells}
              rows={demands}
              url="http://localhost:8080/demand/"
            />
          </Grid>
        </Grid>
      )}
    </React.Fragment>
  );
};

export default AllDemandPage;
