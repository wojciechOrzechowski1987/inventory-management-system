import * as React from "react";
import { useContext, useState } from "react";
import Grid from "@mui/material/Grid";
import Button from "@mui/material/Button";
import { Link } from "react-router-dom";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";
import Stack from "@mui/material/Stack";
import DeleteForeverIcon from "@mui/icons-material/DeleteForever";
import EditIcon from "@mui/icons-material/Edit";
import { useTheme } from "@emotion/react";
import axios from "axios";
import DeleteEntryDialog from "../../../ui/DeleteEntryDialog";
import AuthContext from "../../../auth/AuthContex";

export default function VendorCard(props) {
  const theme = useTheme();
  const vendor = props.vendor;
  const authCtx = useContext(AuthContext);
  const [show, setShow] = useState(true);
  const [openDialog, setOpenDialog] = useState(false);

  const deleteEntry = (entryId) => {
    axios
      .delete("http://localhost:8080/vendor/delete/" + entryId, {
        headers: {
          Authorization: "Bearer " + authCtx.token,
        },
      })
      .then((response) => {
        if (response.status === 204) {
          setShow(false);
          setOpenDialog(false);
        }
      })
      .catch((error) => {
        console.log(error.message);
      })
      .finally(() => {
        setOpenDialog(false);
      });
  };

  const openInDialog = () => {
    setOpenDialog(true);
  };
  return (
    <React.Fragment>
      {show && (
        <Grid item>
          <Card
            sx={{
              backgroundColor: theme.palette.primary.light,
              width: 300,
              height: 150,
            }}
          >
            <CardContent>
              <Typography color="textSecondary" gutterBottom>
                Dostawca: {vendor.vendorName}
              </Typography>
              <Typography color="textSecondary" gutterBottom>
                Liczba materiałów: {vendor.productItems.length}
              </Typography>
              <Stack direction="row" justifyContent="flex-end" spacing={2}>
                <Button
                  variant="contained"
                  color="error"
                  endIcon={<DeleteForeverIcon />}
                  onClick={() => openInDialog()}
                >
                  Usuń
                </Button>
                <Button
                  component={Link}
                  to={{
                    pathname: "editVendor/" + vendor.id,
                  }}
                  state={{
                    editedVendor: vendor,
                  }}
                  variant="contained"
                  color="info"
                  endIcon={<EditIcon />}
                >
                  Edytuj
                </Button>
              </Stack>
            </CardContent>
          </Card>
        </Grid>
      )}
      {openDialog && (
        <DeleteEntryDialog
          open={openDialog}
          setOpenDialog={setOpenDialog}
          deleteEntry={deleteEntry}
          entryId={vendor.id}
          text={
            "Czy na pewno chcesz usunąć dostawcę " + vendor.vendorName + " ?"
          }
        />
      )}
    </React.Fragment>
  );
}
