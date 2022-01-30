package com.worzech.inventorymanagementsystem.controller;

import com.worzech.inventorymanagementsystem.model.popcGroup.PopcGroupBasicDto;
import com.worzech.inventorymanagementsystem.model.popcGroup.PopcGroupNewAndEditDto;
import com.worzech.inventorymanagementsystem.service.popcGroup.PopcGroupService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "popcGroup", produces = "application/json")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class PopcGroupController {

    private final PopcGroupService popcGroupService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<PopcGroupNewAndEditDto> getAllPopcGroups() {
        return popcGroupService.getAllPopcGroups();
    }

    @GetMapping("/basic")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<PopcGroupBasicDto> getAllPopcGroupsBasic() {
        return popcGroupService.getAllPopcGroupsBasic();
    }

    @GetMapping("/editGroup/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PopcGroupNewAndEditDto getPopcGroupById(@PathVariable Long id) {
        return popcGroupService.findPopcGroupById(id);
    }

    @DeleteMapping("/delete/{popcGroupId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletePopcGroup(@PathVariable("popcGroupId") Long popcGroupId) {
        popcGroupService.deletePopcGroupById(popcGroupId);
    }

    @PostMapping(path = "/newGroup", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PopcGroupNewAndEditDto createNewPopcGroup(@Valid @RequestBody PopcGroupNewAndEditDto popcGroupNewAndEditDto) {
        return popcGroupService.createNewPopcGroup(popcGroupNewAndEditDto);
    }

    @PutMapping(path = "/editGroup/{id}", consumes = "application/json")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PopcGroupNewAndEditDto editPopcGroup(@PathVariable Long id, @Valid @RequestBody PopcGroupNewAndEditDto popcGroupNewAndEditDto) {
        return popcGroupService.updatePopcGroup(id, popcGroupNewAndEditDto);
    }

}
