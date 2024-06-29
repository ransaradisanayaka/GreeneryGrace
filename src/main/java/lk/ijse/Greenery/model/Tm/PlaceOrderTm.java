package lk.ijse.Greenery.model.Tm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PlaceOrderTm {
    private String productId;
    private String qty;
    private String unitPrice;
    private String amount;
    private String description;
    private String customerId;


}
