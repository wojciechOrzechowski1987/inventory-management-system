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
import Checkbox from "@mui/material/Checkbox";
import CheckBoxIcon from "@mui/icons-material/CheckBox";
import CheckBoxOutlineBlankIcon from "@mui/icons-material/CheckBoxOutlineBlank";
import AuthContext from "../../../auth/AuthContex";

export default function MaterialSubgroupForm(props) {
  const [subgroupName, setSubgroupName] = React.useState(
    props.subgroup.popcSubgroupName
  );

  const [groupName, setGroupName] = React.useState(
    props.subgroup.popcGroupName
  );

  const [materials, setNewMaterials] = React.useState(props.subgroup.materials);

  const theme = useTheme();
  const navigate = useNavigate();
  const authCtx = useContext(AuthContext);

  const submitForm = (event) => {
    event.preventDefault();

    const popcSubgroup = {
      popcSubgroupName: subgroupName,
      popcGroupName: groupName,
      popcMaterials: materials,
    };

    if (!props.subgroup.id) {
      axios
        .post("http://localhost:8080/popcSubgroup/newSubgroup", popcSubgroup, {
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
          "http://localhost:8080/popcSubgroup/editSubgroup/" +
            props.subgroup.id,
          popcSubgroup,
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
            props.subgroup.popcSubgroupName === ""
              ? "Dodaj nową podgrupę materiałową."
              : "Edycja podgrupy materiałowej."
          }
        />
        <Grid container direction="column" justifyContent="center">
          <Box
            component="form"
            sx={{
              "& .MuiTextField-root": { m: 1, width: 550 },
            }}
          >
            <Grid item>
              <TextField
                id="filled-search"
                label="Nazwa podgrupy"
                name="subgroupName"
                value={subgroupName}
                onChange={(e) => setSubgroupName(e.target.value)}
              />
            </Grid>
            <Grid item>
              <Autocomplete
                options={props.groups}
                getOptionLabel={(option) =>
                  option.popcGroupName ? option.popcGroupName : ""
                }
                defaultValue={props.groups.find(
                  (group) => group.popcGroupName === groupName
                )}
                onChange={(e, value) => {
                  return value !== null
                    ? setGroupName(value.popcGroupName)
                    : setGroupName(null);
                }}
                renderInput={(params) => (
                  <TextField {...params} label="Grupa materiałowa" />
                )}
              />
            </Grid>
            <Grid item>
              {props.materials.length > 0 ? (
                <Autocomplete
                  multiple
                  id="checkboxes-tags-demo"
                  disableCloseOnSelect
                  noOptionsText="Brak materiałów"
                  options={props.materials}
                  onChange={(event, value) => setNewMaterials(value)}
                  getOptionLabel={(option) =>
                    option.popcMaterialCode + ". " + option.popcMaterialName
                  }
                  defaultValue={props.materials.filter((materials) =>
                    props.subgroup.popcMaterials.some(
                      (popcMaterial) =>
                        materials.popcMaterialCode ===
                        popcMaterial.popcMaterialCode
                    )
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
                      {option.popcMaterialCode + ". " + option.popcMaterialName}
                    </li>
                  )}
                  renderInput={(params) => (
                    <TextField
                      {...params}
                      label="Materiały"
                      placeholder="Szukaj"
                    />
                  )}
                />
              ) : (
                <TextField
                  disabled
                  id="outlined-disabled"
                  label="Materiały"
                  defaultValue="Brak wolnych materiałów"
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
                    onClick={submitForm}
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
