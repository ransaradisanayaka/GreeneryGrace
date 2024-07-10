package lk.ijse.Greenery.dto;

import java.math.BigDecimal;

public class OrderDetailDTO {/*
    private String orderId;
    private String productId;


    public OrderDetailDTO(String orderId, String productId) {
    }*/ private String orderId;
    private String productId;
    private int qty;
    private BigDecimal unitPrice;

    public OrderDetailDTO(String orderId, String productId, String qty, String unitPrice) {
    }

    public OrderDetailDTO(String productId, int qty, BigDecimal unitPrice) {
        this.productId = productId;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public OrderDetailDTO(String orderId, String productId, int qty, BigDecimal unitPrice) {
        this.orderId = orderId;
        this.productId = productId;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" +
                "productId='" + productId + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
