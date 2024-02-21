package model;

import java.time.LocalDateTime;

/**
 * Appointments class is created for recording objects into tables called Appointments
 */
public class Appointments {

    /**
     * Primary key Declarations
     */
    private int appointmentId;
    private String appointmentTitle;
    private String appointmentDescription;
    private String appointmentLocation;
    private String appointmentType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String lastUpdatedBy;
    private int CountTotal;

    /**
     * Foreign key Declarations
     */
    //Foreign Keys
    private int customerId;
    private int userId;
    private int contactId;

    /**
     * Constructor used in appointmentModificationFormController.onActionModificationAppointment()
     * @param appointmentId
     * @param appointmentTitle
     * @param appointmentDescription
     * @param appointmentLocation
     * @param appointmentType
     * @param startTime
     * @param endTime
     * @param customerId
     * @param userId
     * @param contactId
     */
    //Do you need foreign keys in here?
    public Appointments(int appointmentId, String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType, LocalDateTime startTime, LocalDateTime endTime, int customerId, int userId, int contactId) {
        this.appointmentId = appointmentId;
        this.appointmentTitle = appointmentTitle;
        this.appointmentDescription = appointmentDescription;
        this.appointmentLocation = appointmentLocation;
        this.appointmentType = appointmentType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    /**
     * Constructor used in mainMenuController Populating table
     * @param appointmentId
     * @param appointmentTitle
     * @param appointmentDescription
     * @param appointmentLocation
     * @param contactId
     * @param appointmentType
     * @param startTime
     * @param endTime
     * @param customerId
     * @param userId
     */
    public Appointments(int appointmentId, String appointmentTitle, String appointmentDescription, String appointmentLocation, int contactId, String appointmentType, LocalDateTime startTime, LocalDateTime endTime, int customerId, int userId) {
        this.appointmentId = appointmentId;
        this.appointmentTitle = appointmentTitle;
        this.appointmentDescription = appointmentDescription;
        this.appointmentLocation = appointmentLocation;
        this.contactId = contactId;
        this.appointmentType = appointmentType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerId = customerId;
        this.userId = userId;
    }

    /**
     * Constructor used in AppointmentsDAO.getAppointForContactList()
     * @param appointmentId
     * @param appointmentTitle
     * @param appointmentType
     * @param appointmentDescription
     * @param startTime
     * @param endTime
     * @param customerId
     */
    public Appointments(int appointmentId, String appointmentTitle, String appointmentType, String appointmentDescription, LocalDateTime startTime, LocalDateTime endTime, int customerId) {
        this.appointmentId = appointmentId;
        this.appointmentTitle = appointmentTitle;
        this.appointmentType = appointmentType;
        this.appointmentDescription = appointmentDescription;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerId = customerId;
    }

    /**
     * Constructor used for AppointmentsDAO.getAppointmentForUserList()
     * @param appointmentId
     * @param appointmentTitle
     * @param appointmentDescription
     * @param appointmentType
     * @param appointmentLocation
     * @param startTime
     * @param endTime
     * @param customerId
     * @param contactId
     */
    public Appointments(int appointmentId, String appointmentTitle, String appointmentDescription, String appointmentType, String appointmentLocation, LocalDateTime startTime, LocalDateTime endTime, int customerId, int contactId) {
        this.appointmentId = appointmentId;
        this.appointmentTitle = appointmentTitle;
        this.appointmentDescription = appointmentDescription;
        this.appointmentType = appointmentType;
        this.appointmentLocation = appointmentLocation;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerId = customerId;
        this.contactId = contactId;
    }

    /**
     * Constructor used in AppointmentsDAO.getAppointmentTypeTotal()
     * @param appointmentType
     * @param CountTotal
     */
    //Used in AppointmentsDAO - getAppointmentTypeTotal()
    public Appointments(String appointmentType, int CountTotal) {
        this.appointmentType = appointmentType;
        this.CountTotal = CountTotal;
    }

    /**
     * Needed this getTypeTotal Function so getAppointmentTypeTotal Would work
     * @return CountTotal
     */
    public int getTypeCountTotal() {
        return CountTotal;
    }


    //Foreign key functions
    /**
     * getCustomerId() gets the customerId
     * @return customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * setCustomerId sets the customerId
     * @param customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * getUserId() gets the userId
     * @return userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * setUserId sets the userId
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
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
    //End of foreign key functions


    /**
     * getAppointmentId gets the appointmentId
     * @return appointmentId
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * getAppointmentTitle gets the appointmentTitle
     * @return appointmentTitle
     */
    public String getAppointmentTitle() {
        return appointmentTitle;
    }

    /**
     * getAppointmentDescription gets the appointmentDescription
     * @return appointmentDescription
     */
    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    /**
     * getAppointmentLocation gets the appointmentLocation
     * @return appointmentLocation
     */
    public String getAppointmentLocation() {
        return appointmentLocation;
    }

    /**
     * getAppointmentType gets the appointmentType
     * @return appointmentType
     */
    public String getAppointmentType() {
        return appointmentType;
    }

    /**
     * getStartTime gets the startTime
     * @return startTime
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * setStartTime sets the startTime
     * @param startTime
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * getEndTime gets the endTime
     * @return endTime
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * getLastUpdatedBy gets the lastUpdatedBy
     * @return lastUpdatedBy
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * setLastUpdatedBy sets the lastUpdatedBy
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
