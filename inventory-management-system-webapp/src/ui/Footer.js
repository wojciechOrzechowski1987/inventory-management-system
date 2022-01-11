import React from "react";
import Grid from "@mui/material/Grid";
import Hidden from "@mui/material/Hidden";
import Box from "@mui/material/Box";

export default function Footer() {
  return (
    <Box
      component={"div"}
      sx={{
        backgroundColor: "primary.main",
        width: "100%",
        height: "100%",
        zIndex: 1301,
      }}
    >
      <Hidden mdDown>
        <Grid container justifyContent="center">
          <Grid
            item
            sx={{
              fontFamily: "Arial",
              fontSize: "1rem",
              fontWeight: "bold",
              color: "primary.contrastText",
              textDecoration: "none",
              marginTop: "2em",
            }}
          >
            Wojciech Orzechowski - Inventory Managment System
          </Grid>
        </Grid>
      </Hidden>
    </Box>
  );
}
