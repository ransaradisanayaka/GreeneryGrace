package lk.ijse.Greenery.model.Tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderTm {



    private String orderId;
    private String orderQty;
    private String amount;
    private String description;
    private String customerId;





}