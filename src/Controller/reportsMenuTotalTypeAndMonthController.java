package Controller;

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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static DAO.AppointmentsDAO.getAppointmentMonthTotal;
import static DAO.AppointmentsDAO.getAppointmentTypeTotal;

/**
 * reportsMenuTotalTypeAndMonthController is used to see appointment totals based off of Type and Month
 */
public class reportsMenuTotalTypeAndMonthController implements Initializable {

    /**
     * Appointment Type TableView/Column Declarations
     */
    @FXML private TableView<Appointments> tableViewAppointmentType;
    @FXML private TableColumn<Appointments, String> appointmentColType;
    @FXML private TableColumn<Appointments, Integer> appointmentColTypeTotalAmount;

    /**
     * Appointment Month TableView/Column Declarations
     */
    @FXML private TableView<Appointments> tableViewAppointmentMonth;
    @FXML private TableColumn<Appointments, String> appointmentColMonth;
    @FXML private TableColumn<Appointments, Integer> appointmentColMonthTotalAmount;


    /**
     * onActionReturnToReportsMenu(ActionEvent) returns the user to the Reports Menu
     * @param event
     * @throws IOException
     */
    @FXML void onActionReturnToReportsMenu(ActionEvent event) throws IOException {
        reportsMenuController.returnToReportsMenu(event);
    }

    /**
     * initialize sets tableview/columns and gets the Type/Month Totals
     * @param url
     * @param resourceBundle
     */
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
