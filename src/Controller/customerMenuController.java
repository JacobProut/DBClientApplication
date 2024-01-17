package Controller;

import DAO.JDBC;
import DAO.mainMenuControllerDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Customers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Optional;

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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log out");
        alert.setHeaderText("Attempting to log out\r" + "Any unsaved data will be LOST!");
        alert.setContentText("Are you sure you want to continue?\r" + "Click 'OK' to confirm exit.\r" + "Click 'Cancel' to go back.");

        Optional<ButtonType> confirmation = alert.showAndWait();

        if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
            JDBC.closeConnection();
            System.exit(0);
        }
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
    void radioButtonViewAll(ActionEvent event) throws IOException {
        stage = (Stage) ((RadioButton)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/mainMenu.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
        stage.setTitle("Appointment Scheduler");
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
