import * as React from "react";
import { useContext } from "react";
import PropTypes from "prop-types";
import Box from "@mui/material/Box";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TablePagination from "@mui/material/TablePagination";
import TableRow from "@mui/material/TableRow";
import TableSortLabel from "@mui/material/TableSortLabel";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Paper from "@mui/material/Paper";
import { visuallyHidden } from "@mui/utils";
import Button from "@mui/material/Button";
import DeleteForeverIcon from "@mui/icons-material/DeleteForever";
import EditIcon from "@mui/icons-material/Edit";
import Stack from "@mui/material/Stack";
import TextField from "@mui/material/TextField";
import axios from "axios";
import { Link } from "react-router-dom";
import DeleteEntryDialog from "../../../ui/DeleteEntryDialog";
import AuthContext from "../../../auth/AuthContex";
import DoDisturbIcon from "@mui/icons-material/DoDisturb";
import AddBoxIcon from "@mui/icons-material/AddBox";

function descendingComparator(a, b, orderBy) {
  if (b[orderBy] < a[orderBy]) {
    return -1;
  }
  if (b[orderBy] > a[orderBy]) {
    return 1;
  }
  return 0;
}

function getComparator(order, orderBy) {
  return order === "desc"
    ? (a, b) => descendingComparator(a, b, orderBy)
    : (a, b) => -descendingComparator(a, b, orderBy);
}

// This method is created for cross-browser compatibility, if you don't
// need to support IE11, you can use Array.prototype.sort() directly
function stableSort(array, comparator) {
  const stabilizedThis = array.map((el, index) => [el, index]);
  stabilizedThis.sort((a, b) => {
    const order = comparator(a[0], b[0]);
    if (order !== 0) {
      return order;
    }
    return a[1] - b[1];
  });
  return stabilizedThis.map((el) => el[0]);
}

function EnhancedTableHead(props) {
  const { order, orderBy, onRequestSort } = props;
  const createSortHandler = (property) => (event) => {
    onRequestSort(event, property);
  };

  return (
    <TableHead>
      <TableRow>
        {props.headCells.map((headCell, index) => {
          if (headCell.sortable === true) {
            return (
              <TableCell
                key={`${headCell}${index}`}
                align={"left"}
                sortDirection={orderBy === headCell.id ? order : false}
              >
                <TableSortLabel
                  active={orderBy === headCell.id}
                  direction={orderBy === headCell.id ? order : "asc"}
                  onClick={createSortHandler(headCell.id)}
                >
                  <Typography>{headCell.label}</Typography>
                  {orderBy === headCell.id ? (
                    <Box component="span" sx={visuallyHidden}>
                      {order === "desc"
                        ? "sorted descending"
                        : "sorted ascending"}
                    </Box>
                  ) : null}
                </TableSortLabel>
              </TableCell>
            );
          } else {
            return (
              <TableCell key={`${headCell}${index}`}>
                {" "}
                <Typography>{headCell.label}</Typography>
              </TableCell>
            );
          }
        })}
      </TableRow>
    </TableHead>
  );
}

EnhancedTableHead.propTypes = {
  onRequestSort: PropTypes.func.isRequired,
  order: PropTypes.oneOf(["asc", "desc"]).isRequired,
  orderBy: PropTypes.string.isRequired,
  rowCount: PropTypes.number.isRequired,
};

function EnhancedTableToolbar(props) {
  const { search, onHandleSearch } = props;
  const authCtx = useContext(AuthContext);
  const createSearchHandler = (property) => {
    onHandleSearch(property);
  };
  return (
    <Toolbar sx={{ maxWidth: 1050, minWidth: 1050 }}>
      <TextField
        sx={{ flex: "1 1 100%", margin: "10px" }}
        label="Wyszukaj"
        type="search"
        size={"small"}
        color={"secondary"}
        value={search}
        onChange={(e) => createSearchHandler(e.target.value)}
      />
      <Typography sx={{ flex: "1 1 100%" }}>{props.tableTitle}</Typography>
      {authCtx.authorities.includes("project:write") && (
        <Button
          sx={{ flex: "1 1 50%" }}
          component={Link}
          to={"newProject"}
          variant="contained"
          color="success"
          endIcon={<AddBoxIcon />}
        >
          Dodaj projekt
        </Button>
      )}
    </Toolbar>
  );
}

