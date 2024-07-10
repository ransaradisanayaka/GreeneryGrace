package lk.ijse.Greenery.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Order {
    private String orderId;
    private String orderQty;
    private String amount;
    private String description;
    private String customerId;
    public Order(String orderId, LocalDate orderDate, String customerId, String customerName, BigDecimal orderTotal){

    }
    public Order(String orderId,String orderQty,String amount,String description,String customerId){

    }
  public   String getOrderId(){
        return orderId;
    }
   public void setOrderId(){
        this.orderId=orderId;
   }
    public   String getOrderQty(){
        return orderQty;
    }
    public void setOrderQty(){
        this.orderQty=orderQty;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public   String getAmount(){
        return amount;
    }
    public void setAmount(){
        this.amount=amount;
    } public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String id) {
        this.customerId = customerId;
    }
    @Override
    public String toString(){
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderQty='" + orderQty + '\'' +
                ", amount='" + amount + '\'' +
                ", description='" + description + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';

    }
}
