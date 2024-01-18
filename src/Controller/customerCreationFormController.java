package Controller;

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
import model.First_Level_Divisions;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class customerCreationFormController implements Initializable {

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
    private Pane customerCreationFormPane;

    @FXML
    private ComboBox<First_Level_Divisions> divisionPicker;

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
        alert.setTitle("Closing Customer Creation Page");
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
    void onActionSaveButton(ActionEvent event) throws SQLException {


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
