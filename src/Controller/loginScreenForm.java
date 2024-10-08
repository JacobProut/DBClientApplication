package Controller;

import DAO.AppointmentsDAO;
import DAO.UsersDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointments;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

import static javafx.scene.control.Alert.AlertType.INFORMATION;
import static utility.errorMessages.errorCode;

/**
 * loginScreenForm is the first form that loads up before the Appointment Scheduler Application Form.
 */
public class loginScreenForm implements Initializable {

    /**
     * Button Declaration
     */
    @FXML private Button LoginButton;

    /**
     * Label Declarations
     */
    @FXML private Label ZoneID;
    @FXML private Label labelPassword;
    @FXML private Label labelTimezone;
    @FXML private Label labelUsername;

    /**
     * TextField Declarations
     */
    @FXML private PasswordField passwordField;
    @FXML private TextField usernameField;

    /**
     * isLoginTrue is used for onActionLogin() & loginInfoValidation()
     */
    public boolean isLoginTrue = false;

    /**
     * Initialize sets the ZoneID text to systemDefault()
     *      - Also checks if computers default language is French. If so then it changes text to French.
     * @param url
     * @param resourceBundle
     */
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

    /**
     * onActionLogin(ActionEvent) is used hand to hand with loginInfoValidation(), timesAttemptedToLogin() and checks if the user has an appointment within 15 minutes of log in
     * MAIN METHOD TO LOG INTO mainMenuController
     * @param actionEvent
     * @throws SQLException
     * @throws IOException
     */
    public void onActionLogin(ActionEvent actionEvent) throws SQLException, IOException {
        if (!loginInfoValidation()) return;
        boolean isLoginValid = UsersDAO.verifyLoginInformation(usernameField.getText(), passwordField.getText());

        if (isLoginValid) {
            UsersDAO.verifyLoginInformation(usernameField.getText(), passwordField.getText());
            int loggedInUserID = UsersDAO.getUsersNameById(usernameField.getText());
            ObservableList<Appointments> appointmentsUserList = AppointmentsDAO.getAppointmentForUserList(loggedInUserID);
            loadAppointmentMenu(actionEvent);
            isLoginTrue = true;
            timesAttemptedToLogin();

            boolean hasAppointmentsOnLogin = false;
            for (Appointments selectedAppointment : appointmentsUserList) {
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime start = selectedAppointment.getStartTime();
                LocalDate startDate = selectedAppointment.getStartTime().toLocalDate();
                LocalDateTime nowPlus15Minutes = LocalDateTime.now().plusMinutes(15);
                LocalDateTime nowPlus1Hour = LocalDateTime.now().plusHours(1);
                DateTimeFormatter selectedTimeFormat = DateTimeFormatter.ofPattern("hh:mm:ss a");

                if ((start.isEqual(now) || (start.isBefore(nowPlus15Minutes))) && ((start.isAfter(now) || start.isEqual(nowPlus15Minutes)))) {
                    Alert checkIfAppointmentsWithin15MinsOfLogin = new Alert(INFORMATION);
                    checkIfAppointmentsWithin15MinsOfLogin.setTitle("You have an appointment");
                    checkIfAppointmentsWithin15MinsOfLogin.setHeaderText("There is an appointment within the next 15 minutes!\n" + "Todays date is: [" + startDate + "]");
                    checkIfAppointmentsWithin15MinsOfLogin.setContentText("Selected Appointment_ID of [" + selectedAppointment.getAppointmentId() + "] starts at " + selectedTimeFormat.format(selectedAppointment.getStartTime()) + ".");
                    checkIfAppointmentsWithin15MinsOfLogin.showAndWait();
                    hasAppointmentsOnLogin = true;
                }
                if ((start.isEqual(now) || (start.isBefore(nowPlus1Hour))) && ((start.isAfter(now) || start.isEqual(nowPlus1Hour)))) {
                    Alert checkIfAppointmentsWithin1HourOfLogin = new Alert(INFORMATION);
                    checkIfAppointmentsWithin1HourOfLogin.setTitle("You have an appointment");
                    checkIfAppointmentsWithin1HourOfLogin.setHeaderText("There is an appointment within the next 1 Hour!\n" + "Todays date is: [" + startDate + "]");
                    checkIfAppointmentsWithin1HourOfLogin.setContentText("Selected Appointment_ID of [" + selectedAppointment.getAppointmentId() + "] starts at " + selectedTimeFormat.format(selectedAppointment.getStartTime()) + ".");
                    checkIfAppointmentsWithin1HourOfLogin.showAndWait();
                    hasAppointmentsOnLogin = true;
                }
            }

            if (!hasAppointmentsOnLogin) {
                    Alert noAppointmentsWithInNext15Minutes = new Alert(INFORMATION);
                    noAppointmentsWithInNext15Minutes.setTitle("You have NO Appointments scheduled");
                    noAppointmentsWithInNext15Minutes.setHeaderText("No Appointments Scheduled for at least the next 15 minutes!");
                    noAppointmentsWithInNext15Minutes.setContentText("There are NO Appointments scheduled within the next 15 minutes!");
                    noAppointmentsWithInNext15Minutes.showAndWait();
            }
        }
    }

