package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Locale;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList("English", "French");
        languageComboBoxField.setItems(list);
        ZoneID.setText(String.valueOf(ZoneId.systemDefault()));

        try {
            if (Locale.getDefault().getLanguage().equals("fr")) {
                ResourceBundle rb = ResourceBundle.getBundle("LanguageBundle/language_fr", Locale.getDefault());

                labelUsername.setText(rb.getString("Username"));
                labelPassword.setText(rb.getString("Password"));
                labelTimezone.setText(rb.getString("Timezone"));
                LoginButton.setText(rb.getString("Login"));
                labelLanguage.setText(rb.getString("Language"));
            }
        } catch (Exception e ) {
            System.out.println("Error:"); //!!!!!!!!ADD ERRORMSG HERE!!!!!!!!
        }
    }

    @FXML
    void onActionLogin(ActionEvent event){

    }


    @FXML
    void onActionLanguagePicker(ActionEvent event) {
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