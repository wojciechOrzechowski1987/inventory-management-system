import React from "react";
import Grid from "@mui/material/Grid";
import { Typography } from "@mui/material";

const ErrorPage = () => {
  return (
    <Grid
      container
      direction="column"
      justifyContent={"center"}
      alignItems={"center"}
    >
      <Grid>
        <Typography> BRAK MOŻLIWOŚCI WYŚWIETLENIA STRONY</Typography>
      </Grid>
    </Grid>
  );
};

export default ErrorPage;
