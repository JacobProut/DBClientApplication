package Controller;

import DAO.*;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;
import utility.AppointmentChecks;
import utility.TimeManipulations;
import utility.errorMessages;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

public class appointmentModificationFormController implements Initializable {
    Parent scene;
    Stage stage;

    @FXML private Label zoneID;

    @FXML private TextField appointmentModificationAppointmentID;

    @FXML private TextField appointmentModificationDescription;

    @FXML private TextField appointmentModificationLocation;

    @FXML private TextField appointmentModificationTitle;

    @FXML private TextField appointmentModificationType;

    @FXML private Button cancelAppointmentModification;

    @FXML private ComboBox<LocalTime> comboBoxEndTime;

    @FXML private ComboBox<LocalTime> comboBoxStartTime;

    @FXML private Button confirmAppointmentModification;

    @FXML private ComboBox<Contacts> contactComboBox;

    @FXML private ComboBox<Customers> customerComboBox;

    @FXML private DatePicker endDateCalendar;

    @FXML private DatePicker startDateCalendar;

    @FXML private ComboBox<Users> userComboBox;

    @FXML private Label timeLabel;
    private final boolean timeStopped = false;

    @FXML
    void onActionCancelAppointmentModification(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close Appointment Modification Page");
        alert.setHeaderText("Are you sure you want to leave without saving?");
        alert.setContentText("Click 'OK' to go back to Appointment Scheduler.\r" + "Click 'Cancel' to stay on the Appointment Modification Form.");
        Optional<ButtonType> confirmation = alert.showAndWait();
        if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/mainMenu.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
            stage.setTitle("Appointment Scheduler");
            System.out.println("Returning to Application Scheduler.");
        }
    }

    @FXML
    void onActionComboBoxContact(ActionEvent event) {

    }

    @FXML
    void onActionComboBoxCustomer(ActionEvent event) {

    }

    @FXML
    void onActionComboBoxUser(ActionEvent event) {

    }

    @FXML
    void onActionEndDate(ActionEvent event) {

    }

    @FXML
    void onActionEndTime(ActionEvent event) {

    }

    @FXML
    void onActionModificationAppointment(ActionEvent event) {
        try {
            if (appointFieldsEmpty()) {
                int id = Integer.parseInt(appointmentModificationAppointmentID.getText());
                String title = appointmentModificationTitle.getText();
                String description = appointmentModificationDescription.getText();
                String location = appointmentModificationLocation.getText();
                String type = appointmentModificationType.getText();
                LocalDateTime startOfAppointment = LocalDateTime.of(startDateCalendar.getValue(), comboBoxStartTime.getValue());
                LocalDateTime endOfAppointment = LocalDateTime.of(endDateCalendar.getValue(), comboBoxEndTime.getValue());
                int customerId = customerComboBox.getValue().getCustomerId();
                int userId = userComboBox.getValue().getUserId();
                int contactId = contactComboBox.getValue().getContactId();


                if (AppointmentChecks.openHoursForBusiness(startOfAppointment, endOfAppointment)) {
                }
                else if (startDateCalendar.getValue().isAfter(endDateCalendar.getValue()) || endDateCalendar.getValue().isBefore(startDateCalendar.getValue())) {
                    System.out.println("End date comes before Start date");
                    errorMessages.errorCode(29);
                }
                else if (comboBoxStartTime.getSelectionModel().getSelectedItem().isAfter(comboBoxEndTime.getValue()) || comboBoxEndTime.getSelectionModel().getSelectedItem().isBefore(comboBoxStartTime.getValue())) {
                    System.out.println("Start time is after end time");
                    errorMessages.errorCode(27);
                }
                else if (comboBoxStartTime.getSelectionModel().getSelectedItem().equals(comboBoxEndTime.getValue())) {
                    System.out.println("Start and End time CANNOT be the same!");
                    errorMessages.errorCode(28);
                }
                else if (AppointmentChecks.doTimesOverLap(customerId,startOfAppointment,endOfAppointment)) {
                }
                else {
                    AppointmentsDAO.updateAppointments(id, title, description, location, type, startOfAppointment, endOfAppointment, customerId, userId, contactId);
                    mainMenuController.returnToAppointments(event);
                }
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void onActionStartDate(ActionEvent event) {

    }

    @FXML
    void onActionStartTime(ActionEvent event) {

    }

    public int selectedIndex;
    Appointments selectedAppointment = new Appointments(1, "title", "desc", "location", "type", LocalDateTime.now(), LocalDateTime.now(), 1, 1, 1);
    public void appointmentSelection(int index, Appointments selectedAppointment) throws SQLException {
        this.selectedIndex = index;
        this.selectedAppointment = selectedAppointment;
        appointmentModificationAppointmentID.setText(Integer.toString(selectedAppointment.getAppointmentId()));
        appointmentModificationTitle.setText(selectedAppointment.getAppointmentTitle());
        appointmentModificationDescription.setText(selectedAppointment.getAppointmentDescription());
        appointmentModificationLocation.setText(selectedAppointment.getAppointmentLocation());
        appointmentModificationType.setText(selectedAppointment.getAppointmentType());
        startDateCalendar.setValue(selectedAppointment.getStartTime().toLocalDate());
        endDateCalendar.setValue(selectedAppointment.getEndTime().toLocalDate());
        comboBoxStartTime.setItems(TimeManipulations.timeIntervals());
        comboBoxStartTime.setValue(selectedAppointment.getStartTime().toLocalTime());
        comboBoxEndTime.setItems(TimeManipulations.timeIntervals());
        comboBoxEndTime.setValue(selectedAppointment.getEndTime().toLocalTime());
        customerComboBox.setValue(CustomersDAO.getAllCustomersById(selectedAppointment.getCustomerId()));
        userComboBox.setValue(UsersDAO.getAllUsersById(selectedAppointment.getUserId()));
        contactComboBox.setValue(ContactsDAO.getAllContactsById(selectedAppointment.getContactId()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        zoneID.setText(String.valueOf(ZoneId.systemDefault()));
        customerComboBox.setItems(CustomersDAO.getAllCustomers());
        userComboBox.setItems(UsersDAO.getAllUsers());
        contactComboBox.setItems(ContactsDAO.getAllContacts());
        timeLabel.setText(displayCurrentTime());
    }

    public boolean appointFieldsEmpty() {
        if (appointmentModificationTitle.getText().isBlank() || appointmentModificationTitle.getText().isEmpty()) {
            errorMessages.errorCode(16);
            return false;
        }
        else if (appointmentModificationDescription.getText().isBlank() || appointmentModificationDescription.getText().isEmpty()) {
            errorMessages.errorCode(17);
            return false;
        }
        else if (appointmentModificationLocation.getText().isBlank() || appointmentModificationLocation.getText().isEmpty()) {
            errorMessages.errorCode(18);
            return false;
        }
        else if (appointmentModificationType.getText().isBlank() || appointmentModificationType.getText().isEmpty()) {
            errorMessages.errorCode(19);
            return false;
        }
        else if (startDateCalendar.getValue() == null) {
            errorMessages.errorCode(20);
            return false;
        }
        else if (comboBoxStartTime.getValue() == null) {
            errorMessages.errorCode(21);
            return false;
        }
        else if (endDateCalendar.getValue() == null) {
            errorMessages.errorCode(22);
            return false;
        }
        else if (comboBoxEndTime.getValue() == null) {
            errorMessages.errorCode(23);
            return false;
        }
        else if (customerComboBox.getValue() == null) {
            errorMessages.errorCode(24);
            return false;
        }
        else if (userComboBox.getValue() == null) {
            errorMessages.errorCode(25);
            return false;
        }
        else if (contactComboBox.getValue() == null) {
            errorMessages.errorCode(26);
            return false;
        }
        return true;
    }

    private String displayCurrentTime() {
        Thread currentTime = new Thread(() -> {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");

            while(!timeStopped) {
                try {
                    sleep(1);
                    //Needed this for time to render properly.
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
                final String showCurrentTime = sdf.format(new Date());
                Platform.runLater(()->{
                    timeLabel.setText(showCurrentTime);
                });
            }
        });
        currentTime.start();
        return null;
    }
}