package com.vinissaum.deliverymvp.domain.repositories;

import com.vinissaum.deliverymvp.domain.model.PaymentType;

import java.util.List;

public interface PaymentTypeRepository {

    List<PaymentType> findAll();
    PaymentType find(Long id);
    PaymentType insert(PaymentType paymentType);
    PaymentType update(PaymentType paymentType);
    void delete(PaymentType paymentType);

}
