package lk.ijse.Greenery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data

public class ProductDTo {

        private String productId;
        private String productName;
        private String qty;
        private String description;
        private  String unitPrice;

}
