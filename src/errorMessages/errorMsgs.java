package errorMessages;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.util.ResourceBundle;

public abstract class errorMsgs implements Initializable {
    static ResourceBundle languageBundle = ResourceBundle.getBundle("LanguageBundle/language");

    public static void errorCodes(int codes) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
            switch (codes) {
                case 1: //Empty username field
                    alert.setTitle(languageBundle.getString("EmptyUsernameField"));
                    alert.setContentText(languageBundle.getString("EmptyUserName"));
                    alert.showAndWait();
                    break;

                case 2: //Empty password field
                    alert.setTitle(languageBundle.getString("EmptyPasswordField"));
                    alert.setContentText((languageBundle.getString("EmptyPassword")));
                    alert.showAndWait();
                    break;

                case 3: //Incorrect username
                    alert.setTitle((languageBundle.getString("IncorrectUsernameAndPassword")));
                    alert.setContentText(languageBundle.getString("wrongUserNameAndPassword"));
                    alert.showAndWait();
                    break;

                case 4:
                    alert.setTitle((languageBundle.getString("EmptyUserAndPassword")));
                    alert.setContentText(languageBundle.getString("EmptyUsernPass"));
                    alert.showAndWait();
                    break;

                case 5: //Add/Modify Customer menu: ALL FIELDS EMPTY
                    alert.setTitle((languageBundle.getString("addModifyCustomerAllEmptyFields")));
                    alert.setContentText(languageBundle.getString("AllEmptyAddModifyCustomers"));
                    alert.showAndWait();
                    break;

                case 6: //Add/Modify Customer menu: Empty Customer Name
                    alert.setTitle((languageBundle.getString("EmptyCustomerNameField")));
                    alert.setContentText(languageBundle.getString("EmptyCustomerName"));
                    alert.showAndWait();
                    break;

                case 7: //Add/Modify Customer menu: Empty Address
                    alert.setTitle((languageBundle.getString("EmptyCustomerAddressField")));
                    alert.setContentText(languageBundle.getString("EmptyCustomerAddress"));
                    alert.showAndWait();
                    break;

                case 8: //Add/Modify Customer menu: Empty Postal Code
                    alert.setTitle((languageBundle.getString("EmptyCustomerPostalCodeField")));
                    alert.setContentText(languageBundle.getString("EmptyCustomerPostalCode"));
                    alert.showAndWait();
                    break;

                case 9: //Add/Modify Customer menu: Empty Phone Number
                    alert.setTitle((languageBundle.getString("EmptyCustomerPhoneNumberField")));
                    alert.setContentText(languageBundle.getString("EmptyCustomerPhoneNumber"));
                    alert.showAndWait();
                    break;

                case 10: //Add/Modify Customer menu: Empty Country Box
                    alert.setTitle((languageBundle.getString("EmptyCustomerCountryBoxField")));
                    alert.setContentText(languageBundle.getString("EmptyCustomerCountryBox"));
                    alert.showAndWait();
                    break;

                case 11: //Add/Modify Customer menu: Empty Division Box
                    alert.setTitle((languageBundle.getString("EmptyCustomerDivisionsBoxField")));
                    alert.setContentText(languageBundle.getString("EmptyCustomerDivisionsBox"));
                    alert.showAndWait();
                    break;

            }
        }

}
