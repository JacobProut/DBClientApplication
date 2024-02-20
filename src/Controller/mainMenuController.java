package Controller;

import DAO.AppointmentsDAO;
import DAO.JDBC;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;
import static javafx.scene.control.Alert.AlertType.WARNING;
import static javafx.scene.control.ButtonType.CANCEL;
import static javafx.scene.control.ButtonType.OK;
import static utility.errorMessages.errorCode;

public class mainMenuController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML private Label ZoneID;
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
    @FXML private Label timeLabel;

    //Used for displayCurrentTime()
    private final boolean timeStopped = false;

    @FXML
    void onActionAddAppointment(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/appointmentCreationForm.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
        stage.centerOnScreen();
        stage.setTitle("Appointment Creation Form");
        System.out.println("Switching to Appointment Creation Form.");
    }

    @FXML
    void onActionDeleteAppointment(ActionEvent event) throws SQLException {
        ObservableList<Appointments> allAppointments;

        if (appointmentSchedulerTable.getSelectionModel().getSelectedItems().isEmpty()) {
            errorCode(31);
            System.out.println("Null Selection while trying to Delete an Appointment.");
        }
        else {
            int appointmentId = appointmentSchedulerTable.getSelectionModel().getSelectedItem().getAppointmentId();
            LocalDateTime appointmentStartTime = appointmentSchedulerTable.getSelectionModel().getSelectedItem().getStartTime();
            DateTimeFormatter selectedTimeFormat = DateTimeFormatter.ofPattern("hh:mm:ss a");
            DateTimeFormatter selectedDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            Alert appointmentForDeletion = new Alert(WARNING);
            appointmentForDeletion.setTitle("Removing Appointment");
            appointmentForDeletion.setHeaderText("Attempting to DELETE an Appointment with the Appointment_ID of [" + appointmentId + "].\n" + "That is scheduled on [" + selectedDateFormat.format(appointmentStartTime) + "] at [" + selectedTimeFormat.format(appointmentStartTime) + "]");
            appointmentForDeletion.setContentText("Are you sure you want to continue?\r" + "Click 'OK' to confirm deletion of the selected Appointment.\r" + "Click 'Cancel' to go back to Appointment Scheduler.");
            appointmentForDeletion.getButtonTypes().add(CANCEL);
            appointmentForDeletion.showAndWait();

            if(appointmentForDeletion.getResult() == OK) {
                AppointmentsDAO.removeAppointment(appointmentId);
                allAppointments = AppointmentsDAO.getAllAppointments();
                appointmentSchedulerTable.setItems(allAppointments);
                appointmentSchedulerTable.refresh();
                System.out.println("Appointment_ID [" + appointmentId + "] Deletion Confirmed!");
            }
            else if (appointmentForDeletion.getResult() == CANCEL){
                System.out.println("Deletion of Appointment_ID [" + appointmentId + "] Canceled!");
            }
        }
    }

    @FXML
    void onActionReports(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/reportsMenu.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
        stage.centerOnScreen();
        stage.setTitle("Reports Menu");
        System.out.println("Opening up Reports Menu");
    }

    @FXML
    void onActionUpdateAppointment(ActionEvent event) throws IOException, SQLException {
        if (appointmentSchedulerTable.getSelectionModel().isEmpty()) {
            errorCode(30);
            System.out.println("Null Selection while trying to Update an Appointment.");
        }
        else {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            FXMLLoader loadupper = new FXMLLoader(getClass().getResource("/view/appointmentModificationForm.fxml"));
            scene = loadupper.load();
            Appointments appointmentSelection = appointmentSchedulerTable.getSelectionModel().getSelectedItem();
            appointmentModificationFormController controller = loadupper.getController();
            controller.appointmentSelection(appointmentSchedulerTable.getSelectionModel().getSelectedIndex(), appointmentSelection);
            stage.setScene(new Scene(scene));
            stage.show();
            stage.centerOnScreen();
            stage.setTitle("Appointment Modification Page");
            System.out.println("Switching to Appointment Modification Form.");
        }

    }

    @FXML
    void radioButtonViewAll(ActionEvent event) {
        appointmentSchedulerTable.setItems(AppointmentsDAO.getAllAppointments());

        //Added a placeholder in the TableView for when there is nothing in the database!
        appointmentSchedulerTable.setPlaceholder(new Label("There are no appointments in the database!"));
        System.out.println("Viewing All Appointments.");
    }

    @FXML
    void radioButtonViewAllCustomers(ActionEvent event) throws IOException {
        stage = (Stage) ((RadioButton)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/customerMenu.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
        stage.centerOnScreen();
        stage.setTitle("Customer View List");
        System.out.println("Switching to Customer View List.");
    }

    @FXML
    void radioButtonViewByMonth(ActionEvent event) {
        appointmentSchedulerTable.setItems(AppointmentsDAO.viewMonthAppoints());

        //added a placeholder in the TableView for when there is nothing being displayed.
        appointmentSchedulerTable.setPlaceholder(new Label("There are no appointments scheduled for this upcoming month!"));
        System.out.println("Viewing Appointments by Month.");
    }

    @FXML
    void radioButtonViewByWeek(ActionEvent event) {
        appointmentSchedulerTable.setItems(AppointmentsDAO.viewWeekAppoints());

        //added a placeholder in the TableView for when there is nothing being displayed.
        appointmentSchedulerTable.setPlaceholder(new Label("There are no appointments scheduled for this week!"));
        System.out.println("Viewing Appointments by Week.");
    }

    @FXML
    void onActionLogout(ActionEvent event) {
        System.out.println("Logout Button Selected.");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log out");
        alert.setHeaderText("Attempting to log out\r" + "Any unsaved data will be LOST!");
        alert.setContentText("Are you sure you want to continue?\r" + "Click 'OK' to confirm exit.\r" + "Click 'Cancel' to stay on the Appointment Scheduler Form.");

        Optional<ButtonType> confirmation = alert.showAndWait();

        if (confirmation.isPresent() && confirmation.get() == OK) {
            JDBC.closeConnection();
            System.out.println("Shutting down Application.");
            System.exit(0);
        }
        if (confirmation.isPresent() && confirmation.get() == CANCEL) {
            System.out.println("Logout canceled.");
        }
    }

    public static void returnToAppointments(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(customerMenuController.class.getResource("/view/mainMenu.fxml")));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
        stage.setTitle("Appointment Scheduler Form");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TimeZone & Time Setters
        ZoneID.setText(String.valueOf(ZoneId.systemDefault()));
        timeLabel.setText(displayCurrentTime());

        //Populates TableView
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

    private String displayCurrentTime() {
        //Lambda expression that displays the Current [systemDefault] time.
        Thread currentTime = new Thread(() -> {
            SimpleDateFormat simpleFormat = new SimpleDateFormat("hh:mm:ss a");

            while(!timeStopped) {
                try {
                    sleep(1);
                    //Needed this for time to render properly.
                }
                catch (Exception e) {
                    System.out.println("System Cannot Display Time!");
                    throw new RuntimeException(e);
                }
                final String showCurrentTime = simpleFormat.format(new Date());

                //Lambda expression that sets timeLabel to showCurrentTime.
                Platform.runLater(()-> timeLabel.setText(showCurrentTime));
            }
        });
        currentTime.start();
        return null;
    }
}
