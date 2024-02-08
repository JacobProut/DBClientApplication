package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;
import model.Customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static DAO.JDBC.createConnection;

public class ContactsDAO {

public static ObservableList<Contacts> getAllContacts() {
    ObservableList<Contacts> contactsObservableList = FXCollections.observableArrayList();

    try {
        String getEveryContact = "SELECT * FROM Contacts";
        PreparedStatement collectAllContacts = createConnection().prepareStatement(getEveryContact);
        ResultSet result = collectAllContacts.executeQuery();

        while (result.next()) {
            int contactId = result.getInt("Contact_ID");
            String contactName = result.getString("Contact_Name");
            String contactEmail = result.getString("Email");

            Contacts allContacts = new Contacts(contactId, contactName, contactEmail);
            contactsObservableList.add(allContacts);
        }
    }
    catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return contactsObservableList;
}

    public static Contacts getAllContactsById(int contactId) throws SQLException {
        try {
            String getContactId = "SELECT * FROM contacts WHERE Contact_ID = ?";
            PreparedStatement selectedContactId = createConnection().prepareStatement(getContactId);

            selectedContactId.setInt(1, contactId);
            selectedContactId.execute();

            ResultSet results = selectedContactId.executeQuery();

            while (results.next()) {
                int collectedId = results.getInt("Contact_ID");
                String collectedName = results.getString("Contact_Name");

                return new Contacts(collectedId, collectedName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }



}
