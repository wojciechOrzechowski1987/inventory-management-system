import React from "react";
import CircularProgress from "@mui/material/CircularProgress";
import useGet from "../../hooks/Get";
import Grid from "@mui/material/Grid";
import ProductItemTable from "./components/ProductItemTable";
import ErrorPage from "../errorPage/ErrorPage";
import Typography from "@mui/material/Typography";

const headCells = [
  {
    id: "popcMaterialCode",
    label: "KOD POPC",
    sortable: true,
  },
  {
    id: "productItemName",
    label: "NAZWA PRODUKTU",
    sortable: true,
  },
  {
    id: "price",
    label: "CENA",
    sortable: true,
  },
  {
    id: "vendorName",
    label: "DOSTAWCA",
    sortable: true,
  },

  {
    id: "action",
    label: "AKCJE",
  },
];

const AllProductItemPage = () => {
  const {
    error: errorProductItems,
    isPending: isPendingProductItems,
    data: productItems,
  } = useGet("http://localhost:8080/productItem");

  return (
    <React.Fragment>
      {isPendingProductItems && <CircularProgress color="success" />}
      {errorProductItems && <ErrorPage />}
      {productItems &&
        productItems.forEach((a) => {
          a.search = true;
        })}
      {productItems && (
        <Grid container direction="column" alignItems={"center"}>
          <Grid item marginTop={2} marginBottom={2}>
            <Typography>PRODUKTY</Typography>
          </Grid>
          <Grid item>
            <ProductItemTable
              sx={{ width: 1050 }}
              tableTitle="PRODUKTY"
              headCells={headCells}
              rows={productItems}
              url="http://localhost:8080/productItem/"
            />
          </Grid>
        </Grid>
      )}
    </React.Fragment>
  );
};

export default AllProductItemPage;
