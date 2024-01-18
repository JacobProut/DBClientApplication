package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class appointmentModificationFormController {

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
    void onActionCancelAppointmentModification(ActionEvent event) {

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
