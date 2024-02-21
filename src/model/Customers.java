package model;
import java.time.LocalDateTime;

/**
 * Customers class is created for recording objects into tables/ComboBoxes
 */
public class Customers {

    /**
     * Primary Key Declaration
     */
    private int customerId;


    /**
     * Foreign Key Declaration
     */
    private int divisionId;
    /**
     * Foreign Key Declaration
     */
    private int countryId;


    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhoneNumber;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;


    /**
     * Constructor used in CustomersDao.getAllCustomers()
     * @param customerId
     * @param customerName
     * @param customerAddress
     * @param customerPostalCode
     * @param customerPhoneNumber
     * @param createDate
     * @param createdBy
     * @param lastUpdated
     * @param lastUpdatedBy
     * @param divisionId
     * @param countryId
     */
    public Customers(int customerId, String customerName, String customerAddress, String customerPostalCode, String customerPhoneNumber, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdated, String lastUpdatedBy, int divisionId, int countryId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhoneNumber = customerPhoneNumber;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionId = divisionId;
        this.countryId = countryId;
    }

    /**
     * Constructor used in CustomerDAO.getAllCustomersById(int)
     * @param collectedId
     * @param collectedName
     */
    public Customers(int collectedId, String collectedName) {
        this.customerId = collectedId;
        this.customerName = collectedName;
    }

    /**
     * getDivisionId() gets divisionId
     * @return divisionId
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * setDivisionId() sets divisionId
     * @param divisionId
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * getCountryId() gets the countryId
     * @return countryId
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * setCountryId(int countryId) sets the countryId.
     * @param countryId
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * getCustomerId() gets the customerId
     * @return customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * setCustomerId(int customerId) set the customerId
     * @param customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * getCustomerName() gets the customerName
     * @return customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * getCustomerAddress() gets customerAddress
     * @return customerAddress
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * getCustomerPostalCode() gets the customerPostalCode
     * @return customerPostalCode
     */
    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    /**
     * getCustomerPhoneNumber() gets the customerPhoneNumber
     * @return customerPhoneNumber
     */
    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    /**
     * getCreateDate() gets the createDate
     * @return createDate
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * setCreateDate() sets the createDate
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
     * setCreatedBy() sets createdBy
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * getLastUpdated() gets lastUpdated
     * !!Shows it has no usages but when I delete it, the customer table no longer works?!!
     * @return lastUpdated
     */
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    /**
     * setLastUpdated() sets lastUpdated
     * !!Shows it has no usages but when I delete it, the customer table no longer works?!!
     * @param lastUpdated
     */
    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * getLastUpdatedBy() gets lastUpdatedBy
     * @return lastUpdateBy
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
     * @return this.customerName
     */
    public String toString() {
        return this.customerName;
    }

}