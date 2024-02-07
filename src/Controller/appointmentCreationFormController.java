package Controller;

import DAO.ContactsDAO;
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

import javax.naming.PartialResultException;
import java.io.IOException;
import java.net.URL;
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
        }
    }

    @FXML
    void onActionCreateAppointment(ActionEvent event) {
        String title = appointmentCreationTitle.getText();
        String description = appointmentCreationDescription.getText();
        String location = appointmentCreationLocation.getText();

    }

    @FXML
    void onActionEndDate(ActionEvent event) {

    }

    @FXML
    void onActionStartDate(ActionEvent event) {

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
    }



    //add appointment empty fields. MAY REMAKE SO IT INCLUDES errorMessages.errorMsg.errorCode();
    /*public boolean appointFieldsEmpty() {
        if (appointmentCreationTitle.getText().isBlank() || appointmentCreationTitle.getText().isEmpty()) {
            return true;
        }
        else if (appointmentCreationDescription.getText().isBlank() || appointmentCreationDescription.getText().isEmpty()) {
            return true;
        }
        else if (appointmentCreationLocation.getText().isBlank() || appointmentCreationLocation.getText().isEmpty()) {
            return true;
        }
        else if (appointmentCreationContact.getText().isBlank() || appointmentCreationContact.getText().isEmpty()) {
            return true;
        }
        else if (appointmentCreationType.getText().isBlank() || appointmentCreationType.getText().isEmpty()) {
            return true;
        }
        else if (startDatefxid.getValue() == null) {
            return true;
        }

        return false;
    }
*/
}
