package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

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
    private TableView<?> appointmentSchedulerTable;

    @FXML
    private TableColumn<?, Integer> tableColAppointmentID;

    @FXML
    private TableColumn<?, ?> tableColContact;

    @FXML
    private TableColumn<?, Integer> tableColCustomerID;

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
    private TableColumn<?, Integer> tableColUserID;

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


    //Not sure if this is right but it switches to customerMenu GUI
    @FXML
    void radioButtonViewAllCustomers(ActionEvent event) throws IOException {
        Stage stage;
        Parent scene;

        stage = (Stage) ((RadioButton)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/customerMenu.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
        stage.setTitle("Customer View List");

    }

    @FXML
    void radioButtonViewByMonth(ActionEvent event) {

    }

    @FXML
    void radioButtonViewByWeek(ActionEvent event) {

    }

}
