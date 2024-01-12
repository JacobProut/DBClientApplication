package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

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

    @FXML
    void onActionCancelAppointment(ActionEvent event) {

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
