Title: Scheduling Application for Software 2 Course-C195
Purpose of application: Adding, Updating, Deleting Appointments/Customers from a User interface that is linked to a mySQL database.

Author: Jacob Prout
Contact Information:[Student identification number - 000791718]
Application Version: 1.0 [First Attempt]
Current date: 2-20-2024 [February 20th, 2024]

IDE version: IntelliJ IDEA Community Edition 2023.2.2
Full JDK Version: jdk-17.0.8
JavaFX version Compatible with JDK version[SDK]: Oracle OpenJDK version 17.0.8


!!Line 112 to 120 References A3F!!


Directions on how to run the program:
    Starting up the program there will a be a Login Screen.
    There are 2 options for User-Name and Password that will work to log in:
        - The first User-Name and Password is: test
        - The second User-Name and Password that work is: admin
            - These can be written in Lower-case or Upper-case [IT IS NOT CASE-SENSITIVE].
    *If the username/password are incorrect, there will be an alert explaining what's wrong*
        **EVERY LOG-IN ATTEMPT IS RECORDED AND SENT TO "login_activity.txt"**


Appointment Scheduler Menu[Main Menu]:
    * On log in, the user will be prompted with a message letting them know if there are any appointments scheduled within the next 15-Minutes *

    At the top of the menu there are 4 radio buttons: View all, View by Month, View by Week, and View all Customers.
        - View All: Views all the appointments in the database.
        - View by Month: Views all the appointments for the CURRENT month.
        - View by Week : Views all the appointments for the CURRENT week.
        - View all Customers: Opens up the "Customer View List".

    In the middle of the menu there is a table with information regarding the current Appointments in the database:
        - Appointment_ID, Title, Description, Location, Contact, Type, Start Date/Time, End Date/Time, Customer_ID, and User_ID.

    Below the table there are 3 buttons: Add Appointment, Update Appointment, and Delete Appointment:
        - Add Appointment: Opens up the "Appointment Creation Form".
            - This is used to create NEW appointments.

        - Update Appointment: Opens up the "Appointment Modification Form".
            * This only opens up if an Appointment is SELECTED on the table *
            - This is used to modify EXISTING appointments.

        - Delete Appointment:
            * This only works if an Appointment is SELECTED from the table, otherwise it will send a NULL Error *
                - A prompt will appear asking if the selected appointment should be deleted.
                    - If "OK" is selected: The Appointment is deleted.
                    - If "Cancel" is selected: The prompt goes away with no changes to Appointments.


    On the bottom of the menu there are 2 buttons: Reports, and Logout.
        - Reports: Opens up the Reports Menu.
        - Logout: A prompt will appear asking if the user would like to log out.
            - If "OK" is selected: The application will fully close.
            - If "Cancel" is selected: The prompt will go away and the user will be returned to the Appointment Scheduler.


Customer View List:
    At the top of the menu there is button called "Return to Appointment Scheduler".
        - Return to Appointment Scheduler: Clicking this button returns to the "Appointment Scheduler" menu.

    In the middle of the menu there is a table with information regarding the current Customers in the database:
        - Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, and Division_ID.

    Below the table there are 3 buttons: Add Customer, Update Customer, and Delete Customer:
            - Add Customer: Opens up the "Customer Creation Form".
                - This is used to create NEW Customers.

            - Update Appointment: Opens up the "Appointment Modification Form".
                * This only opens up if a Customer is SELECTED on the table *
                - This is used to modify EXISTING Customers.

            - Delete Appointment:
                * This only works if a Customer is SELECTED from the table, otherwise it will send a NULL Error *
                    - A prompt will appear asking if the selected Customer should be deleted.
                        - If the Customer has Existing Appointments, the prompt will inform the user that the Appointments will be deleted with the selected Customer.
                            - If "OK" is selected: The Customer and the associated Appointments will be deleted.
                            - If "Cancel" is selected: The prompt goes away with no changes to Customers or their associated Appointments.

                        - If the Customer does NOT have any existing Appointments, the prompt will inform the user they're about to remove a Customer.
                            - If "OK" is selected: The Customer is deleted.
                            - If "Cancel" is selected: The prompt goes away with no changes to Customers.

    On the bottom of the menu there are 2 buttons: Reports, and Logout.
            - Reports: Opens up the Reports Menu.
            - Logout: A prompt will appear asking if the user would like to log out.
                - If "OK" is selected: The application will fully close.
                - If "Cancel" is selected: The prompt will go away and the user will be returned to the Appointment Scheduler.


Reports Menu:
    On this form there are 4 button options: Appointment Totals by Type and Month, Contact Schedules, Schedules created by Users, and Return to Appointment Scheduler.
        - Appointment Totals by Type and Month Button: Opens up the "Viewing Total amount of Types and Months for Appointments" Form.
            - On this form there are 2 Tables: Appointments by Type Table, and Appointment by Month Table.
                - Appointments by Type Table: This shows how many Total Appointments there are by the Appointment Type.
                - Appointments by Month Table: This shows how many Total Appointments there are by the Appointment Months.

            - On the bottom of the form there is a button called: Return to Reports Menu.
                - Clicking on this button returns the user to "Reports Menu".

        - Contact Schedules Button: Opens up the "Viewing Contact Schedules" Form.
            - On this form there is a comboBox that contains all the "Contacts" in the database and a Table right below it.
                - Selecting a "Contact" will populate the table with all the Appointments associated to that "Contact".
                    - The table will show: Appointment_ID, Title, Type, Description, Start Date/Time, End Date/Time, and Customer_ID.

            - On the bottom of the form there is a button called: Return to Reports Menu.
                - Clicking on this button returns the user to "Reports Menu".

                                **THIS IS THE ADDITIONAL REPORT FOR A3F**
        - Schedules created by Users Button: Opens up the "Viewing User Schedules" Form.
            - On this form there is a comboBox that contains all the "Users" in the database and a Table right below it.
                - Selecting a "User" will populate the table with all the Appointments associated/created to/by that "User".
                    - The table will show: Appointment_ID, Title, Description, Location, Type, Start Date/Time, End Date/Time, Customer_ID, and Contact_ID.

            - On the bottom of the form there is a button called: Return to Reports Menu.
                - Clicking on this button returns the user to "Reports Menu".
                                **END OF ADDITIONAL REPORT FOR A3F**

        - Return to Appointment Scheduler Button:
            - Clicking on this button opens up a prompt called "Closing Reports Menu".
                - If "OK" is selected: The user will return to the "Appointment Scheduler" Form.
                - If "Cancel" is selected: The prompt will go away and the user will stay on the "Reports Menu".


MySql Connector Driver Version Number: mysql-connector-java-8.0.25