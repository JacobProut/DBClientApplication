package model;

import java.time.LocalDateTime;

/**
 * Countries class is created for recording objects into tables/ComboBoxes
 */
public class Countries {
    /**
     * Primary Key Declaration
     */
    private int countryId;

    /**
     * Declarations
     */
    private String country;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;


    public Countries(int countryId, String country, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy) {
        this.countryId = countryId;
        this.country = country;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Constructor used in CountriesDAO.getAllCountriesList() & CountriesDAO.getSelectedCustomerCountry(int)
     * @param countryId
     * @param country
     */
    public Countries(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
    }

    /**
     * getCountryId() gets countryId
     * @return countryId
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * setCountryId(int countryId) sets countryId
     * @param countryId
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * getCountry() gets country
     * @return country
     */
    public String getCountry() {
       return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * getCreateDate() gets createDate
     * @return createDate
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * setCreateDate() sets createDate
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

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * getLastUpdatedBy() get lastUpdatedBy
     * @return lastUpdatedBy
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * setLastUpdatedBy(String lastUpdatedBy) sets lastUpdatedBy
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * toString() needed for tableview mishaps
     * @return this.country
     */
    public String toString() {
        return this.country;
    }

}
