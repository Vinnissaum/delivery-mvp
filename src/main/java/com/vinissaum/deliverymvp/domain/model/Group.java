package com.vinissaum.deliverymvp.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tb_group")
@Data
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "permissions_group", joinColumns = @JoinColumn(name = "permission_id"),
    inverseJoinColumns = @JoinColumn(name = "tb_group_id")
    )
    private List<Permission> permissions = new ArrayList<>();

}
