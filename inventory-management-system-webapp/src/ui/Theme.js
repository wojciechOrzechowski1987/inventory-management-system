import { grey } from "@mui/material/colors";
import { createTheme } from "@mui/material/styles";

const theme = createTheme({
  palette: {
    primary: {
      main: grey[500],
    },
    secondary: {
      main: grey[100],
    },
    background: {
      paper: grey[400],
    },
  },
  typography: {
    fontFamily: "Raleway",
    body1: {
      fontWeight: 600,
    },
    button: {
      fontWeight: 700,
      textTransform: "none",
    },
  },
});
export default theme;
