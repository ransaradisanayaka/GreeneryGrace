package lk.ijse.Greenery.model.Tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public class ProductTm {

        private String productId;
        private String productName;
        private String qty;
        private String description;
        private  String unitPrice;


        public ProductTm(String productId, String productName, int qty, String description, String unitPrice) {
        }
    }


