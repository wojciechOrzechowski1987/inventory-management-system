import React, { useContext, useState } from "react";
import { ThemeProvider } from "@mui/material/styles";
import { Route, Routes } from "react-router-dom";
import theme from "./ui/Theme";
import Header from "./ui/Header";
import Footer from "./ui/Footer";
import AllDistrictPage from "./pages/district/AllDistrictPage";
import AddDistrictPage from "./pages/district/AddDistrictPage";
import EditDistrictPage from "./pages/district/EditDistrictPage";
import AllProjectsPage from "./pages/project/AllProjectsPage";
import AddProjectPage from "./pages/project/AddProjectPage";
import EditProjectPage from "./pages/project/EditProjectPage";
import AllPopcMaterialGroupPage from "./pages/popcMaterialGroup/AllPopcMaterialGroupPage";
import AddPopcMaterialGroupPage from "./pages/popcMaterialGroup/AddPopcMaterialGroupPage";
import EditPopcMaterialGroupPage from "./pages/popcMaterialGroup/EditPopcMaterialGroupPage";
import AllMaterialSubgroupPage from "./pages/popcmaterialSubgroup/AllMaterialSubgroupPage";
import AddMaterialSubgroupPage from "./pages/popcmaterialSubgroup/AddMaterialSubgroupPage";
import EditMaterialSubgroupPage from "./pages/popcmaterialSubgroup/EditMaterialSubgroupPage";
import AllPopcMaterialPage from "./pages/popcMaterial/AllPopcMaterialPage";
import AddPopcMaterialPage from "./pages/popcMaterial/AddPopcMaterialPage";
import EditPopcMaterialPage from "./pages/popcMaterial/EditPopcMaterialPage";
import AllVendorPage from "./pages/vendor/AllVendorPage";
import AddVendorPage from "./pages/vendor/AddVendorPage";
import EditVendorPage from "./pages/vendor/EditVendorPage";
import AllProductItemPage from "./pages/productItem/AllProductItemPage";
import EditProductItemPage from "./pages/productItem/EditProductItemPage";
import AddProductItemPage from "./pages/productItem/AddProductItemPage";
import AllDemandPage from "./pages/demand/AllDemandPage";
import AddDemandPage from "./pages/demand/AddDemandPage";
import EditDemandPage from "./pages/demand/EditDemandPage";
import AllPurchasePage from "./pages/purchase/AllPurchasePage";
import AddOrderPage from "./pages/purchase/AddOrderPage";
import LoginPage from "./pages/login/LoginPage";
import AuthContext from "./auth/AuthContex";
import Box from "@mui/material/Box";

function App() {
  const [selectedIndex, setSelectedIndex] = useState(0);
  const [value, setValue] = useState(0);
  const authCtx = useContext(AuthContext);

  return (
    <ThemeProvider theme={theme}>
      <Header
        value={value}
        setValue={setValue}
        selectedIndex={selectedIndex}
        setSelectedIndex={setSelectedIndex}
      />
      <Box
        display="flex"
        justifyContent="center"
        alignItems="center"
        minHeight="88.1vh"
        sx={{
          backgroundColor: theme.palette.primary.main,
        }}
      >
        <Routes>
          {!authCtx.isLoggedIn && <Route path="" element={<LoginPage />} />}

          <Route path="/district" element={<AllDistrictPage />} />
          <Route path="/district/newDistrict" element={<AddDistrictPage />} />
          <Route
            path="/district/editDistrict/:districtId"
            element={<EditDistrictPage />}
          />

          <Route path="/project" element={<AllProjectsPage />} />
          <Route path="/project/newProject" element={<AddProjectPage />} />
          <Route
            path="/project/editProject/:projectId"
            element={<EditProjectPage />}
          />

          <Route path="/demand" element={<AllDemandPage />} />
          <Route path="/demand/newDemand" element={<AddDemandPage />} />
          <Route
            path="/demand/editDemand/:demandId"
            element={<EditDemandPage />}
          />

          <Route path="/order" element={<AllPurchasePage />} />
          <Route path="/order/newOrder" element={<AddOrderPage />} />

          <Route path="/report" element={<div>Raporty</div>} />

          <Route
            path="/administration/materialGroups"
            element={<AllPopcMaterialGroupPage />}
          />
          <Route
            path="/administration/materialGroups/newGroup"
            element={<AddPopcMaterialGroupPage />}
          />
          <Route
            path="/administration/materialGroups/editGroup/:groupId"
            element={<EditPopcMaterialGroupPage />}
          />

          <Route
            path="/administration/materialSubgroups"
            element={<AllMaterialSubgroupPage />}
          />
          <Route
            path="/administration/materialSubgroups/newSubgroup"
            element={<AddMaterialSubgroupPage />}
          />
          <Route
            path="/administration/materialSubgroups/editSubgroup/:subgroupId"
            element={<EditMaterialSubgroupPage />}
          />
          <Route
            path="/administration/material"
            element={<AllPopcMaterialPage />}
          />
          <Route
            path="/administration/material/newMaterial"
            element={<AddPopcMaterialPage />}
          />
          <Route
            path="/administration/material/editMaterial/:materialId"
            element={<EditPopcMaterialPage />}
          />
          <Route path="/administration/vendor" element={<AllVendorPage />} />
          <Route
            path="/administration/vendor/newVendor"
            element={<AddVendorPage />}
          />
          <Route
            path="/administration/vendor/editVendor/:vendorId"
            element={<EditVendorPage />}
          />
          <Route
            path="/administration/productItem"
            element={<AllProductItemPage />}
          />
          <Route
            path="/administration/productItem/newProductItem"
            element={<AddProductItemPage />}
          />
          <Route
            path="/administration/productItem/editProductItem/:productItemId"
            element={<EditProductItemPage />}
          />
        </Routes>
      </Box>
      {<Footer />}
    </ThemeProvider>
  );
}

export default App;
