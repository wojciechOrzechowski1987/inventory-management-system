import Dialog from "@mui/material/Dialog";
import {
  DialogActions,
  DialogContentText,
  DialogTitle,
  useTheme,
} from "@mui/material";
import DialogContent from "@mui/material/DialogContent";
import Button from "@mui/material/Button";
import DeleteForeverIcon from "@mui/icons-material/DeleteForever";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import React from "react";

export default function DeleteEntryDialog(props) {
  const theme = useTheme();

  return (
    <div>
      <Dialog
        open={props.open}
        onClose={() => props.setOpenDialog(false)}
        sx={{
          zIndex: theme.zIndex.modal + 2,
        }}
      >
        <DialogTitle id="alert-dialog-title">{props.text}</DialogTitle>
        <DialogContent>
          <DialogContentText>
            W wyniku operacji usuniesz wszystkie dane związane z wybranym
            obiektem!
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button
            size="samll"
            variant="contained"
            color="error"
            endIcon={<DeleteForeverIcon />}
            onClick={() => props.deleteEntry(props.entryId)}
          >
            Usuń
          </Button>
          <Button
            size="large"
            variant="contained"
            color="success"
            endIcon={<ArrowBackIcon />}
            autoFocus
            onClick={() => props.setOpenDialog(false)}
          >
            Anuluj
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}
