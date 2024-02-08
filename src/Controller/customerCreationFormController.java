package Controller;

import DAO.CountriesDAO;
import DAO.CustomersDAO;
import DAO.First_Level_DivisionsDAO;
import utility.errorMessages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Countries;
import model.First_Level_Divisions;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class customerCreationFormController implements Initializable {
    Parent scene;
    Stage stage;

    @FXML
    private ComboBox<Countries> countryPicker;

    @FXML
    private Button creationCancelButton;

    @FXML
    private TextField creationCustomerAddress;

    @FXML
    private TextField creationCustomerID;

    @FXML
    private TextField creationCustomerName;

    @FXML
    private TextField creationCustomerPhoneNumber;

    @FXML
    private TextField creationCustomerPostalCode;

    @FXML
    private ComboBox<First_Level_Divisions> divisionPicker;

    @FXML
    private Button saveButton;


    //Working method without Last_Updated_By | Add Last_Updated_By

    @FXML
    void onActionSaveButton(ActionEvent event) {
        try {
            if (createCustomerValidation()) {
                String customerName = creationCustomerName.getText();
                String customerAddress = creationCustomerAddress.getText();
                String customerPostalCode = creationCustomerPostalCode.getText();
                String customerPhoneNumber = creationCustomerPhoneNumber.getText();
                LocalDateTime createDate = LocalDateTime.now();
                LocalDateTime lastUpdated = LocalDateTime.now();
                First_Level_Divisions customerMenuDivisionId = divisionPicker.getValue();
                int divisionId = customerMenuDivisionId.getDivisionId();
                String createdBy = "User";
                String lastUpdatedBy = "User";
                CustomersDAO.createCustomer(customerName, customerAddress, customerPostalCode, customerPhoneNumber, createDate, createdBy, lastUpdated, lastUpdatedBy, divisionId);
                customerMenuController.returnToCustomerAppointments(event);
            }
        } catch (Exception e) {
            System.out.println("Must be Error with Customer Creation");
            throw new RuntimeException(e);
        }
    }


    @FXML
    void onActionCreationCancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Closing Customer Creation Page");
        alert.setHeaderText("Are you sure you want to leave without saving?");
        alert.setContentText("Click 'OK' to confirm deletion.\r" + "Click 'Cancel' to go back.");
        Optional<ButtonType> confirmation = alert.showAndWait();
        if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/customerMenu.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
            stage.setTitle("Customer View List");
            System.out.println("Returning to Customer View List.");
        }
    }

    @FXML
    void onActionDivisionPicker(ActionEvent event) {

    }

    //I believe this is completed.
    @FXML
    void onActionCountryPicker(ActionEvent event) {
        Countries list = countryPicker.getValue();
        divisionPicker.setItems(First_Level_DivisionsDAO.countryToDivision(list.getCountryId()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryPicker.setItems(CountriesDAO.getAllCountriesList());
    }

    public boolean createCustomerValidation() {
        if (creationCustomerName.getText().isEmpty() && creationCustomerAddress.getText().isEmpty() && creationCustomerPostalCode.getText().isEmpty() && creationCustomerPhoneNumber.getText().isEmpty() && countryPicker.getSelectionModel().isEmpty() && divisionPicker.getSelectionModel().isEmpty()) {
            errorMessages.errorCode(5);
            return false;
        } else if (creationCustomerName.getText().isBlank() || creationCustomerName.getText().isEmpty()) {
            errorMessages.errorCode(6);
            return false;
        } else if (creationCustomerAddress.getText().isBlank() || creationCustomerAddress.getText().isEmpty()) {
            errorMessages.errorCode(7);
            return false;
        } else if (creationCustomerPostalCode.getText().isBlank() || creationCustomerPostalCode.getText().isEmpty()) {
            errorMessages.errorCode(8);
            return false;
        } else if (creationCustomerPhoneNumber.getText().isBlank() || creationCustomerPhoneNumber.getText().isEmpty()) {
            errorMessages.errorCode(9);
            return false;
        } else if (countryPicker.getSelectionModel().isEmpty()) {
            errorMessages.errorCode(10);
            return false;
        } else if (divisionPicker.getSelectionModel().isEmpty()) {
            errorMessages.errorCode(11);
            return false;
        } else if (divisionPicker.getValue() == null) {
            Countries countries = countryPicker.getValue();
            if (countries == null) {
                errorMessages.errorCode(12);
                return false;
            }
        }
        return true;
    }
}


