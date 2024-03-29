package utility;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.util.ResourceBundle;

/**
 * errorMessages was created to keep almost all Error Alerts into one spot.
 */
public abstract class errorMessages implements Initializable {
    static ResourceBundle languageBundle = ResourceBundle.getBundle("LanguageBundle/language");

    public static void errorCode(int codes) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
            switch (codes) {
                case 1: //Empty username field
                    alert.setTitle(languageBundle.getString("EmptyUsernameField"));
                    alert.setContentText(languageBundle.getString("EmptyUserName"));
                    alert.showAndWait();
                    break;

                case 2: //Empty password field
                    alert.setTitle(languageBundle.getString("EmptyPasswordField"));
                    alert.setContentText((languageBundle.getString("EmptyPassword")));
                    alert.showAndWait();
                    break;

                case 3: //Incorrect username
                    alert.setTitle((languageBundle.getString("IncorrectUsernameAndPassword")));
                    alert.setContentText(languageBundle.getString("wrongUserNameAndPassword"));
                    alert.showAndWait();
                    break;

                case 4:
                    alert.setTitle((languageBundle.getString("EmptyUserAndPassword")));
                    alert.setContentText(languageBundle.getString("EmptyUsernPass"));
                    alert.showAndWait();
                    break;

                case 5: //Add/Modify Customer menu: ALL FIELDS EMPTY
                    alert.setTitle((languageBundle.getString("addModifyCustomerAllEmptyFields")));
                    alert.setContentText(languageBundle.getString("AllEmptyAddModifyCustomers"));
                    alert.showAndWait();
                    break;

                case 6: //Add/Modify Customer menu: Empty Customer Name
                    alert.setTitle((languageBundle.getString("EmptyCustomerNameField")));
                    alert.setContentText(languageBundle.getString("EmptyCustomerName"));
                    alert.showAndWait();
                    break;

                case 7: //Add/Modify Customer menu: Empty Address
                    alert.setTitle((languageBundle.getString("EmptyCustomerAddressField")));
                    alert.setContentText(languageBundle.getString("EmptyCustomerAddress"));
                    alert.showAndWait();
                    break;

                case 8: //Add/Modify Customer menu: Empty Postal Code
                    alert.setTitle((languageBundle.getString("EmptyCustomerPostalCodeField")));
                    alert.setContentText(languageBundle.getString("EmptyCustomerPostalCode"));
                    alert.showAndWait();
                    break;

                case 9: //Add/Modify Customer menu: Empty Phone Number
                    alert.setTitle((languageBundle.getString("EmptyCustomerPhoneNumberField")));
                    alert.setContentText(languageBundle.getString("EmptyCustomerPhoneNumber"));
                    alert.showAndWait();
                    break;

                case 10: //Add/Modify Customer menu: Empty Country Box
                    alert.setTitle((languageBundle.getString("EmptyCustomerCountryBoxField")));
                    alert.setContentText(languageBundle.getString("EmptyCustomerCountryBox"));
                    alert.showAndWait();
                    break;

                case 11: //Add/Modify Customer menu: Empty Division Box
                    alert.setTitle((languageBundle.getString("EmptyCustomerDivisionsBoxField")));
                    alert.setContentText(languageBundle.getString("EmptyCustomerDivisionsBox"));
                    alert.showAndWait();
                    break;

                case 12: //Add/Modify Customer menu: Null Division box
                    alert.setTitle((languageBundle.getString("NullCustomerDivisionsBoxField")));
                    alert.setContentText(languageBundle.getString("NullCustomerDivisionsBox"));
                    alert.showAndWait();
                    break;

                case 13: //customerMenuController onActionDeleteCustomer Method error
                    alert.setTitle((languageBundle.getString("NoCustomerSelected")));
                    alert.setHeaderText((languageBundle.getString("NoCustomerSelectedHeaderText")));
                    alert.setContentText(languageBundle.getString("NoCustomerSelectedContentText"));
                    alert.showAndWait();
                    break;

                case 14: // customerModificationFormController onActionUpdateCustomer/ updateCustomerValidation Incorrect division for country
                    alert.setTitle((languageBundle.getString("IncorrectDivisionPicker")));
                    alert.setContentText(languageBundle.getString("IncorrectDivisionPickerValue"));
                    alert.showAndWait();
                    break;

                case 15: //customerMenuController No customer view table update selection
                    alert.setTitle((languageBundle.getString("PleaseSelectACustomer")));
                    alert.setHeaderText((languageBundle.getString("PleaseSelectACustomerHeader")));
                    alert.setContentText(languageBundle.getString("PleaseSelectACustomerDescription"));
                    alert.showAndWait();
                    break;

                case 16: //Appointment Creation/Modification Forms: Empty Title
                    alert.setTitle((languageBundle.getString("appointmentEmptyTitle")));
                    alert.setContentText(languageBundle.getString("appointmentEmptyTitleDescription"));
                    alert.showAndWait();
                    break;

                case 17: //Appointment Creation/Modification Forms: Empty Description
                    alert.setTitle((languageBundle.getString("appointmentEmptyDescription")));
                    alert.setContentText(languageBundle.getString("appointmentEmptyDescriptionDescription"));
                    alert.showAndWait();
                    break;

                case 18: //Appointment Creation/Modification Forms: Empty Location
                    alert.setTitle((languageBundle.getString("appointmentEmptyLocation")));
                    alert.setContentText(languageBundle.getString("appointmentEmptyLocationDescription"));
                    alert.showAndWait();
                    break;

                case 19: //Appointment Creation/Modification Forms: Empty Type
                    alert.setTitle((languageBundle.getString("appointmentEmptyType")));
                    alert.setContentText(languageBundle.getString("appointmentEmptyTypeDescription"));
                    alert.showAndWait();
                    break;

                case 20: //Appointment Creation/Modification Forms: Empty Start Date
                    alert.setTitle((languageBundle.getString("appointmentEmptyStartDate")));
                    alert.setContentText(languageBundle.getString("appointmentEmptyStartDateDescription"));
                    alert.showAndWait();
                    break;

                case 21: //Appointment Creation/Modification Forms: Empty Start Time
                    alert.setTitle((languageBundle.getString("appointmentEmptyStartTime")));
                    alert.setContentText(languageBundle.getString("appointmentEmptyStartTimeDescription"));
                    alert.showAndWait();
                    break;

                case 22: //Appointment Creation/Modification Forms: Empty End Date
                    alert.setTitle((languageBundle.getString("appointmentEmptyEndDate")));
                    alert.setContentText(languageBundle.getString("appointmentEmptyEndDateDescription"));
                    alert.showAndWait();
                    break;

                case 23: //Appointment Creation/Modification Forms: Empty End Time
                    alert.setTitle((languageBundle.getString("appointmentEmptyEndTime")));
                    alert.setContentText(languageBundle.getString("appointmentEmptyEndTimeDescription"));
                    alert.showAndWait();
                    break;

                case 24: //Appointment Creation/Modification Forms: Empty Customer ComboBox Field
                    alert.setTitle((languageBundle.getString("appointmentEmptyCustomerComboBox")));
                    alert.setContentText(languageBundle.getString("appointmentEmptyCustomerComboBoxDescription"));
                    alert.showAndWait();
                    break;

                case 25: //Appointment Creation/Modification Forms: Empty User ComboBox Field
                    alert.setTitle((languageBundle.getString("appointmentEmptyUserComboBox")));
                    alert.setContentText(languageBundle.getString("appointmentEmptyUserComboBoxDescription"));
                    alert.showAndWait();
                    break;

                case 26: //Appointment Creation/Modification Forms: Empty Contact ComboBox Field
                    alert.setTitle((languageBundle.getString("appointmentEmptyContactComboBox")));
                    alert.setContentText(languageBundle.getString("appointmentEmptyContactComboBoxDescription"));
                    alert.showAndWait();
                    break;

                case 27: //appointmentCreationFormController: Incorrect Times
                    alert.setTitle((languageBundle.getString("appointmentCreationFormControllerIncorrectTimesTitle")));
                    alert.setHeaderText(languageBundle.getString("appointmentCreationFormControllerIncorrectTimesHeaderText"));
                    alert.setContentText(languageBundle.getString("appointmentCreationFormControllerIncorrectTimesContentText"));
                    alert.showAndWait();
                    break;

                case 28: //appointmentCreationFormController: Start and End time cannot be the same
                    alert.setTitle((languageBundle.getString("appointmentCreationFormControllerIncorrectStartEndTimesTitle")));
                    alert.setHeaderText(languageBundle.getString("appointmentCreationFormControllerIncorrectStartEndTimesHeaderText"));
                    alert.setContentText(languageBundle.getString("appointmentCreationFormControllerIncorrectStartEndTimesContentText"));
                    alert.showAndWait();
                    break;

                case 29: //appointmentCreationFormController: Start and End dates cannot be the same
                    alert.setTitle((languageBundle.getString("appointmentCreationFormControllerIncorrectStartEndCANNOTBESAMETimesTitle")));
                    alert.setHeaderText(languageBundle.getString("appointmentCreationFormControllerIncorrectStartEndCANNOTBESAMETimesHeaderText"));
                    alert.setContentText(languageBundle.getString("appointmentCreationFormControllerIncorrectStartEndCANNOTBESAMETimesContentText"));
                    alert.showAndWait();
                    break;

                case 30: //appointmentModificationFormController: onActionModificationAppointment null selection error code
                    alert.setTitle((languageBundle.getString("appointmentModificationFormControllerNullTableSelectionTitle")));
                    alert.setHeaderText(languageBundle.getString("appointmentModificationFormControllerNullTableSelectionHeader"));
                    alert.setContentText(languageBundle.getString("appointmentModificationFormControllerNullTableSelectionContent"));
                    alert.showAndWait();
                    break;

                case 31: //appointmentModificationFormController: onActionDeleteAppointment null selection error code
                    alert.setTitle((languageBundle.getString("appointmentModificationFormControllerNullTableSelectionDeleteTitle")));
                    alert.setHeaderText(languageBundle.getString("appointmentModificationFormControllerNullTableSelectionDeleteHeader"));
                    alert.setContentText(languageBundle.getString("appointmentModificationFormControllerNullTableSelectionDeleteContent"));
                    alert.showAndWait();
                    break;

                case 32: //reportsMenuContactScheduleController onActionComboBoxContacts Null Error
                    alert.setTitle((languageBundle.getString("reportsEmptyComboBox")));
                    alert.setHeaderText((languageBundle.getString("reportsNoContactHeader")));
                    alert.setContentText((languageBundle.getString("reportsNoContactContent")));
                    alert.showAndWait();
                    break;

                case 33: //reportsMenuUsersScheduleController onActionComboBoxUsers() Null Error
                    alert.setTitle((languageBundle.getString("reportsEmptyComboBox")));
                    alert.setHeaderText((languageBundle.getString("reportsNoUserHeader")));
                    alert.setContentText((languageBundle.getString("reportsNoUserContent")));
                    alert.showAndWait();
                    break;
            }
        }

}
