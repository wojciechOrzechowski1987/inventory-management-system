import React, { useContext } from "react";
import CircularProgress from "@mui/material/CircularProgress";
import useGet from "../../hooks/Get";
import VendorCard from "./components/VendorCard";
import Grid from "@mui/material/Grid";
import Button from "@mui/material/Button";
import { Link } from "react-router-dom";
import AddBoxIcon from "@mui/icons-material/AddBox";
import { useTheme } from "@emotion/react";
import ErrorPage from "../errorPage/ErrorPage";
import Typography from "@mui/material/Typography";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import AuthContext from "../../auth/AuthContex";

const AllVendorPage = () => {
  const theme = useTheme();
  const authCtx = useContext(AuthContext);
  const {
    error: errorVendors,
    isPending: isPendingVendors,
    data: vendors,
  } = useGet("http://localhost:8080/vendor");

  return (
    <React.Fragment>
      {isPendingVendors && <CircularProgress color="success" />}
      {errorVendors && <ErrorPage />}
      {vendors && (
        <Grid container direction="row" justifyContent="center">
          <Grid item marginTop={2} marginBottom={2}>
            <Typography>DOSTAWCY</Typography>
          </Grid>
          <Grid
            container
            direction="row"
            alignItems="center"
            justifyContent="center"
            gap={2}
          >
            {vendors &&
              vendors.map((vendor, index) => (
                <VendorCard key={`${vendor}${index}`} vendor={vendor} />
              ))}
            {authCtx.authorities.includes("ROLE_ADMIN") && (
              <Grid item>
                <Card
                  sx={{
                    display: "flex",
                    backgroundColor: theme.palette.primary.light,
                    width: 300,
                    height: 150,
                    justifyContent: "center",
                    alignItems: "center",
                  }}
                >
                  <CardContent>
                    <Button
                      component={Link}
                      to={"newVendor"}
                      variant="contained"
                      color="success"
                      endIcon={<AddBoxIcon />}
                    >
                      Dodaj dostawcę
                    </Button>
                  </CardContent>
                </Card>
              </Grid>
            )}
          </Grid>
        </Grid>
      )}
    </React.Fragment>
  );
};

export default AllVendorPage;
