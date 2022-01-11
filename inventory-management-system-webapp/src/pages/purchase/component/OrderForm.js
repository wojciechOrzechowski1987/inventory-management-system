import Grid from "@mui/material/Grid";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import DemandCard from "./DemandCard";
import OrderCard from "./OrderCard";
import OrdersListCard from "./OrdersListCard";

export default function OrderForm(props) {
  useNavigate();
  const [selectedDemand, setSelectedDemand] = useState(false);
  const [orderDataComplete, setOrderDataComplete] = useState(false);
  const [orderData, setOrderData] = useState([]);

  const selectedDemandHandler = (isSelectedDemandSet) => {
    setSelectedDemand(isSelectedDemandSet);
  };

  const orderHandler = (isOrderDataSet, data) => {
    setOrderDataComplete(isOrderDataSet);
    setOrderData(data);
  };

  return (
    <React.Fragment>
      <Grid item>
        <DemandCard
          projects={props.projects}
          demand={props.demand}
          popcMaterials={props.popcMaterials}
          onDemandSet={selectedDemandHandler}
        />
      </Grid>
      {selectedDemand && (
        <Grid item sx={{ minWidth: 1500 }}>
          <OrderCard
            projects={props.projects}
            vendors={props.vendors}
            demand={props.demand}
            popcMaterials={props.popcMaterials}
            productItems={props.productItems}
            onOrderSet={orderHandler}
          />
        </Grid>
      )}
      {orderDataComplete && (
        <Grid item>
          <OrdersListCard
            orders={orderData}
            isdemand={selectedDemand}
            demand={props.demand}
          />
        </Grid>
      )}
    </React.Fragment>
  );
}
