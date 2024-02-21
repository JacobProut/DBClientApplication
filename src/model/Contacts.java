package model;


/**
 * Contacts class is created for recording objects into tables/ComboBoxes
 */
public class Contacts {


    /**
     * Primary Key Declarations
     */
    private int contactId;
    private String contactName;
    private String contactEmail;

    /**
     * Constructor used in ContactsDAO.getAllContacts()
     * @param contactId
     * @param contactName
     * @param contactEmail
     */
    public Contacts(int contactId, String contactName, String contactEmail) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    /**
     * Constructor used in ContactsDAO.getAllContactsById(int contactId)
     * @param collectedId
     * @param collectedName
     */
    public Contacts(int collectedId, String collectedName) {
        this.contactId = collectedId;
        this.contactName = collectedName;
    }

    /**
     * getContactId gets the contactId
     * @return contactId
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * setContactId sets the contactId
     * @param contactId
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * toString needed for tableview mishaps
     * @return this.contactName
     */
    public String toString() {
        return this.contactName;
    }
}
