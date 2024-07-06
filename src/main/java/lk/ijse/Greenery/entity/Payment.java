package lk.ijse.Greenery.entity;

public class Payment {
    private String paymentId;
    private String paymentMethod;
    private String amount;
    private String date;
    private String customerId;
    public Payment(){

    }
    public Payment(String paymentId,String paymentMethod,String amount,String date,String customerId){
     this.paymentId=paymentId;
     this.paymentMethod=paymentMethod;
     this.amount=amount;
     this.date=date;
     this.customerId=customerId;
    }
    public String getPaymentId(){
        return paymentId;
    }
    public void setPaymentId(String paymentId){
        this.paymentId=paymentId;
    }
    public String getPaymentMethod(){
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod){
        this.paymentMethod=paymentMethod;
    }public String getAmount(){
        return amount;
    }
    public void setAmount(String amount){
        this.amount=amount;
    }
    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date=date;
    }
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String id) {
        this.customerId = customerId;
    }
@Override
public String toString(){
    return "Payment{" +
            "paymentId='" + paymentId + '\'' +
            ", paymentMethod='" + paymentMethod + '\'' +
            ", amount='" + amount+ '\'' +
            ", date='" + date + '\'' +
            ", customerId='" + customerId + '\'' +
            '}';

}}
