import * as React from "react";
import { useContext, useState } from "react";
import Grid from "@mui/material/Grid";
import Button from "@mui/material/Button";
import { Link } from "react-router-dom";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";
import Stack from "@mui/material/Stack";
import DeleteForeverIcon from "@mui/icons-material/DeleteForever";
import EditIcon from "@mui/icons-material/Edit";
import { useTheme } from "@emotion/react";
import { Chip } from "@mui/material";
import DeleteEntryDialog from "../../../ui/DeleteEntryDialog";
import axios from "axios";
import AuthContext from "../../../auth/AuthContex";

export default function PopcMaterialGroupCard(props) {
  const theme = useTheme();
  const authCtx = useContext(AuthContext);
  const group = props.group;
  const [show, setShow] = useState(true);
  const [openDialog, setOpenDialog] = useState(false);

  const deleteEntry = (entryId) => {
    axios
      .delete("http://localhost:8080/popcGroup/delete/" + entryId, {
        headers: {
          Authorization: "Bearer " + authCtx.token,
        },
      })

      .then((response) => {
        if (response.status === 204) {
          setShow(false);
          setOpenDialog(false);
        }
      })
      .catch((error) => {
        console.log(error.message);
      })
      .finally(() => {
        setOpenDialog(false);
      });
  };

  const openInDialog = () => {
    setOpenDialog(true);
  };

  return (
    <React.Fragment>
      {show && (
        <Card
          sx={{
            backgroundColor: theme.palette.primary.light,
            width: 701.13,
            height: 247.38,
          }}
        >
          <CardContent>
            <Typography color="textSecondary" gutterBottom>
              Grupa: {group.popcGroupName}
            </Typography>
            <Typography color="textSecondary" gutterBottom>
              Podgrupy:
            </Typography>
            <Grid
              container
              justifyContent="center"
              spacing={2}
              marginBottom={"16px"}
            >
              {group.popcSubgroup
                .sort((a, b) =>
                  a.popcSubgroupName.localeCompare(b.popcSubgroupName)
                )
                .map((subgroup, index) => (
                  <Grid item key={`${subgroup}${index}`}>
                    <Typography>
                      <Chip
                        key={`${subgroup}${index}`}
                        sx={{
                          borderSpacing: 2,
                        }}
                        label={subgroup.popcSubgroupName}
                      />
                    </Typography>
                  </Grid>
                ))}
            </Grid>
            {authCtx.authorities.includes("ROLE_ADMIN") && (
              <Stack
                direction="row"
                spacing={2}
                marginTop={"20px"}
                justifyContent={"flex-end"}
                alignItems={"flex-end"}
              >
                <Button
                  variant="contained"
                  color="error"
                  endIcon={<DeleteForeverIcon />}
                  onClick={() => openInDialog()}
                >
                  Usuń
                </Button>
                <Button
                  component={Link}
                  to={{
                    pathname: "editGroup/" + group.id,
                  }}
                  state={{
                    editedGroup: group,
                  }}
                  variant="contained"
                  color="info"
                  endIcon={<EditIcon />}
                >
                  Edytuj
                </Button>
              </Stack>
            )}
          </CardContent>
        </Card>
      )}
      {openDialog && (
        <DeleteEntryDialog
          open={openDialog}
          setOpenDialog={setOpenDialog}
          deleteEntry={deleteEntry}
          entryId={group.id}
          text={
            "Czy na pewno chcesz usunąć grupę materiałową " +
            group.popcGroupName +
            " ?"
          }
        />
      )}
    </React.Fragment>
  );
}
