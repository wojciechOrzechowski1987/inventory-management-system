import Grid from "@mui/material/Grid";
import Button from "@mui/material/Button";
import { Link } from "react-router-dom";
import AddBoxIcon from "@mui/icons-material/AddBox";
import { useTheme } from "@emotion/react";
import useGet from "../../hooks/Get";
import CircularProgress from "@mui/material/CircularProgress";
import React, { useContext } from "react";
import DistrictCard from "./components/DistrictCard";
import AuthContext from "../../auth/AuthContex";
import ErrorPage from "../errorPage/ErrorPage";
import Typography from "@mui/material/Typography";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";

const AllDistrictPage = () => {
  const theme = useTheme();
  const authCtx = useContext(AuthContext);

  const {
    error: errorDistricts,
    isPending: isPendingDistricts,
    data: districts,
  } = useGet("http://localhost:8080/district");
  const {
    error: errorProjectStatus,
    isPending: isPendingProjectStatus,
    data: projectStatus,
  } = useGet("http://localhost:8080/project/enums");

  return (
    <React.Fragment>
      {isPendingDistricts && isPendingProjectStatus && (
        <CircularProgress color="success" />
      )}
      {(errorDistricts || errorProjectStatus) && <ErrorPage />}

      {districts && projectStatus && (
        <Grid container direction="row" justifyContent="center">
          <Grid item marginTop={2} marginBottom={2}>
            <Typography>STATUS - REGIONY</Typography>
          </Grid>
          <Grid
            container
            direction="row"
            alignItems="center"
            justifyContent="center"
            gap={2}
          >
            {districts
              .sort((a, b) => a.districtName.localeCompare(b.districtName))
              .map((district, index) => (
                <Grid item>
                  <DistrictCard
                    key={`${district}${index}`}
                    district={district}
                    projectStatus={projectStatus}
                  />
                </Grid>
              ))}
            {authCtx.authorities.includes("ROLE_ADMIN") && (
              <Grid item>
                <Card
                  key={"new"}
                  sx={{
                    display: "flex",
                    backgroundColor: theme.palette.primary.light,
                    width: 701.13,
                    height: 247.38,
                    justifyContent: "center",
                    alignItems: "center",
                  }}
                >
                  <CardContent>
                    <Button
                      component={Link}
                      to={"newDistrict"}
                      variant="contained"
                      color="success"
                      endIcon={<AddBoxIcon />}
                    >
                      Dodaj region
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

export default AllDistrictPage;
