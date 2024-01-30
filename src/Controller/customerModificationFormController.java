package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

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

    }

    @FXML
    void onActionModificationDivisionPicker(ActionEvent event) {

    }

    @FXML
    void onActionUpdateCustomerButton(ActionEvent event) {

    }

}
