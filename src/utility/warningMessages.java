package utility;

import javafx.scene.control.Alert;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class warningMessages {
    public static void warningCode(int code) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        switch (code) {
            case 1: //Used in AppointmentChecks.openHoursForBusiness()
                LocalTime start = TimeManipulations.establishLocalStartingTime();
                LocalTime end = TimeManipulations.establishLocalEndingTime();
                alert.setTitle("Incorrect Hours Selected!");
                alert.setHeaderText("Hours selected are not within Business Hours");
                alert.setContentText("Appointment hours selected are outside of the current business hours: 8:00:00AM -TO- 10:00:00PM Eastern Standard Time(EST).\r" + "If you are trying to schedule an appointment, please schedule between: " + start.format(DateTimeFormatter.ofPattern("h:mm:ss")) + "AM -TO- " + end.format(DateTimeFormatter.ofPattern("h:mm:ss")) + "PM Local Time!");
                alert.showAndWait();
                break;

            case 2: //Used in AppointmentChecks.doTimesOverLap()
                alert.setTitle("Overlap Warning");
                alert.setHeaderText("Start time Overlapping");
                alert.setContentText("The start time cannot be during another customers appointment time!");
                alert.showAndWait();
                break;

            case 3: //Used in AppointmentChecks.doTimesOverLap()
                alert.setTitle("Overlap Warning");
                alert.setHeaderText("End time Overlapping");
                alert.setContentText("The end time cannot be during another customers appointment time!");
                alert.showAndWait();
                break;

            case 4: //Used in AppointmentChecks.doTimesOverLap()
                alert.setTitle("Overlap Warning");
                alert.setHeaderText("Selected times are overlapping other customers");
                alert.setContentText("Appointment cannot start or end during the same time as another customers appointment!");
                alert.showAndWait();
                break;

        }
    }
}