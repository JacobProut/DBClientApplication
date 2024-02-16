package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Appointments;
import model.Contacts;

import java.io.IOException;
import java.sql.Timestamp;

public class reportsMenuContactScheduleController {

    @FXML
    private TableColumn<Appointments, Integer> appointmentCustomerIDCol;

    @FXML
    private TableColumn<Appointments, Timestamp> appointmentEndDateAndTimeCol;

    @FXML
    private TableColumn<Appointments, Integer> appointmentIDCol;

    @FXML
    private TableColumn<Appointments, Timestamp> appointmentStartDateAndTimeCol;

    @FXML
    private TableColumn<Appointments, String> appointmentTitleCol;

    @FXML
    private TableColumn<Appointments, String> appointmentTypeCol;

    @FXML
    private TableColumn<Appointments, String> appointmentDescriptionCol;

    @FXML
    private ComboBox<Contacts> comboBoxContacts;

    @FXML
    private TableView<Appointments> tableViewContact;

    @FXML
    void onActionComboBoxContacts(ActionEvent event) {

    }

    @FXML
    void onActionReturnToReportsMenu(ActionEvent event) throws IOException {
        reportsMenuController.returnToReportsMenu(event);
    }

}
