package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.First_Level_Divisions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static DAO.JDBC.createConnection;

/**
 * First_Level_DivisionsDAO contains all First_Level_Division Methods to communicate with the MySQL database.
 */
public class First_Level_DivisionsDAO {

    /**
     * getAllDivisions() gets all Divisions Data
     * @return divisionsObservableList
     */
    public static ObservableList<First_Level_Divisions> getAllDivisions() {
        ObservableList<First_Level_Divisions> divisionsObservableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM first_level_divisions";
            PreparedStatement preparedStatement = createConnection().prepareStatement(sql);
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
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return divisionsObservableList;
    }

    /**
     * countryToDivision(int countryId) is used in customerCreationFormController.onActionCountryPicker(ActionEvent) & customerModificationCountryPicker(ActionEvent) to fill a comboBox.
     * Also used in customerModificationCountryPicker.customerSelection
     *
     * @param countryId
     * @return countryToDivisionComboBoxes
     */
    public static ObservableList<First_Level_Divisions> countryToDivision(int countryId) {
        ObservableList<First_Level_Divisions> countryToDivisionComboBoxes = FXCollections.observableArrayList();

        try {
            String countryDivision = "SELECT * FROM first_level_divisions WHERE Country_ID = " + countryId;
            PreparedStatement preparedStatement = createConnection().prepareStatement(countryDivision);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                int divisionId = result.getInt("Division_ID");
                String divisionName = result.getString("Division");
                countryId = result.getInt("Country_ID");

                First_Level_Divisions countryDivisionList = new First_Level_Divisions(divisionId, divisionName, countryId);
                countryToDivisionComboBoxes.add(countryDivisionList);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return countryToDivisionComboBoxes;
    }

    /**
     * getSelectedCustomerDivisionLevel(int divisionId) used in customerModificationFormController.customerSelection
     * @param divisionId
     * @return First_Level_Divisions(selectedDivId, selectedDivName)
     */
    public static First_Level_Divisions getSelectedCustomerDivisionLevel(int divisionId) {

        try {
            String selectedCustomerDivisionId = "SELECT Division_ID, Division FROM first_level_divisions WHERE Division_ID = ?";
            PreparedStatement selectedDivision = createConnection().prepareStatement(selectedCustomerDivisionId);

            selectedDivision.setInt(1, divisionId);
            selectedDivision.execute();

            ResultSet selectedResults = selectedDivision.getResultSet();
            selectedResults.next();

            int selectedDivId = selectedResults.getInt("Division_ID");
            String selectedDivName = selectedResults.getString("Division");
            return new First_Level_Divisions(selectedDivId, selectedDivName);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
