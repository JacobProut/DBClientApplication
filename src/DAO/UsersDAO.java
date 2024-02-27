package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static DAO.JDBC.createConnection;

/**
 * UsersDAO contains all User Methods to communicate with the MySQL database.
 */
public class UsersDAO {
    /**
     * getAllUsers() used in appointmentCreationFormController. Initialize to set userComboBox.
     *  - Also used in appointmentModification. Initialize to set userComboBox.
     *  - Also used in reportsMenuUsersScheduleController. Initialize to set a userComboBox
     * @return usersObservableList
     */
    public static ObservableList<Users> getAllUsers() {
        Connection connection;
        ObservableList<Users> usersObservableList = FXCollections.observableArrayList();
        try {
            connection = JDBC.createConnection();
            String users = "SELECT User_ID, User_Name FROM users;";
            ResultSet result = connection.createStatement().executeQuery(users);
            while(result.next()) {
                Users allUsers = new Users(result.getInt("User_ID"), result.getString("User_Name"));
                usersObservableList.add(allUsers);
            }
            return usersObservableList;
        }
        catch (SQLException e) {
            System.out.println("Error with getting ALL Users");
            throw new RuntimeException(e);
        }
    }

    /**
     * getAllUsersById(String userId) used for appointmentModificationFormController.appointmentSelection to set the userComboBox
     * @param userId
     * @return new Users(collectedId, collectedName) or null
     * @throws SQLException
     */
    public static Users getAllUsersById(int userId) throws SQLException {
        try {
            String getUserId = "SELECT * FROM users WHERE User_ID = ?";
            PreparedStatement selectedUserId = createConnection().prepareStatement(getUserId);

            selectedUserId.setInt(1, userId);
            selectedUserId.execute();

            ResultSet results = selectedUserId.executeQuery();

            while (results.next()) {
                int collectedId = results.getInt("User_ID");
                String collectedName = results.getString("User_Name");

                return new Users(collectedId, collectedName);
            }
        }
        catch (SQLException e) {
            System.out.println("Error with getting ALL Users by ID");
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * getUserNameById() was created for loginScreenForm.onActionLogin(ActionEvent event) to be used in reportsMenuUsersScheduleController.onActionComboBoxUsers(Action Event)
     * This was a way of collecting the username for reportsMenuUsersScheduleController.onActionComboBoxUsers(Action Event)
     * @param userName
     * @return selectedUserId
     * @throws SQLException
     */
    public static int getUsersNameById(String userName) throws SQLException {
            String getIdByNam = "SELECT * FROM users WHERE User_Name = ?";
            PreparedStatement selectedUserName = createConnection().prepareStatement(getIdByNam);

            selectedUserName.setString(1, userName);

            ResultSet resultSet = selectedUserName.executeQuery();

            int selectedUserId = 0;
            while (resultSet.next()) {
                selectedUserId= resultSet.getInt("User_ID");
            }
            return selectedUserId;
    }

    /**
     * verifyLoginInformation() was created for loginScreenForm.onActionLogin(ActionEvent event) && loginScreenForm.loginInfoValidation()
     * Method was created to send error messages for blank, empty User_Name & Password fields
     * @param username
     * @param password
     * @return true/false
     * @throws SQLException
     */
    public static boolean verifyLoginInformation(String username, String password) throws SQLException {
        String checkingLogin = "SELECT * FROM users WHERE User_Name=? AND Password=?";
        PreparedStatement verifyLogin = JDBC.createConnection().prepareStatement(checkingLogin);
        verifyLogin.setString(1, username);
        verifyLogin.setString(2, password);
        try {
            verifyLogin.execute();
            ResultSet result = verifyLogin.getResultSet();
            return (result.next());
        }
        catch (Exception e) {
            System.out.println("ERROR OCCURRED: " + e.getMessage());
            return false;
        }
    }
}
