package lk.ijse.Greenery.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class StockDTo {

    private String stockId;
    private String name;
    private String qtyOnHand;
    private String type;
}