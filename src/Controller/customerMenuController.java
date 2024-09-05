package Controller;

import DAO.AppointmentsDAO;
import DAO.CustomersDAO;
import DAO.JDBC;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;
import model.Customers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;
import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import static javafx.scene.control.Alert.AlertType.WARNING;
import static javafx.scene.control.ButtonType.CANCEL;
import static javafx.scene.control.ButtonType.OK;
import static utility.errorMessages.errorCode;

/**
 * customerMenuController is the Customer View List Form
 */
public class customerMenuController implements Initializable {
    Stage stage;
    Parent scene;

    /**
     * Label Declarations
     */
    @FXML private Label zoneID;
    @FXML private Label timeLabel;

    /**
     * TableView/Column Declarations
     */
    @FXML private TableView<Customers> customerTableView;
    @FXML private TableColumn<Customers, String> tableColCustomerAddress;
    @FXML private TableColumn<Customers, String> tableColCustomerCreatedBy;
    @FXML private TableColumn<Customers, Timestamp> tableColCustomerCreatedDate;
    @FXML private TableColumn<Customers, Integer> tableColCustomerID;
    @FXML private TableColumn<Customers, Timestamp> tableColCustomerLastUpdated;
    @FXML private TableColumn<Customers, String> tableColCustomerLastUpdatedBy;
    @FXML private TableColumn<Customers, String> tableColCustomerName;
    @FXML private TableColumn<Customers, String> tableColCustomerPhoneNumber;
    @FXML private TableColumn<Customers, Integer> tableColCustomerPostalCode;
    @FXML private TableColumn<Customers, String> tableColCustomerDivisionId;

    /**
     * timeStopped is used for displayCurrentTime()
     */
    private final boolean timeStopped = false;

    /**
     * onActionAddCustomer(ActionEvent) opens up customerCreationForm.fxml to create Customers
     * @param event
     * @throws IOException
     */
    @FXML void onActionAddCustomer(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/customerCreationForm.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
        stage.centerOnScreen();
        stage.setTitle("Customer Creation Page");
        System.out.println("Switching to Customer Creation Form.");
    }


    /**
     * onActionDeleteCustomer(ActionEvent) is the method used to delete customers from the tableview
     *      - If a customer has an appointment, it will warn the user before customer is deleted
     * @param event
     * @throws SQLException
     */
    @FXML void onActionDeleteCustomer(ActionEvent event) throws SQLException {
        ObservableList<Customers> allCustomers = FXCollections.observableArrayList();
        Customers selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            errorCode(13);
            System.out.println("Null Selection while trying to Delete a Customer.");
            return;
        }

        //Used to count amount of appointments
        int numberOfCustomerAppointments = 0;
        int customerId = customerTableView.getSelectionModel().getSelectedItem().getCustomerId();
        String customerName = customerTableView.getSelectionModel().getSelectedItem().getCustomerName();
        ObservableList<Appointments> appointmentsCustomerList = AppointmentsDAO.getAllAppointments();

        for (Appointments appointments : appointmentsCustomerList) {
            int selectedAppointmentCustomerId = appointments.getCustomerId();
            if (selectedAppointmentCustomerId == customerId) {
                numberOfCustomerAppointments++;
            }
        }

        if (numberOfCustomerAppointments == 0) {
            Alert deletion = new Alert(CONFIRMATION);
            deletion.setTitle("Confirm Removal of Customer");
            deletion.setHeaderText("You are about to remove a Customer named [" + customerName + "] with a Customer_ID of [" + customerId + "]!");
            deletion.setContentText("Are you sure you want to continue?\r" + "Click 'OK' to confirm deletion.\r" + "Click 'Cancel' to go back to Customer View Form.");
            deletion.showAndWait();

            if (deletion.getResult() == OK) {
                CustomersDAO.removeCustomerFromTableView(customerTableView.getSelectionModel().getSelectedItem().getCustomerId());
                System.out.println("Selected Customer has been deleted!");
                allCustomers = CustomersDAO.getAllCustomers();
                customerTableView.setItems(allCustomers);
                customerTableView.refresh();
            }
        }

