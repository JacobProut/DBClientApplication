package model;

import java.time.LocalDateTime;

/**
 * Users class is created for recording objects into tables/comboBoxs and more.
 */
public class Users {

    /**
     * Primary key Declaration
     */
    private int userId;

    /**
     * String Declarations
     */
    private String userName;
    private String password;
    private String createdBy;
    private String lastUpdatedBy;

    /**
     * LocalDateTime Declarations
     */
    private LocalDateTime lastUpdate;
    private LocalDateTime createDate;

    /**
     * Constructor for getting all user information
     * @param userId
     * @param userName
     * @param password
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     */
    public Users(int userId, String userName, String password, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * getUserId() gets userId
     * @return userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * setUserId(int userid) sets the userId based off an Int Value.
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * getPassword() gets password
     * @return password;
     */
    public String getPassword() {
        return password;
    }

    /**
     * setPassword(String password) sets password based off of a String Value.
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * getCreateDate() gets createDate
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
     * setCreatedBy(String createdBy) sets createdBy based off of a String Value.
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
     * setLastUpdatedBy(String lastUpdatedBy) sets lastUpdatedBy based off of a String Value.
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Users(String UserName) sets a String userName to this userName
     * @param userName
     */
    public Users(String userName) {
        this.userName = userName;
    }

    /**
     * Constructor used in UsersDAO.getAllUsers() & UserDAO.getAllUsersById(id)
     * @param userId
     * @param userName
     */
    public Users(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    /**
     * toString() needed for tableview mishaps
     * @return this.contactName
     */
    public String toString() {
        return this.userName;
    }
}