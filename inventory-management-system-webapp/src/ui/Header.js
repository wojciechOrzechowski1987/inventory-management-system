import React, { useContext, useState } from "react";
import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import useScrollTrigger from "@mui/material/useScrollTrigger";
import { useNavigate } from "react-router-dom";
import logo from "../assets/logo.svg";
import { useTheme } from "@emotion/react";
import Box from "@mui/material/Box";
import styled from "@emotion/styled";
import AuthContex from "../auth/AuthContex";
import MyTabs from "./MyTabs";

function ElevationScroll(props) {
  const { children } = props;

  const trigger = useScrollTrigger({
    disableHysteresis: true,
    threshold: 0,
  });

  return React.cloneElement(children, {
    elevation: trigger ? 4 : 0,
  });
}

const Offset = styled("div")(({ theme }) => ({
  ...theme.mixins.toolbar,
}));

export default function Header(props) {
  const theme = useTheme();
  const navigate = useNavigate();
  const authCtx = useContext(AuthContex);
  const isLoggedIn = authCtx.isLoggedIn;
  const [anchorEl, setAnchorEl] = useState(null);

  const handleChange = (e, newValue) => {
    props.setValue(newValue);
  };

  const handleClick = (index, e) => {
    setAnchorEl({ [index]: e.currentTarget });
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const handleMenuItemClick = (e, i) => {
    setAnchorEl(null);
    props.setSelectedIndex(i);
  };

  const logoutHandler = () => {
    authCtx.logout();
    props.setSelectedIndex(0);
    props.setValue(0);
    navigate("/", { replace: true });
  };

  return (
    <React.Fragment>
      <ElevationScroll>
        <AppBar
          position={"fixed"}
          sx={{
            zIndex: theme.zIndex.modal + 1,
          }}
        >
          <Toolbar>
            <Box
              component="img"
              alt={"project_logo"}
              src={logo}
              sx={{
                height: "4em",
              }}
            />
            <Box
              component="span"
              alt={"Inventory Managment System"}
              sx={{
                marginLeft: "2rem",
                fontFamily: "Raleway",
                textTransform: "none",
                fontWeight: 700,
                fontSize: "1rem",
              }}
            >
              Inventory Managment System
            </Box>
            {isLoggedIn && (
              <React.Fragment>
                <MyTabs
                  handleChange={handleChange}
                  handleClick={handleClick}
                  handleClose={handleClose}
                  handleMenuItemClick={handleMenuItemClick}
                  logoutHandler={logoutHandler}
                  anchorEl={anchorEl}
                  value={props.value}
                  setValue={props.setValue}
                  selectedIndex={props.selectedIndex}
                  setSelectedIndex={props.setSelectedIndex}
                />
              </React.Fragment>
            )}
          </Toolbar>
        </AppBar>
      </ElevationScroll>
      <Offset />
    </React.Fragment>
  );
}
