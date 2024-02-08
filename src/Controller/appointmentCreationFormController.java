package Controller;

import DAO.AppointmentsDAO;
import DAO.ContactsDAO;
import DAO.CustomersDAO;
import DAO.UsersDAO;
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
import utility.errorMessages;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class appointmentCreationFormController implements Initializable {

    @FXML private TextField appointmentCreationAppointmentID;

    @FXML private ComboBox<LocalTime> comboBoxStartTime;

    @FXML private ComboBox<LocalTime> comboBoxEndTime;

    @FXML private ComboBox<Users> userComboBox;

    @FXML private ComboBox<Customers> customerComboBox;

    @FXML private ComboBox<Contacts> contactComboBox;

    @FXML private TextField appointmentCreationDescription;

    @FXML private TextField appointmentCreationLocation;

    @FXML private TextField appointmentCreationTitle;

    @FXML private TextField appointmentCreationType;

    @FXML private Button cancelAppointment;

    @FXML private Button createAppointment;

    @FXML private DatePicker endDateCalendar;

    @FXML private DatePicker startDateCalendar;

    Parent scene;
    Stage stage;

    @FXML
    void onActionCancelAppointment(ActionEvent event) throws IOException {
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
            stage.setTitle("Appointment Scheduler");
            System.out.println("Returning to Appointment Scheduler.");
        }
    }

    /*@FXML
    void onActionCreateAppointment(ActionEvent event) {
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

                AppointmentsDAO.createAppointments(title, description, location, type, startOfAppointment, endOfAppointment, customerId, userId, contactId);
                mainMenuController.returnToAppointments(event);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/

    /*@FXML
    void onActionCreateAppointment(ActionEvent event) {
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
                else if (comboBoxStartTime.getSelectionModel().getSelectedItem().isAfter(comboBoxEndTime.getValue()) || comboBoxEndTime.getSelectionModel().getSelectedItem().isBefore(comboBoxStartTime.getValue()) || (comboBoxStartTime.getSelectionModel().getSelectedItem().equals(comboBoxEndTime.getValue()))) {
                    System.out.println("Start time is after end time or Times are the same");
                    errorMessages.errorCode(27);
                }
                else {
                        AppointmentsDAO.createAppointments(title, description, location, type, startOfAppointment, endOfAppointment, customerId, userId, contactId);
                        mainMenuController.returnToAppointments(event);
                }

            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }*/

    @FXML
    void onActionCreateAppointment(ActionEvent event) {
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
                else if (comboBoxStartTime.getSelectionModel().getSelectedItem().isAfter(comboBoxEndTime.getValue()) || comboBoxEndTime.getSelectionModel().getSelectedItem().isBefore(comboBoxStartTime.getValue())) {
                    System.out.println("Start time is after end time");
                    errorMessages.errorCode(27);
                }
                else if (comboBoxStartTime.getSelectionModel().getSelectedItem().equals(comboBoxEndTime.getValue())) {
                    System.out.println("Start and End time CANNOT be the same!");
                    errorMessages.errorCode(28);
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

    @FXML
    void onActionComboBoxContact(ActionEvent event) {

    }

    @FXML
    void onActionStartTime(ActionEvent event) {

    }

    @FXML
    void onActionEndTime(ActionEvent event) {

    }

    @FXML
    void onActionComboBoxCustomer(ActionEvent actionEvent) {

    }

    @FXML
    void onActionComboBoxUser(ActionEvent actionEvent) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactComboBox.setItems(ContactsDAO.getAllContacts());
        userComboBox.setItems(UsersDAO.getAllUsers());
        customerComboBox.setItems(CustomersDAO.getAllCustomers());

        //Not sure if this is correct yet
        comboBoxStartTime.setItems(TimeManipulations.timeIntervals());
        comboBoxEndTime.setItems(TimeManipulations.timeIntervals());

        //Not sure if ill use this lambdas expression
        comboBoxStartTime.valueProperty().addListener((firstLookedAtTime, oldTime, newTime) -> comboBoxEndTime.setValue(newTime.plusHours(1)));
    }



    //add appointment empty fields.
    public boolean appointFieldsEmpty() {
        if (appointmentCreationTitle.getText().isBlank() || appointmentCreationTitle.getText().isEmpty()) {
            errorMessages.errorCode(16);
            return false;
        }
        else if (appointmentCreationDescription.getText().isBlank() || appointmentCreationDescription.getText().isEmpty()) {
            errorMessages.errorCode(17);
            return false;
        }
        else if (appointmentCreationLocation.getText().isBlank() || appointmentCreationLocation.getText().isEmpty()) {
            errorMessages.errorCode(18);
            return false;
        }
        else if (appointmentCreationType.getText().isBlank() || appointmentCreationType.getText().isEmpty()) {
            errorMessages.errorCode(19);
            return false;
        }
        else if (startDateCalendar.getValue() == null) {
            errorMessages.errorCode(20);
            return false;
        }

        //not sure if comboBoxStartTime works properly
        else if (comboBoxStartTime.getValue() == null) {
            errorMessages.errorCode(21);
            return false;
        }
        else if (endDateCalendar.getValue() == null) {
            errorMessages.errorCode(22);
            return false;
        }

        //not sure if comboBoxEndTime works properly
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

}
