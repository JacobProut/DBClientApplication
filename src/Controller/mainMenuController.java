package Controller;

import DAO.JDBC;
import DAO.AppointmentsDAO;
import javafx.application.Platform;
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
import utility.errorMessages;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;
import static javafx.scene.control.ButtonType.CANCEL;
import static utility.errorMessages.*;

public class mainMenuController implements Initializable {
    Stage stage;
    Parent scene;

    //WILL NEED TO REMOVE UN-USED DECLARATIONS
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
    @FXML private Label timeLabel;
    private final boolean timeStopped = false;


    @FXML
    void onActionAddAppointment(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/appointmentCreationForm.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
        stage.setTitle("Appointment Creation Form");
        System.out.println("Switching to Appointment Creation Form.");
    }

    //ADD DELETE METHOD
    @FXML
    void onActionDeleteAppointment(ActionEvent event) {
        if (appointmentSchedulerTable.getSelectionModel().isEmpty()) {
            errorCode(31);
            System.out.println("Null Selection while trying to Delete an Appointment.");
        }

    }

    @FXML
    void onActionLogout(ActionEvent event) {
        System.out.println("Logout Button Selected.");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log out");
        alert.setHeaderText("Attempting to log out\r" + "Any unsaved data will be LOST!");
        alert.setContentText("Are you sure you want to continue?\r" + "Click 'OK' to confirm exit.\r" + "Click 'Cancel' to stay on the Appointment Scheduler Form.");

        Optional<ButtonType> confirmation = alert.showAndWait();

        if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
            JDBC.closeConnection();
            System.out.println("Shutting down Application.");
            System.exit(0);
        }
        if (confirmation.isPresent() && confirmation.get() == CANCEL) {
            System.out.println("Logout canceled.");
        }
    }

    @FXML
    void onActionReports(ActionEvent event) {

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

    //For some odd reason, this doesn't work.
    @FXML
    void radioButtonViewByWeek(ActionEvent event) {
        appointmentSchedulerTable.setItems(AppointmentsDAO.viewWeekAppoints());

        //added a placeholder in the TableView for when there is nothing being displayed.
        appointmentSchedulerTable.setPlaceholder(new Label("There are no appointments scheduled for this week!"));
        System.out.println("Viewing Appointments by Week.");
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
        timeLabel.setText(displayCurrentTime());
    }




    //Found this code up online and added my own touches to it. Figured it would be nice to see a displayed time on the forms.
    private String displayCurrentTime() {
        Thread currentTime = new Thread(() -> {
            SimpleDateFormat simpleFormat = new SimpleDateFormat("hh:mm:ss a");

            while(!timeStopped) {
                try {
                    sleep(1);
                    //Needed this for time to render properly.
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
                final String showCurrentTime = simpleFormat.format(new Date());
                Platform.runLater(()->{
                    timeLabel.setText(showCurrentTime);
                });
            }
        });
        currentTime.start();
        return null;
    }

}
