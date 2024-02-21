package model;

import java.time.LocalDateTime;

/**
 * First_Level_Divisions class is mostly created for ComboBoxes and filling in table data
 */
public class First_Level_Divisions {

    /**
     * Primary Key Declaration
     */
    private int divisionId;

    /**
     * Foreign key Declarations
     */
    private int countryId;

    /**
     * Declarations
     */
    private String divisionName;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;

    /**
     * Constructor for First_Level_DivisionsDAO.getAllDivisions()
     * @param divisionId
     * @param divisionName
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param countryId
     */
    public First_Level_Divisions(int divisionId, String divisionName, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy, int countryId) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryId = countryId;
    }

    /**
     * Constructor for First_Level_DivisionDAO.countryToDivision(int)
     * @param divisionId
     * @param divisionName
     * @param countryId
     */
    public First_Level_Divisions(int divisionId, String divisionName, int countryId) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.countryId = countryId;
    }

    /**
     * Constructor for First_Level_Divisions.getSelectedCustomerDivisionsLevel(int)
     * @param selectedDivId
     * @param selectedDivName
     */
    public First_Level_Divisions(int selectedDivId, String selectedDivName) {
        this.divisionId = selectedDivId;
        this.divisionName = selectedDivName;
    }

    /**
     * getCountryId() get countryId
     * @return countryId
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * setCountryId(Int countryId) set countryId
     * @param countryId
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * getDivisionId() gets divisionId
     * @return divisionId
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * getCreateDate() gets createDate
     * @return createDate
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * setCreateDate(LocalDateTime createDate) sets createDate
     * @param createDate
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * getCreatedBy() gets createdBy
     * @return createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * setCreatedBy(String createdBy) sets createdBy
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * getLastUpdatedBy() gets lastUpdatedBy
     * @return lastUpdatedBy
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * setLastUpdatedBy(String lastUpdatedBy)  sets lastUpdatedBy
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * toString() needed for tableview mishaps
     * @return this.divisionName
     */
    public String toString() {
        return this.divisionName;
    }

    //MAYBE be used in a later program version
    /*  public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }*/

}
