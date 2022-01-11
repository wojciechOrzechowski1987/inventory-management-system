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

export default function ProductItemForm(props) {
  const theme = useTheme();
  const navigate = useNavigate();
  const authCtx = useContext(AuthContext);

  const [productItemName, setProductItemName] = React.useState(
    props.productItem.productItemName
  );

  const [price, setPrice] = React.useState(props.productItem.price);

  const [popcMaterialCode, setPopcMaterialCode] = React.useState(
    props.productItem.popcMaterialCode
  );

  const [vendorName, setVendorName] = React.useState(
    props.productItem.vendorName
  );

  const submitForm = (event) => {
    event.preventDefault();

    const productItem = {
      productItemName: productItemName,
      price: price,
      popcMaterialCode: popcMaterialCode,
      vendorName: vendorName,
    };

    if (!props.productItem.id) {
      axios
        .post("http://localhost:8080/productItem/newProductItem", productItem, {
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
          "http://localhost:8080/productItem/editProductItem/" +
            props.productItem.id,
          productItem,
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
            props.productItem.productItemName === ""
              ? "Dodaj nowy produkt."
              : "Edycja produktu."
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
              <Autocomplete
                options={props.popcMaterials}
                getOptionLabel={(option) =>
                  option.popcMaterialCode ? option.popcMaterialCode : ""
                }
                defaultValue={props.popcMaterials.find(
                  (code) => code.popcMaterialCode === popcMaterialCode
                )}
                onChange={(e, value) => {
                  return value !== null
                    ? setPopcMaterialCode(value.popcMaterialCode)
                    : setPopcMaterialCode(null);
                }}
                renderInput={(params) => (
                  <TextField {...params} label="Kod produktu" />
                )}
              />
            </Grid>
            <Grid item>
              <Autocomplete
                options={props.vendors}
                getOptionLabel={(option) =>
                  option.vendorName ? option.vendorName : ""
                }
                defaultValue={props.vendors.find(
                  (vendor) => vendor.vendorName === vendorName
                )}
                onChange={(e, value) => {
                  return value !== null
                    ? setVendorName(value.vendorName)
                    : setVendorName(null);
                }}
                renderInput={(params) => (
                  <TextField {...params} label="Producent" />
                )}
              />
            </Grid>
            <Grid item>
              <TextField
                id="filled-search"
                label="Nazwa produktu"
                name="productItemName"
                value={productItemName}
                onChange={(e) => setProductItemName(e.target.value)}
              />
            </Grid>
            <Grid item>
              <TextField
                id="filled-search"
                label="Cena"
                name="price"
                value={price}
                onChange={(e) => setPrice(e.target.value)}
              />
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
