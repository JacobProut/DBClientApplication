package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.First_Level_Divisions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;


public class First_Level_DivisionsDAO {

    public static ObservableList<First_Level_Divisions> getAllDivisions() {
        ObservableList<First_Level_Divisions> divisionsObservableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM first_level_divisions";
            PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                int divisionId = result.getInt("Division_ID");
                String division = result.getString("Division");
                LocalDateTime createDate = result.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = result.getString("Created_By");
                LocalDateTime lastUpdate = result.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdatedBy = result.getString("Last_Updated_By");
                int countryId = result.getInt("Country_ID");

                First_Level_Divisions allDivisions = new First_Level_Divisions(divisionId, division, createDate, createdBy, lastUpdate, lastUpdatedBy, countryId);
                divisionsObservableList.add(allDivisions);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return divisionsObservableList;
    }


}
