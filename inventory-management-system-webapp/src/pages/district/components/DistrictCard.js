import React, { useContext, useState } from "react";
import Button from "@mui/material/Button";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Grid from "@mui/material/Grid";
import { useTheme } from "@emotion/react";
import Stack from "@mui/material/Stack";
import { Link, useNavigate } from "react-router-dom";
import Typography from "@mui/material/Typography";
import DeleteForeverIcon from "@mui/icons-material/DeleteForever";
import EditIcon from "@mui/icons-material/Edit";
import axios from "axios";
import DeleteEntryDialog from "../../../ui/DeleteEntryDialog";
import AuthContext from "../../../auth/AuthContex";
import { Chip } from "@mui/material";

export default function DistrictCard(props) {
  const theme = useTheme();
  const authCtx = useContext(AuthContext);
  const district = props.district;
  const projectStatus = props.projectStatus;
  const [openDialog, setOpenDialog] = useState(false);
  const navigate = useNavigate();

  const deleteEntry = (entryId) => {
    axios
      .delete("http://localhost:8080/district/delete/" + entryId, {
        headers: {
          Authorization: "Bearer " + authCtx.token,
        },
      })
      .then((response) => {
        if (response.status === 204) {
          navigate(0);
        }
      })
      .catch((error) => {
        console.log(error.message);
      })
      .finally(() => {
        setOpenDialog(false);
      });
  };

  const openInDialog = () => {
    setOpenDialog(true);
  };

  return (
    <React.Fragment>
      <Card
        sx={{
          backgroundColor: theme.palette.primary.light,
          width: 701.13,
          height: 247.38,
        }}
      >
        <CardContent>
          <Typography color="textSecondary" gutterBottom>
            Region: {district.districtName}
          </Typography>
          <Typography color="textSecondary" gutterBottom>
            Koordynator: {district.owner.toUpperCase()}
          </Typography>
          <Typography color="textSecondary" gutterBottom>
            Łączna liczba projektów: {district.projects.length}
          </Typography>
          <Typography color="textSecondary" gutterBottom>
            Status projektu / liczba projektów:
          </Typography>
          <Grid
            container
            justifyContent="center"
            spacing={2}
            marginBottom={"16px"}
          >
            {projectStatus.map((projectStatus, index) => {
              let counter = 0;
              district.projects.forEach((districtProject) => {
                if (
                  districtProject.status.toString() === projectStatus.toString()
                )
                  counter++;
              });

              return (
                <Grid item key={`${projectStatus}${index}`}>
                  <Typography>
                    <Chip
                      component={"span"}
                      sx={{
                        backgroundColor:
                          (projectStatus === "Nowy" && "white") ||
                          (projectStatus === "Zapotrzebowany" && "yellow") ||
                          (projectStatus === "Zamówiony" && "orange") ||
                          (projectStatus === "Dostarczony" && "green") ||
                          (projectStatus === "Anulowany" && "red"),
                      }}
                      label={projectStatus + " " + counter}
                    />
                  </Typography>
                </Grid>
              );
            })}
          </Grid>
          {authCtx.authorities.includes("ROLE_ADMIN") && (
            <Stack direction="row" justifyContent="flex-end" spacing={2}>
              <Button
                variant="contained"
                color="error"
                endIcon={<DeleteForeverIcon />}
                onClick={() => openInDialog()}
              >
                Usuń
              </Button>
              <Button
                component={Link}
                to={{
                  pathname: "editDistrict/" + district.id,
                }}
                state={{
                  editedDistrict: district,
                }}
                variant="contained"
                color="info"
                endIcon={<EditIcon />}
              >
                Edytuj
              </Button>
            </Stack>
          )}
        </CardContent>
      </Card>
      {openDialog && (
        <DeleteEntryDialog
          open={openDialog}
          setOpenDialog={setOpenDialog}
          deleteEntry={deleteEntry}
          entryId={district.id}
          text={
            "Czy na pewno chcesz usunąć region " + district.districtName + " ?"
          }
        />
      )}
    </React.Fragment>
  );
}
