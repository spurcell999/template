package com.project.template.repositories;

import com.project.template.domain.Payment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;


@Repository
@Transactional
public interface PaymentRepository extends CrudRepository<Payment, Long> {

    Payment findByPaymentReferenceId(String paymentReferenceId) throws NoResultException;

    //Below query provides the list of transactions whose amount is greater than some amount.
    List<Payment> findByAmountGreaterThan(String amount);

    //We can write custom native queries for above like below
    @Query(value = "select 1 from payment p where p.amount > ?1",nativeQuery = true)
    List<Payment> greaterThanAmount(String amount);

    //We can write custom queries for above like below
    @Query(value = "select p from Payment p where p.amount > ?1")
    List<Payment> greaterThanAmount1(String amount);

    //We can write custom queries for above like below
    @Query(value = "select p from Payment p where p.amount > ?1")
    List<Payment> greaterThanAmount2(@Param("amount") String amount);

    @Query(value = "select H2VERSION() from dual", nativeQuery = true)
    String dbConnectionHealthQuery();

}
