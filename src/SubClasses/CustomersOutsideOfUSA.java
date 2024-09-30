package SubClasses;

import model.Customers;

import java.time.LocalDateTime;

public class CustomersOutsideOfUSA extends Customers {

    public CustomersOutsideOfUSA(int customerId, String customerName, String customerAddress, String customerPostalCode, String customerPhoneNumber, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdated, String lastUpdatedBy, int divisionId, int countryId) {
        super(customerId, customerName, customerAddress, customerPostalCode, customerPhoneNumber, createDate, createdBy, lastUpdated, lastUpdatedBy, divisionId, countryId);
    }

    @Override
    public boolean isInUSA() {
        return false;
    }

    @Override
    public boolean outOfUSA() {
        return true;
    }

    @Override
    public String toString() {
        return "Customer in USA: " + customerName;
    }
}
