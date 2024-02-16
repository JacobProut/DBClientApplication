package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import static javafx.scene.control.ButtonType.OK;

public class reportsMenuController {
    Parent scene;
    Stage stage;

    @FXML
    void onActionAppointmentTotalByTypeAndMonth(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/reportsMenuTotalTypeAndMonth.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
        stage.centerOnScreen();
        stage.setTitle("Viewing Total amount of Types and Months for Appointments");
        System.out.println("Loading Appointment Types and Months Form.");

    }

    @FXML
    void onActionContactSchedules(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/reportsMenuContactSchedule.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
        stage.centerOnScreen();
        stage.setTitle("Viewing Contact Schedules");
        System.out.println("Loading Contact Schedule Form.");

    }

    @FXML
    void onActionTotalNumberOfCustomersByCountry(ActionEvent event) {

    }

    @FXML
    void onActionReturnToAppointmentScheduler(ActionEvent event) throws IOException {
       Alert alert = new Alert(CONFIRMATION);
        alert.setTitle("Closing Reports Menu");
        alert.setHeaderText("You are about to return to the Appointment Scheduler");
        alert.setContentText("Click 'OK' to go back to Appointment Scheduler.\r" + "Click 'Cancel' to stay on the Reports Menu Form.");
        Optional<ButtonType> confirmation = alert.showAndWait();
        if (confirmation.isPresent() && confirmation.get() == OK) {
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/mainMenu.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
            stage.centerOnScreen();
            stage.setTitle("Appointment Scheduler");
            System.out.println("Returning to Appointment Scheduler.");
        }
    }

    public static void returnToReportsMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(reportsMenuController.class.getResource("/view/reportsMenu.fxml")));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
        stage.setTitle("Reports Menu");
        System.out.println("Returning to Reports Menu.");
    }
}
