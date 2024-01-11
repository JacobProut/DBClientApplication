package errorMessages;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.util.ResourceBundle;

public abstract class errorMsgs implements Initializable {
    static ResourceBundle languageBundle = ResourceBundle.getBundle("language/lang");

    public static void errorCodes(int codes) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
            switch (codes) {
                case 1:
                    alert.setTitle(languageBundle.getString("BadUsername"));
                    alert.setContentText(languageBundle.getString("ErrorUsername"));
                    alert.showAndWait();
                    break;

                case 2:
                    alert.setTitle(languageBundle.getString("BadPassword"));
                    alert.setContentText((languageBundle.getString("ErrorPassword")));
                    alert.showAndWait();
                    break;

                case 3:
                    alert.setTitle((languageBundle.getString("BadUserOrPass")));
                    alert.setContentText(languageBundle.getString("ErrorUserOrPass"));
                    alert.showAndWait();
                    break;
            }
        }

    }
