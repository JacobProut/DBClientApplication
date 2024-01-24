package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountriesDAO {

    //No idea why it gives "model@------" in combobox
    public static ObservableList<Countries> getAllCountries() {
        ObservableList<Countries> countriesObservableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Country_ID, Country FROM countries";
            PreparedStatement getCountry = JDBC.connection.prepareStatement(sql);
            ResultSet result = getCountry.executeQuery();

            while (result.next()) {
                int countryId = result.getInt("Country_ID");
                String countryName = result.getString("Country");
                Countries allCountriesData = new Countries(countryId, countryName);
                countriesObservableList.add(allCountriesData);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return countriesObservableList;
    }


}
