package lk.ijse.Greenery.model.Tm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class OrderDetailTm {
    private String productId;
    private String qty;
    private String unitPrice;

    private String description;
    private BigDecimal total;

    public OrderDetailTm(String productId, String qty, String description, BigDecimal unitPrice, BigDecimal total) {
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }


}
