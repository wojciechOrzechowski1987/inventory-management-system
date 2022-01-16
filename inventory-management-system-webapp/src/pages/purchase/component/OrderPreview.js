import Grid from "@mui/material/Grid";
import TextField from "@mui/material/TextField";
import Stack from "@mui/material/Stack";
import Button from "@mui/material/Button";
import React from "react";
import Box from "@mui/material/Box";
import CachedIcon from "@mui/icons-material/Cached";
import { useNavigate } from "react-router-dom";

export default function OrderPreview(props) {
  const navigate = useNavigate();

  const buttonClick = () => {
    navigate(-1);
  };

  return (
    <Box>
      <Grid item sx={{ m: 1 }}>
        <TextField
          disabled
          label="DOSTAWCA"
          size="small"
          defaultValue={props.order.productItems[0].vendor}
        />
      </Grid>
      {props.order.productItems.map((item) => (
        <Grid item>
          <Stack
            direction="row"
            justifyContent="flex-start"
            spacing={2}
            sx={{ m: 1 }}
          >
            <TextField
              disabled
              label="POPC3"
              size="small"
              defaultValue={item.popcMaterialCode}
            />
            <TextField
              disabled
              sx={{ width: 350 }}
              label="Produkt"
              size="small"
              defaultValue={item.productItem}
            />
            <TextField
              disabled
              label="Ilość"
              size="small"
              defaultValue={item.quantity}
            />
          </Stack>
        </Grid>
      ))}
      <Grid item sx={{ m: 2 }}>
        <Stack direction="row" justifyContent="flex-end" spacing={2}>
          <Grid item>
            <Button
              onClick={buttonClick}
              variant="contained"
              color="success"
              endIcon={<CachedIcon />}
            >
              Powrót do zamówień
            </Button>
          </Grid>
        </Stack>
      </Grid>
    </Box>
  );
}
