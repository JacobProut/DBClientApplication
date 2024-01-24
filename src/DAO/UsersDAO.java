package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class UsersDAO {

    //Not sure if ill need this in the future or not DONT DELETE UNTIL FORSURE
    /*public static ObservableList<Users> getAllUsers() {
        ObservableList<Users> usersObservableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM users";
            PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                int userId = result.getInt("User_ID");
                String userName = result.getString("User_Name");
                String password = result.getString("Password");
                LocalDateTime createDate = result.getTimestamp("Create_Date").toLocalDateTime();
                String createBy = result.getString("Created_By");
                LocalDateTime lastUpdate = result.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdatedBy = result.getString("Last_Updated_By");

                Users allUsers = new Users(userId, userName, password, createDate, createBy, lastUpdate, lastUpdatedBy);
                usersObservableList.add(allUsers);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usersObservableList;
    }*/

    public static ObservableList<Users> getAllUsers() {
        Connection connection;
        ObservableList<Users> usersObservableList = FXCollections.observableArrayList();
        try {
            connection = JDBC.createConnection();
            String sql = "SELECT User_ID, User_Name FROM users;";
            ResultSet result = connection.createStatement().executeQuery(sql);
            while(result.next()) {
                Users allUsers = new Users(result.getInt("User_ID"), result.getString("User_Name"));
                usersObservableList.add(allUsers);
            }
            return usersObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean verifyLoginInformation(String username, String password) throws SQLException {
        String checkingLogin = "SELECT * FROM users WHERE User_Name=? AND Password=?";
        PreparedStatement preparedStatement = JDBC.createConnection().prepareStatement(checkingLogin);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        try {
            preparedStatement.execute();
            ResultSet result = preparedStatement.getResultSet();
            return (result.next());
        } catch (Exception e) {
            System.out.println("ERROR OCCURRED: " + e.getMessage());
            return false;
        }
    }

}
