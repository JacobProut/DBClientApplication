package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Contacts;
import model.Customers;
import model.Users;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Optional;

public class appointmentModificationFormController {

    @FXML
    private TextField appointmentModificationAppointmentID;

    @FXML
    private TextField appointmentModificationDescription;

    @FXML
    private TextField appointmentModificationLocation;

    @FXML
    private TextField appointmentModificationTitle;

    @FXML
    private TextField appointmentModificationType;

    @FXML
    private Button cancelAppointmentModification;

    @FXML
    private ComboBox<LocalTime> comboBoxEndTime;

    @FXML
    private ComboBox<LocalTime> comboBoxStartTime;

    @FXML
    private Button confirmAppointmentModification;

    @FXML
    private ComboBox<Contacts> contactComboBox;

    @FXML
    private ComboBox<Customers> customerComboBox;

    @FXML
    private DatePicker endDateCalendar;

    @FXML
    private DatePicker startDateCalendar;

    @FXML
    private ComboBox<Users> userComboBox;

    Parent scene;
    Stage stage;

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

    }

    @FXML
    void onActionStartDate(ActionEvent event) {

    }

    @FXML
    void onActionStartTime(ActionEvent event) {

    }

}