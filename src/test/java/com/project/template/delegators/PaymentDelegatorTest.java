package com.project.template.delegators;

import com.project.template.api.rest.request.PaymentRequest;
import com.project.template.api.rest.response.PaymentResponse;
import com.project.template.services.PaymentService;
import com.project.template.transfer_objects.PaymentDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by e074765 on 6/12/2018.
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PaymentDelegatorTest {

    @InjectMocks
    PaymentDelegator paymentDelegator;

    @Mock
    PaymentService service;

    PaymentDTO paymentDTO = null;

    PaymentRequest paymentRequest = null;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        paymentDTO = createPaymentDTO();
        paymentRequest = createPaymentRequest();
    }

    private PaymentDTO createPaymentDTO() {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPaymentReferenceId("part_ref1234");
        paymentDTO.setNetwork("VISAOCT");
        paymentDTO.setNetworkProcessor("MNGS");
        paymentDTO.setStatus("APPROVED");
        paymentDTO.setCreatedBy("SYSTEM");
        paymentDTO.setCreatedTs(Calendar.getInstance());
        paymentDTO.setUpdatedBy("SYSTEM");
        paymentDTO.setUpdatedTs(Calendar.getInstance());
        return paymentDTO;
    }

    private PaymentRequest createPaymentRequest() {
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setPaymentReferenceId("part_ref1234");
        paymentRequest.setPaymentType("CREDIT");
        paymentRequest.setAmount("100");
        return paymentRequest;
    }

    @Test
    public void testDelegatePaymentRequest() {
        when(service.servePaymentRequest(any(PaymentDTO.class))).thenReturn(paymentDTO);
        PaymentResponse paymentResponse = paymentDelegator.delegatePaymentRequest(paymentRequest);
        assertEquals("part_ref1234", paymentResponse.getPaymentReferenceId());
        assertEquals("VISAOCT", paymentResponse.getNetwork());
        assertEquals("MNGS", paymentResponse.getNetworkProcessor());
        assertEquals("APPROVED", paymentResponse.getStatus());
    }

    @Test
    public void testGetPaymentDetails() {
        when(service.getPaymentDetail(any(String.class))).thenReturn(paymentDTO);
        PaymentResponse paymentResponse = paymentDelegator.getPaymentDetails("ref_1234");
        assertEquals("part_ref1234", paymentResponse.getPaymentReferenceId());
        assertEquals("VISAOCT", paymentResponse.getNetwork());
        assertEquals("MNGS", paymentResponse.getNetworkProcessor());
        assertEquals("APPROVED", paymentResponse.getStatus());
    }


    @Test
    public void testRedisDelegatePaymentRequest() {
        when(service.servePaymentRedisRequest(any(PaymentDTO.class))).thenReturn(paymentDTO);
        PaymentResponse paymentResponse = paymentDelegator.delegatePaymentRedisRequest(paymentRequest);
        assertEquals("part_ref1234", paymentResponse.getPaymentReferenceId());
        assertEquals("VISAOCT", paymentResponse.getNetwork());
        assertEquals("MNGS", paymentResponse.getNetworkProcessor());
        assertEquals("APPROVED", paymentResponse.getStatus());
    }

    @Test
    public void testRedisGetPaymentDetails() {
        when(service.getPaymentRedisDetail(any(String.class))).thenReturn(paymentDTO);
        PaymentResponse paymentResponse = paymentDelegator.getPaymentRedisDetails("ref_1234");
        assertEquals("part_ref1234", paymentResponse.getPaymentReferenceId());
        assertEquals("VISAOCT", paymentResponse.getNetwork());
        assertEquals("MNGS", paymentResponse.getNetworkProcessor());
        assertEquals("APPROVED", paymentResponse.getStatus());
    }
}
