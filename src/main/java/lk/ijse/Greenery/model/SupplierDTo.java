package lk.ijse.Greenery.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class SupplierDTo {

    private String supplierId;
    private String supplierName;
    private String supplerContact;
    private String supplierAddress;
    private String supplierNIC;
    private String description;

}
