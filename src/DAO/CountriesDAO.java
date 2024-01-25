package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;
import model.First_Level_Divisions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountriesDAO {

    //No idea why it gives "model@------" in combobox
    // - FIXED: Added in model.Countries
    //    public String toString() {
    //        return this.country;
    //    }
    public static ObservableList<Countries> getAllCountriesList() {
        ObservableList<Countries> countriesObservableList = FXCollections.observableArrayList();

        try {
            String countries = "SELECT Country_ID, Country FROM countries";
            PreparedStatement getCountry = JDBC.connection.prepareStatement(countries);
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
