package com.vinissaum.deliverymvp.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vinissaum.deliverymvp.domain.model.Permission;
import com.vinissaum.deliverymvp.domain.repositories.PermissionRepository;

@Component
public class PermissionRepositoryImpl implements PermissionRepository {

    @PersistenceContext
    private EntityManager manager;

    public List<Permission> findAll() {
        return manager.createQuery("from Permission", Permission.class)
            .getResultList();
    }

    public Permission find(Long id) {
        return manager.find(Permission.class, id);
    }

    @Transactional
    public Permission insert(Permission permission) {
        return manager.merge(permission);
    }

    @Transactional
    public Permission update(Permission permission) {
        return manager.merge(permission);
    }

    @Transactional
    public void delete(Permission permission) {
        manager.remove(permission);
    }

}
