package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

public class customerMenuController {

    @FXML
    private Button addCustomerButton;

    @FXML
    private TableView<?> customerTableView;

    @FXML
    private Pane customerViewPane;

    @FXML
    private Button deleteCustomerButton;

    @FXML
    private Button logoutButton;

    @FXML
    private RadioButton radioButtonViewAll;

    @FXML
    private RadioButton radioButtonViewAllCustomers;

    @FXML
    private RadioButton radioButtonViewByMonth;

    @FXML
    private RadioButton radioButtonViewByWeek;

    @FXML
    private ToggleGroup radioButtons;

    @FXML
    private Button reportsButton;

    @FXML
    private TableColumn<?, ?> tableColCustomerAddress;

    @FXML
    private TableColumn<?, ?> tableColCustomerCreatedBy;

    @FXML
    private TableColumn<?, ?> tableColCustomerCreatedDate;

    @FXML
    private TableColumn<?, ?> tableColCustomerID;

    @FXML
    private TableColumn<?, ?> tableColCustomerLastUpdated;

    @FXML
    private TableColumn<?, ?> tableColCustomerLastUpdatedBy;

    @FXML
    private TableColumn<?, ?> tableColCustomerName;

    @FXML
    private TableColumn<?, ?> tableColCustomerPhoneNumber;

    @FXML
    private TableColumn<?, ?> tableColCustomerPostalCode;

    @FXML
    private TableColumn<?, ?> tableColCustomerStateAndProvinces;

    @FXML
    private Button updateCustomerButton;

    @FXML
    void onActionAddCustomer(ActionEvent event) {

    }

    @FXML
    void onActionDeleteCustomer(ActionEvent event) {

    }

    @FXML
    void onActionLogout(ActionEvent event) {

    }

    @FXML
    void onActionReports(ActionEvent event) {

    }

    @FXML
    void onActionUpdateCustomer(ActionEvent event) {

    }

    @FXML
    void radioButtonViewAll(ActionEvent event) {

    }

    @FXML
    void radioButtonViewAllCustomers(ActionEvent event) {

    }

    @FXML
    void radioButtonViewByMonth(ActionEvent event) {

    }

    @FXML
    void radioButtonViewByWeek(ActionEvent event) {

    }

}
