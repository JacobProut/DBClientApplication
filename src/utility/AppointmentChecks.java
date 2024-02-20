package utility;

import DAO.AppointmentsDAO;
import javafx.collections.ObservableList;
import model.Appointments;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class AppointmentChecks {

    //Changed computers timezone to EST and looked at what the ZoneId.systemDefault() was to get EST Timezone of: "ZoneId.of("America/New_York")".
    public static boolean openHoursForBusiness(LocalDateTime startTime, LocalDateTime endTime) {
        ZoneId locationEST = ZoneId.of("America/New_York");
        ZoneId locationLocal = ZoneId.systemDefault();

        LocalDateTime appointmentEstStart = startTime.atZone(locationLocal).withZoneSameInstant(locationEST).toLocalDateTime();
        LocalDateTime businessEstStartTime = appointmentEstStart.withHour(8).withMinute(0).withSecond(0);
        LocalDateTime appointmentEstEnd = endTime.atZone(locationLocal).withZoneSameInstant(locationEST).toLocalDateTime();
        LocalDateTime businessEstEndTime = appointmentEstEnd.withHour(22).withMinute(0).withSecond(0);

        if (appointmentEstEnd.isAfter(businessEstEndTime) || appointmentEstStart.isBefore(businessEstStartTime)) {
            //Uses TimeManipulations.establishLocalStartingTime() & TimeManipulations.establishLocalEndingTime()
            warningMessages.warningCode(1);
            System.out.println("!Appointment is NOT within Business Hours!");
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

            //Empty body statement otherwise I get an error?
            if (customerId != overlap.getCustomerId()) {

            }
            else if (startTime.isBefore(appointmentEndTime) && (startTime.isAfter(appointmentStartTime))) {
                warningMessages.warningCode(2);
                System.out.println("!Appointment Start time cannot be during another customers appointment time!");
                return true;
            }
            else if (endTime.isBefore(appointmentEndTime) && (endTime.isAfter(appointmentStartTime))) {
                warningMessages.warningCode(3);
                System.out.println("!Appointment End time cannot be during another customers appointment time!");
                return true;
            }
            else if (appointmentEndTime.isEqual(startTime) || appointmentStartTime.isEqual(startTime)){
                warningMessages.warningCode(4);
                System.out.println("!Appointment start/end times cannot be during the same time as another customers appointments!");
                return true;
            }
        }
        return false;
    }
}
