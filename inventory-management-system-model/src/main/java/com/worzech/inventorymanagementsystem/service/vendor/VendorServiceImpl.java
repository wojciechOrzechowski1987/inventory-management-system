package com.worzech.inventorymanagementsystem.service.vendor;

import com.worzech.inventorymanagementsystem.domain.ProductItem;
import com.worzech.inventorymanagementsystem.domain.Vendor;
import com.worzech.inventorymanagementsystem.exceptions.ResourceNotFoundException;
import com.worzech.inventorymanagementsystem.mapper.vendor.VendorBasicMapper;
import com.worzech.inventorymanagementsystem.mapper.vendor.VendorNewAndEditMapper;
import com.worzech.inventorymanagementsystem.mapper.vendor.VendorPurchaseMapper;
import com.worzech.inventorymanagementsystem.model.vendor.VendorBasicDto;
import com.worzech.inventorymanagementsystem.model.vendor.VendorNewAndEditDto;
import com.worzech.inventorymanagementsystem.model.vendor.VendorPurchaseDto;
import com.worzech.inventorymanagementsystem.repository.ProductItemRepository;
import com.worzech.inventorymanagementsystem.repository.VendorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VendorServiceImpl implements VendorService {

    private final VendorBasicMapper vendorBasicMapper;
    private final VendorNewAndEditMapper vendorNewAndEditMapper;
    private final VendorPurchaseMapper vendorPurchaseMapper;
    private final VendorRepository vendorRepository;
    private final ProductItemRepository productItemRepository;

    @Override
    public List<VendorNewAndEditDto> getAllVendors() {
        return vendorRepository
                .findAll()
                .stream()
                .map(vendorNewAndEditMapper::toVendorNewAndEditDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VendorBasicDto> getAllVendorsBasic() {
        return vendorRepository
                .findAll()
                .stream()
                .map(vendorBasicMapper::toVendorBasicDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VendorPurchaseDto> getAllVendorsPurchase() {
        return vendorRepository
                .findAll()
                .stream()
                .map(vendorPurchaseMapper::toVendorPurchaseDto)
                .collect(Collectors.toList());
    }

    @Override
    public VendorNewAndEditDto findVendorById(Long id) {
        return vendorRepository.findById(id)
                .map(vendorNewAndEditMapper::toVendorNewAndEditDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }

    @Override
    public VendorNewAndEditDto createNewVendor(VendorNewAndEditDto vendorDto) {
        return saveAndReturnDto(vendorNewAndEditMapper.fromVendorNewAndEditDto(vendorDto));
    }

    private VendorNewAndEditDto saveAndReturnDto(Vendor vendor) {
        Vendor newVendor = new Vendor();
        newVendor.setVendorName(vendor.getVendorName());
        vendor.getProductItems()
                .iterator()
                .forEachRemaining(
                        (productItem ->
                                newVendor
                                        .addProductItemToVendor(productItemRepository
                                                .findProductItemByProductItemName(productItem.getProductItemName())
                                                .orElseThrow(ResourceNotFoundException::new))));

        vendorRepository.save(newVendor);

        return vendorNewAndEditMapper.toVendorNewAndEditDto(newVendor);
    }

    @Override
    public VendorNewAndEditDto updateVendor(Long id, VendorNewAndEditDto vendorDto) {
        return updateAndReturnDto(id, vendorDto);
    }

    private VendorNewAndEditDto updateAndReturnDto(Long id, VendorNewAndEditDto vendorDto) {
        Vendor vendor = vendorRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        vendor.setVendorName(vendorDto.getVendorName());
        iterateProductItemsInVendor(vendor, vendor.getProductItems());
        if (vendorDto.getProductItems().size() != 0) {
            vendorDto.getProductItems().iterator().forEachRemaining(productItem -> vendor
                    .addProductItemToVendor(productItemRepository
                            .findProductItemByProductItemName(productItem.getProductItemName())
                            .orElseThrow(ResourceNotFoundException::new)));
        }
        vendorRepository.save(vendor);
        return vendorNewAndEditMapper.toVendorNewAndEditDto(vendor);
    }

    private void iterateProductItemsInVendor(Vendor vendor, Set<ProductItem> set) {
        for (Iterator<ProductItem> i = set.iterator(); i.hasNext(); ) {
            ProductItem element = i.next();
            vendor.removeProductItemFromVendorIterate(element);
            i.remove();
            productItemRepository.save(element);
        }
    }
}
