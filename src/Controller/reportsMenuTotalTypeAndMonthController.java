package Controller;

import DAO.AppointmentsDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;
import java.sql.SQLException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static DAO.AppointmentsDAO.getAppointmentMonthTotal;
import static DAO.AppointmentsDAO.getAppointmentTypeTotal;

public class reportsMenuTotalTypeAndMonthController implements Initializable {
    Parent scene;
    Stage stage;


    @FXML
    private TableColumn<Appointments, String> appointmentColMonth;

    @FXML
    private TableColumn<Appointments, Integer> appointmentColMonthTotalAmount;

    @FXML
    private TableColumn<Appointments, String> appointmentColType;

    @FXML
    private TableColumn<Appointments, Integer> appointmentColTypeTotalAmount;

    @FXML
    private TableView<Appointments> tableViewAppointmentMonth;

    @FXML
    private TableView<Appointments> tableViewAppointmentType;

    @FXML
    void onActionReturnToReportsMenu(ActionEvent event) throws IOException {
        reportsMenuController.returnToReportsMenu(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            tableViewAppointmentType.setItems(getAppointmentTypeTotal());
            tableViewAppointmentMonth.setItems(getAppointmentMonthTotal());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tableViewAppointmentType.setPlaceholder(new Label("There is no available 'Type' Data in our database!"));
        tableViewAppointmentMonth.setPlaceholder(new Label("There is no available 'Month' Data in our database!"));
        appointmentColType.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appointmentColTypeTotalAmount.setCellValueFactory(new PropertyValueFactory<>("typeCountTotal"));
        appointmentColMonth.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appointmentColMonthTotalAmount.setCellValueFactory(new PropertyValueFactory<>("typeCountTotal"));
    }
}
