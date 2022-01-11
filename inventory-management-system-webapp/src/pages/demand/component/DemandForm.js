import { Autocomplete, CardHeader } from "@mui/material";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Grid from "@mui/material/Grid";
import TextField from "@mui/material/TextField";
import Stack from "@mui/material/Stack";
import Button from "@mui/material/Button";
import CancelIcon from "@mui/icons-material/Cancel";
import DeleteForeverIcon from "@mui/icons-material/DeleteForever";
import SaveIcon from "@mui/icons-material/Save";
import AddBoxIcon from "@mui/icons-material/AddBox";
import { useTheme } from "@emotion/react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { Controller, useFieldArray, useForm } from "react-hook-form";
import Box from "@mui/material/Box";
import React, { useContext } from "react";
import AuthContext from "../../../auth/AuthContex";

export default function DemandForm(props) {
  const theme = useTheme();
  const navigate = useNavigate();
  const authCtx = useContext(AuthContext);
  const isNew = props.demand.demandName === "";

  const { control, handleSubmit, getValues } = useForm({
    defaultValues: {
      projectName: props.demand.projectName,
      demandName: props.demand.demandName,
      demandPopcMaterials: props.demand.demandPopcMaterials,
    },
  });

  const { fields, append, remove } = useFieldArray({
    keyName: "key",
    control,
    name: "demandPopcMaterials",
  });

  const onSubmit = (data) => {
    if (!props.demand.id) {
      axios
        .post("http://localhost:8080/demand/newDemand", data, {
          headers: {
            Authorization: "Bearer " + authCtx.token,
          },
        })
        .then(() => {
          navigate(-1);
        });
    } else {
      axios
        .put(
          "http://localhost:8080/demand/editDemand/" + props.demand.id,
          data,
          {
            headers: {
              Authorization: "Bearer " + authCtx.token,
            },
          }
        )
        .then(() => {
          navigate(-1);
        });
    }
  };

  return (
    <Card
      sx={{
        backgroundColor: theme.palette.primary.light,
      }}
    >
      <CardContent>
        <CardHeader
          title={
            isNew
              ? "Dodaj nowe zapotrzebowanie."
              : authCtx.authorities.includes("ROLE_ADMIN")
              ? "Edycja zapotrzebowania."
              : "Podgląd zapotrzebowania."
          }
        />
        <Grid container direction="column" justifyContent="center">
          <form onSubmit={handleSubmit(onSubmit)} noValidate>
            <Grid
              item
              sx={{
                "& .MuiTextField-root": { m: 1, width: 500 },
              }}
            >
              {isNew || authCtx.authorities.includes("ROLE_ADMIN") ? (
                <Controller
                  name="projectName"
                  control={control}
                  render={({ field: { onChange } }) => (
                    <Autocomplete
                      options={props.projects}
                      getOptionLabel={(option) =>
                        option.projectName ? option.projectName : ""
                      }
                      defaultValue={props.projects.find(
                        (projectItem) =>
                          projectItem.projectName === props.demand.projectName
                      )}
                      onChange={(e, value) => {
                        value !== null
                          ? onChange(value.projectName)
                          : onChange(null);
                      }}
                      renderInput={(params) => (
                        <TextField {...params} label="Projekt" />
                      )}
                    />
                  )}
                />
              ) : (
                <TextField
                  disabled
                  label="Projekt"
                  defaultValue={props.demand.projectName}
                />
              )}
            </Grid>
            {!isNew && (
              <Grid
                item
                sx={{
                  "& .MuiTextField-root": { m: 1, width: 500 },
                }}
              >
                <Controller
                  render={({ field }) => (
                    <TextField
                      disabled
                      label="Nazwa zapotrzebowania"
                      {...field}
                    />
                  )}
                  name="demandName"
                  control={control}
                />
              </Grid>
            )}
            {fields.map((item, index) => {
              return (
                <Stack
                  sx={{ m: 1 }}
                  key={item.key}
                  direction="row"
                  justifyContent="left"
                  spacing={1}
                >
                  {isNew || authCtx.authorities.includes("ROLE_ADMIN") ? (
                    <Controller
                      name={`demandPopcMaterials.${index}.popcMaterialCode`}
                      control={control}
                      render={({ field: { onChange } }) => (
                        <Autocomplete
                          size={"small"}
                          fullWidth
                          options={props.popcMaterials}
                          getOptionLabel={(option) =>
                            option.popcMaterialCode
                              ? option.popcMaterialCode
                              : ""
                          }
                          defaultValue={props.popcMaterials.find(
                            (popcMaterial) =>
                              popcMaterial.popcMaterialCode ===
                              getValues(
                                `demandPopcMaterials.${index}.popcMaterialCode`
                              )
                          )}
                          onChange={(e, value) => {
                            value !== null
                              ? onChange(value.popcMaterialCode)
                              : onChange(null);
                          }}
                          renderInput={(params) => (
                            <TextField {...params} label="Materiał" />
                          )}
                        />
                      )}
                    />
                  ) : (
                    <Controller
                      render={({ field }) => (
                        <TextField
                          disabled
                          size="small"
                          defaultValue={getValues(
                            `demandPopcMaterials.${index}.popcMaterialCode`
                          )}
                          {...field}
                        />
                      )}
                      name={`demandPopcMaterials.${index}.popcMaterialCode`}
                      control={control}
                    />
                  )}
                  {isNew || authCtx.authorities.includes("ROLE_ADMIN") ? (
                    <Controller
                      render={({ field }) => (
                        <TextField
                          type="number"
                          label="Ilość"
                          size="small"
                          {...field}
                        />
                      )}
                      name={`demandPopcMaterials.${index}.quantity`}
                      control={control}
                    />
                  ) : (
                    <Controller
                      render={({ field }) => (
                        <TextField
                          disabled
                          type="number"
                          label="Ilość"
                          size="small"
                          {...field}
                        />
                      )}
                      name={`demandPopcMaterials.${index}.quantity`}
                      control={control}
                    />
                  )}

                  <Box display={"flex"} alignItems={"center"}>
                    {(isNew || authCtx.authorities.includes("ROLE_ADMIN")) && (
                      <Button
                        size={"small"}
                        variant="contained"
                        color="error"
                        onClick={() => remove(index)}
                        endIcon={<DeleteForeverIcon />}
                      >
                        Usuń
                      </Button>
                    )}
                  </Box>
                </Stack>
              );
            })}
            <Stack direction="row" justifyContent="flex-start" sx={{ m: 1 }}>
              {(isNew || authCtx.authorities.includes("ROLE_ADMIN")) && (
                <Button
                  size="small"
                  variant="contained"
                  color="success"
                  endIcon={<AddBoxIcon />}
                  onClick={() => {
                    append({ popcMaterialCode: "", quantity: "" });
                  }}
                >
                  Dodaj materiał
                </Button>
              )}
            </Stack>
            <Grid item>
              <Stack direction="row" justifyContent="flex-end" spacing={2}>
                <Button
                  variant="contained"
                  color="error"
                  endIcon={<CancelIcon />}
                  onClick={() => navigate(-1)}
                >
                  Anuluj i wróć
                </Button>
                {(isNew || authCtx.authorities.includes("ROLE_ADMIN")) && (
                  <Button
                    type="submit"
                    variant="contained"
                    color="success"
                    endIcon={<SaveIcon />}
                  >
                    Zapisz
                  </Button>
                )}
              </Stack>
            </Grid>
          </form>
        </Grid>
      </CardContent>
    </Card>
  );
}
