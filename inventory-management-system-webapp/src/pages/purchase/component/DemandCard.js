import { Autocomplete, CardHeader } from "@mui/material";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Grid from "@mui/material/Grid";
import TextField from "@mui/material/TextField";
import Stack from "@mui/material/Stack";
import React, { useState } from "react";
import { useTheme } from "@emotion/react";

export default function DemandCard(props) {
  const theme = useTheme();

  const [selectedProject, setSelectedProject] = useState();
  const [selectedDemand, setSelectedDemand] = useState([]);

  return (
    <Card
      sx={{
        backgroundColor: theme.palette.primary.light,
      }}
    >
      <CardHeader title="WYBIERZ ZAPOTRZEBOWANIE" />
      <CardContent>
        <Grid container direction="row" justifyContent="center">
          <Grid
            item
            alignSelf={"center"}
            sx={{
              "& .MuiTextField-root": { m: 1, width: 500 },
            }}
          >
            <Autocomplete
              options={props.projects}
              getOptionLabel={(option) =>
                option.projectCode + ". " + option.projectName
              }
              onChange={(event, value) => {
                if (value !== null) {
                  setSelectedProject(value);
                } else {
                  setSelectedProject(null);
                  setSelectedDemand(null);
                  props.onDemandSet(false);
                }
              }}
              renderInput={(params) => (
                <TextField {...params} label="Projekt" />
              )}
            />
            {selectedProject && (
              <Autocomplete
                options={selectedProject.demands.filter(
                  (demand) => demand.demandStatus !== "Zamówiony"
                )}
                getOptionLabel={(option) => option.demandName}
                onChange={(event, value) => {
                  if (value !== null) {
                    setSelectedDemand(value.demandPopcMaterials);
                    props.onDemandSet(true, value.demandName);
                  } else {
                    setSelectedDemand(null);
                    props.onDemandSet(false, "");
                  }
                }}
                renderInput={(params) => (
                  <TextField {...params} label="Zapotrzebowanie" />
                )}
              />
            )}
          </Grid>
          {selectedDemand && (
            <Grid item>
              {selectedDemand.map((item) => (
                <Stack
                  sx={{ m: 1 }}
                  direction="row"
                  justifyContent="center"
                  spacing={1}
                >
                  <TextField
                    disabled
                    label="Materiał"
                    size="small"
                    defaultValue={item.popcMaterialCode}
                  />

                  <TextField
                    disabled
                    label="Ilość"
                    size="small"
                    defaultValue={item.quantity}
                  />
                </Stack>
              ))}
            </Grid>
          )}
        </Grid>
      </CardContent>
    </Card>
  );
}
