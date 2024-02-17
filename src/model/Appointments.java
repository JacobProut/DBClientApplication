package model;

import java.time.LocalDateTime;

public class Appointments {

    //Primary Key
    private int appointmentId;


    private String appointmentTitle;
    private String appointmentDescription;
    private String appointmentLocation;
    private String appointmentType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime appointmentCreationDate;
    private String appointmentCreatedBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int CountTotal;

    //Foreign Keys
    private int customerId;
    private int userId;
    private int contactId;

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

    //Used in AppointmentsDAO - getAppointmentTypeTotal()
    public Appointments(String appointmentType, int CountTotal) {
        this.appointmentType = appointmentType;
        this.CountTotal = CountTotal;
    }

    //Used in AppointmentsDAO - getAppointForContactList()
    public Appointments(int appointmentId, String appointmentTitle, String appointmentType, String appointmentDescription, LocalDateTime startTime, LocalDateTime endTime, int customerId) {
        this.appointmentId = appointmentId;
        this.appointmentTitle = appointmentTitle;
        this.appointmentType = appointmentType;
        this.appointmentDescription = appointmentDescription;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerId = customerId;
    }

    //Needed this getTypeTotal Function so getAppointmentTypeTotal Would work
    public int getTypeCountTotal() {
        return CountTotal;
    }



    //Foreign key functions
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    //End of foreign key functions


    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getAppointmentTitle() {
        return appointmentTitle;
    }

    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }

    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    public String getAppointmentLocation() {
        return appointmentLocation;
    }

    public void setAppointmentLocation(String appointmentLocation) {
        this.appointmentLocation = appointmentLocation;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getAppointmentCreationDate() {
        return appointmentCreationDate;
    }

    public void setAppointmentCreationDate(LocalDateTime appointmentCreationDate) {
        this.appointmentCreationDate = appointmentCreationDate;
    }

    public String getAppointmentCreatedBy() {
        return appointmentCreatedBy;
    }

    public void setAppointmentCreatedBy(String appointmentCreatedBy) {
        this.appointmentCreatedBy = appointmentCreatedBy;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
