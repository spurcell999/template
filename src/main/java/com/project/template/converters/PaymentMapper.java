package com.project.template.converters;

import com.project.template.api.rest.request.PaymentRequest;
import com.project.template.api.rest.response.PaymentResponse;
import com.project.template.domain.Payment;
import com.project.template.domain.RedisPayment;
import com.project.template.transfer_objects.PaymentDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PaymentMapper {

    //Custom way to handle the mapping as we want
    default PaymentDTO mapRequest (PaymentRequest request){
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPaymentReferenceId(request.getPaymentReferenceId());
        paymentDTO.setPaymentType(request.getPaymentType());
        paymentDTO.setAmount(request.getAmount());
        paymentDTO.setReceiverAccount(request.getReceiverAccount());
        paymentDTO.setSenderAccount(request.getSenderAccount());
        return paymentDTO;
    }

    //Simple mapping
    PaymentResponse mapResponse (PaymentDTO payment);

    //Mapping of one field in custom way . Same field is mapped for demo purpose.
    @Mapping(source = "paymentReferenceId", target = "paymentReferenceId")
    Payment mapPaymentEntity(PaymentDTO paymentDTO);

    //This annotation just inverse the mapping of PaymentDTO to Payment written above.
    @InheritInverseConfiguration
    PaymentDTO mapPaymentDTO(Payment payment);

    // PaymentDTO to RedisPayment
    RedisPayment paymentDtoToRedisPayment(PaymentDTO paymentDTO);

    // RedisPayment to PaymentDTO
    PaymentDTO paymentDtoToRedisPayment(RedisPayment redis);
}
