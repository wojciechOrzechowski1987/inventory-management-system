import React, { useContext } from "react";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Checkbox from "@mui/material/Checkbox";
import FormControl from "@mui/material/FormControl";
import Grid from "@mui/material/Grid";
import AddBoxIcon from "@mui/icons-material/AddBox";
import CancelIcon from "@mui/icons-material/Cancel";
import Box from "@mui/material/Box";
import { useTheme } from "@emotion/react";
import Stack from "@mui/material/Stack";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { Autocomplete, CardHeader } from "@mui/material";
import CheckBoxIcon from "@mui/icons-material/CheckBox";
import CheckBoxOutlineBlankIcon from "@mui/icons-material/CheckBoxOutlineBlank";
import AuthContext from "../../../auth/AuthContex";

export default function DistrictForm(props) {
  const [districtName, setDistrictName] = React.useState(
    props.district.districtName
  );
  const authCtx = useContext(AuthContext);
  const [projects, setProjects] = React.useState(props.district.projects);
  const selectableProjects = props.selectableProjects;

  const [user, setUser] = React.useState(props.district.owner);

  const [districtNameError, setDistrictNameError] = React.useState(false);
  const [districtNameErrorMessage, setDistrictNameErrorMessage] =
    React.useState("");

  const theme = useTheme();
  const navigate = useNavigate();

  const submitDistrict = (event) => {
    event.preventDefault();
    setDistrictNameError(false);
    setDistrictNameErrorMessage("");

    const district = {
      districtName: districtName,
      projects: projects,
      owner: user.username,
    };
    if (!props.district.id) {
      axios
        .post("http://localhost:8080/district/newDistrict", district, {
          headers: {
            Authorization: "Bearer " + authCtx.token,
          },
        })
        .then((response) => {
          navigate(-1);
        })
        .catch((error) => {
          if (error.response.data.fieldErrors) {
            error.response.data.fieldErrors.forEach((fieldError) => {
              if (fieldError.field === "districtName") {
                setDistrictNameError(true);
                setDistrictNameErrorMessage(fieldError.message);
              }
              if (fieldError.field === "unique") {
                setDistrictNameError(true);
                setDistrictNameErrorMessage(fieldError.message);
              }
            });
          } else if (error.response.data) {
            setDistrictNameError(true);
            setDistrictNameErrorMessage(error.response.data);
          }
        });
    } else {
      console.log(district);
      axios
        .put(
          "http://localhost:8080/district/editDistrict/" + props.district.id,
          district,
          {
            headers: {
              Authorization: "Bearer " + authCtx.token,
            },
          }
        )
        .then(() => {
          navigate(-1);
        })
        .catch((error) => {
          if (error.response.data.fieldErrors) {
            error.response.data.fieldErrors.forEach((fieldError) => {
              if (fieldError.field === "districtName") {
                setDistrictNameError(true);
                setDistrictNameErrorMessage(fieldError.message);
              }
              if (fieldError.field === "unique") {
                setDistrictNameError(true);
                setDistrictNameErrorMessage(fieldError.message);
              }
            });
          } else if (error.response.data) {
            setDistrictNameError(true);
            setDistrictNameErrorMessage(error.response.data);
          }
        });
    }
  };

  return (
    <React.Fragment>
      <Card
        sx={{
          backgroundColor: theme.palette.primary.light,
        }}
      >
        <CardContent>
          <CardHeader
            title={
              props.district.districtName === ""
                ? "Dodaj nowy region."
                : "Edycja regionu."
            }
          />
          <Grid container direction="column" justifyContent="center">
            <Box
              component="form"
              sx={{
                "& .MuiTextField-root": { m: 1, width: 500 },
              }}
            >
              <Grid item>
                <TextField
                  error={districtNameError}
                  helperText={districtNameErrorMessage}
                  id="districtName"
                  label="Nazwa regionu"
                  name="districtName"
                  defaultValue={districtName}
                  onChange={(e) => setDistrictName(e.target.value)}
                />
              </Grid>

              <Grid item>
                <Autocomplete
                  id="coordinator"
                  disableClearable
                  disableCloseOnSelect
                  options={props.users}
                  onChange={(event, value) => setUser(value)}
                  getOptionLabel={(option) => option.username}
                  defaultValue={props.users.find(
                    (user) => user.username === props.district.owner
                  )}
                  ListboxProps={{
                    style: { maxHeight: "16rem" },
                  }}
                  style={{ width: 500 }}
                  renderOption={(props, option, { selected }) => (
                    <li {...props}>
                      <Checkbox
                        icon=<CheckBoxOutlineBlankIcon fontSize="small" />
                        checkedIcon=<CheckBoxIcon fontSize="small" />
                        style={{ marginRight: 8 }}
                        checked={selected}
                      />
                      {option.username}
                    </li>
                  )}
                  renderInput={(params) => (
                    <TextField
                      {...params}
                      label="Koordynator"
                      placeholder="Szukaj"
                    />
                  )}
                />
              </Grid>

              <Grid item>
                {selectableProjects.length > 0 ? (
                  <Autocomplete
                    multiple
                    id="projects"
                    disableCloseOnSelect
                    noOptionsText="Brak wolnych projektów"
                    options={selectableProjects}
                    onChange={(event, value) => setProjects(value)}
                    getOptionLabel={(option) =>
                      option.projectCode + ". " + option.projectName
                    }
                    defaultValue={selectableProjects.filter(
                      (project) => project.district !== null
                    )}
                    ListboxProps={{
                      style: { maxHeight: "16rem" },
                    }}
                    style={{ width: 500 }}
                    renderOption={(props, option, { selected }) => (
                      <li {...props}>
                        <Checkbox
                          icon=<CheckBoxOutlineBlankIcon fontSize="small" />
                          checkedIcon=<CheckBoxIcon fontSize="small" />
                          style={{ marginRight: 8 }}
                          checked={selected}
                        />
                        {option.projectCode + ". " + option.projectName}
                      </li>
                    )}
                    renderInput={(params) => (
                      <TextField
                        {...params}
                        label="Projekty"
                        placeholder="Szukaj"
                      />
                    )}
                  />
                ) : (
                  <TextField
                    disabled
                    id="noProjects"
                    label="Projekty"
                    defaultValue="Brak wolnych projektów"
                  />
                )}
              </Grid>
              <Grid item>
                <FormControl
                  sx={{
                    width: 500,
                    m: 1,
                  }}
                >
                  <Stack direction="row" justifyContent="flex-end" spacing={2}>
                    <Button
                      variant="contained"
                      color="error"
                      endIcon={<CancelIcon />}
                      onClick={() => navigate(-1)}
                    >
                      Anuluj
                    </Button>
                    <Button
                      variant="contained"
                      color="success"
                      endIcon={<AddBoxIcon />}
                      onClick={submitDistrict}
                    >
                      Zapisz
                    </Button>
                  </Stack>
                </FormControl>
              </Grid>
            </Box>
          </Grid>
        </CardContent>
      </Card>
    </React.Fragment>
  );
}
