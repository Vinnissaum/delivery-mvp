package com.vinissaum.deliverymvp.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tb_user")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(nullable = false,  columnDefinition = "datetime")
    private LocalDateTime registerDate;

    @ManyToMany
    @JoinTable(name = "user_group", joinColumns = @JoinColumn(name = "tb_user_id"),
    inverseJoinColumns = @JoinColumn(name = "tb_group_id"))
    private List<Group> groups = new ArrayList<>();

}
