package com.project.template.services;

import com.project.template.domain.Payment;
import com.project.template.domain.RedisPayment;
import com.project.template.repositories.PaymentRepository;
import com.project.template.repositories.RedisPaymentRepository;
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
public class PaymentServiceTest {

    @InjectMocks
    PaymentService paymentService;

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    RedisPaymentRepository redisRepository;

    PaymentDTO paymentDTO = null;

    Payment payment = null;

    RedisPayment redisPayment = null;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        paymentDTO = createPaymentDTO();
        payment = createPayment();
        redisPayment = createRedisPayment();
    }

    private Payment createPayment() {
        Payment payment = new Payment();
        payment.setPaymentReferenceId("part_ref1234");
        payment.setNetwork("VISAOCT");
        payment.setNetworkProcessor("MNGS");
        payment.setStatus("APPROVED");
        payment.setCreatedBy("SYSTEM");
        payment.setCreatedTs(Calendar.getInstance());
        payment.setUpdatedBy("SYSTEM");
        payment.setUpdatedTs(Calendar.getInstance());
        return payment;
    }

    private RedisPayment createRedisPayment() {
        RedisPayment redisPayment = new RedisPayment();
        redisPayment.setPaymentReferenceId("part_ref1234");
        redisPayment.setNetwork("VISAOCT");
        redisPayment.setNetworkProcessor("MNGS");
        redisPayment.setStatus("APPROVED");
        redisPayment.setCreatedBy("SYSTEM");
        redisPayment.setCreatedTs(Calendar.getInstance());
        redisPayment.setUpdatedBy("SYSTEM");
        redisPayment.setUpdatedTs(Calendar.getInstance());
        return redisPayment;
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

    @Test
    public void testServePaymentRequest() {
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);
        PaymentDTO paymentDTOObject = paymentService.servePaymentRequest(paymentDTO);
        assertNotNull(paymentDTOObject);
        assertEquals("part_ref1234", paymentDTOObject.getPaymentReferenceId());
        assertEquals("VISAOCT", paymentDTOObject.getNetwork());
        assertEquals("MNGS", paymentDTOObject.getNetworkProcessor());
        assertEquals("APPROVED", paymentDTOObject.getStatus());
    }

    @Test
    public void testGetPaymentDetail() {
        when(paymentRepository.findByPaymentReferenceId(any(String.class))).thenReturn(payment);
        PaymentDTO paymentDTOObject = paymentService.getPaymentDetail("testref1234");
        assertNotNull(paymentDTOObject);
        assertEquals("part_ref1234", paymentDTOObject.getPaymentReferenceId());
        assertEquals("VISAOCT", paymentDTOObject.getNetwork());
        assertEquals("MNGS", paymentDTOObject.getNetworkProcessor());
        assertEquals("APPROVED", paymentDTOObject.getStatus());
    }

    @Test
    public void testServePaymentRedisRequest() {
        when(redisRepository.save(any(RedisPayment.class))).thenReturn(redisPayment);
        PaymentDTO paymentDTOObject = paymentService.servePaymentRedisRequest(paymentDTO);
        assertNotNull(paymentDTOObject);
        assertEquals("part_ref1234", paymentDTOObject.getPaymentReferenceId());
        assertEquals("VISAOCT", paymentDTOObject.getNetwork());
        assertEquals("MNGS", paymentDTOObject.getNetworkProcessor());
        assertEquals("APPROVED", paymentDTOObject.getStatus());
    }

    @Test
    public void testGetPaymentRedisDetail() {
        when(redisRepository.findByPaymentReferenceId(any(String.class))).thenReturn(redisPayment);
        PaymentDTO paymentDTOObject = paymentService.getPaymentRedisDetail("testref1234");
        assertNotNull(paymentDTOObject);
        assertEquals("part_ref1234", paymentDTOObject.getPaymentReferenceId());
        assertEquals("VISAOCT", paymentDTOObject.getNetwork());
        assertEquals("MNGS", paymentDTOObject.getNetworkProcessor());
        assertEquals("APPROVED", paymentDTOObject.getStatus());
    }

}
