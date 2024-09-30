package SubClasses;

import model.Customers;

import java.time.LocalDateTime;

public class CustomersInUSA extends Customers {

    public CustomersInUSA(int customerId, String customerName, String customerAddress, String customerPostalCode, String customerPhoneNumber, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdated, String lastUpdatedBy, int divisionId) {
        super(customerId, customerName, customerAddress, customerPostalCode, customerPhoneNumber, createDate, createdBy, lastUpdated, lastUpdatedBy, divisionId, 1); // countryId is set to 1 for USA
    }

    @Override
    public boolean isInUSA() {
        return true;
    }

    @Override
    public boolean outOfUSA() {
        return false;
    }

    @Override
    public String toString() {
        return "Customer in USA: " + customerName;
    }
}
