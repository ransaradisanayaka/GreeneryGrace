package lk.ijse.Greenery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;





public class CustomerDTO  {

    private String customerId;
    private String customerName;
    private String customerAddress;
    private String customerContact;
    public CustomerDTO(){

    }
    public CustomerDTO(String customerId,String customerName, String customerAddress, String customerContact) {
        this.customerId=customerId;
        this.customerName=customerName;
        this.customerAddress=customerAddress;
        this.customerContact=customerContact;
    }
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String id) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String name) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
    public String getCustomerContact() {
        return customerContact;
    }
    public void setCustomerContact(String customerContact){
        this.customerContact=customerContact;
    }
    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                '}';
    }



}



