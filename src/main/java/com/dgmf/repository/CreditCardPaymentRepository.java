package com.dgmf.repository;

import com.dgmf.entity.CreditCardPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardPaymentRepository
        extends JpaRepository<CreditCardPayment, Long> {
}
