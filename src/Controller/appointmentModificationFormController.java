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
import model.Appointments;
import model.Contacts;
import model.Customers;
import model.Users;
import utility.AppointmentChecks;
import utility.TimeManipulations;
import utility.errorMessages;

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
import static javafx.scene.control.Alert.AlertType.CONFIRMATION;

/**
 * appointmentModificationFormController is used to modify selected appointments
 */
public class appointmentModificationFormController implements Initializable {
    Parent scene;
    Stage stage;

    /**
     * Label Declarations
     */
    @FXML private Label zoneID;
    @FXML private Label timeLabel;

    /**
     * ComboBox Declarations
     */
    @FXML private ComboBox<LocalTime> comboBoxEndTime;
    @FXML private ComboBox<LocalTime> comboBoxStartTime;
    @FXML private ComboBox<Contacts> contactComboBox;
    @FXML private ComboBox<Customers> customerComboBox;
    @FXML private ComboBox<Users> userComboBox;

    /**
     * TextField Declarations
     */
    @FXML private TextField appointmentModificationAppointmentID;
    @FXML private TextField appointmentModificationDescription;
    @FXML private TextField appointmentModificationLocation;
    @FXML private TextField appointmentModificationTitle;
    @FXML private TextField appointmentModificationType;

    /**
     * DatePicker Declarations
     */
    @FXML private DatePicker endDateCalendar;
    @FXML private DatePicker startDateCalendar;

    /**
     * timeStopped is used for displayCurrentTime()
     */
    private final boolean timeStopped = false;

    /**
     * selectedIndex & selectedAppointment are used for appointmentSelection()
     */
    public int selectedIndex;
    Appointments selectedAppointment = new Appointments(1, "title", "desc", "location", "type", LocalDateTime.now(), LocalDateTime.now(), 1, 1, 1);

    /**
     * onActionCancelAppointmentModification(ActionEvent) is used to return to mainMenu.fxml[Appointment Scheduler Form]
     *      - Alert is given stating the appointment_Id and appointment_Type when button is clicked
     *          - Pressing 'OK' sends the user back to mainMenu.fxml
     * @param event
     * @throws IOException
     */
    @FXML void onActionCancelAppointmentModification(ActionEvent event) throws IOException {
        Alert alert = new Alert(CONFIRMATION);
        alert.setTitle("Close Appointment Modification Page");
        alert.setHeaderText("You are about to cancel an Appointment with a Appointment_ID of [" + appointmentModificationAppointmentID.getText() + "] and with a Appointment Type of [" + appointmentModificationType.getText() + "].\r" + "Are you sure you want to continue?");
        alert.setContentText("Click 'OK' to go back to Appointment Scheduler.\r" + "Click 'Cancel' to stay on the Appointment Modification Form.");
        Optional<ButtonType> confirmation = alert.showAndWait();
        if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/mainMenu.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
            stage.centerOnScreen();
            stage.setTitle("Appointment Scheduler");
            System.out.println("Returning to Application Scheduler.");
        }
    }

    /**
     * onActionModificationAppointment(ActionEvent) method to Modify selected appointment[appointmentSelection()] + add it to database
     *      - Method contains openHoursForBusiness() and doTimesOverLap()
     * @param event
     */
    @FXML void onActionModificationAppointment(ActionEvent event) {
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
        }
        catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * appointmentSelection used in mainMenuController.onActionUpdateAppointment(ActionEvent) to get selected appointment from tableview and put information into appointmentModificationFormController fields.
     * @param index
     * @param selectedAppointment
     * @throws SQLException
     */
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

    /**
     * initialize sets zoneID and timeLabel to systemDefault date and currentTime()[Lambda], sets ComboBoxes items.
     * Lambda expression 1: timeLabel.setText(displayCurrentTime()) [Explained above displayCurrentTime() javaDoc]
     * Lambda expression 2: comboBoxStartTime.valueProperty().addListener((firstLookedAtTime, oldTime, newTime) -> comboBoxEndTime.setValue(newTime.plusMinutes(15)));
     *      - this automatically sets the comboBoxEndTime to 15 minutes after the comboBoxStartTime
     * Lambda expression 3: startDateCalendar.valueProperty().addListener((firstLookedAtDate, oldDate, newDate) -> endDateCalendar.setValue(newDate));
     *      - this automatically sets endDateCalendar date to the same as startDateCalendar date
     *          - I did this considering I don't see how an appointment can run into a different day if the business hours aren't 24/7.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TimeZone & Time Setters
        zoneID.setText(String.valueOf(ZoneId.systemDefault()));
        timeLabel.setText(displayCurrentTime());

        //Sets Contact, User, Customer ComboBoxes with getAll functions
        customerComboBox.setItems(CustomersDAO.getAllCustomers());
        userComboBox.setItems(UsersDAO.getAllUsers());
        contactComboBox.setItems(ContactsDAO.getAllContacts());

        //!!!Lambdas Expressions!!!
        //Sets comboBoxEndTime to comboBoxStartTime plus 15 minutes. This makes it so once a comboBoxStartTime has a set time, comboBoxEndTime adds 15minutes automatically.
        comboBoxStartTime.valueProperty().addListener((firstLookedAtTime, oldTime, newTime) -> comboBoxEndTime.setValue(newTime.plusMinutes(15)));
        //Sets endDateCalendar to the same as startDateCalendar
        startDateCalendar.valueProperty().addListener((firstLookedAtDate, oldDate, newDate) -> endDateCalendar.setValue(newDate));
    }

    /**
     * appointFieldsEmpty() checks to make sure textFields, Calendar, ComboBoxes are not empty or Null
     * @return false or true
     */
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

    /**
     * displayCurrentTime() is a Lambda expression used to display systemDefaults time
     * "Lambda newThread(() ->"
     * "Lambda Platform.runLater(()-> "
     * @return null
     */
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