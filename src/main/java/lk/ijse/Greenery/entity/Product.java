package lk.ijse.Greenery.entity;

public class Product {
    private String productId;
    private String productName;
    private String qty;
    private String description;
    private String unitPrice;

    public Product() {
    }

    public Product(String productId, String productName, String qty, String description, String unitPrice) {
        this.productId = productId;
        this.productName = productName;
        this.qty = qty;
        this.description = description;
        this.unitPrice = unitPrice;


    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }
@Override
public String toString(){
    return "Product{" +
            "productId='" + productId + '\'' +
            ", productName='" + productName + '\'' +
            ", qty='" + qty + '\'' +
            ", description='" + description + '\'' +
            ", unitPrice='" + unitPrice + '\'' +
            '}';

}}