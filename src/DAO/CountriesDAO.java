package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CountriesDAO {

    //No idea if this is right
    public static ObservableList<Countries> getAllCountriesData() {
        ObservableList<Countries> countriesObservableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM countries";
            PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                int countryId = result.getInt("Country_ID");
                String country = result.getString("Country");
                LocalDateTime createDate = result.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = result.getString("Created_By");
                LocalDateTime lastUpdate = result.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdatedBy = result.getString("Last_Updated_By");


                Countries allCountriesData = new Countries(countryId, country, createDate, createdBy, lastUpdate, lastUpdatedBy);
                countriesObservableList.add(allCountriesData);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return countriesObservableList;
    }

}
