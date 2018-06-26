package com.project.template.delegators;

import com.project.template.api.rest.request.PaymentRequest;
import com.project.template.api.rest.response.PaymentResponse;
import com.project.template.converters.PaymentMapper;
import com.project.template.exception.ServiceException;
import com.project.template.services.PaymentService;
import com.project.template.transfer_objects.PaymentDTO;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class PaymentDelegator {

    @Autowired
    PaymentService service;

    PaymentMapper mapper= Mappers.getMapper(PaymentMapper.class);

    public PaymentResponse delegatePaymentRequest(PaymentRequest request) throws ServiceException {
        log.info("Payment Delegator Serving payment request with request reference id {}.", request.getPaymentReferenceId());
        try {
            //TODO: Validate Payment Request
            //Map Request Object to a DTO Object or VO.
            PaymentDTO payment = mapper.mapRequest(request);

            //For testing
            if(payment.getAmount().equals("4")){
                throw new Exception("For testing");
            }

            //Call Business Service Layer
            payment = service.servePaymentRequest(payment);
            log.info("Payment Delegator : responding the response object.");
            // Map DTO Object or VO to response object.
            return mapper.mapResponse(payment);
        }catch(ServiceException excp){
            log.error("Service Exception with message {} and status {}", excp.getMessage(), excp.getStatus());
            throw excp;
        }catch(Exception e){
            log.error("Error in Processing Payment request with message {} and stacktrace", e.getMessage(), e.getStackTrace());
            throw new ServiceException("Error in processing payment request", e.getCause(), HttpStatus.INTERNAL_SERVER_ERROR, ServiceException.getSystemError());
        }
    }

    public PaymentResponse getPaymentDetails(String reference_id) throws ServiceException {
        log.info("Payment Delegator Serving get payment detail with request reference id {}.", reference_id);
        try {
            PaymentDTO payment = service.getPaymentDetail(reference_id);
            //Call Business Service Layer
            // Map DTO Object or VO to response object.
            PaymentResponse response = mapper.mapResponse(payment);
            log.info("Payment Delegator : responding the response object.");
            return response;
        }catch(ServiceException excp){
            log.error("Service Exception with message {} and status {}", excp.getMessage(), excp.getStatus());
            throw excp;
        }catch(Exception e){
            log.error("Error in Processing Payment request with message {} and stacktrace", e.getMessage(), e.getStackTrace());
            throw new ServiceException("Error in processing payment request", e.getCause(), HttpStatus.INTERNAL_SERVER_ERROR, ServiceException.getSystemError());
        }
    }

    public PaymentResponse delegatePaymentRedisRequest(PaymentRequest request) throws ServiceException {
        log.info("Payment Delegator Serving payment request redis with request reference id {}.", request.getPaymentReferenceId());
        try {
            //TODO: Validate Payment Request
            //Map Request Object to a DTO Object or VO.
            PaymentDTO payment = mapper.mapRequest(request);

            //For testing
            if(payment.getAmount().equals("4")){
                throw new Exception("For testing");
            }


            //Call Business Service Layer
            payment = service.servePaymentRedisRequest(payment);
            log.info("Payment Delegator : responding the response object.");
            // Map DTO Object or VO to response object.
            return mapper.mapResponse(payment);
        }catch(ServiceException excp){
            log.error("Service Exception with message {} and status {}", excp.getMessage(), excp.getStatus());
            throw excp;
        }catch(Exception e){
            log.error("Error in Processing Payment request with message {} and stacktrace", e.getMessage(), e.getStackTrace());
            throw new ServiceException("Error in processing payment request", e.getCause(), HttpStatus.INTERNAL_SERVER_ERROR, ServiceException.getSystemError());
        }
    }

    public PaymentResponse getPaymentRedisDetails(String reference_id) throws ServiceException {
        log.info("Payment Delegator Serving get payment detail with request reference id {}.", reference_id);
        try {
            PaymentDTO payment = service.getPaymentRedisDetail(reference_id);
            //Call Business Service Layer
            // Map DTO Object or VO to response object.
            PaymentResponse response = mapper.mapResponse(payment);
            log.info("Payment Delegator : responding the response object.");
            return response;
        }catch(ServiceException excp){
            log.error("Service Exception with message {} and status {}", excp.getMessage(), excp.getStatus());
            throw excp;
        }catch(Exception e){
            log.error("Error in Processing Payment request with message {} and stacktrace", e.getMessage(), e.getStackTrace());
            throw new ServiceException("Error in processing payment request", e.getCause(), HttpStatus.INTERNAL_SERVER_ERROR, ServiceException.getSystemError());
        }
    }
}
