package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static DAO.JDBC.createConnection;

/**
 * CountriesDAO contains all CountriesDAO Methods to communicate with the MySQL database.
 */
public class CountriesDAO {

    /**
     * getAllCountriesList() is used in customerCreationFormController and customerModificationFormController. Initialize countryPicker comboBox
     * @return countriesObservableList
     *
     * ERROR I occurred:
     * //No idea why it gives "model@------" in comboBox
     *     // - FIXED: Added in model.Countries
     *     //    public String toString() {
     *     //        return this.country;
     *     //    }
     */
    public static ObservableList<Countries> getAllCountriesList() {
        ObservableList<Countries> countriesObservableList = FXCollections.observableArrayList();

        try {
            String countries = "SELECT Country_ID, Country FROM countries";
            PreparedStatement getCountry = createConnection().prepareStatement(countries);
            ResultSet result = getCountry.executeQuery();

            while (result.next()) {
                int countryId = result.getInt("Country_ID");
                String countryName = result.getString("Country");
                Countries allCountriesData = new Countries(countryId, countryName);
                countriesObservableList.add(allCountriesData);
            }
        }
        catch (SQLException e) {
            System.out.println("Error with getting ALL Countries for list");
            throw new RuntimeException(e);
        }
        return countriesObservableList;
    }

    /**
     * getSelectedCustomerCountry(int countryId) is used in customerModificationFormController.customerSelection
     * @param countryId
     * @return new Countries(selectedCountryId, selectedCountryName)
     */
    //Method used in customerModificationFormController.java  - customerSelection method
    public static Countries getSelectedCustomerCountry(int countryId) {
        try {
            String selectedCustomerCountryId = "SELECT Country_ID, Country FROM countries WHERE Country_ID = ?";
            PreparedStatement selectedCountry = createConnection().prepareStatement(selectedCustomerCountryId);

            selectedCountry.setInt(1, countryId);
            selectedCountry.execute();

            ResultSet selectedResults = selectedCountry.getResultSet();
            selectedResults.next();

            int selectedCountryId = selectedResults.getInt("Country_ID");
            String selectedCountryName = selectedResults.getString("Country");
            return new Countries(selectedCountryId, selectedCountryName);
        }
        catch (SQLException e) {
            System.out.println("Error with getting SELECTED Customer Country");
            throw new RuntimeException(e);
        }
    }
}
