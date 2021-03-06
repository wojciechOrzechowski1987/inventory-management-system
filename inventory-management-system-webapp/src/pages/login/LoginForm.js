import React, { useContext, useState } from "react";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Grid from "@mui/material/Grid";
import AddBoxIcon from "@mui/icons-material/AddBox";
import Box from "@mui/material/Box";
import { useTheme } from "@emotion/react";
import Stack from "@mui/material/Stack";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import AuthContext from "../../auth/AuthContex";
import { CardHeader } from "@mui/material";

export default function LoginForm() {
  const [username, setUsername] = useState([]);
  const [password, setPassword] = useState([]);
  const authCtx = useContext(AuthContext);
  const theme = useTheme();
  const navigate = useNavigate();
  const [loginError, setLoginError] = React.useState(false);
  const [loginErrorMessage, setLoginErrorMessage] = React.useState("");

  const submitCredentials = (event) => {
    event.preventDefault();

    const credentials = {
      username: username,
      password: password,
    };

    axios
      .post("http://localhost:8080/user/login", credentials)
      .then((response) => {
        if (response.status === 200) {
          authCtx.login(
            response.headers.authorization,
            response.headers.username,
            response.data
          );
          navigate("/district", { replace: true });
        }
      })
      .catch((error) => {
        if (error.response.data.fieldErrors) {
          error.response.data.fieldErrors.forEach((fieldError) => {
            if (fieldError.field === "credentials") {
              setLoginError(true);
              setLoginErrorMessage(fieldError.message);
            }
          });
        } else {
          console.log(error.message);
        }
      });
  };

  return (
    <React.Fragment>
      <Card
        sx={{
          backgroundColor: theme.palette.primary.light,
        }}
      >
        <CardContent>
          <CardHeader
            title="Aplikacja dost??pna tylko dla zalogowanych u??ytkownik??w."
            subheader="Prosz?? o zalogowanie si??."
          />
          <Grid
            container
            direction="column"
            justifyContent="center"
            alignItems={"center"}
          >
            <Box
              sx={{
                "& .MuiTextField-root": { m: 1, width: 500 },
              }}
            >
              <Grid item>
                <TextField
                  error={loginError}
                  helperText={loginErrorMessage}
                  id="filled-search"
                  label="U??ytkownik"
                  name="username"
                  onChange={(e) => setUsername(e.target.value)}
                />
              </Grid>
              <Grid item>
                <TextField
                  error={loginError}
                  helperText={loginErrorMessage}
                  id="filled-password-input"
                  label="Has??o"
                  type="password"
                  onChange={(e) => setPassword(e.target.value)}
                />
              </Grid>

              <Grid item>
                <Stack direction="row" justifyContent="center" spacing={2}>
                  <Button
                    variant="contained"
                    color="success"
                    endIcon={<AddBoxIcon />}
                    onClick={submitCredentials}
                  >
                    Zaloguj
                  </Button>
                </Stack>
              </Grid>
            </Box>
          </Grid>
        </CardContent>
      </Card>
    </React.Fragment>
  );
}
