package com.worzech.inventorymanagementsystem.service.popcGroup;

import com.worzech.inventorymanagementsystem.domain.PopcGroup;
import com.worzech.inventorymanagementsystem.domain.PopcSubgroup;
import com.worzech.inventorymanagementsystem.exceptions.ResourceNotFoundException;
import com.worzech.inventorymanagementsystem.mapper.popcGroup.PopcGroupBasicMapper;
import com.worzech.inventorymanagementsystem.mapper.popcGroup.PopcGroupMapper;
import com.worzech.inventorymanagementsystem.model.popcGroup.PopcGroupBasicDto;
import com.worzech.inventorymanagementsystem.model.popcGroup.PopcGroupNewAndEditDto;
import com.worzech.inventorymanagementsystem.repository.PopcGroupRepository;
import com.worzech.inventorymanagementsystem.repository.PopcSubgroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PopcGroupServiceImpl implements PopcGroupService {

    private final PopcGroupMapper popcGroupMapper;
    private final PopcGroupBasicMapper popcGroupBasicMapper;
    private final PopcGroupRepository popcGroupRepository;
    private final PopcSubgroupRepository popcSubgroupRepository;

    @Override
    public List<PopcGroupNewAndEditDto> getAllPopcGroups() {
        return popcGroupRepository
                .findAll()
                .stream()
                .map(popcGroupMapper::toPopcGroupNewAndEditDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PopcGroupBasicDto> getAllPopcGroupsBasic() {
        return popcGroupRepository
                .findAll()
                .stream()
                .map(popcGroupBasicMapper::toPopcGroupBasicDto)
                .collect(Collectors.toList());
    }

    @Override
    public PopcGroupNewAndEditDto findPopcGroupById(Long id) {
        return popcGroupRepository.findById(id)
                .map(popcGroupMapper::toPopcGroupNewAndEditDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deletePopcGroupById(Long id) {
        popcGroupRepository.deleteById(id);
    }

    @Override
    public PopcGroupNewAndEditDto createNewPopcGroup(PopcGroupNewAndEditDto popcGroupNewAndEditDto) {
        return saveAndReturnDto(popcGroupMapper.fromPopcGroupNewAndEditDto(popcGroupNewAndEditDto));
    }

    private PopcGroupNewAndEditDto saveAndReturnDto(PopcGroup popcGroup) {
        PopcGroup newPopcGroup = new PopcGroup();
        newPopcGroup.setPopcGroupName(popcGroup.getPopcGroupName());
        popcGroup.getPopcSubgroup()
                .iterator()
                .forEachRemaining
                        (subgroup ->
                                newPopcGroup
                                        .addSubgroupToGroup(
                                                popcSubgroupRepository
                                                        .findPopcSubgroupByPopcSubgroupName(subgroup.getPopcSubgroupName())
                                                        .orElseThrow(ResourceNotFoundException::new)));

        popcGroupRepository.save(newPopcGroup);

        return popcGroupMapper.toPopcGroupNewAndEditDto(newPopcGroup);
    }

    @Override
    public PopcGroupNewAndEditDto updatePopcGroup(Long id, PopcGroupNewAndEditDto popcGroupNewAndEditDto) {
        return updateAndReturnDto(id, popcGroupNewAndEditDto);
    }

    private PopcGroupNewAndEditDto updateAndReturnDto(Long id, PopcGroupNewAndEditDto popcGroupNewAndEditDto) {
        PopcGroup popcGroup = popcGroupRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        popcGroup.setPopcGroupName(popcGroupNewAndEditDto.getPopcGroupName());
        iteratePopcSubgroupsInPopcGroup(popcGroup, popcGroup.getPopcSubgroup());
        if (popcGroupNewAndEditDto.getPopcSubgroup().size() != 0) {
            popcGroupNewAndEditDto.getPopcSubgroup().iterator().forEachRemaining(subgroup -> popcGroup
                    .addSubgroupToGroup(popcSubgroupRepository
                            .findPopcSubgroupByPopcSubgroupName(subgroup.getPopcSubgroupName())
                            .orElseThrow(ResourceNotFoundException::new)
                    ));
        }
        popcGroupRepository.save(popcGroup);
        return popcGroupMapper.toPopcGroupNewAndEditDto(popcGroup);
    }

    private void iteratePopcSubgroupsInPopcGroup(PopcGroup popcGroup, Set<PopcSubgroup> set) {
        for (Iterator<PopcSubgroup> i = set.iterator(); i.hasNext(); ) {
            PopcSubgroup element = i.next();
            popcGroup.removeSubgroupFromGroupIterate(element);
            i.remove();
            popcSubgroupRepository.save(element);
        }
    }
}
