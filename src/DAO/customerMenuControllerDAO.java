package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;


public class customerMenuControllerDAO {

    public static ObservableList<Customers> getAllCustomers() {
        ObservableList<Customers> customersObservableList = FXCollections.observableArrayList();
        try {
            //may need to add more In this "String sql = "Select * FROM Customers" Statement"!!!
            String sql = "SELECT * FROM Customers";
            PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                int customerId = result.getInt("Customer_ID");
                String customerName = result.getString("Customer_Name");
                String customerAddress = result.getString("Address");
                String customerPostalCode = result.getString("Postal_Code");
                String customerPhoneNumber = result.getString("Phone");
                LocalDateTime createDate = result.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = result.getString("Created_By");
                LocalDateTime lastUpdated = result.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdatedBy = result.getString("Last_Updated_By");
                int divisionId = result.getInt("Division_ID");

                Customers allCustomers = new Customers(customerId, customerName, customerAddress, customerPostalCode, customerPhoneNumber, createDate, createdBy, lastUpdated, lastUpdatedBy, divisionId);
                customersObservableList.add(allCustomers);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customersObservableList;
    }





}
