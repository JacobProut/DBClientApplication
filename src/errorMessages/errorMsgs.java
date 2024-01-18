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


            }
        }

}
