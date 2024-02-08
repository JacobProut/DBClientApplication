package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.*;
import java.time.LocalDateTime;

import static DAO.JDBC.createConnection;

public class AppointmentsDAO {

    public static ObservableList<Appointments> getAllAppointments() {
        ObservableList<Appointments> appointmentsObservableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments ORDER BY appointments.Appointment_ID";
            PreparedStatement getAllAppointmentData = JDBC.connection.prepareStatement(sql);
            ResultSet result = getAllAppointmentData.executeQuery();

            while (result.next()) {
                int appointmentId = result.getInt("Appointment_ID");
                String appointmentTitle = result.getString("Title");
                String appointmentDescription = result.getString("Description");
                String appointmentLocation = result.getString("Location");
                String appointmentType = result.getString("Type");
                LocalDateTime startTime = result.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endTime = result.getTimestamp("End").toLocalDateTime();
                int customerId = result.getInt("Customer_ID");
                int userId = result.getInt("User_ID");
                int contactId = result.getInt("Contact_ID");

                Appointments all = new Appointments(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, startTime, endTime, customerId, userId, contactId);
                appointmentsObservableList.add(all);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return appointmentsObservableList;
    }


    /*public static ObservableList<Appointments> getAllAppointments() {
        ObservableList<Appointments> appointmentsObservableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments ORDER BY appointments.Appointment_ID";
            PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                int appointmentId = result.getInt("Appointment_ID");
                String appointmentTitle = result.getString("Title");
                String appointmentDescription = result.getString("Description");
                String appointmentLocation = result.getString("Location");
                String appointmentType = result.getString("Type");
                LocalDateTime startTime = result.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endTime = result.getTimestamp("End").toLocalDateTime();
                LocalDateTime appointmentCreationDate = result.getTimestamp("Create_date").toLocalDateTime();
                String appointmentCreatedBy = result.getString("Created_By");
                LocalDateTime lastUpdate = result.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdatedBy = result.getString("Last_Updated_By");
                int customerId = result.getInt("Customer_ID");
                int userId = result.getInt("User_ID");
                int contactId = result.getInt("Contact_ID");

                Appointments all = new Appointments(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, startTime, endTime, appointmentCreationDate, appointmentCreatedBy,lastUpdate, lastUpdatedBy, customerId, userId, contactId);
                appointmentsObservableList.add(all);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return appointmentsObservableList;
    }
*/
   public static void createAppointments(String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType, LocalDateTime startTime, LocalDateTime endTime, int customerId, int userId, int contactId) throws SQLException {
        String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement createAppointment = JDBC.connection.prepareStatement(sql);

        createAppointment.setString(1, appointmentTitle);
        createAppointment.setString(2, appointmentDescription);
        createAppointment.setString(3, appointmentLocation);
        createAppointment.setString(4, appointmentType);
        createAppointment.setTimestamp(5, Timestamp.valueOf(startTime));
        createAppointment.setTimestamp(6, Timestamp.valueOf(endTime));
        createAppointment.setInt(7, customerId);
        createAppointment.setInt(8, userId);
        createAppointment.setInt(9, contactId);
        createAppointment.execute();
   }

   public static void removeAppointment(int appointmentId) throws SQLException {
       String deleteAppointment = "DELETE FROM appointments WHERE Appointment_ID = ?";
       PreparedStatement removeAppointment = createConnection().prepareStatement(deleteAppointment);

       removeAppointment.setInt(1, appointmentId);
       removeAppointment.execute();
   }

   //Unsure this works until I can add data within the current week
    // Still doesn't work. Need to fix.
   public static ObservableList<Appointments> viewWeekAppoints() {
        ObservableList<Appointments> viewWeekList = FXCollections.observableArrayList();

        try {
            String week = "SELECT appointments.Appointment_ID, appointments.Title, appointments.Description, appointments.Location, contacts.Contact_ID, appointments.Type, appointments.Start, appointments.End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID FROM appointments INNER JOIN contacts on appointments.Contact_ID = contacts.Contact_ID WHERE WEEK(Start) = WEEK(now()) ORDER BY appointments.Appointment_ID";
            PreparedStatement viewWeekAppointments = createConnection().prepareStatement(week);
            ResultSet result = viewWeekAppointments.executeQuery();

            while (result.next()) {
                int appointmentId = result.getInt("Appointment_ID");
                String appointmentTitle = result.getString("Title");
                String appointmentDescription = result.getString("Description");
                String appointmentLocation = result.getString("Location");
                int contactId = result.getInt("Contact_ID");
                String appointmentType = result.getString("Type");
                LocalDateTime startTime = result.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endTime = result.getTimestamp("End").toLocalDateTime();
                int customerId = result.getInt("Customer_ID");
                int userId = result.getInt("User_ID");

                Appointments viewByWeek = new Appointments(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, contactId, appointmentType, startTime, endTime, customerId, userId);
                viewWeekList.add(viewByWeek);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
       return viewWeekList;
   }

   //works
   public static ObservableList<Appointments> viewMonthAppoints() {
        ObservableList<Appointments> viewMonthList = FXCollections.observableArrayList();

        try {
            String month = "SELECT appointments.Appointment_ID, appointments.Title, appointments.Description, appointments.Location, contacts.Contact_ID, appointments.Type, appointments.Start, appointments.End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID FROM appointments INNER JOIN contacts on appointments.Contact_ID = contacts.Contact_ID WHERE MONTH(Start) = MONTH(now()) ORDER BY appointments.Appointment_ID";
            PreparedStatement viewMonthAppointments = createConnection().prepareStatement(month);
            ResultSet result = viewMonthAppointments.executeQuery();

            while (result.next()) {
                int appointmentId = result.getInt("Appointment_ID");
                String appointmentTitle = result.getString("Title");
                String appointmentDescription = result.getString("Description");
                String appointmentLocation = result.getString("Location");
                int contactId = result.getInt("Contact_ID");
                String appointmentType = result.getString("Type");
                LocalDateTime startTime = result.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endTime = result.getTimestamp("End").toLocalDateTime();
                int customerId = result.getInt("Customer_ID");
                int userId = result.getInt("User_ID");

                Appointments viewByMonth = new Appointments(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, contactId, appointmentType, startTime, endTime, customerId, userId);
                viewMonthList.add(viewByMonth);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
       return viewMonthList;
   }

}
