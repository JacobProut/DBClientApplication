package Controller;

import DAO.CountriesDAO;
import DAO.CustomersDAO;
import DAO.First_Level_DivisionsDAO;
import errorMessages.errorMsgs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Countries;
import model.Customers;
import model.First_Level_Divisions;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class customerModificationFormController implements Initializable {

    @FXML
    private ComboBox<First_Level_Divisions> divisionPicker;

    @FXML
    private TextField modificationCustomerAddress;

    @FXML
    private TextField modificationCustomerID;

    @FXML
    private TextField modificationCustomerName;

    @FXML
    private TextField modificationCustomerPhoneNumber;

    @FXML
    private TextField modificationCustomerPostalCode;

    @FXML
    private ComboBox<Countries> modificationCountryPicker;

    Parent scene;
    Stage stage;

    @FXML
    void onActionModificationCancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close Modification Page");
        alert.setHeaderText("Are you sure you want to leave without saving?");
        alert.setContentText("Click 'OK' to confirm deletion.\r" + "Click 'Cancel' to go back.");
        Optional<ButtonType> confirmation = alert.showAndWait();
        if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/customerMenu.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
            stage.setTitle("Customer View List");
        }

    }

    @FXML
    void onActionModificationCountryPicker(ActionEvent event) {
        divisionPicker.setValue(null);
        Countries list = modificationCountryPicker.getValue();
        divisionPicker.setItems(First_Level_DivisionsDAO.countryToDivision(list.getCountryId()));

    }

    @FXML
    void onActionUpdateCustomerButton(ActionEvent event) {

        try {
            if (updateCustomerValidation()) {
                int updateCustomerID = Integer.parseInt(modificationCustomerID.getText());
                String updateCustomerName = modificationCustomerName.getText();
                String updateCustomerAddress = modificationCustomerAddress.getText();
                String updateCustomerPostalCode = modificationCustomerPostalCode.getText();
                String updateCustomerPhoneNumber = modificationCustomerPhoneNumber.getText();
                //int updateCustomerCountry = modificationCountryPicker.getValue().getCountryId();
                int updateCustomerDivisionId = divisionPicker.getValue().getDivisionId();
                LocalDateTime updateLastUpdated = LocalDateTime.now();
                String updateLastUpdatedBy = "User";

                CustomersDAO.updateCustomer(updateCustomerID, updateCustomerName, updateCustomerAddress, updateCustomerPostalCode, updateCustomerPhoneNumber, updateLastUpdated, updateLastUpdatedBy, updateCustomerDivisionId);
                customerMenuController.returnToCustomerAppointments(event);
            }
        }
        catch (NumberFormatException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    // Method that involves Customers model to include "getCountryId()" WHICH IS NOT IN THE DATABASE ERD FOR COURSE
    public int selectedIndex;
    Customers selectedCustomer = new Customers(1, "name", "n", "n", "n", LocalDateTime.now(), "script", LocalDateTime.now(), "script", 1, 1);
    public void customerSelection(int index, Customers selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
        this.selectedIndex = index;
        modificationCustomerID.setText(String.valueOf(selectedCustomer.getCustomerId()));
        modificationCustomerName.setText(selectedCustomer.getCustomerName());
        modificationCustomerAddress.setText(selectedCustomer.getCustomerAddress());
        modificationCustomerPostalCode.setText(selectedCustomer.getCustomerPostalCode());
        modificationCustomerPhoneNumber.setText(selectedCustomer.getCustomerPhoneNumber());
        First_Level_Divisions selectedDivID = First_Level_DivisionsDAO.getSelectedCustomerDivisionLevel(selectedCustomer.getDivisionId());
        divisionPicker.setValue(selectedDivID);
        Countries selectedCountryId = CountriesDAO.getSelectedCustomerCountry(selectedCustomer.getCountryId());
        modificationCountryPicker.setValue(selectedCountryId);
        Countries selectedCountryNDiv = modificationCountryPicker.getValue();
        divisionPicker.setItems(First_Level_DivisionsDAO.countryToDivision(selectedCountryNDiv.getCountryId()));
    }

    public boolean updateCustomerValidation() {
        if (modificationCustomerID.getText().isEmpty() && modificationCustomerAddress.getText().isEmpty() && modificationCustomerPostalCode.getText().isEmpty() && modificationCustomerPhoneNumber.getText().isEmpty() && modificationCountryPicker.getSelectionModel().isEmpty() && divisionPicker.getSelectionModel().isEmpty()) {
            errorMsgs.errorCodes(5);
            return false;
        } else if (modificationCustomerName.getText().isBlank() || modificationCustomerName.getText().isEmpty()) {
            errorMsgs.errorCodes(6);
            return false;
        } else if (modificationCustomerAddress.getText().isBlank() || modificationCustomerAddress.getText().isEmpty()) {
            errorMsgs.errorCodes(7);
            return false;
        } else if (modificationCustomerPostalCode.getText().isBlank() || modificationCustomerPostalCode.getText().isEmpty()) {
            errorMsgs.errorCodes(8);
            return false;
        } else if (modificationCustomerPhoneNumber.getText().isBlank() || modificationCustomerPhoneNumber.getText().isEmpty()) {
            errorMsgs.errorCodes(9);
            return false;
        } else if (modificationCountryPicker.getValue() == null) {
            errorMsgs.errorCodes(10);
            return false;
        } else if (divisionPicker.getValue() == null) {
            errorMsgs.errorCodes(12);
            return false;
        } else if (divisionPicker.getValue() == null) {
            Countries countries = modificationCountryPicker.getValue();
            if (countries == null) {
                errorMsgs.errorCodes(14);
                return false;
            }
        }
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modificationCountryPicker.setItems(CountriesDAO.getAllCountriesList());
    }
}
