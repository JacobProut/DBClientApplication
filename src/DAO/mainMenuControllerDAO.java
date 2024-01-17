package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class mainMenuControllerDAO {

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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return appointmentsObservableList;
    }





}
