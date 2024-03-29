package Controller;

import DAO.AppointmentsDAO;
import DAO.ContactsDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointments;
import model.Contacts;
import utility.errorMessages;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

/**
 * reportsMenuContactScheduleController is used to see appointments based off of selected Contacts.
 */
public class reportsMenuContactScheduleController implements Initializable {

    /**
     * ComboBox Declaration
     */
    @FXML private ComboBox<Contacts> comboBoxContacts;

    /**
     * TableView/Column Declarations
     */
    @FXML private TableView<Appointments> tableViewContact;
    @FXML private TableColumn<Appointments, Integer> appointmentCustomerIDCol;
    @FXML private TableColumn<Appointments, Timestamp> appointmentEndDateAndTimeCol;
    @FXML private TableColumn<Appointments, Integer> appointmentIDCol;
    @FXML private TableColumn<Appointments, Timestamp> appointmentStartDateAndTimeCol;
    @FXML private TableColumn<Appointments, String> appointmentTitleCol;
    @FXML private TableColumn<Appointments, String> appointmentTypeCol;
    @FXML private TableColumn<Appointments, String> appointmentDescriptionCol;


    /**
     * onActionComboBoxContacts(ActionEvent) is used to populate tableview by selected contact from comboBox
     * @param event
     * @throws SQLException
     */
    @FXML void onActionComboBoxContacts(ActionEvent event) throws SQLException {
        if (comboBoxContacts == null) {
            errorMessages.errorCode(32);
        }
        else {
            String nameForId = String.valueOf(comboBoxContacts.getValue());
            int id = ContactsDAO.getContactIdByName(nameForId);
            if (AppointmentsDAO.getAppointmentForContactList(id).isEmpty()) {
                tableViewContact.refresh();
                for (int i = 0; i < tableViewContact.getItems().size(); i++) {
                    tableViewContact.getItems().clear();
                    tableViewContact.setPlaceholder(new Label("There are no appointments for " + nameForId + "."));
                }
            } else {
                tableViewContact.setItems(AppointmentsDAO.getAppointmentForContactList(id));
            }
        }
    }

    /**
     * onActionReturnToReportsMenu(ActionEvent) returns the user to the Reports Menu
     * @param event
     * @throws IOException
     */
    @FXML void onActionReturnToReportsMenu(ActionEvent event) throws IOException {
        reportsMenuController.returnToReportsMenu(event);
    }

    /**
     * initialize fills comboBoxContacts with all Contacts
     * initialize sets cellValues in tableView
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Fill in contact box with all contacts
        comboBoxContacts.setItems(ContactsDAO.getAllContacts());
        comboBoxContacts.setPromptText("Select a Contact");

        //Placeholder for table when there is no Contact selected/No Appointments for Contact.
        tableViewContact.setPlaceholder(new Label("No Contact Selected or Contact has NO appointments"));

        //Set Cell values
        appointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appointmentTitleCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        appointmentTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appointmentDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        appointmentStartDateAndTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        appointmentEndDateAndTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        appointmentCustomerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
    }
}
