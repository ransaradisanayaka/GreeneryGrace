
package lk.ijse.Greenery.model.Tm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class StockTm {
    private String stockId;
    private String name;
    private String qtyOnHand;
    private String type;


}