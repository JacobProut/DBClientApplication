package utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class TimeManipulations {
    //idk if this is correct to fill start/end time comboboxes
    //Currently it allows selection from 8:00am to 22:00[10Pm]
    public static ObservableList<LocalTime> timeIntervals() {
        ObservableList<LocalTime> listOfTimesAvailable = FXCollections.observableArrayList();
        LocalTime startTime = LocalTime.of(6,00);
        LocalTime endTime = LocalTime.MIDNIGHT.minusHours(2);

        while (startTime.isBefore(endTime.plusSeconds(1))) {
            listOfTimesAvailable.add(startTime);
            startTime = startTime.plusMinutes(15);
        }
        return listOfTimesAvailable;
    }

    public static LocalTime establishLocalStartingTime() {
        ZoneId locationEST = ZoneId.of("America/New_York");
        ZoneId locationLocal = ZoneId.systemDefault();

        LocalTime businessTimeOpening = LocalTime.of(8,0);
        LocalDateTime businessLocationET = LocalDateTime.of(LocalDate.now(), businessTimeOpening);
        LocalDateTime businessLocationLocal = businessLocationET.atZone(locationEST).withZoneSameInstant(locationLocal).toLocalDateTime();
        return businessLocationLocal.toLocalTime();
    }

    public static LocalTime establishLocalEndingTime() {
        ZoneId locationEST = ZoneId.of("America/New_York");
        ZoneId locationLocal = ZoneId.systemDefault();

        LocalTime businessTimeClosing = LocalTime.of(22,0);
        LocalDateTime businessLocationET = LocalDateTime.of(LocalDate.now(), businessTimeClosing);
        LocalDateTime businessLocationLocal = businessLocationET.atZone(locationEST).withZoneSameInstant(locationLocal).toLocalDateTime();
        return businessLocationLocal.toLocalTime();
    }

}
