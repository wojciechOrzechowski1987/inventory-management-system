import React from "react";
import { useLocation } from "react-router-dom";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import OrderPreview from "./component/OrderPreview";

export default function EditOrderPage() {
  const location = useLocation();
  const { editedOrder } = location.state;

  return (
    <React.Fragment>
      <Grid container direction="column" alignItems={"center"}>
        <Grid item marginTop={2} marginBottom={2}>
          <Typography>PODGLĄD ZAMÓWIENIA</Typography>
        </Grid>
        <Grid item>
          <OrderPreview order={editedOrder} />
        </Grid>
      </Grid>
    </React.Fragment>
  );
}
