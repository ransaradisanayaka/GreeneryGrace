package lk.ijse.Greenery.entity;


public class CustomEntity {
    //customer
    private String customerId;
    private String customerName;
    private String customerAddress;
private String customerContact;
//employee
private String EmployeeId;
    private String EmployeeName;
    private String EmployeeAddress;
    private String EmployeeContact;
    private String description;
    private String employeeNIC;
    //order
    private String orderId;
    private String orderQty;
    private String amount;
    //orderDetail

//payment
private String paymentId;
    private String paymentMethod;

    private String date;
    //product
    private String productId;
    private String productName;
    private String qty;
  //  private String description;
    private  String unitPrice;
    //stock
    private String stockId;
    private String name;
    private String qtyOnHand;
    private String type;
    //supplier

    private String supplierId;
    private String supplierName;
    private String supplerContact;
    private String supplierAddress;
    private String supplierNIC;
   // private String description;
    //user

    private String userId;
    private String userName;
    private String passWord;
    public CustomEntity(){

    }
    public CustomEntity(String customerId,String customerName,String customerAddress,String customerContact,String EmployeeId,String EmployeeName,String EmployeeAddress,String EmployeeContact,String description, String employeeNIC,String orderId, String orderQty, String amount, String paymentId,String paymentMethod,String date,String productId, String productName,String qty, String unitPrice, String stockId,String name, String qtyOnHand,String type, String supplierId,String supplierName, String supplerContact,String supplierAddress, String supplierNIC, String userId, String userName,String passWord){
        this.customerId=customerId;
        this.customerName=customerName;
        this.customerContact=customerContact;
        this.customerAddress=customerAddress;
        this.EmployeeId=EmployeeId;
        this.EmployeeName=EmployeeName;
        this.EmployeeAddress=EmployeeAddress;
        this.EmployeeContact=EmployeeContact;

    }
    public String getCustomerId() {
        return customerId;
    }
}

