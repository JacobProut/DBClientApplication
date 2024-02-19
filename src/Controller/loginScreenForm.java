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

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

import static utility.errorMessages.errorCode;

public class loginScreenForm implements Initializable {

    @FXML private Button LoginButton;
    @FXML private Label ZoneID;
    @FXML private Label labelPassword;
    @FXML private Label labelTimezone;
    @FXML private Label labelUsername;
    @FXML private TextField passwordField;
    @FXML private TextField usernameField;


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

    boolean isLoginTrue = false;
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
            stage.centerOnScreen();
            System.out.println("Successfully Logged in: Loading Appointment Scheduler.");
            isLoginTrue = true;
        }
    }

    public Boolean loginInfoValidation() throws SQLException, IOException {
        if (usernameField.getText().isBlank() && passwordField.getText().isEmpty()) {
            errorCode(4);
            System.out.println("Failed Login Attempt: Blank User/Password");
            timesAttemptedToLogin();
            isLoginTrue = false;
            return false;
        } else if (usernameField.getText().isBlank() || usernameField.getText().isEmpty()) {
            errorCode(1);
            System.out.println("Failed Login Attempt: Blank or incorrect Username");
            timesAttemptedToLogin();
            isLoginTrue = false;
            return false;
        } else if (passwordField.getText().isBlank() || passwordField.getText().isEmpty()) {
            errorCode(2);
            System.out.println("Failed Login Attempt: Blank or incorrect Password");
            timesAttemptedToLogin();
            isLoginTrue = false;
            return false;
        } else if (!UsersDAO.verifyLoginInformation(usernameField.getText(), passwordField.getText())) {
            errorCode(3);
            System.out.println("Failed Login Attempt: Incorrect username or password");
            timesAttemptedToLogin();
            isLoginTrue = false;
        }
        return true;
    }




    //FIX THIS AND MAKE IT SEEM LEGITLY MADE!!!!
    interface loginTries {
        public String loginAttempt();
    }

    loginTries setLog = () -> {
        return "login_activity.txt";
    };

    LocalDateTime exactTime = LocalDateTime.now();
    public void timesAttemptedToLogin() throws IOException {
        FileWriter loginWriter = new FileWriter(setLog.loginAttempt(), true);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm:ss");
        ZoneId localZone = ZoneId.systemDefault();
        if (isLoginTrue) {
            loginWriter.write(usernameField.getText() + " has successfully logged in on " + formatter.format(exactTime));
        } else if (!isLoginTrue) {
            loginWriter.write(usernameField.getText() + " has failed login on " + formatter.format(exactTime));
        }
        loginWriter.write("\n");
        loginWriter.close();
    }
}