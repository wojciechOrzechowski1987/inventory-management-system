import React from "react";
import Box from "@mui/material/Box";
import CircularProgress from "@mui/material/CircularProgress";
import useGet from "../../hooks/Get";
import Grid from "@mui/material/Grid";
import OrderTable from "./component/OrderTable";
import ErrorPage from "../errorPage/ErrorPage";
import Typography from "@mui/material/Typography";

const headCells = [
  {
    id: "orderDate",
    label: "DATA ZAMÓWIENIA",
    sortable: true,
  },
  {
    id: "demand",
    label: "ZAPOTRZEBOWANIE",
    sortable: true,
  },
  {
    id: "vendorName",
    label: "Dostawca",
    sortable: true,
  },
  {
    id: "action",
    label: "AKCJE",
  },
];

const AllOrderPage = () => {
  const {
    error: errorPurchases,
    isPending: isPendingPurchases,
    data: purchases,
  } = useGet("http://localhost:8080/purchase");

  return (
    <React.Fragment>
      {isPendingPurchases && <CircularProgress color="success" />}
      {errorPurchases && <ErrorPage />}
      {purchases &&
        purchases.forEach((a) => {
          a.search = true;
        })}
      {purchases && (
        <Grid container direction="column" alignItems={"center"}>
          <Grid item marginTop={2} marginBottom={2}>
            <Typography>ZAMÓWIENIA</Typography>
          </Grid>
          <Grid item>
            <OrderTable
              tableTitle="ZAMÓWIENIA"
              headCells={headCells}
              rows={purchases}
              url="http://localhost:8080/purchase/"
            />
          </Grid>
        </Grid>
      )}
    </React.Fragment>
  );
};

export default AllOrderPage;
