package com.worzech.inventorymanagementsystem.service.productItem;

import com.worzech.inventorymanagementsystem.model.productItem.ProductItemBasicDto;
import com.worzech.inventorymanagementsystem.model.productItem.ProductItemNewAndEditDto;

import java.util.List;

public interface ProductItemService {


    List<ProductItemNewAndEditDto> getAllProductItems();

    List<ProductItemBasicDto> getAllProductItemsBasic();

    List<ProductItemBasicDto> getProductItemsNotAssignedToPopcMaterial();

    List<ProductItemBasicDto> getNotAssignedProductItems();

    List<ProductItemNewAndEditDto> getProductItemsForVendorEdit(Long id);

    List<ProductItemBasicDto> getProductItemsForPopcMaterialEdit(Long id);

    ProductItemNewAndEditDto findProductItemById(Long id);

    void deleteProductItemById(Long id);

    ProductItemNewAndEditDto createNewProductItem(ProductItemNewAndEditDto productItemNewAndEditDto);

    ProductItemNewAndEditDto updateProductItem(Long id, ProductItemNewAndEditDto productItemNewAndEditDto);


}
