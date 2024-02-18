package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Appointments;
import model.Users;

import java.sql.Timestamp;

public class reportsMenuUsersScheduleController {

    @FXML
    private TableColumn<Appointments, Integer> appointmentContactIDCol;

    @FXML
    private TableColumn<Appointments, Integer> appointmentCustomerIDCol;

    @FXML
    private TableColumn<Appointments, String> appointmentDescriptionCol;

    @FXML
    private TableColumn<Appointments, Timestamp> appointmentEndDateAndTimeCol;

    @FXML
    private TableColumn<Appointments, Integer> appointmentIDCol;

    @FXML
    private TableColumn<Appointments, String> appointmentLocationCol;

    @FXML
    private TableColumn<Appointments, Timestamp> appointmentStartDateAndTimeCol;

    @FXML
    private TableColumn<Appointments, String> appointmentTitleCol;

    @FXML
    private TableColumn<Appointments, String> appointmentTypeCol;

    @FXML
    private ComboBox<Users> comboBoxUsers;

    @FXML
    private TableView<Appointments> tableViewUsers;

    @FXML
    void onActionComboBoxUsers(ActionEvent event) {

    }

    @FXML
    void onActionReturnToReportsMenu(ActionEvent event) {

    }

}
