package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static DAO.JDBC.createConnection;

/**
 * ContactsDAO contains all ContactsDAO Methods to communicate with the MySQL database.
 */
public class ContactsDAO {

    /**
     * getAllContacts() is used in appointmentCreationFormController. Initialize to set Contacts ComboBox.
     *  - Also used in appointmentModification. Initialize to set Contacts ComboBox.
     *  - Also used in reportsMenuContactScheduleController. Initialize to set a Contacts ComboBox.
     * @return contactsObservableList
     */
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

    /**
     * getContactIdByName(String contactName) is used in reportsMenuContactScheduleController.onActionComboBoxContacts(ActionEvent)
     * @param contactName
     * @return selectedContactId
     * @throws SQLException
     */
    //Method used to get contact ID by Name for reportsMenuContactScheduleController.onActionComboBoxContacts()
    public static int getContactIdByName(String contactName) throws SQLException {
            String getIdByNam = "SELECT * FROM contacts WHERE Contact_Name = ?";
            PreparedStatement selectedContactName = createConnection().prepareStatement(getIdByNam);

            selectedContactName.setString(1, contactName);

            ResultSet resultSet = selectedContactName.executeQuery();

            int selectedContactId = 0;
            while (resultSet.next()) {
                selectedContactId= resultSet.getInt("Contact_ID");
            }
            return selectedContactId;
    }

    /**
     * getAllContactsById(int contactId) is used in appointmentModificationFormController.appointmentSelection to set a Contact ComboBox.
     * @param contactId
     * @return new Contacts(collectedId, collectedName) or null
     * @throws SQLException
     */
    //Method  used in appointmentModificationFormController.appointmentSelection()
    public static Contacts getAllContactsById(int contactId) throws SQLException {
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
        return null;
    }
}
