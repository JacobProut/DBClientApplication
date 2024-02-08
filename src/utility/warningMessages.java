package utility;

import javafx.scene.control.Alert;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class warningMessages {
    public static void warningCode(int code) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        switch (code) {
            case 1:
                LocalTime start = TimeManipulations.establishLocalStartingTime();
                LocalTime end = TimeManipulations.establishLocalEndingTime();
                alert.setTitle("Incorrect Hours Selected!");
                alert.setHeaderText("Hours selected are not within Business Hours");
                alert.setContentText("Appointment hours selected are outside of the current business hours: 8:00am -> 10:00pm Eastern Standard Time(EST).\r" + "If you are trying to schedule an appointment, please schedule between " + start.format(DateTimeFormatter.ofPattern("hh:mm")) + " & " + end.format(DateTimeFormatter.ofPattern("hh:mm")) + "PM Local Time!");
                alert.showAndWait();
                break;

            case 2:
                alert.setTitle("");
                alert.setContentText("");
                alert.showAndWait();
                break;
        }
    }
}