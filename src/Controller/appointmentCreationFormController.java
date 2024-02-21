package Controller;

import DAO.AppointmentsDAO;
import DAO.ContactsDAO;
import DAO.CustomersDAO;
import DAO.UsersDAO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Contacts;
import model.Customers;
import model.Users;
import utility.AppointmentChecks;
import utility.TimeManipulations;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;
import static utility.errorMessages.errorCode;

public class appointmentCreationFormController implements Initializable {
    Parent scene;
    Stage stage;

    @FXML private Label zoneID;
    @FXML private ComboBox<LocalTime> comboBoxStartTime;
    @FXML private ComboBox<LocalTime> comboBoxEndTime;
    @FXML private ComboBox<Users> userComboBox;
    @FXML private ComboBox<Customers> customerComboBox;
    @FXML private ComboBox<Contacts> contactComboBox;
    @FXML private TextField appointmentCreationDescription;
    @FXML private TextField appointmentCreationLocation;
    @FXML private TextField appointmentCreationTitle;
    @FXML private TextField appointmentCreationType;
    @FXML private DatePicker endDateCalendar;
    @FXML private DatePicker startDateCalendar;
    @FXML private Label timeLabel;

    //Used for displayCurrentTime()
    private final boolean timeStopped = false;

    @FXML void onActionCancelAppointment(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close Appointment Creation Page");
        alert.setHeaderText("Are you sure you want to leave without saving?");
        alert.setContentText("Click 'OK' to go back to Appointment Scheduler.\r" + "Click 'Cancel' to stay on the Appointment Creation Form.");
        Optional<ButtonType> confirmation = alert.showAndWait();
        if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/mainMenu.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
            stage.centerOnScreen();
            stage.setTitle("Appointment Scheduler");
            System.out.println("Returning to Appointment Scheduler.");
        }
    }

    //Working create Appointment Method w/openHoursForBusiness & doTimesOverLap!!
    @FXML void onActionCreateAppointment(ActionEvent event) {
        try {
            if (appointFieldsEmpty()) {
                String title = appointmentCreationTitle.getText();
                String description = appointmentCreationDescription.getText();
                String location = appointmentCreationLocation.getText();
                String type = appointmentCreationType.getText();
                LocalDateTime startOfAppointment = LocalDateTime.of(startDateCalendar.getValue(), comboBoxStartTime.getValue());
                LocalDateTime endOfAppointment = LocalDateTime.of(endDateCalendar.getValue(), comboBoxEndTime.getValue());
                int customerId = customerComboBox.getValue().getCustomerId();
                int userId = userComboBox.getValue().getUserId();
                int contactId = contactComboBox.getValue().getContactId();


                if (AppointmentChecks.openHoursForBusiness(startOfAppointment, endOfAppointment)) {
                }
                else if (startDateCalendar.getValue().isAfter(endDateCalendar.getValue()) || endDateCalendar.getValue().isBefore(startDateCalendar.getValue())) {
                    System.out.println("End date comes before Start date");
                    errorCode(29);
                }
                else if (comboBoxStartTime.getSelectionModel().getSelectedItem().isAfter(comboBoxEndTime.getValue()) || comboBoxEndTime.getSelectionModel().getSelectedItem().isBefore(comboBoxStartTime.getValue())) {
                    System.out.println("Start time is after end time");
                    errorCode(27);
                }
                else if (comboBoxStartTime.getSelectionModel().getSelectedItem().equals(comboBoxEndTime.getValue())) {
                    System.out.println("Start and End time CANNOT be the same!");
                    errorCode(28);
                }
                else if (AppointmentChecks.doTimesOverLap(customerId,startOfAppointment,endOfAppointment)) {
                }
                else {
                    AppointmentsDAO.createAppointments(title, description, location, type, startOfAppointment, endOfAppointment, customerId, userId, contactId);
                    mainMenuController.returnToAppointments(event);
                }
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TimeZone & Time Setters
        zoneID.setText(String.valueOf(ZoneId.systemDefault()));
        timeLabel.setText(displayCurrentTime());

        //Sets times for comboBoxes
        comboBoxStartTime.setItems(TimeManipulations.timeIntervals());
        comboBoxStartTime.setPromptText("Select a Start Time");
        comboBoxEndTime.setItems(TimeManipulations.timeIntervals());
        comboBoxEndTime.setPromptText("Select a End Time");

        //Sets Contact, User, Customer ComboBoxes with getAll functions / setPromptText
        contactComboBox.setItems(ContactsDAO.getAllContacts());
        contactComboBox.setPromptText("Select a Contact");
        userComboBox.setItems(UsersDAO.getAllUsers());
        userComboBox.setPromptText("Select a User");
        customerComboBox.setItems(CustomersDAO.getAllCustomers());
        customerComboBox.setPromptText("Select a Customer");

        //!!!Lambdas Expressions!!!
        //Sets comboBoxEndTime to comboBoxStartTime plus 15 minutes. This makes it so once a comboBoxStartTime has a set time, comboBoxEndTime adds 15minutes automatically.
        comboBoxStartTime.valueProperty().addListener((firstLookedAtTime, oldTime, newTime) -> comboBoxEndTime.setValue(newTime.plusMinutes(15)));
        //Sets endDateCalendar to the same as startDateCalendar
        startDateCalendar.valueProperty().addListener((firstLookedAtDate, oldDate, newDate) -> endDateCalendar.setValue(newDate));



    }

    //Add appointment empty fields.
    public boolean appointFieldsEmpty() {
        if (appointmentCreationTitle.getText().isBlank() || appointmentCreationTitle.getText().isEmpty()) {
            errorCode(16);
            return false;
        }
        else if (appointmentCreationDescription.getText().isBlank() || appointmentCreationDescription.getText().isEmpty()) {
            errorCode(17);
            return false;
        }
        else if (appointmentCreationLocation.getText().isBlank() || appointmentCreationLocation.getText().isEmpty()) {
            errorCode(18);
            return false;
        }
        else if (appointmentCreationType.getText().isBlank() || appointmentCreationType.getText().isEmpty()) {
            errorCode(19);
            return false;
        }
        else if (startDateCalendar.getValue() == null) {
            errorCode(20);
            return false;
        }
        else if (comboBoxStartTime.getValue() == null) {
            errorCode(21);
            return false;
        }
        else if (endDateCalendar.getValue() == null) {
            errorCode(22);
            return false;
        }
        else if (comboBoxEndTime.getValue() == null) {
            errorCode(23);
            return false;
        }
        else if (customerComboBox.getValue() == null) {
            errorCode(24);
            return false;
        }
        else if (userComboBox.getValue() == null) {
            errorCode(25);
            return false;
        }
        else if (contactComboBox.getValue() == null) {
            errorCode(26);
            return false;
        }
        return true;
    }

    private String displayCurrentTime() {
        Thread currentTime = new Thread(() -> {
            SimpleDateFormat simpleFormat = new SimpleDateFormat("hh:mm:ss a");

            while(!timeStopped) {
                try {
                    sleep(1);
                    //Needed this for time to render properly.
                }
                catch (Exception e) {
                    System.out.println("Cannot Display time!");
                    throw new RuntimeException(e);
                }
                final String showCurrentTime = simpleFormat.format(new Date());
                Platform.runLater(()-> timeLabel.setText(showCurrentTime));
            }
        });
        currentTime.start();
        return null;
    }

}
