package com.worzech.inventorymanagementsystem.service.popcMaterial;

import com.worzech.inventorymanagementsystem.domain.PopcMaterial;
import com.worzech.inventorymanagementsystem.domain.ProductItem;
import com.worzech.inventorymanagementsystem.exceptions.ResourceNotFoundException;
import com.worzech.inventorymanagementsystem.mapper.popcMaterial.PopcMaterialBasicMapper;
import com.worzech.inventorymanagementsystem.mapper.popcMaterial.PopcMaterialNewAndEditMapper;
import com.worzech.inventorymanagementsystem.model.popcMaterial.PopcMaterialBasicDto;
import com.worzech.inventorymanagementsystem.model.popcMaterial.PopcMaterialNewAndEditDto;
import com.worzech.inventorymanagementsystem.repository.PopcMaterialRepository;
import com.worzech.inventorymanagementsystem.repository.PopcSubgroupRepository;
import com.worzech.inventorymanagementsystem.repository.ProductItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class PopcMaterialServiceImpl implements PopcMaterialService {

    private final PopcMaterialNewAndEditMapper popcMaterialNewAndEditMapper;
    private final PopcMaterialBasicMapper popcMaterialBasicMapper;
    private final PopcMaterialRepository popcMaterialRepository;
    private final ProductItemRepository productItemRepository;
    private final PopcSubgroupRepository popcSubgroupRepository;

    @Override
    public List<PopcMaterialNewAndEditDto> getAllPopcMaterials() {
        return popcMaterialRepository
                .findAll()
                .stream()
                .map(popcMaterialNewAndEditMapper::popcMaterialToPopcMaterialDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PopcMaterialBasicDto> getAllPopcMaterialsBasic() {
        return popcMaterialRepository
                .findAll()
                .stream()
                .map(popcMaterialBasicMapper::toPopcMaterialBasicDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PopcMaterialBasicDto> getPopcMaterialsForPopcSubgroupEdit(Long id) {
        return popcMaterialRepository
                .getPopcSubgroupPopcMaterialsAndNullMaterials(id)
                .stream()
                .map(popcMaterialBasicMapper::toPopcMaterialBasicDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PopcMaterialNewAndEditDto> getNotAssignedPopcMaterials() {
        return popcMaterialRepository.findPopcMaterialByPopcSubgroupIsNull().stream().map(popcMaterialNewAndEditMapper::popcMaterialToPopcMaterialDto).collect(Collectors.toList());
    }

    @Override
    public PopcMaterialNewAndEditDto findPopcMaterialsById(Long id) {
        return popcMaterialRepository.findById(id)
                .map(popcMaterialNewAndEditMapper::popcMaterialToPopcMaterialDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deletePopcMaterialsById(Long id) {
        popcMaterialRepository.deleteById(id);
    }

    @Override
    public PopcMaterialNewAndEditDto createNewPopcMaterial(PopcMaterialNewAndEditDto popcMaterialNewAndEditDto) {
        return saveAndReturnDto(popcMaterialNewAndEditMapper.popcMaterialDtoToPopcMaterial(popcMaterialNewAndEditDto));
    }

    private PopcMaterialNewAndEditDto saveAndReturnDto(PopcMaterial popcMaterial) {
        PopcMaterial newPopcMaterial = new PopcMaterial();
        newPopcMaterial.setPopcMaterialName(popcMaterial.getPopcMaterialName());
        newPopcMaterial.setPopcMaterialCode(popcMaterial.getPopcMaterialCode());
        newPopcMaterial.setPopcMaterialDescription(popcMaterial.getPopcMaterialDescription());
        newPopcMaterial.setPopcSubgroup(
                popcSubgroupRepository.findPopcSubgroupByPopcSubgroupName(popcMaterial.getPopcSubgroup().getPopcSubgroupName())
                        .orElseThrow(ResourceNotFoundException::new)
        );
        popcMaterial.getProductItems()
                .iterator()
                .forEachRemaining(
                        productItem ->
                                newPopcMaterial.addProductItemtoPopcMaterial(productItemRepository
                                        .findProductItemByProductItemName(productItem.getProductItemName())
                                        .orElseThrow(ResourceNotFoundException::new))
                );

        popcMaterialRepository.save(newPopcMaterial);

        return popcMaterialNewAndEditMapper.popcMaterialToPopcMaterialDto(newPopcMaterial);
    }

    @Override
    public PopcMaterialNewAndEditDto updatePopcMaterial(Long id, PopcMaterialNewAndEditDto popcMaterialNewAndEditDto) {
        return updateAndReturnDto(id, popcMaterialNewAndEditDto);
    }

    private PopcMaterialNewAndEditDto updateAndReturnDto(Long id, PopcMaterialNewAndEditDto popcMaterialNewAndEditDto) {
        PopcMaterial popcMaterial = popcMaterialRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        popcMaterial.setPopcMaterialName(popcMaterialNewAndEditDto.getPopcMaterialName());
        popcMaterial.setPopcMaterialCode(popcMaterialNewAndEditDto.getPopcMaterialCode());
        popcMaterial.setPopcMaterialDescription(popcMaterialNewAndEditDto.getPopcMaterialDescription());
        popcMaterial.setPopcSubgroup(popcSubgroupRepository
                .findPopcSubgroupByPopcSubgroupName(popcMaterialNewAndEditDto.getPopcSubgroupName())
                .orElseThrow(ResourceNotFoundException::new));
        iterateProductItemsInPopcMaterial(popcMaterial, popcMaterial.getProductItems());
        if (popcMaterialNewAndEditDto.getProductItems().size() != 0) {
            popcMaterialNewAndEditDto.getProductItems()
                    .iterator()
                    .forEachRemaining(
                            productItem ->
                                    popcMaterial.addProductItemtoPopcMaterial(productItemRepository
                                            .findProductItemByProductItemName(productItem.getProductItemName())
                                            .orElseThrow(ResourceNotFoundException::new))
                    );
        }
        popcMaterialRepository.save(popcMaterial);
        return popcMaterialNewAndEditMapper.popcMaterialToPopcMaterialDto(popcMaterial);
    }

    private void iterateProductItemsInPopcMaterial(PopcMaterial popcMaterial, Set<ProductItem> set) {
        for (Iterator<ProductItem> i = set.iterator(); i.hasNext(); ) {
            ProductItem element = i.next();
            popcMaterial.removeProductItemFromPopcMaterialIterate(element);
            i.remove();
            productItemRepository.save(element);
        }
    }
}
