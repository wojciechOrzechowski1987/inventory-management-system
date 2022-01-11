package com.worzech.inventorymanagementsystem.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PopcGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotBlank
    private String popcGroupName;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "popcGroup")
    private Set<PopcSubgroup> popcSubgroup = new HashSet<>();

    public PopcGroup(String popcGroupName) {
        this.popcGroupName = popcGroupName;
    }

    public void addSubgroupToGroup(PopcSubgroup subgroup) {
        popcSubgroup.add(subgroup);
        subgroup.setPopcGroup(this);
    }

    public void removeSubgroupFromGroupIterate(PopcSubgroup subgroup) {
        subgroup.setPopcGroup(null);
    }


}
