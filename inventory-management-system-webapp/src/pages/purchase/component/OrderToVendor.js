import Grid from "@mui/material/Grid";
import TextField from "@mui/material/TextField";
import Stack from "@mui/material/Stack";
import Button from "@mui/material/Button";
import React, { useContext } from "react";
import Box from "@mui/material/Box";
import SaveIcon from "@mui/icons-material/Save";
import FileDownloadIcon from "@mui/icons-material/FileDownload";
import axios from "axios";
import AuthContext from "../../../auth/AuthContex";
import { useNavigate } from "react-router-dom";

export default function OrderToVendor(props) {
  const authCtx = useContext(AuthContext);
  const navigate = useNavigate();
  const purchaseObject = {
    demand: props.demand.demandName,
    productItems: props.order,
  };

  const onSubmit = () => {
    console.log(purchaseObject);
    axios
      .post("http://localhost:8080/purchase/newPurchase", purchaseObject, {
        headers: {
          Authorization: "Bearer " + authCtx.token,
        },
      })
      .then(() => {
        navigate(-1);
      });
  };

  return (
    <Box>
      <Grid item sx={{ m: 1 }}>
        <TextField
          disabled
          label="DOSTAWCA"
          size="small"
          defaultValue={props.order[0].vendor}
        />
      </Grid>
      {props.order.map((item) => (
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
              defaultValue={item.popc3Code}
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
          <Button
            variant="contained"
            color="info"
            endIcon={<FileDownloadIcon />}
          >
            Generuj plik
          </Button>
          <Button
            onClick={onSubmit}
            variant="contained"
            color="success"
            endIcon={<SaveIcon />}
          >
            Zapisz Zamówienie
          </Button>
        </Stack>
      </Grid>
    </Box>
  );
}
