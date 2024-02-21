package Controller;

import DAO.AppointmentsDAO;
import DAO.UsersDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointments;
import model.Users;
import utility.errorMessages;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

/**
 * reportsMenuUsersScheduleController is used to see appointments based off of selected User.
 */
public class reportsMenuUsersScheduleController implements Initializable {

    /**
     * ComboBox Declaration
     */
    @FXML private ComboBox<Users> comboBoxUsers;

    /**
     * TableView/Column Declarations
     */
    @FXML private TableView<Appointments> tableViewUsers;
    @FXML private TableColumn<Appointments, Integer> appointmentContactIDCol;
    @FXML private TableColumn<Appointments, Integer> appointmentCustomerIDCol;
    @FXML private TableColumn<Appointments, String> appointmentDescriptionCol;
    @FXML private TableColumn<Appointments, Timestamp> appointmentEndDateAndTimeCol;
    @FXML private TableColumn<Appointments, Integer> appointmentIDCol;
    @FXML private TableColumn<Appointments, String> appointmentLocationCol;
    @FXML private TableColumn<Appointments, Timestamp> appointmentStartDateAndTimeCol;
    @FXML private TableColumn<Appointments, String> appointmentTitleCol;
    @FXML private TableColumn<Appointments, String> appointmentTypeCol;

    /**
     * onActionComboBoxUsers(ActionEvent) is used to populate tableview by selected user from comboBox
     * @param event
     * @throws SQLException
     */
    @FXML void onActionComboBoxUsers(ActionEvent event) throws SQLException {
        if (comboBoxUsers == null) {
            errorMessages.errorCode(33);
        }
        else {
            String nameForId = String.valueOf(comboBoxUsers.getValue());
            int userId = UsersDAO.getUsersNameById(nameForId);
            if (AppointmentsDAO.getAppointmentForUserList(userId).isEmpty()) {
                tableViewUsers.refresh();
                for (int i = 0; i < tableViewUsers.getItems().size(); i++) {
                    tableViewUsers.getItems().clear();
                    tableViewUsers.setPlaceholder(new Label("There are no appointments for " + nameForId));
                }
            } else {
                tableViewUsers.setItems(AppointmentsDAO.getAppointmentForUserList(userId));
            }
        }
    }

    /**
     * onActionReturnToReportsMenu(ActionEvent) returns the user to the Reports Menu
     * @param event
     * @throws IOException
     */
    @FXML void onActionReturnToReportsMenu(ActionEvent event) throws IOException {
        reportsMenuController.returnToReportsMenu(event);
    }

    /**
     * initialize fills comboBoxContacts with all Users
     * initialize sets cellValues in tableView
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Fill in contact box with all Users
        comboBoxUsers.setItems(UsersDAO.getAllUsers());
        comboBoxUsers.setPromptText("Select a User");

        //Sets tableViewUsers PlaceHolder Label
        tableViewUsers.setPlaceholder(new Label("No User Selected or User has NO appointments"));

        //Set Cell values
        appointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appointmentTitleCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        appointmentDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        appointmentLocationCol.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        appointmentTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appointmentStartDateAndTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        appointmentEndDateAndTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        appointmentCustomerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appointmentContactIDCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
    }
}
