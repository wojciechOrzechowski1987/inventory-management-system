import React from "react";
import Box from "@mui/material/Box";
import useGet from "../../hooks/Get";
import CircularProgress from "@mui/material/CircularProgress";
import { useLocation } from "react-router-dom";
import PopcMaterialForm from "./component/PopcMaterialForm";

export default function EditPopcMaterialPage() {
  const location = useLocation();
  const { editedPopcMaterial } = location.state;

  const {
    error: errorSubgroups,
    isPending: isPendingSubgroups,
    data: subgroups,
  } = useGet("http://localhost:8080/popcSubgroup/basic");

  const {
    error: errorProductItems,
    isPending: isPendingProductItems,
    data: productItems,
  } = useGet(
    "http://localhost:8080/productItem/" +
      editedPopcMaterial.id +
      "/productItemsForPopcMaterialEdit"
  );

  return (
    <Box
      component="div"
      sx={{
        backgroundColor: "#9e9e9e",
        minHeight: "72.5vh",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      {isPendingSubgroups && isPendingSubgroups && (
        <CircularProgress color="success" />
      )}
      {subgroups && productItems && (
        <PopcMaterialForm
          subgroups={subgroups}
          material={editedPopcMaterial}
          productItems={productItems}
        />
      )}
    </Box>
  );
}
