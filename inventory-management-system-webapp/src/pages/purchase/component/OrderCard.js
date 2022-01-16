import { Autocomplete, CardHeader } from "@mui/material";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Grid from "@mui/material/Grid";
import TextField from "@mui/material/TextField";
import Stack from "@mui/material/Stack";
import Button from "@mui/material/Button";
import DeleteForeverIcon from "@mui/icons-material/DeleteForever";
import AddBoxIcon from "@mui/icons-material/AddBox";
import React from "react";
import { useTheme } from "@emotion/react";
import { Controller, useFieldArray, useForm } from "react-hook-form";
import Box from "@mui/material/Box";
import CachedIcon from "@mui/icons-material/Cached";

export default function OrderCard(props) {
  const theme = useTheme();

  const { control, getValues, handleSubmit, watch } = useForm({
    defaultValues: {
      productItems: [
        { popc3Code: "", vendor: "", productItem: "", quantity: "" },
      ],
    },
  });

  const { fields, append, remove } = useFieldArray({
    keyName: "key",
    control,
    name: "productItems",
  });

  const data = watch("productItems");

  const onSubmit = (formData) => {
    props.onOrderSet(true, formData);
  };

  return (
    <Card
      sx={{
        backgroundColor: theme.palette.primary.light,
      }}
    >
      <CardHeader title="WYBIERZ PRODUKTY" />
      <CardContent>
        <form onSubmit={handleSubmit(onSubmit)} noValidate>
          <Grid container direction="column" justifyContent="center">
            {fields.map((item, index) => {
              return (
                <Stack
                  sx={{ m: 1 }}
                  key={item.key}
                  direction="row"
                  justifyContent="center"
                  spacing={1}
                >
                  <Controller
                    name={`productItems.${index}.popc3Code`}
                    control={control}
                    render={({ field: { onChange } }) => (
                      <Autocomplete
                        sx={{ width: 300 }}
                        size={"small"}
                        options={props.popcMaterials}
                        getOptionLabel={(option) =>
                          option.popcMaterialCode ? option.popcMaterialCode : ""
                        }
                        defaultValue={props.popcMaterials.find(
                          (popcMaterial) =>
                            popcMaterial.popcMaterialCode ===
                            getValues(`productItems.${index}.productItem`)
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
                  <Controller
                    name={`productItems.${index}.vendor`}
                    control={control}
                    render={({ field: { onChange } }) => (
                      <Autocomplete
                        sx={{ width: 350 }}
                        size={"small"}
                        options={props.vendors.filter((vendor) =>
                          vendor.productItems.some(
                            (popcMaterial) =>
                              popcMaterial.popcMaterialCode ===
                              data[index].popc3Code
                          )
                        )}
                        getOptionLabel={(option) =>
                          option.vendorName ? option.vendorName : ""
                        }
                        defaultValue={props.popcMaterials.find(
                          (popcMaterial) =>
                            popcMaterial.popcMaterialCode ===
                            getValues(`productItems.${index}.productItem`)
                        )}
                        onChange={(e, value) => {
                          value !== null
                            ? onChange(value.vendorName)
                            : onChange(null);
                        }}
                        renderInput={(params) => (
                          <TextField {...params} label="Dostawca" />
                        )}
                      />
                    )}
                  />
                  <Controller
                    name={`productItems.${index}.productItem`}
                    control={control}
                    render={({ field: { onChange } }) => (
                      <Autocomplete
                        size={"small"}
                        sx={{ width: 500 }}
                        options={props.productItems.filter(
                          (productItem) =>
                            productItem.vendorName === data[index].vendor &&
                            productItem.popcMaterialCode ===
                              data[index].popc3Code
                        )}
                        getOptionLabel={(option) =>
                          option.productItemName ? option.productItemName : ""
                        }
                        defaultValue={props.popcMaterials.find(
                          (popcMaterial) =>
                            popcMaterial.popcMaterialCode ===
                            getValues(
                              `productItems.${index}.productProductItemName`
                            )
                        )}
                        onChange={(e, value) => {
                          value !== null
                            ? onChange(value.productItemName)
                            : onChange(null);
                        }}
                        renderInput={(params) => (
                          <TextField {...params} label="Produkt" />
                        )}
                      />
                    )}
                  />

                  <Controller
                    render={({ field }) => (
                      <TextField
                        type="number"
                        label="Ilość"
                        size="small"
                        {...field}
                      />
                    )}
                    name={`productItems.${index}.quantity`}
                    control={control}
                  />
                  <Box display={"flex"} alignItems={"center"}>
                    <Button
                      size={"small"}
                      variant="contained"
                      color="error"
                      onClick={() => remove(index)}
                      endIcon={<DeleteForeverIcon />}
                    >
                      Usuń
                    </Button>
                  </Box>
                </Stack>
              );
            })}
            <Stack direction="row" justifyContent="flex-start" sx={{ m: 1 }}>
              <Button
                size="small"
                variant="contained"
                color="success"
                endIcon={<AddBoxIcon />}
                onClick={() => {
                  append({ popc3Code: "", quantity: "" });
                }}
              >
                Dodaj materiał
              </Button>
            </Stack>
            <Grid item>
              <Stack direction="row" justifyContent="flex-end" spacing={2}>
                <Button
                  type="submit"
                  variant="contained"
                  color="success"
                  endIcon={<CachedIcon />}
                >
                  Utwórz zamówienia
                </Button>
              </Stack>
            </Grid>
          </Grid>
        </form>
      </CardContent>
    </Card>
  );
}
