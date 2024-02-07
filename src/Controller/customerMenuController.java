package Controller;

import DAO.CustomersDAO;
import DAO.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;
import model.Customers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    private Button deleteCustomerButton;

    @FXML
    private Button logoutButton;

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

    @FXML
    private Button returnToAppointmentScheduler;

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


    //Make this method give an error on delete if customer has appointments
    ObservableList<Customers> allCustomers = FXCollections.observableArrayList();
    @FXML
    void onActionDeleteCustomer(ActionEvent event) throws SQLException {
        ObservableList<Appointments> getAllAppointments = FXCollections.observableArrayList();
        Customers selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null) {
            errorMessages.errorMsgs.errorCodes(13);
            return;
        }

        CustomersDAO.removeCustomerFromTableView(customerTableView.getSelectionModel().getSelectedItem().getCustomerId());
        allCustomers = CustomersDAO.getAllCustomers();
        customerTableView.setItems(allCustomers);
        customerTableView.refresh();


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

    //Method used in customerModificationFormController.java  - customerSelection method
   @FXML
    void onActionUpdateCustomer(ActionEvent event) throws IOException {

       if (customerTableView.getSelectionModel().isEmpty()) {
           errorMessages.errorMsgs.errorCodes(15);
       }
       else {
           stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
           FXMLLoader loadupper = new FXMLLoader(getClass().getResource("/view/customerModificationForm.fxml"));
           scene = loadupper.load();
           Customers customerSelection = customerTableView.getSelectionModel().getSelectedItem();
           customerModificationFormController controller = loadupper.getController();
           controller.customerSelection(customerTableView.getSelectionModel().getSelectedIndex(), customerSelection);
           stage.setScene(new Scene(scene));
           stage.show();
           stage.setTitle("Customer Modification Page");
       }
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
    void onActionReturnToAppointmentScheduler(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/mainMenu.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
        stage.setTitle("Appointment Scheduler");
        System.out.println("Returning to Appointment Scheduler.");
    }


    public static void returnToCustomerAppointments(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(customerMenuController.class.getResource("/view/customerMenu.fxml")));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Customer View List");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerTableView.setItems(CustomersDAO.getAllCustomers());
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
