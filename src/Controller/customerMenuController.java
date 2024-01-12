package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Customers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Objects;

public class customerMenuController {

    @FXML
    private Button addCustomerButton;

    @FXML
    private TableView<Customers> customerTableView;

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
    private TableColumn<Customers, String> tableColCustomerAddress;

    @FXML
    private TableColumn<Customers, String> tableColCustomerCreatedBy;

    @FXML
    private TableColumn<Customers, Timestamp> tableColCustomerCreatedDate;

    @FXML
    private TableColumn<Customers, Integer> tableColCustomerID;

    @FXML
    private TableColumn<Customers, Timestamp> tableColCustomerLastUpdated;

    @FXML
    private TableColumn<Customers, String> tableColCustomerLastUpdatedBy;

    @FXML
    private TableColumn<Customers, String> tableColCustomerName;

    @FXML
    private TableColumn<Customers, Integer> tableColCustomerPhoneNumber;

    @FXML
    private TableColumn<Customers, Integer> tableColCustomerPostalCode;

    @FXML
    private TableColumn<Customers, String> tableColCustomerStateAndProvinces;

    @FXML
    private Button updateCustomerButton;

    Stage stage;
    Parent scene;

    @FXML
    void onActionAddCustomer(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/customerCreationForm.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
        stage.setTitle("Customer Creation Page");
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


    //Find a way to select item from tableview and have it get modified (Look at software1 project!)
    @FXML
    void onActionUpdateCustomer(ActionEvent event) throws IOException {


        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/customerModificationForm.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
        stage.setTitle("Customer Modification Page");

    }

    @FXML
    void radioButtonViewAll(ActionEvent event) {

    }

    @FXML
    void radioButtonViewAllCustomers(ActionEvent event) throws IOException {
        stage = (Stage) ((RadioButton)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/customerMenu.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
        stage.setTitle("Customer View List");
    }

    @FXML
    void radioButtonViewByMonth(ActionEvent event) {

    }

    @FXML
    void radioButtonViewByWeek(ActionEvent event) {

    }

}
