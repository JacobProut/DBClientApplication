package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ToggleGroup;

public class mainMenuController {

    @FXML
    private Label ZoneID;

    @FXML
    private Button addAppointmentButton;

    @FXML
    private DatePicker dateSearchField;

    @FXML
    private Button deleteAppointmentButton;

    @FXML
    private Button logoutButton;

    @FXML
    private RadioButton radioButtonViewAll;

    @FXML
    private RadioButton radioButtonViewAllCustomers;

    @FXML
    private RadioButton radioButtonViewByMonth;

    @FXML
    private RadioButton radioButtonViewByWeek;

    @FXML
    private ToggleGroup radioButtons;

    @FXML
    private Button reportsButton;

    @FXML
    private TableColumn<?, ?> tableColAppointmentID;

    @FXML
    private TableColumn<?, ?> tableColContact;

    @FXML
    private TableColumn<?, ?> tableColCustomerID;

    @FXML
    private TableColumn<?, ?> tableColDescription;

    @FXML
    private TableColumn<?, ?> tableColEndDateAndTime;

    @FXML
    private TableColumn<?, ?> tableColLocation;

    @FXML
    private TableColumn<?, ?> tableColStartDateAndTime;

    @FXML
    private TableColumn<?, ?> tableColTitle;

    @FXML
    private TableColumn<?, ?> tableColType;

    @FXML
    private TableColumn<?, ?> tableColUserID;

    @FXML
    private Button updateAppointmentButton;

    @FXML
    void onActionAddAppointment(ActionEvent event) {

    }

    @FXML
    void onActionDeleteAppointment(ActionEvent event) {

    }

    @FXML
    void onActionLogout(ActionEvent event) {

    }

    @FXML
    void onActionReports(ActionEvent event) {

    }

    @FXML
    void onActionUpdateAppointment(ActionEvent event) {

    }

    @FXML
    void radioButtonViewAll(ActionEvent event) {

    }

    @FXML
    void radioButtonViewAllCustomers(ActionEvent event) {

    }

    @FXML
    void radioButtonViewByMonth(ActionEvent event) {

    }

    @FXML
    void radioButtonViewByWeek(ActionEvent event) {

    }

}
