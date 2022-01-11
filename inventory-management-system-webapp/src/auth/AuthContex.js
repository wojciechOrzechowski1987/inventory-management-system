import React, { useState } from "react";

const AuthContext = React.createContext({
  token: "",
  authorities: "",
  username: "",
  isLoggedIn: false,
  login: (token, username, authorities) => {},
  logout: () => {},
});

const retrieveStoredToken = () => {
  const storedToken = localStorage.getItem("token");
  const storedAuthorities = localStorage.getItem("authorities");
  const storedUsername = localStorage.getItem("username");

  return {
    token: storedToken,
    authorities: storedAuthorities,
    username: storedUsername,
  };
};

export const AuthContextProvider = (props) => {
  const tokenData = retrieveStoredToken();

  let initialToken, initialAuthorities, initialUsername;
  if (tokenData) {
    initialToken = tokenData.token;
    initialAuthorities = tokenData.authorities;
    initialUsername = tokenData.username;
  }

  const [token, setToken] = useState(initialToken);
  const [authorities, setAuthorities] = useState(initialAuthorities);
  const [username, setUsername] = useState(initialUsername);

  const userIsLoggedIn =
    authorities !== null && username !== null ? !!token : false; //if token is string: empty return FALSE empty TRUE

  const loginHandler = (token, username, authorities) => {
    setToken(token);
    setAuthorities(authorities);
    setUsername(username);
    localStorage.setItem("token", token);
    localStorage.setItem("authorities", authorities);
    localStorage.setItem("username", username);
  };

  const logoutHandler = () => {
    setToken(null);
    setAuthorities([]);
    setUsername(null);
    localStorage.removeItem("token");
    localStorage.removeItem("authorities");
    localStorage.removeItem("username");
  };

  const contextValue = {
    token: token,
    authorities: authorities,
    username: username,
    isLoggedIn: userIsLoggedIn,
    login: loginHandler,
    logout: logoutHandler,
  };

  return (
    <AuthContext.Provider value={contextValue}>
      {props.children}
    </AuthContext.Provider>
  );
};

export default AuthContext;
