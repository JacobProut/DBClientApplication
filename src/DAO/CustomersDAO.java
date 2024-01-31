package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;
import model.Users;

import java.sql.*;
import java.time.LocalDateTime;

import static DAO.JDBC.connection;
import static DAO.JDBC.createConnection;


public class CustomersDAO {

    public static ObservableList<Customers> getAllCustomers() {
        ObservableList<Customers> customersObservableList = FXCollections.observableArrayList();
        try {
            //may need to add more In this "String sql = "Select * FROM Customers" Statement"!!!
            String getAllCustomerData = "SELECT * FROM Customers";
            PreparedStatement getCust = createConnection().prepareStatement(getAllCustomerData);
            ResultSet result = getCust.executeQuery();

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
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customersObservableList;
    }



    //had to include lastUpdated, otherwise i would get java.sql errors
    //FINAL ADDED CREATED_BY & LAST_UPDATED_BY
    public static void createCustomer(String customerName, String customerAddress, String customerPostalCode, String customerPhoneNumber, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdated, String lastUpdatedBy, int divisionId) throws SQLException {
        try {
            String customerCreation = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement createCust = createConnection().prepareStatement(customerCreation);

            createCust.setString(1, customerName);
            createCust.setString(2, customerAddress);
            createCust.setString(3, customerPostalCode);
            createCust.setString(4, customerPhoneNumber);
            createCust.setTimestamp(5, Timestamp.valueOf(createDate));
            createCust.setString(6, createdBy);
            createCust.setTimestamp(7, Timestamp.valueOf(lastUpdated));
            createCust.setString(8, lastUpdatedBy);
            createCust.setInt(9, divisionId);

            createCust.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void removeCustomerFromTableView(int customerId) throws SQLException {
            String removeCustomer = "DELETE FROM customers WHERE Customer_ID = ?";
            PreparedStatement byeByeCustomer = createConnection().prepareStatement(removeCustomer);
            byeByeCustomer.setInt(1, customerId);
            byeByeCustomer.execute();
        }


    public static void updateCustomer(int customerId, String customerName, String customerAddress, String customerPostalCode, String customerPhoneNumber, LocalDateTime lastUpdated, String lastUpdatedBy, int divisionId) {
        try {
            String updateCustomer = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";
            PreparedStatement updateCustomerToDB = createConnection().prepareStatement(updateCustomer);

            updateCustomerToDB.setString(1, customerName);
            updateCustomerToDB.setString(2, customerAddress);
            updateCustomerToDB.setString(3, customerPostalCode);
            updateCustomerToDB.setString(4, customerPhoneNumber);
            updateCustomerToDB.setTimestamp(5, Timestamp.valueOf(lastUpdated));
            updateCustomerToDB.setString(6, lastUpdatedBy);
            updateCustomerToDB.setInt(7, divisionId);
            updateCustomerToDB.setInt(8, customerId);

            updateCustomerToDB.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
