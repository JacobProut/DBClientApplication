package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.naming.PartialResultException;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class appointmentCreationFormController {

    @FXML
    private TextField appointmentCreationAppointmentID;

    @FXML
    private TextField appointmentCreationContact;

    @FXML
    private TextField appointmentCreationDescription;

    @FXML
    private TextField appointmentCreationLocation;

    @FXML
    private TextField appointmentCreationTitle;

    @FXML
    private TextField appointmentCreationType;

    @FXML
    private Button cancelAppointment;

    @FXML
    private Button createAppointment;

    @FXML
    private DatePicker endDatefxid;

    @FXML
    private Spinner<?> endTimeHourSpinner;

    @FXML
    private Spinner<?> endTimeMinuteSpinner;

    @FXML
    private DatePicker startDatefxid;

    @FXML
    private Spinner<?> startTimeHourSpinner;

    @FXML
    private Spinner<?> startTimeMinuteSpinner;

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
        }
    }

    @FXML
    void onActionCreateAppointment(ActionEvent event) {

    }

    @FXML
    void onActionEndDate(ActionEvent event) {

    }

    @FXML
    void onActionStartDate(ActionEvent event) {

    }

}
