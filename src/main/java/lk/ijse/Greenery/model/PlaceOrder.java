package lk.ijse.Greenery.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PlaceOrder {

    private String customerId;
    private String productId;
    private String unitPrice;
    private String orderQty;
    private String amount;
    private String description;


}
