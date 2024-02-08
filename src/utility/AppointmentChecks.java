package utility;

import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class AppointmentChecks {

    public static boolean openHoursForBusiness(LocalDateTime startTime, LocalDateTime endTime) {
        ZoneId locationEST = ZoneId.of("America/New_York");
        ZoneId locationLocal = ZoneId.systemDefault();

        LocalDateTime appointmentEstStart = startTime.atZone(locationLocal).withZoneSameInstant(locationEST).toLocalDateTime();
        LocalDateTime businessEstStartTime = appointmentEstStart.withHour(8).withMinute(0);
        LocalDateTime appointmentEstEnd = endTime.atZone(locationLocal).withZoneSameInstant(locationEST).toLocalDateTime();
        LocalDateTime businessEstEndTime = appointmentEstEnd.withHour(22).withMinute(0);

        if (appointmentEstEnd.isAfter(businessEstEndTime) || appointmentEstStart.isBefore(businessEstStartTime)) {
            warningMessages.warningCode(1);
            return true;
        }
        else {
            return false;
        }

    }

   /* public static boolean doTimesOverLap(int customerId, LocalDateTime startTime, LocalDateTime endTime) {
        LocalDateTime appointmentStartTime;
        LocalDateTime appointmentEndTime;

    }*/



}
