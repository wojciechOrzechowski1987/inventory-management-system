package com.worzech.inventorymanagementsystem.service.productItem;

import com.worzech.inventorymanagementsystem.domain.ProductItem;
import com.worzech.inventorymanagementsystem.exceptions.ResourceNotFoundException;
import com.worzech.inventorymanagementsystem.mapper.productItem.ProductItemBasicMapper;
import com.worzech.inventorymanagementsystem.mapper.productItem.ProductItemNewAndEditMapper;
import com.worzech.inventorymanagementsystem.model.productItem.ProductItemBasicDto;
import com.worzech.inventorymanagementsystem.model.productItem.ProductItemNewAndEditDto;
import com.worzech.inventorymanagementsystem.repository.PopcMaterialRepository;
import com.worzech.inventorymanagementsystem.repository.ProductItemRepository;
import com.worzech.inventorymanagementsystem.repository.VendorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductItemServiceImpl implements ProductItemService {

    private final ProductItemNewAndEditMapper productItemNewAndEditMapper;
    private final ProductItemBasicMapper productItemBasicMapper;
    private final ProductItemRepository productItemRepository;
    private final PopcMaterialRepository popcMaterialRepository;
    private final VendorRepository vendorRepository;

    @Override
    public List<ProductItemNewAndEditDto> getAllProductItems() {
        return productItemRepository
                .findAll()
                .stream()
                .map(productItemNewAndEditMapper::productItemToProductItemDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductItemBasicDto> getAllProductItemsBasic() {
        return productItemRepository
                .findAll()
                .stream()
                .map(productItemBasicMapper::toProductItemBasicDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductItemBasicDto> getProductItemsNotAssignedToPopcMaterial() {
        return productItemRepository
                .findProductItemByPopcMaterialIsNull()
                .stream()
                .map(productItemBasicMapper::toProductItemBasicDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductItemBasicDto> getNotAssignedProductItems() {
        return productItemRepository
                .findProductItemByVendorIsNull()
                .stream()
                .map(productItemBasicMapper::toProductItemBasicDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductItemNewAndEditDto findProductItemById(Long id) {
        return productItemRepository.findById(id)
                .map(productItemNewAndEditMapper::productItemToProductItemDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteProductItemById(Long id) {
        productItemRepository.deleteById(id);
    }

    @Override
    public List<ProductItemNewAndEditDto> getProductItemsForVendorEdit(Long id) {
        return productItemRepository
                .findVendorProjectItemsAndNullProjectItems(id)
                .stream()
                .map(productItemNewAndEditMapper::productItemToProductItemDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductItemBasicDto> getProductItemsForPopcMaterialEdit(Long id) {
        return productItemRepository
                .findPopcMaterialProjectItemsAndNullProjectItems(id)
                .stream()
                .map(productItemBasicMapper::toProductItemBasicDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductItemNewAndEditDto createNewProductItem(ProductItemNewAndEditDto productItemNewAndEditDto) {
        return saveAndReturnDto(productItemNewAndEditMapper.productItemDtoToProductItem(productItemNewAndEditDto));
    }

    private ProductItemNewAndEditDto saveAndReturnDto(ProductItem productItem) {
        ProductItem newProductItem = new ProductItem();
        newProductItem.setProductItemName((productItem.getProductItemName()));
        newProductItem.setPrice(productItem.getPrice());
        if (!Objects.equals(productItem.getPopcMaterial().getPopcMaterialCode(), "")) {
            newProductItem.setPopcMaterial(popcMaterialRepository.findPopcMaterialByPopcMaterialCode(productItem.getPopcMaterial().getPopcMaterialCode()).orElseThrow(ResourceNotFoundException::new));
        }
        if (!Objects.equals(productItem.getVendor().getVendorName(), "")) {
            newProductItem.setVendor(vendorRepository.findVendorByVendorName(productItem.getVendor().getVendorName()));
        }
        productItemRepository.save(newProductItem);

        return productItemNewAndEditMapper.productItemToProductItemDto(newProductItem);
    }

    @Override
    public ProductItemNewAndEditDto updateProductItem(Long id, ProductItemNewAndEditDto productItemNewAndEditDto) {
        return updateAndReturnDTO(id, productItemNewAndEditDto);
    }

    private ProductItemNewAndEditDto updateAndReturnDTO(Long id, ProductItemNewAndEditDto productItemNewAndEditDto) {

        ProductItem productItem = productItemRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        productItem.setProductItemName(productItemNewAndEditDto.getProductItemName());
        productItem.setPrice(productItemNewAndEditDto.getPrice());
        productItem.setPopcMaterial(popcMaterialRepository.findPopcMaterialByPopcMaterialCode(productItemNewAndEditDto.getPopcMaterialCode()).orElseThrow(ResourceNotFoundException::new));
        productItem.setVendor(vendorRepository.findVendorByVendorName(productItemNewAndEditDto.getVendorName()));
        productItemRepository.save(productItem);
        return productItemNewAndEditMapper.productItemToProductItemDto(productItem);
    }


}
