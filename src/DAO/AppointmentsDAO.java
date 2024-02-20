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

    //Attempting to make this for Schedules for Contacts
    public static ObservableList<Appointments> getAppointmentForContactList(int contactId) {
        ObservableList<Appointments> appointmentsContactList = FXCollections.observableArrayList();

        try {
            String contactSql = "SELECT appointments.Appointment_ID, appointments.Title, appointments.Type, appointments.Description, appointments.Start, appointments.End, appointments.Customer_ID FROM appointments JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID WHERE appointments.Contact_ID = '" + contactId + "'";
            PreparedStatement getAppointmentsForContactTable = createConnection().prepareStatement(contactSql);
            ResultSet results = getAppointmentsForContactTable.executeQuery();

            while (results.next()) {
                int appointmentId = results.getInt("Appointment_ID");
                String appointmentTitle = results.getString("Title");
                String appointmentType = results.getString("Type");
                String appointmentDescription = results.getString("Description");
                LocalDateTime startTime = results.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endTime = results.getTimestamp("End").toLocalDateTime();
                int customerId = results.getInt("Customer_ID");

                Appointments contactList = new Appointments(appointmentId, appointmentTitle, appointmentType, appointmentDescription, startTime, endTime, customerId);
                appointmentsContactList.add(contactList);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return appointmentsContactList;
    }

    public static ObservableList<Appointments> getAppointmentForUserList(int userId) {
        ObservableList<Appointments> appointmentsUserList = FXCollections.observableArrayList();

        try {
            String contactSql = "SELECT appointments.Appointment_ID, appointments.Title, appointments.Description, appointments.Location, appointments.Type, appointments.Start, appointments.End, appointments.Customer_ID, appointments.Contact_ID FROM appointments JOIN users ON appointments.User_ID = users.User_ID WHERE appointments.User_ID = '" + userId + "'";
            PreparedStatement getAppointmentsForUserTable = createConnection().prepareStatement(contactSql);
            ResultSet results = getAppointmentsForUserTable.executeQuery();

            while (results.next()) {
                int appointmentId = results.getInt("Appointment_ID");
                String appointmentTitle = results.getString("Title");
                String appointmentDescription = results.getString("Description");
                String appointmentType = results.getString("Type");
                String appointmentLocation = results.getString("Location");
                LocalDateTime startTime = results.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endTime = results.getTimestamp("End").toLocalDateTime();
                int customerId = results.getInt("Customer_ID");
                int contactId = results.getInt("Contact_ID");

                Appointments userList = new Appointments(appointmentId, appointmentTitle, appointmentDescription, appointmentType, appointmentLocation, startTime, endTime, customerId, contactId);
                appointmentsUserList.add(userList);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return appointmentsUserList;
    }

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
    //Working method
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

   //Start of Reports menu DAO
   public static ObservableList<Appointments> getAppointmentTypeTotal() throws SQLException {
        ObservableList<Appointments> totalAppointmentTypeList = FXCollections.observableArrayList();

        try {
            //GROUP BY must be added otherwise I get a SQLSyntaxError
            String type = "SELECT Type, COUNT(*) AS COUNTEDTYPENUMBER FROM appointments GROUP BY Type";
            PreparedStatement typeListStatement = createConnection().prepareStatement(type);

            ResultSet listResult = typeListStatement.executeQuery();
            while (listResult.next()) {
                String typeOfAppointment = listResult.getString("Type");
                int typeCountTotal = listResult.getInt("COUNTEDTYPENUMBER");

                Appointments typeResult = new Appointments(typeOfAppointment, typeCountTotal);
                totalAppointmentTypeList.add(typeResult);
            }
        }
        catch (SQLException e) {
            System.out.println("Error with getting Appointment Type or Type Total");
            throw new RuntimeException(e);
        }
       return totalAppointmentTypeList;
   }

   public static ObservableList<Appointments> getAppointmentMonthTotal() throws SQLException {
        ObservableList<Appointments> totalAppointmentMonthList = FXCollections.observableArrayList();

        try {
            //GROUP BY must be added otherwise I get a SQLSyntaxError
            String month = "SELECT (MONTHNAME(Start)) AS Month, COUNT(*) AS COUNTEDMONTHNUMBER FROM appointments GROUP BY Month";
            PreparedStatement monthListStatement = createConnection().prepareStatement(month);

            ResultSet monthResult = monthListStatement.executeQuery();
            while (monthResult.next()) {
                String appointmentMonth = monthResult.getString("Month");
                int monthCountTotal = monthResult.getInt("COUNTEDMONTHNUMBER");

                Appointments monthCountResult = new Appointments(appointmentMonth, monthCountTotal);
                totalAppointmentMonthList.add(monthCountResult);
            }
        }
        catch (SQLException e) {
            System.out.println("Error with getting Appointment Month or Month Total");
            throw new RuntimeException(e);
        }
       return totalAppointmentMonthList;
   }
   //End of Report menu DAO



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

    public static void updateAppointments(int appointmentId, String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType, LocalDateTime startTime, LocalDateTime endTime, int customerId, int userId, int contactId) throws SQLException {
            String updateAppointment = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
            PreparedStatement updateAppointmentToDB = createConnection().prepareStatement(updateAppointment);

            updateAppointmentToDB.setString(1, appointmentTitle);
            updateAppointmentToDB.setString(2, appointmentDescription);
            updateAppointmentToDB.setString(3, appointmentLocation);
            updateAppointmentToDB.setString(4, appointmentType);
            updateAppointmentToDB.setTimestamp(5, Timestamp.valueOf(startTime));
            updateAppointmentToDB.setTimestamp(6, Timestamp.valueOf(endTime));
            updateAppointmentToDB.setInt(7, customerId);
            updateAppointmentToDB.setInt(8, userId);
            updateAppointmentToDB.setInt(9, contactId);
            updateAppointmentToDB.setInt(10, appointmentId);
            updateAppointmentToDB.execute();
    }

}
