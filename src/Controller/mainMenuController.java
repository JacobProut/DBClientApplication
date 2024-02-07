package Controller;

import DAO.JDBC;
import DAO.AppointmentsDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class mainMenuController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML private Label ZoneID;

    @FXML private Button addAppointmentButton;

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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log out");
        alert.setHeaderText("Attempting to log out\r" + "Any unsaved data will be LOST!");
        alert.setContentText("Are you sure you want to continue?\r" + "Click 'OK' to confirm exit.\r" + "Click 'Cancel' to stay on the Appointment Scheduler Form.");

        Optional<ButtonType> confirmation = alert.showAndWait();

        if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
            JDBC.closeConnection();
            System.exit(0);
        }
    }

    @FXML
    void onActionReports(ActionEvent event) {

    }

    //Find a way to select object in TableView then import it into appointmentModificationForm.fxml scene.
    @FXML
    void onActionUpdateAppointment(ActionEvent event) throws IOException {


        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/appointmentModificationForm.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
        stage.setTitle("Appointment Modification Form");
    }

    @FXML
    void radioButtonViewAll(ActionEvent event) {
        appointmentSchedulerTable.setItems(AppointmentsDAO.getAllAppointments());

        //Added a placeholder in the TableView for when there is nothing in the database!
        appointmentSchedulerTable.setPlaceholder(new Label("There are no appointments in the database!"));
    }

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
        appointmentSchedulerTable.setItems(AppointmentsDAO.viewMonthAppoints());

        //added a placeholder in the TableView for when there is nothing being displayed.
        appointmentSchedulerTable.setPlaceholder(new Label("There are no appointments scheduled for this upcoming month!"));
    }

    //For some odd reason, this doesn't work.
    @FXML
    void radioButtonViewByWeek(ActionEvent event) {
        appointmentSchedulerTable.setItems(AppointmentsDAO.viewWeekAppoints());

        //added a placeholder in the TableView for when there is nothing being displayed.
        appointmentSchedulerTable.setPlaceholder(new Label("There are no appointments scheduled for this upcoming 7 days!"));
    }

    public static void returnToAppointments(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(customerMenuController.class.getResource("/view/mainMenu.fxml")));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Appointment Scheduler Form");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ZoneID.setText(String.valueOf(ZoneId.systemDefault()));
        appointmentSchedulerTable.setItems(AppointmentsDAO.getAllAppointments());
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
