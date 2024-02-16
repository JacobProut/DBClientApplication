package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class reportsMenuTotalTypeAndMonthController {

    @FXML
    private TableColumn<?, ?> appointmentColMonth;

    @FXML
    private TableColumn<?, ?> appointmentColMonthTotalAmount;

    @FXML
    private TableColumn<?, ?> appointmentColType;

    @FXML
    private TableColumn<?, ?> appointmentColTypeTotalAmount;

    @FXML
    private TableView<?> tableViewAppointmentMonth;

    @FXML
    private TableView<?> tableViewAppointmentType;

    @FXML
    void onActionReturnToReportsMenu(ActionEvent event) {

    }

}
