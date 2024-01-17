package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class UsersDAO {


    //NO IDEA IF THIS IS EVEN RIGHT!
    public static ObservableList<Users> getAllUsersData() {
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

                Users allUsers = new Users(userId, userName, password, createDate,createBy, lastUpdate, lastUpdatedBy);
                usersObservableList.add(allUsers);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usersObservableList;
    }



}