    /**
     * loginInfoValidation() is used to check for blank/empty username/password fields.
     *      - if false a prompt gets sent-back in an errorCode.
     * @return false or true
     * @throws SQLException
     * @throws IOException
     */
    public Boolean loginInfoValidation() throws SQLException, IOException {
        if (usernameField.getText().isBlank() && passwordField.getText().isEmpty()) {
            errorCode(4);
            System.out.println("Failed Login Attempt: Blank User/Password");
            isLoginTrue = false;
            timesAttemptedToLogin();
            return false;
        } else if (usernameField.getText().isBlank() || usernameField.getText().isEmpty()) {
            errorCode(1);
            System.out.println("Failed Login Attempt: Blank or incorrect Username");
            isLoginTrue = false;
            timesAttemptedToLogin();
            return false;
        } else if (passwordField.getText().isBlank() || passwordField.getText().isEmpty()) {
            errorCode(2);
            System.out.println("Failed Login Attempt: Blank or incorrect Password");
            isLoginTrue = false;
            timesAttemptedToLogin();
            return false;
        } else if (!UsersDAO.verifyLoginInformation(usernameField.getText(), passwordField.getText())) {
            errorCode(3);
            System.out.println("Failed Login Attempt: Incorrect username or password");
            isLoginTrue = false;
            timesAttemptedToLogin();
        }
        return true;
    }

    /**
     * timesAttemptedToLogin() Is used to send Failed and Successful Login Attempts to "login_activity.txt".
     * @throws IOException
     */
    public void timesAttemptedToLogin() throws IOException {
        //Setting dates/times for cleaner login_Activity.txt messages.
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter selectedTimeFormat = DateTimeFormatter.ofPattern("hh:mm:ss a");

        //Creating a file for FileWriter to type to.
        File file = new File("login_activity.txt");
        FileWriter loginWriter = new FileWriter(file.getAbsoluteFile(), true);

        if (isLoginTrue) {
            loginWriter.write("[" + currentDate + "]" + " User: '" + usernameField.getText() + "' has been Accepted in the database at a time of: [" + selectedTimeFormat.format(currentTime) + "]" + "\n");
        }
        else if (!isLoginTrue) {
            loginWriter.write("[" + currentDate + "]" + " User: '" + usernameField.getText() + "' has been Denied access to the database at a time of: [" + selectedTimeFormat.format(currentTime) + "]" + "\n");
        }
        loginWriter.close();
    }

    /**
     * loadAppointmentMenu(ActionEvent event) is used to take the user to mainMenu.fxml(Appointment Scheduler)
     * Created to clean up code in onActionLogin()
     * @param event
     * @throws IOException
     */
    public static void loadAppointmentMenu(ActionEvent event) throws IOException {
        Parent login = FXMLLoader.load(Objects.requireNonNull(loginScreenForm.class.getResource("/View/mainMenu.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene mainMenu = new Scene(login);
        stage.setTitle("Appointment Scheduler Form");
        stage.setScene(mainMenu);
        stage.show();
        stage.centerOnScreen();
        System.out.println("Successfully Logged in: Loading Appointment Scheduler.");
    }

}