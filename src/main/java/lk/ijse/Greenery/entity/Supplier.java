package lk.ijse.Greenery.entity;

public class Supplier {
    private String supplierId;
    private String supplierName;
    private String supplerContact;
    private String supplierAddress;
    private String supplierNIC;
    private String description;
    public Supplier(){}
    public Supplier(String supplierId,String supplierName,String supplerContact,String supplierAddress,String supplierNIC,String description){
        this.supplierId=supplierId;
        this.supplierName=supplierName;
        this.supplerContact=supplerContact;
        this.supplierAddress=supplierAddress;
        this.supplierNIC=supplierNIC;
        this.description=description;
    }
    public String getSupplierId(){
        return supplierId;
    }
    public void setSupplierId(String supplierId){
        this.supplierId=supplierId;


    }
    public String getSupplierName(){
        return supplierName;
    }
    public void setSupplierName(String supplierName){
        this.supplierName=supplierName;
    }
    public String getSupplierContact(){
        return supplerContact;
    }
    public void setSupplierContact(String supplerContact) {
        this.supplerContact=supplerContact;}
    public String getSupplierAddress(){
        return supplierAddress;
    } public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }
    public String getSupplierNIC(){
        return supplierNIC;
    }
    public void setSupplierNIC(String description){
        this.supplierNIC=supplierNIC;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description=description;
    }

  @Override
  public String toString(){
       return "Supplier{" +
               "supplierId='" + supplierId+ '\'' +
               ",supplierName='" + supplierName + '\'' +
               ",  supplerContact='" + supplerContact + '\'' +
               ", supplierAddress='" + supplierAddress + '\'' +
               ", description='" + description + '\'' +
               ",  supplierNIC='" + supplierNIC + '\'' +
               '}';

   } }