export default function ProjectTable(props) {
  const [rows, setRows] = React.useState(props.rows);
  const [search, setSearch] = React.useState("");
  const [order, setOrder] = React.useState("asc");
  const [orderBy, setOrderBy] = React.useState("name");
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(10);
  const [openDialog, setOpenDialog] = React.useState(false);
  const [objectId, setObjectId] = React.useState();
  const [objectName, setObjectName] = React.useState();
  const authCtx = useContext(AuthContext);

  const handleSearch = (property) => {
    setSearch(property);

    const rowData = rows.map((row) =>
      Object.values(row).filter(
        (option) =>
          option !== true && option !== false && typeof option === "string"
      )
    );

    const matches = rowData.map((row) =>
      row.map((option) => option.toLowerCase().includes(property.toLowerCase()))
    );

    const newRows = [...rows];
    matches.map((row, index) =>
      row.includes(true)
        ? (newRows[index].search = true)
        : (newRows[index].search = false)
    );

    setRows(newRows);
    setPage(0);
  };

  const handleRequestSort = (event, property) => {
    const isAsc = orderBy === property && order === "asc";
    setOrder(isAsc ? "desc" : "asc");
    setOrderBy(property);
  };

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const deleteEntry = (entryId) => {
    axios
      .delete(props.url + "delete/" + entryId, {
        headers: {
          Authorization: "Bearer " + authCtx.token,
        },
      })
      .then((response) => {
        if (response.status === 204) {
          setOpenDialog(false);
          const newRows = [...rows];
          const selectedRow = newRows.filter((row) =>
            Object.values(row).includes(entryId)
          );
          selectedRow.map((row) => (row.search = false));
          setRows(newRows);
        } else {
          console.log("jakis komunikat");
        }
      })
      .catch((error) => {
        console.log(error.message);
      })
      .finally(() => {
        setOpenDialog(false);
      });
  };

  const openInDialog = (entryId, entryName) => {
    setObjectId(entryId);
    setObjectName(entryName);
    setOpenDialog(true);
  };

  // Avoid a layout jump when reaching the last page with empty rows.
  const emptyRows =
    page > 0 ? Math.max(0, (1 + page) * rowsPerPage - rows.length) : 0;

  return (
    <Box>
      {openDialog && (
        <DeleteEntryDialog
          open={openDialog}
          setOpenDialog={setOpenDialog}
          deleteEntry={deleteEntry}
          entryId={objectId}
          text={"Czy napewno chcesz usunąć projekt " + objectName + " ?"}
        />
      )}
      <Paper sx={{ width: "100%", minHeight: "53vh", mb: 2 }}>
        <EnhancedTableToolbar
          //tableTitle={props.tableTitle}
          search={search}
          onHandleSearch={handleSearch}
        />
        <TableContainer>
          <Table
            sx={{ minWidth: 750, minHeight: 700 }}
            aria-labelledby="tableTitle"
            size={"medium"}
          >
            <EnhancedTableHead
              order={order}
              orderBy={orderBy}
              onRequestSort={handleRequestSort}
              rowCount={rows.length}
              headCells={props.headCells}
            />
            <TableBody>
              {/* if you don't need to support IE11, you can replace the `stableSort` call with:
                 rows.slice().sort(getComparator(order, orderBy)) */}
              {stableSort(rows, getComparator(order, orderBy))
                .filter((row) => row.search)
                .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                .map((row, index) => {
                  return (
                    <TableRow key={`${row}${index}`}>
                      <TableCell align="left" width={"300"}>
                        <Typography>{row.projectName}</Typography>
                      </TableCell>
                      <TableCell align="left" width={"70"}>
                        <Typography>{row.projectCode}</Typography>
                      </TableCell>
                      <TableCell align="center" width={"170"}>
                        <Typography
                          sx={{
                            borderRadius: 10,
                            padding: "3px 10px",
                            backgroundColor:
                              (row.status === "Nowy" && "white") ||
                              (row.status === "Zapotrzebowany" && "yellow") ||
                              (row.status === "Zamówiony" && "orange") ||
                              (row.status === "Dostarczony" && "green") ||
                              (row.status === "Anulowany" && "red"),
                          }}
                        >
                          {row.status}
                        </Typography>
                      </TableCell>
                      <TableCell align="left" width="150">
                        <Typography>{row.district}</Typography>
                      </TableCell>

                      <TableCell align="left" width={"200"}>
                        {authCtx.authorities.includes("ROLE_ADMIN") ? (
                          <Stack
                            direction="row"
                            justifyContent="flex-start"
                            spacing={2}
                          >
                            <Button
                              size="small"
                              variant="contained"
                              color="error"
                              endIcon={<DeleteForeverIcon />}
                              onClick={() => {
                                openInDialog(row.id, row.projectName);
                              }}
                            >
                              Usuń
                            </Button>
                            <Button
                              component={Link}
                              to={{
                                pathname: "editProject/" + row.id,
                              }}
                              state={{
                                editedProject: row,
                              }}
                              size="small"
                              variant="contained"
                              color="info"
                              endIcon={<EditIcon />}
                            >
                              Edytuj
                            </Button>
                          </Stack>
                        ) : (
                          <Button
                            sx={{
                              cursor: "not-allowed",
                            }}
                            size="small"
                            variant="contained"
                            color="error"
                            endIcon={<DoDisturbIcon />}
                          >
                            Brak uprawnień
                          </Button>
                        )}
                      </TableCell>
                    </TableRow>
                  );
                })}
              {emptyRows > 0 && (
                <TableRow
                  style={{
                    height: 53 * emptyRows,
                  }}
                >
                  <TableCell colSpan={6} />
                </TableRow>
              )}
            </TableBody>
          </Table>
        </TableContainer>
        <TablePagination
          rowsPerPageOptions={[5, 10, 25, 50, 100]}
          component="div"
          count={rows.length}
          rowsPerPage={rowsPerPage}
          page={page}
          onPageChange={handleChangePage}
          onRowsPerPageChange={handleChangeRowsPerPage}
          labelRowsPerPage="Projektów na stronę:"
          labelDisplayedRows={({ from, to, count }) =>
            `Wyświetlono ${from}-${to} projektów z ${count}`
          }
        />
      </Paper>
    </Box>
  );
}
