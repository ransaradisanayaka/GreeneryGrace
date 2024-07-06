package lk.ijse.Greenery.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class StockDTO {

    private String stockId;
    private String name;
    private String qtyOnHand;
    private String type;
}