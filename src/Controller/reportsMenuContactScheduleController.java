package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class reportsMenuContactScheduleController {

    @FXML
    private TableColumn<?, ?> appointmentCustomerIDCol;

    @FXML
    private TableColumn<?, ?> appointmentEndDateAndTimeCol;

    @FXML
    private TableColumn<?, ?> appointmentIDCol;

    @FXML
    private TableColumn<?, ?> appointmentStartDateAndTimeCol;

    @FXML
    private TableColumn<?, ?> appointmentTitleCol;

    @FXML
    private TableColumn<?, ?> appointmentTypeCol;

    @FXML
    private TableColumn<?, ?> apppointmentDescriptionCol;

    @FXML
    private ComboBox<?> comboBoxContacts;

    @FXML
    private TableView<?> tableViewContact;

    @FXML
    void onActionComboBoxContacts(ActionEvent event) {

    }

    @FXML
    void onActionReturnToReportsMenu(ActionEvent event) throws IOException {
        reportsMenuController.returnToReportsMenu(event);
    }

}
