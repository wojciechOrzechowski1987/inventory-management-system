import { useContext, useEffect, useState } from "react";
import axios from "axios";
import AuthContext from "../auth/AuthContex";

const useGet = (url) => {
  const authCtx = useContext(AuthContext);
  const [data, setData] = useState(null);
  const [isPending, setIsPending] = useState(true);
  const [error, setError] = useState(false);

  useEffect(() => {
    setTimeout(() => {
      axios
        .get(url, {
          headers: {
            Authorization: "Bearer " + authCtx.token,
          },
        })
        .then((response) => {
          setIsPending(false);
          setData(response.data);
          setError(false);
        })
        .catch((err) => {
          setIsPending(false);
          console.log(err.message);
          setError(true);
        });
    }, 1000);
  }, [authCtx.token, url]);

  return { data, isPending, error };
};

export default useGet;
