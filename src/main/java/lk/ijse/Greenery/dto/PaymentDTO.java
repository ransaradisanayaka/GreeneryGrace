package lk.ijse.Greenery.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data

    public class PaymentDTO {

        private String paymentId;
        private String paymentMethod;
        private String amount;
        private String date;
        private String customerId;


    }

