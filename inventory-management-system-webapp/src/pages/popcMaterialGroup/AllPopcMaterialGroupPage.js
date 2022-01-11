import React, { useContext } from "react";
import CircularProgress from "@mui/material/CircularProgress";
import useGet from "../../hooks/Get";
import PopcMaterialGroupCard from "./components/PopcMaterialGroupCard";
import { useTheme } from "@emotion/react";
import Grid from "@mui/material/Grid";
import { Link } from "react-router-dom";
import AddBoxIcon from "@mui/icons-material/AddBox";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import AuthContext from "../../auth/AuthContex";
import ErrorPage from "../errorPage/ErrorPage";

const AllPopcMaterialGroupPage = () => {
  const theme = useTheme();
  const authCtx = useContext(AuthContext);
  const {
    error: errorPopcGroups,
    isPending: isPendingPopcGroups,
    data: popcGroups,
  } = useGet("http://localhost:8080/popcGroup");

  return (
    <React.Fragment>
      {isPendingPopcGroups && <CircularProgress color="success" />}
      {errorPopcGroups && <ErrorPage />}
      {popcGroups && (
        <Grid container direction="column" alignItems="center">
          <Grid item marginTop={2} marginBottom={2}>
            <Typography>GRUPY MATERIAŁOWE</Typography>
          </Grid>
          <Grid
            container
            direction="row"
            alignItems="center"
            justifyContent="center"
            gap={2}
          >
            {popcGroups.map((popcGroup, index) => (
              <Grid item>
                <PopcMaterialGroupCard
                  key={`${popcGroup}${index}`}
                  group={popcGroup}
                />
              </Grid>
            ))}
            {authCtx.authorities.includes("ROLE_ADMIN") && (
              <Grid item>
                <Card
                  sx={{
                    display: "flex",
                    backgroundColor: theme.palette.primary.light,
                    width: 701.13,
                    minHeight: 247.38,
                    justifyContent: "center",
                    alignItems: "center",
                  }}
                >
                  <CardContent>
                    <Button
                      component={Link}
                      to={"newGroup"}
                      variant="contained"
                      color="success"
                      endIcon={<AddBoxIcon />}
                    >
                      Dodaj grupę materiałową
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

export default AllPopcMaterialGroupPage;
