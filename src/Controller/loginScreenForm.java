package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.time.ZoneId;

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

    Stage stage;
    Parent scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList("English", "French");
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
            System.out.println("Error:"); //!!!!!!!!ADD ERRORMSG HERE!!!!!!!!
        }
    }




    //NEED to add sql login information into login.
    //The method in here right now is strictly just to proceed to appointment scheduler page
    @FXML
    public void onActionLogin(ActionEvent event) throws IOException, SQLException {


        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/mainMenu.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
        stage.setTitle("Appointment Scheduler");
    }


    @FXML
    public void onActionLanguagePicker(ActionEvent event) {
        languageComboBoxField.getSelectionModel().getSelectedItem();

        try {
            if (languageComboBoxField.getSelectionModel().getSelectedItem().equals("French")) {
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
            System.out.println("ERROR");
        }

    }
}