package utility;

import DAO.AppointmentsDAO;
import javafx.collections.ObservableList;
import model.Appointments;

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

    public static boolean doTimesOverLap(int customerId, LocalDateTime startTime, LocalDateTime endTime) {
        LocalDateTime appointmentStartTime;
        LocalDateTime appointmentEndTime;

        ObservableList<Appointments> appointmentsObservableList = AppointmentsDAO.getAllAppointments();
        for (Appointments overlap : appointmentsObservableList) {
            appointmentStartTime = overlap.getStartTime();
            appointmentEndTime = overlap.getEndTime();

            if (customerId != overlap.getCustomerId()) {

            }
            else if (startTime.isBefore(appointmentEndTime) && (startTime.isAfter(appointmentStartTime))) {
                warningMessages.warningCode(3);
                return true;
            }
            else if (endTime.isBefore(appointmentEndTime) && (endTime.isAfter(appointmentStartTime))) {
                warningMessages.warningCode(4);
                return true;
            }
            else if (appointmentEndTime.isEqual(startTime) || appointmentStartTime.isEqual(startTime)){
                warningMessages.warningCode(2);
                return true;
            }
        }
        return false;
    }

}
