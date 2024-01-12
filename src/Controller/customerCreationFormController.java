package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.naming.PartialResultException;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

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

    Parent scene;
    Stage stage;

    @FXML
    void onActionCountryPicker(ActionEvent event) {

    }

    @FXML
    void onActionCreationCancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close Creation Page");
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
    void onActionDivisionPicker(ActionEvent event) {

    }

    @FXML
    void onActionSaveButton(ActionEvent event) {

    }

}
