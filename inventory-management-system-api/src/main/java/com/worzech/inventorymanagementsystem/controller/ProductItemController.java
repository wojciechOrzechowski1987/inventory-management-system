package com.worzech.inventorymanagementsystem.controller;

import com.worzech.inventorymanagementsystem.model.productItem.ProductItemBasicDto;
import com.worzech.inventorymanagementsystem.model.productItem.ProductItemNewAndEditDto;
import com.worzech.inventorymanagementsystem.service.productItem.ProductItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "productItem", produces = "application/json")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ProductItemController {

    private final ProductItemService productItemService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ProductItemNewAndEditDto> getAllProductItems() {
        return productItemService.getAllProductItems();
    }

    @GetMapping("/basic")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ProductItemBasicDto> getAllProductItemsBasic() {
        return productItemService.getAllProductItemsBasic();
    }

    @GetMapping(path = "/noPopcMaterial")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ProductItemBasicDto> getProductItemsNotAssignedToPopcMaterial() {
        return productItemService.getProductItemsNotAssignedToPopcMaterial();
    }

    @GetMapping(path = "/notAssigned")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ProductItemBasicDto> getNotAssignedProductItems() {
        return productItemService.getNotAssignedProductItems();
    }

    @GetMapping("/{id}/productItemsForEdit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ProductItemNewAndEditDto> productItemsForVendorEdit(@PathVariable Long id) {
        return productItemService.getProductItemsForVendorEdit(id);
    }

    @GetMapping("/{id}/productItemsForPopcMaterialEdit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ProductItemBasicDto> productItemsForPopcMaterialEdit(@PathVariable Long id) {
        return productItemService.getProductItemsForPopcMaterialEdit(id);
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public ProductItemNewAndEditDto findProductItemById(@PathVariable Long id) {
        return productItemService.findProductItemById(id);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteProductItem(@PathVariable("id") Long id) {
        productItemService.deleteProductItemById(id);
    }


    @PostMapping(path = "/newProductItem", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ProductItemNewAndEditDto createNewProductItem(@Valid @RequestBody ProductItemNewAndEditDto productItemNewAndEditDto) {
        return productItemService.createNewProductItem(productItemNewAndEditDto);
    }

    @PutMapping(path = "/editProductItem/{id}", consumes = "application/json")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ProductItemNewAndEditDto editProductItem(@PathVariable Long id, @Valid @RequestBody ProductItemNewAndEditDto productItemNewAndEditDto) {
        return productItemService.updateProductItem(id, productItemNewAndEditDto);
    }

}
