package Controller;

import DAO.UsersDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class loginScreenForm implements Initializable {

    @FXML
    private Button LoginButton;

    @FXML
    private Label ZoneID;

    @FXML
    private Label labelLanguage;

    @FXML
    private Label labelPassword;

    @FXML
    private Label labelTimezone;

    @FXML
    private Label labelUsername;

    @FXML
    private ComboBox<String> languageComboBoxField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField usernameField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList("English", "Français");
        languageComboBoxField.setItems(list);
        ZoneID.setText(String.valueOf(ZoneId.systemDefault()));

        try {
            if (Locale.getDefault().getLanguage().equals("fr")) {
                ResourceBundle rbfr = ResourceBundle.getBundle("LanguageBundle/language_fr", Locale.getDefault());

                labelUsername.setText(rbfr.getString("Username"));
                labelPassword.setText(rbfr.getString("Password"));
                labelTimezone.setText(rbfr.getString("Timezone"));
                LoginButton.setText(rbfr.getString("Login"));
                labelLanguage.setText(rbfr.getString("Language"));
            }
        } catch (Exception e ) {
            System.out.println("ERROR OCCURRED: "+ e.getMessage());
        }
    }

    @FXML
    public void onActionLanguagePicker(ActionEvent event) {
        languageComboBoxField.getSelectionModel().getSelectedItem();

        try {
            if (languageComboBoxField.getSelectionModel().getSelectedItem().equals("Français")) {
                ResourceBundle rb = ResourceBundle.getBundle("LanguageBundle/language_fr");

                labelUsername.setText(rb.getString("Username"));
                labelPassword.setText(rb.getString("Password"));
                labelTimezone.setText(rb.getString("Timezone"));
                LoginButton.setText(rb.getString("Login"));
                labelLanguage.setText(rb.getString("Language"));

            } else if (languageComboBoxField.getSelectionModel().getSelectedItem().equals("English")) {
                ResourceBundle rb = ResourceBundle.getBundle("LanguageBundle/language_en");

                labelUsername.setText(rb.getString("Username"));
                labelPassword.setText(rb.getString("Password"));
                labelTimezone.setText(rb.getString("Timezone"));
                LoginButton.setText(rb.getString("Login"));
                labelLanguage.setText(rb.getString("Language"));
            }
        } catch (Exception e) {
            System.out.println("ERROR OCCURRED: " + e.getMessage());
        }
    }


    //FIND A WAY TO MAKE IT SO ERROR MESSAGES TURN TO FRENCH!!!!!!!!!!!!
    public void onActionLogin(ActionEvent actionEvent) throws SQLException, IOException {
        if (!loginInfoValidation()) return;
        boolean isLoginValid = UsersDAO.verifyLoginInformation(usernameField.getText(), passwordField.getText());

        if (isLoginValid) {
            UsersDAO.verifyLoginInformation(usernameField.getText(), passwordField.getText());
            Parent login = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/mainMenu.fxml")));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(login);
            stage.setTitle("Appointment Scheduler Form");
            stage.setScene(scene);
            stage.show();
        }
       }


     public Boolean loginInfoValidation() throws SQLException {
        if (usernameField.getText().isEmpty() && passwordField.getText().isEmpty()) {
            errorMessages.errorMsgs.errorCodes(4);
            return false;
        }
        else if (usernameField.getText().isBlank() || usernameField.getText().isEmpty()) {
            errorMessages.errorMsgs.errorCodes(1);
            return false;
        }
        else if (passwordField.getText().isBlank() || passwordField.getText().isEmpty()) {
            errorMessages.errorMsgs.errorCodes(2);
            return false;
        }
        else if (!UsersDAO.verifyLoginInformation(usernameField.getText(), passwordField.getText())) {
            errorMessages.errorMsgs.errorCodes(3);
        }
        return true;
    }

}