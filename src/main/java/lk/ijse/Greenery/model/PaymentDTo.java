package lk.ijse.Greenery.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data

    public class PaymentDTo {

        private String paymentId;
        private String paymentMethod;
        private String amount;
        private String date;
        private String customerId;


    }

