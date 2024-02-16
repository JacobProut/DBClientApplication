package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class reportsMenuTotalTypeAndMonthController {
    Parent scene;
    Stage stage;


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
    void onActionReturnToReportsMenu(ActionEvent event) throws IOException {
        reportsMenuController.returnToReportsMenu(event);
    }

}
