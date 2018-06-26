package com.project.template.validators;

import com.project.template.api.rest.request.PaymentRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PaymentValidator implements ConstraintValidator<PaymentConstraint, PaymentRequest> {
    @Override
    public void initialize(PaymentConstraint paymentConstraint) {
    }
    @Override
    public boolean isValid(PaymentRequest paymentRequest,ConstraintValidatorContext cxt) {
        if(paymentRequest.getAmount()==null || paymentRequest.getPaymentReferenceId()==null
                || paymentRequest.getReceiverAccount()==null || paymentRequest.getSenderAccount()==null){
            cxt.buildConstraintViolationWithTemplate("Values can not be null")
                    .addPropertyNode("senderAccount")
                     .addConstraintViolation();
            return false;
        }
        else
            return true;
    }

}
