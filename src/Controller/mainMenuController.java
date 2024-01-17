package Controller;

import DAO.mainMenuControllerDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.ResourceBundle;

public class mainMenuController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML private Label ZoneID;

    @FXML private Button addAppointmentButton;

    @FXML private DatePicker dateSearchField;

    @FXML private Button deleteAppointmentButton;

    @FXML private Button logoutButton;

    @FXML private RadioButton radioButtonViewAll;

    @FXML private RadioButton radioButtonViewAllCustomers;

    @FXML private RadioButton radioButtonViewByMonth;

    @FXML private RadioButton radioButtonViewByWeek;

    @FXML private ToggleGroup radioButtons;

    @FXML private Button reportsButton;

    @FXML private TableView<Appointments> appointmentSchedulerTable;

    @FXML private TableColumn<Appointments, Integer> tableColAppointmentID;

    @FXML private TableColumn<Appointments, Integer> tableColContact;

    @FXML private TableColumn<Appointments, Integer> tableColCustomerID;

    @FXML private TableColumn<Appointments, String> tableColDescription;

    @FXML private TableColumn<Appointments, Timestamp> tableColEndDateAndTime;

    @FXML private TableColumn<Appointments, String> tableColLocation;

    @FXML private TableColumn<Appointments, Timestamp> tableColStartDateAndTime;

    @FXML private TableColumn<Appointments, String> tableColTitle;

    @FXML private TableColumn<Appointments, String> tableColType;

    @FXML private TableColumn<Appointments, Integer> tableColUserID;

    @FXML private Button updateAppointmentButton;


    @FXML
    void onActionAddAppointment(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/appointmentCreationForm.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
        stage.setTitle("Appointment Creation Form");
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
        appointmentSchedulerTable.setItems(mainMenuControllerDAO.getAllAppointments());
    }


    //Not sure if this is right, but it switches to customerMenu GUI
    @FXML
    void radioButtonViewAllCustomers(ActionEvent event) throws IOException {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentSchedulerTable.setItems(mainMenuControllerDAO.getAllAppointments());
        tableColAppointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        tableColTitle.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        tableColDescription.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        tableColLocation.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        tableColContact.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        tableColType.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        tableColStartDateAndTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        tableColEndDateAndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        tableColCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tableColUserID.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }
}
