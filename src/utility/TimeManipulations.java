package utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalTime;

public class TimeManipulations {
    //idk if this is correct to fill start/end time comboboxes
    //Currently it allows selection from 8:00am to 22:00[10Pm]
    public static ObservableList<LocalTime> timeIntervals() {
        ObservableList<LocalTime> listOfTimesAvailable = FXCollections.observableArrayList();
        LocalTime startTime = LocalTime.of(8,00);
        LocalTime endTime = LocalTime.MIDNIGHT.minusHours(2);

        while (startTime.isBefore(endTime.plusSeconds(1))) {
            listOfTimesAvailable.add(startTime);
            startTime = startTime.plusMinutes(15);
        }
        return listOfTimesAvailable;
    }

}
