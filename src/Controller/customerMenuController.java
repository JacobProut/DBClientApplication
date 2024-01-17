package Controller;

import DAO.JDBC;
import DAO.customerMenuControllerDAO;
import DAO.mainMenuControllerDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Customers;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class customerMenuController implements Initializable {

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
    private TableColumn<Customers, String> tableColCustomerPhoneNumber;

    @FXML
    private TableColumn<Customers, Integer> tableColCustomerPostalCode;

    @FXML
    private TableColumn<Customers, String> tableColCustomerDivisionId;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerTableView.setItems(customerMenuControllerDAO.getAllCustomers());
        tableColCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tableColCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tableColCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        tableColCustomerPostalCode.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        tableColCustomerPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("customerPhoneNumber"));
        tableColCustomerCreatedDate.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        tableColCustomerCreatedBy.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        tableColCustomerLastUpdated.setCellValueFactory(new PropertyValueFactory<>("lastUpdated"));
        tableColCustomerLastUpdatedBy.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        tableColCustomerDivisionId.setCellValueFactory(new PropertyValueFactory<>("divisionId"));



    }

}
