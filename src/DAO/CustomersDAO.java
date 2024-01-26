package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;
import model.Users;

import java.sql.*;
import java.time.LocalDateTime;

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
    //NOT FINAL!!! NEED TO ADD Created_By and Last_Updated_By
    public static void createCustomer(String customerName, String customerAddress, String customerPostalCode, String customerPhoneNumber, LocalDateTime createDate, LocalDateTime lastUpdated, int divisionId) throws SQLException {
        try {
            String customerCreation = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Last_Update, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement createCust = createConnection().prepareStatement(customerCreation);

            createCust.setString(1, customerName);
            createCust.setString(2, customerAddress);
            createCust.setString(3, customerPostalCode);
            createCust.setString(4, customerPhoneNumber);
            createCust.setTimestamp(5, Timestamp.valueOf(createDate));
            createCust.setTimestamp(6, Timestamp.valueOf(lastUpdated));
            createCust.setInt(7, divisionId);

            createCust.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    ////THIS METHOD IS TRYING TO ADD THING INTO Created_By and Last_Updated_By. NOT WORKING - Sends [admin, test] to Created_By/Last_Update_By col in mySql

    /*public static void createCustomer(String customerName, String customerAddress, String customerPostalCode, String customerPhoneNumber, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdated, String lastUpdatedBy, int divisionId) throws SQLException {
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
    }*/

    public static void removeCustomerFromTableView(int customerId) throws SQLException {
            String removeCustomer = "DELETE FROM customers WHERE Customer_ID = ?";
            PreparedStatement byeByeCustomer = createConnection().prepareStatement(removeCustomer);
            byeByeCustomer.setInt(1, customerId);
            byeByeCustomer.execute();
        }

}
