package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class reportsMenuUsersScheduleController {

    @FXML
    private TableColumn<?, ?> appointmentContactIDCol;

    @FXML
    private TableColumn<?, ?> appointmentCustomerIDCol;

    @FXML
    private TableColumn<?, ?> appointmentDescriptionCol;

    @FXML
    private TableColumn<?, ?> appointmentEndDateAndTimeCol;

    @FXML
    private TableColumn<?, ?> appointmentIDCol;

    @FXML
    private TableColumn<?, ?> appointmentLocationCol;

    @FXML
    private TableColumn<?, ?> appointmentStartDateAndTimeCol;

    @FXML
    private TableColumn<?, ?> appointmentTitleCol;

    @FXML
    private TableColumn<?, ?> appointmentTypeCol;

    @FXML
    private ComboBox<?> comboBoxUsers;

    @FXML
    private TableView<?> tableViewUsers;

    @FXML
    void onActionComboBoxUsers(ActionEvent event) {

    }

    @FXML
    void onActionReturnToReportsMenu(ActionEvent event) {

    }

}
