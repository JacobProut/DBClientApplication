package utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class TimeManipulations {
    //Currently it allows selection from 6:00am to 22:00[10Pm]
    public static ObservableList<LocalTime> timeIntervals() {
        ObservableList<LocalTime> listOfTimesAvailable = FXCollections.observableArrayList();

        //LocalTime.MIDNIGHT.plusHours(1) is so 1am is the earliest a time could be selected.
        LocalTime startTime = LocalTime.MIDNIGHT.plusHours(1);

        //LocalTime.MIDNIGHT.minusHours(2) added so 11pm is the latest a time could be selected.
        LocalTime endTime = LocalTime.MIDNIGHT.minusHours(1);

        //Needed to add plusSeconds, otherwise it wouldn't work properly
        while (startTime.isBefore(endTime.plusSeconds(1))) {
            listOfTimesAvailable.add(startTime);
            //startTime.plusMinutes(15) makes it so selections of times is every 15 minutes.
            startTime = startTime.plusMinutes(15);
        }
        return listOfTimesAvailable;
    }

    //This method sets the starting time in AppointmentChecks.openHoursForBusiness()
    public static LocalTime establishLocalStartingTime() {
        ZoneId locationEST = ZoneId.of("America/New_York");
        ZoneId locationLocal = ZoneId.systemDefault();

        //Opening time is 8AM EST
        LocalTime businessTimeOpening = LocalTime.of(8,0);
        LocalDateTime businessLocationET = LocalDateTime.of(LocalDate.now(), businessTimeOpening);
        LocalDateTime businessLocationLocal = businessLocationET.atZone(locationEST).withZoneSameInstant(locationLocal).toLocalDateTime();
        return businessLocationLocal.toLocalTime();
    }

    //This method sets the ending time in AppointmentChecks.openHoursForBusiness()
    public static LocalTime establishLocalEndingTime() {
        ZoneId locationEST = ZoneId.of("America/New_York");
        ZoneId locationLocal = ZoneId.systemDefault();

        //Closing time is 10PM EST
        LocalTime businessTimeClosing = LocalTime.of(22,0);
        LocalDateTime businessLocationET = LocalDateTime.of(LocalDate.now(), businessTimeClosing);
        LocalDateTime businessLocationLocal = businessLocationET.atZone(locationEST).withZoneSameInstant(locationLocal).toLocalDateTime();
        return businessLocationLocal.toLocalTime();
    }

}
