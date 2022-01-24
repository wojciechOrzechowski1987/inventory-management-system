package com.worzech.inventorymanagementsystem.model.vendor;

import com.worzech.inventorymanagementsystem.model.productItem.ProductItemBasicDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class VendorNewAndEditDto {

    private Long id;
    @NotBlank(message = "Pole nie może być puste.")
    @Size(min=3,max=20, message = "Nazwa dostawcy musi się zawierać między 3 a 20 znakami.")
    private String vendorName;
    private Set<ProductItemBasicDto> productItems = new HashSet<>();

}
