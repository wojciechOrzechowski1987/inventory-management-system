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

export default function VendorForm(props) {
  const [vendorName, setVendorName] = React.useState(props.vendor.vendorName);
  const [productItems, setProductItems] = React.useState(
    props.vendor.productItems
  );
  const authCtx = useContext(AuthContext);

  const theme = useTheme();
  const navigate = useNavigate();

  const submitForm = (event) => {
    event.preventDefault();

    const vendor = {
      vendorName: vendorName,
      productItems: productItems,
    };

    if (!props.vendor.id) {
      axios
        .post("http://localhost:8080/vendor/newVendor", vendor, {
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
          "http://localhost:8080/vendor/editVendor/" + props.vendor.id,
          vendor,
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
            props.vendor.vendorName === ""
              ? "Dodaj nowego dostawcę."
              : "Edycja dostawcy."
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
                label="Nazwa dostawcy"
                name="vendorName"
                value={vendorName}
                onChange={(e) => setVendorName(e.target.value)}
              />
            </Grid>
            {
              <Grid item>
                {props.productItems.length > 0 ? (
                  <Autocomplete
                    multiple
                    id="checkboxes-tags-demo"
                    disableCloseOnSelect
                    noOptionsText="Brak wolnych produktów"
                    options={props.productItems}
                    onChange={(event, value) => setProductItems(value)}
                    getOptionLabel={(option) => option.productItemName}
                    defaultValue={props.productItems.filter((productItem) =>
                      props.vendor.productItems.some(
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
                        {option.popcMaterialCode} - {option.productItemName}
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
                    label="Produkty"
                    defaultValue="Brak wolnych produktów"
                  />
                )}
              </Grid>
            }
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
