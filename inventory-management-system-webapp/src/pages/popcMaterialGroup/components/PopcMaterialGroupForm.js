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

export default function PopcMaterialGroupForm(props) {
  const [groupName, setGroupName] = React.useState(props.group.popcGroupName);
  const [subgroups, setSubgroups] = React.useState([]);

  const theme = useTheme();
  const authCtx = useContext(AuthContext);
  const navigate = useNavigate();

  const submitForm = (event) => {
    event.preventDefault();

    const popcGroup = {
      popcGroupName: groupName,
      popcSubgroup: subgroups,
    };

    if (!props.group.id) {
      axios
        .post("http://localhost:8080/popcGroup/newGroup", popcGroup, {
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
          "http://localhost:8080/popcGroup/editGroup/" + props.group.id,
          popcGroup,
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
            props.group.popcGroupName === ""
              ? "Dodaj nową grupę materiałową."
              : "Edycja grupy materiałowej."
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
                label="Nazwa grupy"
                name="groupName"
                value={groupName}
                onChange={(e) => setGroupName(e.target.value)}
              />
            </Grid>
            <Grid item>
              {props.subgroups.length > 0 ? (
                <Autocomplete
                  multiple
                  id="checkboxes-tags-demo"
                  disableCloseOnSelect
                  noOptionsText="Brak wolnych podgrup"
                  options={props.subgroups}
                  onChange={(event, value) => setSubgroups(value)}
                  getOptionLabel={(option) => option.popcSubgroupName}
                  defaultValue={props.subgroups.filter((subgroup) =>
                    props.group.popcSubgroup.some(
                      (groupSubgroup) =>
                        groupSubgroup.popcSubgroupName ===
                        subgroup.popcSubgroupName
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
                      {option.popcSubgroupName}
                    </li>
                  )}
                  renderInput={(params) => (
                    <TextField
                      {...params}
                      label="Podgrupy"
                      placeholder="Szukaj"
                    />
                  )}
                />
              ) : (
                <TextField
                  disabled
                  id="outlined-disabled"
                  label="Podgrupy"
                  defaultValue="Brak wolnych podgrup"
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
                    size="small"
                    color="error"
                    endIcon={<CancelIcon />}
                    onClick={() => navigate(-1)}
                  >
                    Anuluj
                  </Button>
                  <Button
                    variant="contained"
                    size="small"
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
