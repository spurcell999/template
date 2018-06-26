package com.project.template.services;

import com.project.template.converters.PaymentMapper;
import com.project.template.domain.Payment;
import com.project.template.domain.RedisPayment;
import com.project.template.exception.ServiceException;
import com.project.template.repositories.PaymentRepository;
import com.project.template.repositories.RedisPaymentRepository;
import com.project.template.transfer_objects.PaymentDTO;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Calendar;


@Service
@Slf4j
public class PaymentService {

    @Autowired
    PaymentRepository repository;

    @Autowired
    RedisPaymentRepository redisRepository;

    PaymentMapper mapper= Mappers.getMapper(PaymentMapper.class);

    public PaymentDTO servePaymentRequest(PaymentDTO paymentDTO) throws ServiceException{
        log.info("Serving a payment request with reference id {}", paymentDTO.getPaymentReferenceId());

        //TODO: Perform business logic here, if any.
        //TODO: Map DTO/VO to a appropriate Domain Entity, based on relational or non-relational persistence required.
        //TODO: Perform data persistence
        //TODO: Perform backend Service Calls, if any.
        //TODO: Perform business logic here, if any.
        //TODO: Map DTO/VO to a appropriate Domain Entity, based on relational or non-relational persistence required.
        //TODO: Perform post back end call persistence

        paymentDTO.setNetwork("VISAOCT");
        paymentDTO.setNetworkProcessor("MNGS");
        paymentDTO.setStatus("APPROVED");
        paymentDTO.setCreatedBy("SYSTEM");
        paymentDTO.setCreatedTs(Calendar.getInstance());
        paymentDTO.setUpdatedBy("SYSTEM");
        paymentDTO.setUpdatedTs(Calendar.getInstance());

        Payment payment = mapper.mapPaymentEntity(paymentDTO);

        payment = repository.save(payment);

        paymentDTO = mapper.mapPaymentDTO(payment);

        log.info("Responding payment DTO.");
        return paymentDTO;
    }

    public PaymentDTO getPaymentDetail(@NonNull String reference_id) throws ServiceException{
        log.info("Serving get payment request for reference id {}", reference_id);
        try {
            Payment payment = repository.findByPaymentReferenceId(reference_id);
            if(payment != null) {
                return mapper.mapPaymentDTO(payment);
            } else {
                throw new ServiceException("No Payment Details Found", null, HttpStatus.BAD_REQUEST, ServiceException.getSystemError());
            }
        } catch(Exception e){
            log.error("Error in Processing Payment request with message {} and stacktrace", e.getMessage(), e.getStackTrace());
            throw new ServiceException("Error in processing payment request", e.getCause(), HttpStatus.INTERNAL_SERVER_ERROR, ServiceException.getSystemError());
        }
    }



    public PaymentDTO servePaymentRedisRequest(@NonNull PaymentDTO paymentDTO) throws ServiceException{
        log.info("Serving a payment request with reference id {}", paymentDTO.getPaymentReferenceId());

        //TODO: Perform business logic here, if any.
        //TODO: Map DTO/VO to a appropriate Domain Entity, based on relational or non-relational persistence required.
        //TODO: Perform data persistence
        //TODO: Perform backend Service Calls, if any.
        //TODO: Perform business logic here, if any.
        //TODO: Map DTO/VO to a appropriate Domain Entity, based on relational or non-relational persistence required.
        //TODO: Perform post back end call persistence

        paymentDTO.setNetwork("VISAOCT");
        paymentDTO.setNetworkProcessor("MNGS");
        paymentDTO.setStatus("APPROVED");
        paymentDTO.setCreatedBy("SYSTEM");
        paymentDTO.setCreatedTs(Calendar.getInstance());
        paymentDTO.setUpdatedBy("SYSTEM");
        paymentDTO.setUpdatedTs(Calendar.getInstance());

        RedisPayment payment = mapper.paymentDtoToRedisPayment(paymentDTO);
        payment.setPaymentId(200L);

        payment = redisRepository.save(payment);

        paymentDTO = mapper.paymentDtoToRedisPayment(payment);

        log.info("Responding payment DTO.");
        return paymentDTO;
    }

    public PaymentDTO getPaymentRedisDetail(@NonNull String reference_id) throws ServiceException{
        log.info("Serving get payment redis request for reference id {}", reference_id);
        try {
            RedisPayment redisPayment = redisRepository.findByPaymentReferenceId(reference_id);
            if(redisPayment != null) {
                return mapper.paymentDtoToRedisPayment(redisRepository.findByPaymentReferenceId(reference_id));
            } else {
                throw new ServiceException("No Payment Details Found", null, HttpStatus.BAD_REQUEST, ServiceException.getSystemError());
            }
        } catch(Exception e){
            log.error("Error in Processing Payment Redis request with message {} and stacktrace", e.getMessage(), e.getStackTrace());
            throw new ServiceException("Error in processing payment redis request", e.getCause(), HttpStatus.INTERNAL_SERVER_ERROR, ServiceException.getSystemError());
        }
    }

}
