package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.*;
import java.time.LocalDateTime;

public class AppointmentsDAO {

    public static ObservableList<Appointments> getAllAppointments() {
        ObservableList<Appointments> appointmentsObservableList = FXCollections.observableArrayList();

        try {
            //This is going to be needed once getting contactsDAO working
            //*String sql = "SELECT * FROM appointments JOIN contacts ON appointments.Contacts_ID ORDER BY appointments.Appointment_ID";*//
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

   public static void createAppointments(String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType, LocalDateTime startTime, LocalDateTime endTime, int customerId, int userId, int contactId) throws SQLException {
        String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);

        preparedStatement.setString(1, appointmentTitle);
        preparedStatement.setString(2, appointmentDescription);
        preparedStatement.setString(3, appointmentLocation);
        preparedStatement.setString(4, appointmentType);
        preparedStatement.setTimestamp(5, Timestamp.valueOf(startTime));
        preparedStatement.setTimestamp(6, Timestamp.valueOf(endTime));
        preparedStatement.setInt(7, customerId);
        preparedStatement.setInt(8, userId);
        preparedStatement.setInt(9, contactId);
        preparedStatement.execute();
   }

   //Unsure this works until I can add data within the current week
   public static ObservableList<Appointments> viewWeekAppoints() {
        ObservableList<Appointments> viewWeekList = FXCollections.observableArrayList();

        try {
            String week = "SELECT appointments.Appointment_ID, appointments.Title, appointments.Description, appointments.Location, contacts.Contact_ID, appointments.Type, appointments.Start, appointments.End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID FROM appointments INNER JOIN contacts on appointments.Contact_ID = contacts.Contact_ID WHERE WEEK(Start) = WEEK(now()) ORDER BY appointments.Appointment_ID";
            PreparedStatement preparedStatement = JDBC.connection.prepareStatement(week);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                int appointmentId = result.getInt("Appointment_ID");
                String appointmentTitle = result.getString("Title");
                String appointmentDescription = result.getString("Description");
                String appointmentLocation = result.getString("Location");
                int contactId = result.getInt("Contact");
                String appointmentType = result.getString("Type");
                LocalDateTime startTime = result.getTimestamp("Start Date/Time").toLocalDateTime();
                LocalDateTime endTime = result.getTimestamp("End Date/Time").toLocalDateTime();
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

   public static ObservableList<Appointments> viewMonthAppoints() {
        ObservableList<Appointments> viewMonthList = FXCollections.observableArrayList();

        try {
            String month = "SELECT appointments.Appointment_ID, appointments.Title, appointments.Description, appointments.Location, contacts.Contact_ID, appointments.Type, appointments.Start, appointments.End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID FROM appointments INNER JOIN contacts on appointments.Contact_ID = contacts.Contact_ID WHERE MONTH(Start) = MONTH(now()) ORDER BY appointments.Appointment_ID";
            PreparedStatement preparedStatement = JDBC.connection.prepareStatement(month);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                int appointmentId = result.getInt("Appointment_ID");
                String appointmentTitle = result.getString("Title");
                String appointmentDescription = result.getString("Description");
                String appointmentLocation = result.getString("Location");
                int contactId = result.getInt("Contact");
                String appointmentType = result.getString("Type");
                LocalDateTime startTime = result.getTimestamp("Start Date/Time").toLocalDateTime();
                LocalDateTime endTime = result.getTimestamp("End Date/Time").toLocalDateTime();
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
