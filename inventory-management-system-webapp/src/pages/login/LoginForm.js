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
        } else {
          let errorMessage = "Authentication failed!";
          throw new Error(errorMessage);
        }
      })
      .catch((error) => {
        if (error.response.data) {
          setLoginError(true);
          setLoginErrorMessage(error.response.data);
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
            title="Aplikacja dostępna tylko dla zalogowanych użytkowników."
            subheader="Proszę o zalogowanie się."
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
                  label="Użytkownik"
                  name="username"
                  onChange={(e) => setUsername(e.target.value)}
                />
              </Grid>
              <Grid item>
                <TextField
                  error={loginError}
                  helperText={loginErrorMessage}
                  id="filled-password-input"
                  label="Hasło"
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
