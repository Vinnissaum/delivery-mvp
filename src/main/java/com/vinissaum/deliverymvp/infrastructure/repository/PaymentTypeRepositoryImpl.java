package com.vinissaum.deliverymvp.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vinissaum.deliverymvp.domain.model.PaymentType;
import com.vinissaum.deliverymvp.domain.repositories.PaymentTypeRepository;

@Component
public class PaymentTypeRepositoryImpl implements PaymentTypeRepository {

    @PersistenceContext
    private EntityManager manager;

    public List<PaymentType> findAll() {
        return manager.createQuery("from PaymentType", PaymentType.class)
            .getResultList();
    }

    public PaymentType find(Long id) {
        return manager.find(PaymentType.class, id);
    }

    @Transactional
    public PaymentType insert(PaymentType paymentType) {
        return manager.merge(paymentType);
    }

    @Transactional
    public PaymentType update(PaymentType paymentType) {
        return manager.merge(paymentType);
    }

    @Transactional
    public void delete(PaymentType paymentType) {
        manager.remove(paymentType);
    }

}
