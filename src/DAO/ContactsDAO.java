package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;

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

}
