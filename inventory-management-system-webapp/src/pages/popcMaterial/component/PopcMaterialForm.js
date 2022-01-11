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

export default function PopcMaterialForm(props) {
  const [materialName, setMaterialName] = React.useState(
    props.material.popcMaterialName
  );
  const [materialCode, setMaterialCode] = React.useState(
    props.material.popcMaterialCode
  );
  const [materialDescription, setMaterialDescription] = React.useState(
    props.material.popcMaterialCode
  );
  const [selectedSubgroup, setSelectedSubgroup] = React.useState(
    props.material.popcSubgroupName
  );
  const [selectedProductItems, setSelectedProductItems] = React.useState(
    props.material.productItems
  );

  const theme = useTheme();
  const navigate = useNavigate();
  const authCtx = useContext(AuthContext);

  const submitMaterial = (event) => {
    event.preventDefault();

    const material = {
      popcMaterialCode: materialCode,
      popcMaterialDescription: materialDescription,
      popcMaterialName: materialName,
      popcSubgroupName: selectedSubgroup,
      productItems: selectedProductItems,
    };

    if (!props.material.id) {
      axios
        .post("http://localhost:8080/popcMaterial/newMaterial", material, {
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
          "http://localhost:8080/popcMaterial/editMaterial/" +
            props.material.id,
          material,
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
            props.material.popcMaterialName === ""
              ? "Dodaj nowy materiał."
              : "Edycja materiału."
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
                label="Kod materiału"
                name="materialCode"
                value={materialCode}
                onChange={(e) => setMaterialCode(e.target.value)}
              />
            </Grid>
            <Grid item>
              <TextField
                id="filled-search"
                label="Nazwa materiału"
                name="materialName"
                value={materialName}
                onChange={(e) => setMaterialName(e.target.value)}
              />
            </Grid>
            <Grid item>
              <TextField
                id="filled-search"
                label="Opis materiału"
                name="materialDescription"
                value={materialDescription}
                onChange={(e) => setMaterialDescription(e.target.value)}
              />
            </Grid>
            <Grid item>
              <Autocomplete
                options={props.subgroups}
                getOptionLabel={(option) =>
                  option.popcSubgroupName ? option.popcSubgroupName : ""
                }
                defaultValue={props.subgroups.find(
                  (subgroup) => subgroup.popcSubgroupName === selectedSubgroup
                )}
                onChange={(e, value) => {
                  return value !== null
                    ? setSelectedSubgroup(value.popcSubgroupName)
                    : setSelectedSubgroup(null);
                }}
                renderInput={(params) => (
                  <TextField {...params} label="Podgrupa" />
                )}
              />
            </Grid>
            <Grid item>
              {props.productItems.length > 0 ? (
                <Autocomplete
                  multiple
                  id="checkboxes-tags-demo"
                  disableCloseOnSelect
                  noOptionsText="Brak wolnych produktów"
                  options={props.productItems}
                  onChange={(event, value) => setSelectedProductItems(value)}
                  getOptionLabel={(option) =>
                    option.vendorName + "-" + option.productItemName
                  }
                  defaultValue={props.productItems.filter((productItem) =>
                    props.material.productItems.some(
                      (materialProductItem) =>
                        materialProductItem.productItemName ===
                        productItem.productItemName
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
                      {option.vendorName} - {option.productItemName}
                    </li>
                  )}
                  renderInput={(params) => (
                    <TextField
                      {...params}
                      label="Produkty"
                      placeholder="Szukaj"
                    />
                  )}
                />
              ) : (
                <TextField
                  disabled
                  id="outlined-disabled"
                  label="Materiały"
                  defaultValue="Brak wolnych produktów"
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
                    onClick={submitMaterial}
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
