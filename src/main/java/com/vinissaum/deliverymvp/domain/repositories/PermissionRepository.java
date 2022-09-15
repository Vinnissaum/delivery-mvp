package com.vinissaum.deliverymvp.domain.repositories;

import com.vinissaum.deliverymvp.domain.model.Permission;

import java.util.List;

public interface PermissionRepository {

    List<Permission> findAll();
    Permission find(Long id);
    Permission insert(Permission permission);
    Permission update(Permission permission);
    void delete(Permission permission);

}
