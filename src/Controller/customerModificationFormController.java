package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class customerModificationFormController {

    @FXML
    private Pane customerModificationFormPane;

    @FXML
    private ComboBox<?> divisionPicker;

    @FXML
    private Button modificationCancelButton;

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
    private ComboBox<?> modificationCountryPicker;

    @FXML
    private Button updateCustomerButton;

    @FXML
    void onActionModificationCancel(ActionEvent event) {

    }

    @FXML
    void onActionModificationCountryPicker(ActionEvent event) {

    }

    @FXML
    void onActionModificationDivisionPicker(ActionEvent event) {

    }

    @FXML
    void onActionUpdateCustomerButton(ActionEvent event) {

    }

}
