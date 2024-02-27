package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static DAO.JDBC.createConnection;

/**
 * CustomersDAO contains all Customers Methods to communicate with the MySQL database.
 */
public class CustomersDAO {

    /**
     * getAllCustomers() is used in appointmentCreationFormController. Initialize to set customerComboBox.
     * Also used in appointmentModificationFormController. Initialize to set customerComboBox.
     * Also used in customerMenuController.onActionDeleteCustomer(ActionEvent) & In the Initialize section to SET customerTableView
     * @return customersObservableList
     */
    public static ObservableList<Customers> getAllCustomers() {
        ObservableList<Customers> customersObservableList = FXCollections.observableArrayList();
        try {
            String getAllCustomerData = "SELECT *, first_level_divisions.Division, first_level_divisions.Country_ID, countries.Country FROM customers JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID JOIN countries ON countries.Country_ID = first_level_divisions.Country_ID ORDER BY customers.Customer_ID";
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
                int countryId = result.getInt("Country_ID");

                Customers allCustomers = new Customers(customerId, customerName, customerAddress, customerPostalCode, customerPhoneNumber, createDate, createdBy, lastUpdated, lastUpdatedBy, divisionId, countryId);
                customersObservableList.add(allCustomers);
            }
        }
        catch (SQLException e) {
            System.out.println("Error with getting ALL Customers");
            throw new RuntimeException(e);
        }
        return customersObservableList;
    }

    /**
     * createCustomer() is used in customerCreationFormController.onActionSaveButton(ActionEvent)
     * This creates a customer and adds them to the tableview
     * @param customerName
     * @param customerAddress
     * @param customerPostalCode
     * @param customerPhoneNumber
     * @param createDate
     * @param createdBy
     * @param lastUpdated
     * @param lastUpdatedBy
     * @param divisionId
     * @throws SQLException
     * //had to include lastUpdated, otherwise I would get java.sql errors
     * //had to add CREATED_BY & LAST_UPDATED_BY
     */
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

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * removeCustomerFromTableView(int customerId) is used in customerMenuController.onActionDeleteCustomer(ActionEvent)
     * It removes customer from table view by their customerId
     * @param customerId
     * @throws SQLException
     */
    public static void removeCustomerFromTableView(int customerId) throws SQLException {
            String removeCustomer = "DELETE FROM customers WHERE Customer_ID = ?";
            PreparedStatement byeByeCustomer = createConnection().prepareStatement(removeCustomer);
            byeByeCustomer.setInt(1, customerId);
            byeByeCustomer.execute();
    }

    /**
     * updateCustomer() is used in customerModificationFormController.onActionUpdateCustomerButton(ActionEvent)
     * Updates customer data into table.
     * @param customerId
     * @param customerName
     * @param customerAddress
     * @param customerPostalCode
     * @param customerPhoneNumber
     * @param lastUpdated
     * @param lastUpdatedBy
     * @param divisionId
     */
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
            System.out.println("Error with UPDATING Customer");
            throw new RuntimeException(e);
        }
    }

    /**
     * getAllCustomersById(int customerId) is used in appointmentModificationFormController.appointmentSelection()
     * Sets value of customerComboBox
     * @param customerId
     * @return new Customers(collectedId, collectedName) or Null
     * @throws SQLException
     */
    public static Customers getAllCustomersById(int customerId) throws SQLException {
        try {
            String getCustomerId = "SELECT * FROM customers WHERE Customer_ID = ?";
            PreparedStatement selectedCustomerId = createConnection().prepareStatement(getCustomerId);

            selectedCustomerId.setInt(1, customerId);
            selectedCustomerId.execute();

            ResultSet results = selectedCustomerId.executeQuery();

            while (results.next()) {
                int collectedId = results.getInt("Customer_ID");
                String collectedName = results.getString("Customer_Name");

                return new Customers(collectedId, collectedName);
            }
        }
        catch (SQLException e) {
            System.out.println("Error with getting ALL Customers by ID");
            throw new RuntimeException(e);
        }
        return null;
    }
}
