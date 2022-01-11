import React, { useEffect, useState } from "react";
import { useTheme } from "@mui/styles";
import { Link } from "react-router-dom";
import { useContext } from "react";
import AuthContex from "../auth/AuthContex";
import { menuOption, routes } from "../data/InputData";
import Menu from "@mui/material/Menu";
import MenuItem from "@mui/material/MenuItem";
import Tab from "@mui/material/Tab";
import Button from "@mui/material/Button";
import { Tabs } from "@mui/material";
import LogoutDialog from "./LogoutDialog";

function capitalize(string) {
  return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase();
}

export default function MyTabs(props) {
  const theme = useTheme();
  const authCtx = useContext(AuthContex);
  const [openDialog, setOpenDialog] = useState(false);
  const openInDialog = () => {
    setOpenDialog(true);
  };

  useEffect(() => {
    [
      ...menuOption.status,
      ...menuOption.demand,
      ...menuOption.order,
      ...menuOption.administration,
      ...routes,
    ].forEach((route) => {
      switch (window.location.pathname) {
        case `${route.link}`:
          if (props.value !== route.activeIndex) {
            props.setValue(route.activeIndex);
            if (
              route.selectedIndex &&
              route.selectedIndex !== props.selectedIndex
            ) {
              props.setSelectedIndex(route.selectedIndex);
            }
          }
          break;
        default:
          break;
      }
    });
  }, [props.value, props.selectedIndex, props]);

  const menuVariant = (
    <React.Fragment>
      {Object.keys(menuOption).map((option, index) => (
        <Menu
          key={`${option}${index}`}
          anchorEl={props.anchorEl && props.anchorEl[index]}
          open={(props.anchorEl && Boolean(props.anchorEl[index])) || false}
          onClose={props.handleClose}
          MenuListProps={{ onMouseLeave: props.handleClose }}
          anchorOrigin={{
            vertical: "top",
            horizontal: "left",
          }}
          style={{ zIndex: 1302 }}
          keepMounted
          sx={{
            "& .MuiPaper-root": {
              backgroundColor: theme.palette.primary.main,
            },
            color: theme.palette.primary.contrastText,
            borderRadius: "0px",
          }}
        >
          {menuOption[option].map((option, index) => (
            <MenuItem
              key={`${option}${index}`}
              component={Link}
              to={option.link}
              sx={{
                fontFamily: "Raleway",
                textTransform: "none",
                fontWeight: 700,
                fontSize: "1rem",
                opacity: 0.7,
                "&:hover": {
                  opacity: 1,
                },
                "&.Mui-selected": {
                  opacity: 1,
                },
              }}
              onClick={(event) => {
                props.handleMenuItemClick(event, option.selectedIndex);
                props.setValue(option.activeIndex);
                props.handleClose();
              }}
              selected={
                option.selectedIndex === props.selectedIndex &&
                props.value === option.activeIndex
              }
            >
              {option.name}
            </MenuItem>
          ))}
        </Menu>
      ))}
    </React.Fragment>
  );

  return (
    <React.Fragment>
      {authCtx.authorities.includes("ROLE_ADMIN") ? (
        <Tabs
          value={props.value}
          onChange={props.handleChange}
          indicatorColor="primary"
          sx={{
            marginLeft: "auto",
            "& .MuiTabs-indicator": {
              backgroundColor: theme.palette.primary.contrastText,
            },
          }}
        >
          {routes.map((route, index) => (
            <Tab
              key={`${route}${index}`}
              component={Link}
              to={route.link}
              label={route.name}
              onMouseEnter={(event) => props.handleClick(index, event)}
              sx={{
                "&.Mui-selected": {
                  color: theme.palette.primary.contrastText,
                },
                fontWeight: theme.typography.fontWeightBold,
                fontSize: "1rem",
                minWidth: 10,
                marginLeft: "25px",
              }}
            />
          ))}
          <Tab
            componet={Button}
            onClick={openInDialog}
            label={"Wyloguj, " + capitalize(authCtx.username)}
            sx={{
              fontWeight: theme.typography.fontWeightBold,
              fontSize: "1rem",
              minWidth: 10,
              marginLeft: "25px",
            }}
          />
        </Tabs>
      ) : (
        <Tabs
          value={props.value}
          onChange={props.handleChange}
          indicatorColor="primary"
          sx={{
            marginLeft: "auto",
            "& .MuiTabs-indicator": {
              backgroundColor: theme.palette.primary.contrastText,
            },
          }}
        >
          {routes.flatMap((route, index) =>
            !route.administration ? (
              <Tab
                key={`${route}${index}`}
                component={Link}
                to={route.link}
                label={route.name}
                onMouseEnter={(event) => props.handleClick(index, event)}
                sx={{
                  "&.Mui-selected": {
                    color: theme.palette.primary.contrastText,
                  },
                  fontWeight: theme.typography.fontWeightBold,
                  fontSize: "1rem",
                  minWidth: 10,
                  marginLeft: "25px",
                }}
              />
            ) : (
              <></>
            )
          )}
          <Tab
            componet={Button}
            onClick={openInDialog}
            label={"Wyloguj, " + capitalize(authCtx.username)}
            sx={{
              fontWeight: theme.typography.fontWeightBold,
              fontSize: "1rem",
              minWidth: 10,
              marginLeft: "25px",
            }}
          />
        </Tabs>
      )}
      {menuVariant}
      {openDialog && (
        <LogoutDialog
          open={openDialog}
          setOpenDialog={setOpenDialog}
          logoutHandler={props.logoutHandler}
        />
      )}
    </React.Fragment>
  );
}
