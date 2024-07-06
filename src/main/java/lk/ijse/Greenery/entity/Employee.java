package lk.ijse.Greenery.entity;

import java.math.BigDecimal;

public class Employee {
    private String EmployeeId;
    private String EmployeeName;
    private String EmployeeAddress;
    private String EmployeeContact;
    private String description;
    private String employeeNIC;

    public Employee() {

    }

    public Employee(String EmployeeId, String EmployeeName, String EmployeeAddress, String EmployeeContact, String description, String employeeNIC) {

    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(String EmployeeId) {

        this.EmployeeId = EmployeeId;
    }

    public String getEmployeeName() {

        return EmployeeName;
    }

    public void setEmployeeName(String EmployeeName) {
        this.EmployeeName = EmployeeName;
    }

    public String getEmployeeAddress() {

        return EmployeeAddress;
    }

    public void setEmployeeAddress(String EmployeeAddress) {
        this.EmployeeAddress = EmployeeAddress;
    }

    public String getEmployeeContact() {

        return EmployeeContact;
    }

    public void setEmployeeContact(String EmployeeContact) {
        this.EmployeeContact = EmployeeContact;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmployeeNIC() {

        return employeeNIC;
    }

    public void setEmployeeNIC(String EmployeeNIC) {
        this.employeeNIC = EmployeeNIC;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "EmployeeId='" + EmployeeId + '\'' +
                ", EmployeeName='" + EmployeeName + '\'' +
                ", EmployeeAddress='" + EmployeeAddress + '\'' +
                ", EmployeeContact='" + EmployeeContact + '\'' +
                ", description='" + description + '\'' +
                ", EmployeeNIC='" + employeeNIC + '\'' +
                '}';

    }
}