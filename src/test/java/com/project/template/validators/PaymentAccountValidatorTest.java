package com.project.template.validators;

import com.project.template.api.rest.request.PaymentRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.*;

import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

/**
 * Created by E082175 on 6/18/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class PaymentAccountValidatorTest {
    PaymentRequest paymentRequest = null;
    private Validator validator;
    @Before
    public void setUp() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        paymentRequest = createPaymentRequest();
    }

    private PaymentRequest createPaymentRequest() {
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setPaymentReferenceId("part_ref1234");
        paymentRequest.setPaymentType("CREDIT");
        paymentRequest.setAmount("100");
        return paymentRequest;
    }

    @Test
    public void paymentAccountValid() throws Exception {
        paymentRequest.setSenderAccount("5013040000000018");
        paymentRequest.setReceiverAccount("5184680430000006");
        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(paymentRequest);
        assertEquals(0, violations.size());
    }

    @Test
    public void paymentAccountInValidSender() throws Exception {
        paymentRequest.setSenderAccount("1013040000000018");
        paymentRequest.setReceiverAccount("5184680430000006");
        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(paymentRequest);
        assertEquals(3, violations.size());
    }

    @Test
    public void paymentAccountInValidReciever() throws Exception {
        paymentRequest.setSenderAccount("5013040000000018");
        paymentRequest.setReceiverAccount("2013040000000018");
        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(paymentRequest);
        assertEquals(3, violations.size());
    }

    @Test
    public void paymentRequestNullCheck() throws Exception {

        paymentRequest.setPaymentReferenceId(null);
        paymentRequest.setAmount(null);
        paymentRequest.setSenderAccount(null);
        paymentRequest.setReceiverAccount(null);
        paymentRequest.setPaymentType(null);
        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(paymentRequest);
        assertEquals(6, violations.size());

    }

    @Test
    public void paymentRequestNullCheckRefId() throws Exception {

        paymentRequest.setPaymentReferenceId(null);
        paymentRequest.setSenderAccount("5013040000000018");
        paymentRequest.setReceiverAccount("5184680430000006");
        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(paymentRequest);
        assertEquals(3, violations.size());
    }

    @Test
    public void paymentRequestMessageCheck() throws Exception {
        paymentRequest.setSenderAccount("1113040000000018");
        paymentRequest.setReceiverAccount("5184680430000006");
        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(paymentRequest);
        assertEquals(3, violations.size());
        violations.forEach(paymentRequestConstraintViolation -> {
            assertTrue(paymentRequestConstraintViolation.getConstraintDescriptor()
                    .getMessageTemplate().matches("(?i)Account number is not an acceptable type.|" +
                            "Sender Account is not valid."));
        });
    }

    @Test
    public void paymentRequestAllMessageCheck() throws Exception {
        paymentRequest.setAmount(null);
        paymentRequest.setSenderAccount("1113040000000018");
        paymentRequest.setReceiverAccount("5184680430000006");
        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(paymentRequest);
        assertEquals(6, violations.size());
        violations.forEach(paymentRequestConstraintViolation -> {
            assertTrue(paymentRequestConstraintViolation.getConstraintDescriptor()
                    .getMessageTemplate().matches("(?i)Values can not be null.|" +
                            "Account number is not an acceptable type.|" +
                            "Amount is Mandatory.|" +
                            "Account number is not an acceptable type.|" +
                            "Sender Account is not valid.|" +
                            "Values can not be null"));
        });
    }
}