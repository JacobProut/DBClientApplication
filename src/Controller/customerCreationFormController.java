package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class customerCreationFormController {

    @FXML
    private ComboBox<?> countryPicker;

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
    private Pane customerCreationFormPane;

    @FXML
    private ComboBox<?> divisionPicker;

    @FXML
    private Button saveButton;

    @FXML
    void onActionCountryPicker(ActionEvent event) {

    }

    @FXML
    void onActionCreationCancel(ActionEvent event) {

    }

    @FXML
    void onActionDivisionPicker(ActionEvent event) {

    }

    @FXML
    void onActionSaveButton(ActionEvent event) {

    }

}
