import { Autocomplete, CardHeader } from "@mui/material";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import FormControl from "@mui/material/FormControl";
import Stack from "@mui/material/Stack";
import Button from "@mui/material/Button";
import CancelIcon from "@mui/icons-material/Cancel";
import AddBoxIcon from "@mui/icons-material/AddBox";
import React, { useContext } from "react";
import { useTheme } from "@emotion/react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import AuthContext from "../../../auth/AuthContex";

export default function ProjectForm(props) {
  const [projectName, setProjectName] = React.useState(
    props.project.projectName
  );
  const [projectCode, setProjectCode] = React.useState(
    props.project.projectCode
  );
  const [selectedDistrict, setSelectedDistrict] = React.useState(
    props.project.district
  );

  const [projectStatus, setProjectStatus] = React.useState(
    props.project.status
  );

  const [projectNameError, setProjectNameError] = React.useState(false);
  const [projectNameErrorMessage, setProjectNameErrorMessage] =
    React.useState("");
  const [projectCodeError, setProjectCodeError] = React.useState(false);
  const [projectCodeErrorMessage, setProjectCodeErrorMessage] =
    React.useState("");

  const authCtx = useContext(AuthContext);

  const theme = useTheme();
  const navigate = useNavigate();

  const submitProject = (event) => {
    event.preventDefault();
    setProjectCodeError(false);
    setProjectNameError(false);
    setProjectCodeErrorMessage("");
    setProjectNameErrorMessage("");

    const project = {
      projectCode: projectCode,
      projectName: projectName,
      status: projectStatus,
      district: selectedDistrict,
    };

    if (!props.project.id) {
      axios
        .post("http://localhost:8080/project/newProject", project, {
          headers: {
            Authorization: "Bearer " + authCtx.token,
          },
        })
        .then(() => {
          navigate(-1);
        })
        .catch((error) => {
          if (error.response.data.fieldErrors) {
            error.response.data.fieldErrors.forEach((fieldError) => {
              if (fieldError.field === "projectName") {
                setProjectNameError(true);
                setProjectNameErrorMessage(fieldError.message);
              }
              if (fieldError.field === "projectCode") {
                setProjectCodeError(true);
                setProjectCodeErrorMessage(fieldError.message);
              }
              if (fieldError.field === "unique") {
                setProjectNameError(true);
                setProjectCodeError(true);
                setProjectNameErrorMessage(
                  "Nazwa lub kod projektu istnieje w bazie"
                );
                setProjectCodeErrorMessage(
                  "Nazwa lub kod projektu istnieje w bazie"
                );
              }
            });
          } else if (error.response.data) {
            setProjectNameError(true);
            setProjectNameErrorMessage(error.response.data);
          }
        });
    } else {
      axios
        .put(
          "http://localhost:8080/project/editProject/" + props.project.id,
          project,
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
            props.project.projectName === ""
              ? "Dodaj nowy projekt."
              : "Edycja projektu."
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
                id="filled-search"
                error={projectNameError}
                helperText={projectNameErrorMessage}
                label="Nazwa projektu"
                name="projectName"
                value={projectName}
                onChange={(e) => setProjectName(e.target.value)}
              />
            </Grid>
            <Grid item>
              <TextField
                id="filled-search"
                error={projectCodeError}
                helperText={projectCodeErrorMessage}
                label="Kod projektu"
                name="projectCode"
                value={projectCode}
                onChange={(e) => setProjectCode(e.target.value)}
              />
            </Grid>
            <Grid item>
              <Autocomplete
                options={props.districts}
                getOptionLabel={(option) =>
                  option.districtName ? option.districtName : ""
                }
                defaultValue={props.districts.find(
                  (district) => district.districtName === selectedDistrict
                )}
                onChange={(e, value) => {
                  return value !== null
                    ? setSelectedDistrict(value.districtName)
                    : setSelectedDistrict(null);
                }}
                renderInput={(params) => (
                  <TextField {...params} label="Region" />
                )}
              />
            </Grid>
            {props.status !== null && (
              <Grid item>
                <Autocomplete
                  options={props.status}
                  disableClearable
                  defaultValue={props.status.find(
                    (status) => status === projectStatus
                  )}
                  onChange={(e, value) => setProjectStatus(value)}
                  renderInput={(params) => (
                    <TextField {...params} label="Status projektu" />
                  )}
                />
              </Grid>
            )}
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
                    onClick={submitProject}
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
  );
}
