import { CardHeader } from "@mui/material";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Grid from "@mui/material/Grid";
import React, { useEffect, useState } from "react";
import { useTheme } from "@emotion/react";
import { useNavigate } from "react-router-dom";
import OrderToVendor from "./OrderToVendor";

export default function OrdersListCard(props) {
  const theme = useTheme();
  useNavigate();
  const [vendorsCount, setVendorsCount] = useState(null);

  useEffect(() => {
    if (props.orders.productItems) {
      let myArray = props.orders.productItems.map((o) => o.vendor);
      let unique = [...new Set(myArray)];
      setVendorsCount(unique);
      for (let i = 0; i < unique.length; i++) {
        props.orders.productItems.filter((o) => o.vendor === unique[i]);
      }
    }
  }, [props.orders.productItems]);

  return (
    <Card
      sx={{
        backgroundColor: theme.palette.primary.light,
      }}
    >
      <CardHeader title="LISTA ZAMÓWIEŃ" />
      <CardContent>
        {vendorsCount &&
          vendorsCount.map((vendor) => {
            let orderArray = props.orders.productItems.filter(
              (o) => o.vendor === vendor
            );
            return (
              <Grid
                container
                direction="row"
                justifyContent={"center"}
                sx={{ marginBottom: 5 }}
              >
                <OrderToVendor order={orderArray} demand={props.demand} />
              </Grid>
            );
          })}
      </CardContent>
    </Card>
  );
}
