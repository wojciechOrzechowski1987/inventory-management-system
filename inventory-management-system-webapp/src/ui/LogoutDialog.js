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
import DoNotDisturbIcon from "@mui/icons-material/DoNotDisturb";
import React from "react";
import Stack from "@mui/material/Stack";
import CheckIcon from "@mui/icons-material/Check";

export default function LogoutDialog(props) {
  const theme = useTheme();

  const logout = () => {
    props.setOpenDialog(false);
    props.logoutHandler();
  };

  return (
    <div>
      <Dialog
        open={props.open}
        onClose={() => props.setOpenDialog(false)}
        sx={{
          zIndex: theme.zIndex.modal + 2,
        }}
      >
        <DialogTitle id="alert-dialog-title">Wylogowanie</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Czy na pewno chcesz się wylogować?
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Stack direction="row" spacing={2} justifyContent="center">
            <Button
              size="small"
              variant="contained"
              color={"error"}
              endIcon={<CheckIcon />}
              onClick={() => logout()}
            >
              TAK
            </Button>
            <Button
              size="small"
              variant="contained"
              endIcon={<DoNotDisturbIcon />}
              color={"success"}
              autoFocus
              onClick={() => props.setOpenDialog(false)}
            >
              NIE
            </Button>
          </Stack>
        </DialogActions>
      </Dialog>
    </div>
  );
}
