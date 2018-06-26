package com.project.template.api.rest;

import com.project.template.api.rest.request.PaymentRequest;
import com.project.template.api.rest.response.PaymentResponse;
import com.project.template.delegators.PaymentDelegator;
import com.project.template.exception.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by e074765 on 6/12/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentRestTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    PaymentDelegator delegator;

    private MockMvc mockMvc;

    PaymentResponse paymentResponse = null;

    private String jsonText = "{\n" +
            "\t\"payment_reference_id\" : \"abc_ref124\",\n" +
            "\t\"sender_account\" : \"5102589999999905\",\n" +
            "\t\"receiver_account\" : \"4007880000000752\",\n" +
            "\t\"amount\" : \"41\",\n" +
            "\t\"payment_type\" : \"P2P\",\n" +
            "\t\"asd\" : \"asd\"\n" +
            "}";

    private String malformedJsonText = "\n" +
            "\t\"payment_reference_id\" : \"abc_ref124\",\n" +
            "\t\"sender_account\" : \"1111111112123\",\n" +
            "\t\"receiver_account\" : \"222222222324212312321\",\n" +
            "\t\"amount\" : \"41\",\n" +
            "\t\"payment_type\" : \"P2P\",\n" +
            "\t\"asd\" : \"asd\"\n" +
            "}";

    private String xmlText = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<root>\n" +
            "   <amount>41</amount>\n" +
            "   <asd>asd</asd>\n" +
            "   <payment_reference_id>abc_ref124</payment_reference_id>\n" +
            "   <payment_type>P2P</payment_type>\n" +
            "   <receiver_account>4007880000000752</receiver_account>\n" +
            "   <sender_account>5102589999999905</sender_account>\n" +
            "</root>";

    private String malformedXmlText = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<root>\n" +
            "   <amount>41</amount>\n" +
            "   <asd>asd</asd>\n" +
            "   <payment_reference_id>abc_ref124</payment_reference_id>\n" +
            "   <payment_type>P2P</payment_type>\n" +
            "   <receiver_account>4007880000000752</receiver_account>\n" +
            "   <sender_account>1111111112123</sender_account>\n" +
            "</root>";

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        paymentResponse = createPaymentResponse();
    }

    private PaymentResponse createPaymentResponse() {
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setPaymentReferenceId("part_ref_1234");
        paymentResponse.setNetworkProcessor("MNGS");
        paymentResponse.setNetwork("VISAOCT");
        paymentResponse.setAmount("1234");
        return paymentResponse;
    }

    @Test
    public void testCreatePaymentHappyPathJson() throws Exception {
        given(delegator.delegatePaymentRequest(any(PaymentRequest.class))).willReturn(paymentResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/send/v1/create-payment")
                .content(jsonText)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreatePaymentBadRequestJson() throws Exception {
        given(delegator.delegatePaymentRequest(any(PaymentRequest.class))).willReturn(paymentResponse);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/send/v1/create-payment")
                .content(malformedJsonText)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testCreatePaymentHappyPathXML() throws Exception {
        given(delegator.delegatePaymentRequest(any(PaymentRequest.class))).willReturn(paymentResponse);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/send/v1/create-payment")
                .content(xmlText)
                .contentType(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_XML);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreatePaymentScenarioBadRequestXML() throws Exception {
        given(delegator.delegatePaymentRequest(any(PaymentRequest.class))).willReturn(paymentResponse);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/send/v1/create-payment")
                .content(malformedXmlText)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testPartnerCreatePaymentHappyPathJson() throws Exception {
        given(delegator.getPaymentDetails(any(String.class))).willReturn(paymentResponse);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/send/v1/ref_1234/create-payment")
                .content(jsonText)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void testPartnerCreatePaymentBadRequestJson() throws Exception {
        given(delegator.getPaymentDetails(any(String.class))).willReturn(paymentResponse);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/send/v1/ref_1234/create-payment")
                .content(malformedJsonText)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testPartnerCreatePaymentHappyPathXML() throws Exception {
        given(delegator.getPaymentDetails(any(String.class))).willReturn(paymentResponse);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/send/v1/ref_1234/create-payment")
                .content(xmlText)
                .contentType(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_XML);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void testPartnerCreatePaymentScenarioBadRequestXML() throws Exception {
        given(delegator.getPaymentDetails(any(String.class))).willReturn(paymentResponse);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/send/v1/ref_1234/create-payment")
                .content(malformedXmlText)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testRedisCreatePaymentHappyPathJson() throws Exception {

        given(delegator.delegatePaymentRedisRequest(any(PaymentRequest.class))).willReturn(paymentResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/send/v1/create-payment-redis")
                .content(jsonText)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void testRedisCreatePaymentBadRequestJson() throws Exception {
        given(delegator.delegatePaymentRedisRequest(any(PaymentRequest.class))).willReturn(paymentResponse);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/send/v1/create-payment-redis")
                .content(malformedJsonText)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testRedisCreatePaymentHappyPathXML() throws Exception {
        given(delegator.delegatePaymentRedisRequest(any(PaymentRequest.class))).willReturn(paymentResponse);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/send/v1/create-payment-redis")
                .content(xmlText)
                .contentType(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_XML);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void testRedisCreatePaymentScenarioBadRequestXML() throws Exception {
        given(delegator.delegatePaymentRedisRequest(any(PaymentRequest.class))).willReturn(paymentResponse);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/send/v1/create-payment-redis")
                .content(malformedXmlText)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetPaymentHappyPathJson() throws Exception {
        given(delegator.getPaymentDetails(anyString())).willReturn(paymentResponse);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/send/v1/get-payment/ref123")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPaymentBadRequestJson() throws Exception {
        given(delegator.getPaymentDetails(anyString())).willThrow(new ServiceException("BAD Request!",HttpStatus.BAD_REQUEST,null));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/send/v1/get-payment/ref989898")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetPaymentHappyPathXML() throws Exception {
        given(delegator.getPaymentDetails(anyString())).willReturn(paymentResponse);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/send/v1/get-payment/ref123")
                .contentType(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_XML);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPaymentBadRequestXML() throws Exception {
        given(delegator.getPaymentDetails(anyString())).willThrow(new ServiceException("BAD Request!",HttpStatus.BAD_REQUEST,null));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/send/v1/get-payment/ref546567")
                .contentType(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_XML);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}
