package lk.ijse.Greenery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrdersDTO {

    private String orderId;
    private String orderQty;
    private String amount;
    private String description;
    private String customerId;


}