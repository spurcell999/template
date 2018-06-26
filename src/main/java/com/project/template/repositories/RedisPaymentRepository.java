package com.project.template.repositories;

import com.project.template.domain.RedisPayment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;


@Repository
public interface RedisPaymentRepository extends CrudRepository<RedisPayment, Long> {
    RedisPayment findByPaymentReferenceId(String paymentReferenceId) throws NoResultException;
}
