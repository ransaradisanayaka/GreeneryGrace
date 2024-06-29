package lk.ijse.Greenery.model.Tm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data

    public class PaymentTm {
        private String paymentId;
        private String paymentMethod;
        private String amount;
        private String date;
        private String customerId;

    }

