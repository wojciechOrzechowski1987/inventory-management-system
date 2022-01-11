package com.worzech.inventorymanagementsystem.service.popcSubgroup;

import com.worzech.inventorymanagementsystem.domain.PopcMaterial;
import com.worzech.inventorymanagementsystem.domain.PopcSubgroup;
import com.worzech.inventorymanagementsystem.exceptions.ResourceNotFoundException;
import com.worzech.inventorymanagementsystem.mapper.popcSubgroup.PopcSubgroupBasicMapper;
import com.worzech.inventorymanagementsystem.mapper.popcSubgroup.PopcSubgroupNewAndEditMapper;
import com.worzech.inventorymanagementsystem.model.popcSubgroup.PopcSubgroupBasicDto;
import com.worzech.inventorymanagementsystem.model.popcSubgroup.PopcSubgroupNewAndEditDto;
import com.worzech.inventorymanagementsystem.repository.PopcGroupRepository;
import com.worzech.inventorymanagementsystem.repository.PopcMaterialRepository;
import com.worzech.inventorymanagementsystem.repository.PopcSubgroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PopcSubgroupServiceImpl implements PopcSubgroupService {

    private final PopcSubgroupNewAndEditMapper popcSubgroupNewAndEditMapper;
    private final PopcSubgroupBasicMapper popcSubgroupBasicMapper;
    private final PopcSubgroupRepository popcSubgroupRepository;
    private final PopcGroupRepository popcGroupRepository;
    private final PopcMaterialRepository popcMaterialRepository;

    @Override
    public List<PopcSubgroupNewAndEditDto> getAllPopcSubgroups() {
        return popcSubgroupRepository
                .findAll()
                .stream()
                .map(popcSubgroupNewAndEditMapper::popcSubgroupToPopcSubgroupDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PopcSubgroupBasicDto> getAllPopcSubgroupsBasic() {
        return popcSubgroupRepository
                .findAll()
                .stream()
                .map(popcSubgroupBasicMapper::toPopcSubgroupBasicDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PopcSubgroupBasicDto> getNotAssignedSubgroups() {
        return popcSubgroupRepository
                .findPopcSubgroupByPopcGroupIsNull()
                .stream()
                .map(popcSubgroupBasicMapper::toPopcSubgroupBasicDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PopcSubgroupBasicDto> getPopcSubgroupsForPopcGroupEdit(Long id) {
        return popcSubgroupRepository
                .findGroupSubgroupsAndNullSubgroups(id)
                .stream()
                .map(popcSubgroupBasicMapper::toPopcSubgroupBasicDto)
                .collect(Collectors.toList());
    }

    @Override
    public PopcSubgroupNewAndEditDto findPopcSubgroupById(Long id) {
        return popcSubgroupRepository.findById(id)
                .map(popcSubgroupNewAndEditMapper::popcSubgroupToPopcSubgroupDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public PopcSubgroupBasicDto findPopcSubgroupsByName(String popcSubgroupName) {
        return popcSubgroupBasicMapper
                .toPopcSubgroupBasicDto(popcSubgroupRepository
                        .findPopcSubgroupByPopcSubgroupName(popcSubgroupName)
                        .orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public void deletePopcSubgroupsById(Long id) {
        popcSubgroupRepository.deleteById(id);
    }

    @Override
    public PopcSubgroupNewAndEditDto createNewPopcSubgroup(PopcSubgroupNewAndEditDto popcSubgroupNewAndEditDto) {
        return saveAndReturnDto(popcSubgroupNewAndEditMapper.popcSubgroupDtoToPopcSubgroup(popcSubgroupNewAndEditDto));
    }

    private PopcSubgroupNewAndEditDto saveAndReturnDto(PopcSubgroup popcSubgroup) {
        PopcSubgroup newPopcSubgroup = new PopcSubgroup();
        newPopcSubgroup.setPopcSubgroupName(popcSubgroup.getPopcSubgroupName());
        if (!Objects.equals(popcSubgroup.getPopcGroup().getPopcGroupName(), "")) {
            newPopcSubgroup.setPopcGroup(popcGroupRepository.getPopcGroupByPopcGroupName(popcSubgroup.getPopcGroup().getPopcGroupName()));
        }
        popcSubgroup.getPopcMaterials()
                .iterator()
                .forEachRemaining(material ->
                        newPopcSubgroup
                                .addPopcMaterialToPopcSubgroup(
                                        popcMaterialRepository
                                                .findById(material.getId())
                                                .orElseThrow(ResourceNotFoundException::new)
                                ));
        popcSubgroupRepository.save(newPopcSubgroup);

        return popcSubgroupNewAndEditMapper.popcSubgroupToPopcSubgroupDto(newPopcSubgroup);
    }

    @Override
    public PopcSubgroupNewAndEditDto updatePopcSubgroup(Long id, PopcSubgroupNewAndEditDto popcSubgroupNewAndEditDto) {
        return updateAndReturnDto(id, popcSubgroupNewAndEditDto);
    }

    private PopcSubgroupNewAndEditDto updateAndReturnDto(Long id, PopcSubgroupNewAndEditDto popcSubgroupNewAndEditDto) {
        PopcSubgroup popcSubgroup = popcSubgroupRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        popcSubgroup.setPopcSubgroupName(popcSubgroupNewAndEditDto.getPopcSubgroupName());
        popcSubgroup.setPopcGroup(popcGroupRepository.getPopcGroupByPopcGroupName(popcSubgroupNewAndEditDto.getPopcGroupName()));
        iterateMaterialsInSubgroup(popcSubgroup, popcSubgroup.getPopcMaterials());
        if (popcSubgroupNewAndEditDto.getPopcMaterials().size() != 0) {
            popcSubgroupNewAndEditDto.getPopcMaterials().iterator().forEachRemaining(material -> popcSubgroup
                    .addPopcMaterialToPopcSubgroup(popcMaterialRepository
                            .findPopcMaterialByPopcMaterialCode(material.getPopcMaterialCode())
                            .orElseThrow(ResourceNotFoundException::new)));
        }
        popcSubgroupRepository.save(popcSubgroup);
        return popcSubgroupNewAndEditMapper.popcSubgroupToPopcSubgroupDto(popcSubgroup);
    }

    private void iterateMaterialsInSubgroup(PopcSubgroup popcSubgroup, Set<PopcMaterial> set) {
        for (Iterator<PopcMaterial> i = set.iterator(); i.hasNext(); ) {
            PopcMaterial element = i.next();
            popcSubgroup.removePopcMaterialFromPopcSubgroup(element);
            i.remove();
            popcMaterialRepository.save(element);
        }
    }
}
