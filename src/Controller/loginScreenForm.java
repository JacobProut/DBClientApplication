package Controller;

import DAO.UsersDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

import errorMessages.errorMsgs;
public class loginScreenForm implements Initializable {

    @FXML
    private Button LoginButton;

    @FXML
    private Label ZoneID;

    @FXML
    private Label labelPassword;

    @FXML
    private Label labelTimezone;

    @FXML
    private Label labelUsername;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField usernameField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ZoneID.setText(String.valueOf(ZoneId.systemDefault()));

        try {
            if (Locale.getDefault().getLanguage().equals("fr")) {
                ResourceBundle rbfr = ResourceBundle.getBundle("LanguageBundle/language_fr", Locale.getDefault());

                labelUsername.setText(rbfr.getString("Username"));
                labelPassword.setText(rbfr.getString("Password"));
                labelTimezone.setText(rbfr.getString("Timezone"));
                LoginButton.setText(rbfr.getString("Login"));
            }
        } catch (Exception e) {
            System.out.println("ERROR OCCURRED: "+ e.getMessage());
        }
    }

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
        if (usernameField.getText().isBlank() && passwordField.getText().isEmpty()) {
            errorMsgs.errorCodes(4);
            return false;
        } else if (usernameField.getText().isBlank() || usernameField.getText().isEmpty()) {
            errorMsgs.errorCodes(1);
            return false;
        } else if (passwordField.getText().isBlank() || passwordField.getText().isEmpty()) {
            errorMsgs.errorCodes(2);
            return false;
        } else if (!UsersDAO.verifyLoginInformation(usernameField.getText(), passwordField.getText())) {
            errorMsgs.errorCodes(3);
        }
        return true;
    }
    
}