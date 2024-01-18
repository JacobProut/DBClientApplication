package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class appointmentModificationFormController {
    Stage stage;
    Parent scene;

    @FXML
    private TextField appointmentModificationAppointmentID;

    @FXML
    private TextField appointmentModificationContact;

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
    private DatePicker endDatefxid;

    @FXML
    private Spinner<?> endTimeHourSpinner;

    @FXML
    private Spinner<?> endTimeMinuteSpinner;

    @FXML
    private Button modificationAppointment;

    @FXML
    private DatePicker startDatefxid;

    @FXML
    private Spinner<?> startTimeHourSpinner;

    @FXML
    private Spinner<?> startTimeMinuteSpinner;

    @FXML
    void onActionCancelAppointmentModification(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close Appointment Modification Page");
        alert.setHeaderText("Are you sure you want to leave without saving?");
        alert.setContentText("Click 'OK' to go back to Appointment Scheduler.\r" + "Click 'Cancel' to stay on the Appointment Modification Form");
        Optional<ButtonType> confirmation = alert.showAndWait();
        if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/mainMenu.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
            stage.setTitle("Appointment Scheduler");

        }
    }

    @FXML
    void onActionEndDate(ActionEvent event) {

    }

    @FXML
    void onActionModificationAppointment(ActionEvent event) {

    }

    @FXML
    void onActionStartDate(ActionEvent event) {

    }

}
