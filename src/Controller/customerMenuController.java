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

import java.io.IOException;
import java.util.Objects;

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

    @FXML
    void onActionUpdateCustomer(ActionEvent event) {

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
