package com.project.template.validators;

import com.project.template.api.rest.request.PaymentRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by E082175 on 6/11/2018.
 */
public class PaymentAccountValidator implements ConstraintValidator<PaymentAccountConstraint, PaymentRequest> {
    @Override
    public void initialize(PaymentAccountConstraint paymentActConstraint) {
    }
    @Override
    public boolean isValid(PaymentRequest paymentRequest,ConstraintValidatorContext cxt) {

        if((paymentRequest.getSenderAccount()!=null && paymentRequest.getSenderAccount().startsWith("1") )
                || (paymentRequest.getReceiverAccount()!=null && paymentRequest.getReceiverAccount().startsWith("2")))
        {
            cxt.buildConstraintViolationWithTemplate("Account number is not an acceptable type.")
                    .addPropertyNode("senderAccount")
                    .addConstraintViolation();
            return false;
        }
        else
            return true;

    }

}