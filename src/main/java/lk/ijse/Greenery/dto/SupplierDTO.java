package lk.ijse.Greenery.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class SupplierDTO {

    private String supplierId;
    private String supplierName;
    private String supplerContact;
    private String supplierAddress;
    private String supplierNIC;
    private String description;

}