        if (numberOfCustomerAppointments >= 1) {
            Alert appointmentLinked = new Alert(WARNING);
            appointmentLinked.setTitle("Removing Customer with Appointments");
            appointmentLinked.setHeaderText("Attempting to remove a Customer named [" + customerName + "] with a Customer_ID of [" + customerId + "] that has '" + numberOfCustomerAppointments + "' appointment(s)!");
            appointmentLinked.setContentText("Are you sure you want to continue?\r" + "Click 'OK' to confirm deletion of selected Customer and their Appointments.\r" + "Click 'Cancel' to go back to Customer View Form.");
            appointmentLinked.getButtonTypes().add(CANCEL);
            appointmentLinked.showAndWait();

            if (appointmentLinked.getResult() == OK) {
                for (Appointments appointments : appointmentsCustomerList) {
                    if (appointments.getCustomerId() == customerId) {
                        AppointmentsDAO.removeAppointment(appointments.getAppointmentId());
                    }
                }
                CustomersDAO.removeCustomerFromTableView(customerTableView.getSelectionModel().getSelectedItem().getCustomerId());
                System.out.println("Selected Customer and their Appointments have been deleted!");
                allCustomers = CustomersDAO.getAllCustomers();
                customerTableView.setItems(allCustomers);
                customerTableView.refresh();
            }
        }
    }

    /**
     * onActionLogout(ActionEvent) is used to log the user out of the program.
     *      - If the OK button is pressed, the mySQL connection is closed nad the system.exit() command is run
     * @param event
     */
    @FXML void onActionLogout(ActionEvent event) {
        System.out.println("Logout Button Selected.");
        Alert alert = new Alert(CONFIRMATION);
        alert.setTitle("Log out");
        alert.setHeaderText("Attempting to log out\r" + "Any unsaved data will be LOST!");
        alert.setContentText("Are you sure you want to continue?\r" + "Click 'OK' to confirm exit.\r" + "Click 'Cancel' to go back.");

        Optional<ButtonType> confirmation = alert.showAndWait();

        if (confirmation.isPresent() && confirmation.get() == OK) {
            JDBC.closeConnection();
            System.out.println("Shutting down Application.");
            System.exit(0);
        }
        if (confirmation.isPresent() && confirmation.get() == CANCEL) {
            System.out.println("Logout canceled.");
        }
    }

    /**
     * onActionReports(ActionEvent) opens up reportsMenu.fxml to see reports
     * @param event
     * @throws IOException
     */
    @FXML void onActionReports(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/reportsMenu.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
        stage.centerOnScreen();
        stage.setTitle("Reports Menu");
        System.out.println("Opening up Reports Menu");

    }

    /**
     * onActionUpdateCustomer(ActionEvent) uses selected customer from tableView then opens up customerModificationForm.fxml with all the selected customers data put into modification fields
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    //Method used in customerModificationFormController.java  - customerSelection method
    @FXML void onActionUpdateCustomer(ActionEvent event) throws IOException {

       if (customerTableView.getSelectionModel().isEmpty()) {
           errorCode(15);
           System.out.println("Null Selection while trying to Update a Customer.");
       }
       else {
           stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
           FXMLLoader loadupper = new FXMLLoader(getClass().getResource("/view/customerModificationForm.fxml"));
           scene = loadupper.load();
           Customers customerSelection = customerTableView.getSelectionModel().getSelectedItem();
           customerModificationFormController controller = loadupper.getController();
           controller.customerSelection(customerTableView.getSelectionModel().getSelectedIndex(), customerSelection);
           stage.setScene(new Scene(scene));
           stage.show();
           stage.centerOnScreen();
           stage.setTitle("Customer Modification Page");
           System.out.println("Switching to Customer Modification Form.");
       }
    }

    /**
     * radioButtonViewAllCustomers(ActionEvent) just shows that the user is currently on the Customer View List
     * @param event
     * @throws IOException
     */
    @FXML void radioButtonViewAllCustomers(ActionEvent event) throws IOException {
        stage = (Stage) ((RadioButton)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/customerMenu.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
        stage.setTitle("Customer View List");

        customerTableView.setPlaceholder(new Label("There are no customers in the database"));
        System.out.println("Viewing All Customers");
    }

    @FXML
    void radioButtonViewAllOutOfState(ActionEvent event) throws SQLException {
        customerTableView.setItems(CustomersDAO.getAllCustomersOutsideUSA());

        customerTableView.setPlaceholder(new Label("There are no customers outside of the USA"));
        System.out.println("Viewing Customers Located outside of the USA");
    }

    @FXML
    void radioButtonViewAllUSA(ActionEvent event) throws SQLException {
        customerTableView.setItems(CustomersDAO.getAllCustomersInUSA(1));

        customerTableView.setPlaceholder(new Label("There are no customers in the USA Region"));
        System.out.println("Viewing Customers Located inside of the USA");
    }

    /**
     * onActionReturnToAppointmentScheduler(ActionEvent) opens up mainMenu.fxml to see Appointment Scheduler
     * @param event
     * @throws IOException
     */
    @FXML void onActionReturnToAppointmentScheduler(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/mainMenu.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
        stage.centerOnScreen();
        stage.setTitle("Appointment Scheduler");
        System.out.println("Returning to Appointment Scheduler.");
    }

    /**
     * onActionReturnToAppointmentScheduler(ActionEvent) opens up customerMenu.fxml to see Customer View List
     * Used in customerCreationFormController.onActionSaveButton(ActionEvent) & customerModificationFormController.onActionUpdateCustomerButton(ActionEvent)
     * @param event
     * @throws IOException
     */
    public static void returnToCustomerAppointments(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(customerMenuController.class.getResource("/view/customerMenu.fxml")));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
        stage.setTitle("Customer View List");
    }

    /**
     * Initialize sets the zoneId and time label to systemDefault timezone and time
     *      - Also populates the Customer TableView
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TimeZone & Time Setters
        zoneID.setText(String.valueOf(ZoneId.systemDefault()));
        timeLabel.setText(displayCurrentTime());

        //Populates TableView
        customerTableView.setItems(CustomersDAO.getAllCustomers());
        tableColCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tableColCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tableColCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        tableColCustomerPostalCode.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        tableColCustomerPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("customerPhoneNumber"));
        tableColCustomerCreatedDate.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        tableColCustomerCreatedBy.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        tableColCustomerLastUpdated.setCellValueFactory(new PropertyValueFactory<>("lastUpdated"));
        tableColCustomerLastUpdatedBy.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        tableColCustomerDivisionId.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
    }


    /**
     * displayCurrentTime() is a Lambda expression used to display systemDefaults time
     * "Lambda newThread(() ->"
     * "Lambda Platform.runLater(()-> "
     * @return null
     */
    private String displayCurrentTime() {
        Thread currentTime = new Thread(() -> {
            SimpleDateFormat simpleFormat = new SimpleDateFormat("hh:mm:ss a");

            while(!timeStopped) {
                try {
                    sleep(1);
                    //Needed this for time to render properly.
                }
                catch (Exception e) {
                    System.out.println("Cannot Display time!");
                    throw new RuntimeException(e);
                }
                final String showCurrentTime = simpleFormat.format(new Date());
                Platform.runLater(()-> timeLabel.setText(showCurrentTime));
            }
        });
        currentTime.start();
        return null;
    }
}
