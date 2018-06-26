package com.project.template.api.rest;

import com.project.template.api.rest.request.PaymentRequest;
import com.project.template.api.rest.response.PaymentResponse;
import com.project.template.delegators.PaymentDelegator;
import com.project.template.exception.ServiceException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/send/v1")
@Slf4j
@Api(tags = "Payment-Services")
public class PaymentRest extends BaseRest {

    @Autowired
    PaymentDelegator delegator;

    @RequestMapping(value = "/create-payment",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a Payment Transfer",notes = "Returns the Payment resource created or system error message")
    public  @ResponseBody
    PaymentResponse createPayment(@Valid @RequestBody PaymentRequest request,
                                  @RequestHeader(value = X_OPENAPI_CLIENTID,required = false) String openApiClientId,
                                  @RequestHeader(value = ACCEPT, defaultValue = MediaType.APPLICATION_JSON_VALUE) String accept,
                                  @RequestHeader(value = CONTENT_TYPE,required = true) String contentType,
                                  @RequestHeader(value = X_OPENAPI_TRANID, required = false) String openApiRqstId
    ) throws ServiceException {
        log.info("Handling Create Payment Request....delegating to service layer.");
        //TODO: Map header and parameter fields to request object before passing to delegator.
        return delegator.delegatePaymentRequest(request);
    }

    @RequestMapping(value = "/{partnerId}/create-payment",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a Payment Transfer with partner id",notes = "Returns the Payment resource created or system error message")
    public  @ResponseBody PaymentResponse createPayment(@RequestBody PaymentRequest request,
                                                        @RequestHeader(value = X_OPENAPI_CLIENTID, required = false) String openApiClientId,
                                                        @RequestHeader(value = ACCEPT, defaultValue = MediaType.APPLICATION_JSON_VALUE) String accept,
                                                        @RequestHeader(value = CONTENT_TYPE, required = true) String contentType,
                                                        @RequestHeader(value = X_OPENAPI_TRANID,required = false) String openApiRqstId,
                                                        @ApiParam(value = "Partner ID",required = true)
                                                        @PathVariable(value = PARTNER_ID, required = true) String partner_id
    )throws ServiceException {
        log.info("Handling Create Payment Request....delegating to service layer.");
        //TODO: Map header and parameter fields to request object before passing to delegator.
        return delegator.delegatePaymentRequest(request);
    }

    @RequestMapping(value = "/get-payment/{reference_id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Payment Details",notes = "Returns the Payment resource available in system or system error message")
    public  @ResponseBody PaymentResponse createPayment(@RequestHeader(value = X_OPENAPI_CLIENTID, required = false) String openApiClientId,
                                                        @RequestHeader(value = ACCEPT, defaultValue = MediaType.APPLICATION_JSON_VALUE) String accept,
                                                        @RequestHeader(value = CONTENT_TYPE, required = false) String contentType,
                                                        @RequestHeader(value = X_OPENAPI_TRANID, required = false) String openApiRqstId,
                                                        @ApiParam(value = "Transaction Reference ID",required = true)
                                                        @PathVariable(value = REFERENCE_ID, required = true) String reference_id
    )throws ServiceException {
        log.info("Handling Get Payment Detail....delegating to service layer.");
        //TODO: Map header and parameter fields to request object before passing to delegator.
        return delegator.getPaymentDetails(reference_id);
    }

    @RequestMapping(value = "/create-payment-redis",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public  @ResponseBody PaymentResponse createPaymentRedis(@RequestBody PaymentRequest request,
                                                        @RequestHeader(value = X_OPENAPI_CLIENTID,required = false) String openApiClientId,
                                                        @RequestHeader(value = ACCEPT, defaultValue = MediaType.APPLICATION_JSON_VALUE) String accept,
                                                        @RequestHeader(value = CONTENT_TYPE,required = true) String contentType,
                                                        @RequestHeader(value = X_OPENAPI_TRANID, required = false) String openApiRqstId
    ) throws ServiceException {
        log.info("Handling Create Payment Redis Request....delegating to service layer.");
        //TODO: Map header and parameter fields to request object before passing to delegator.
        return delegator.delegatePaymentRedisRequest(request);
    }

    @RequestMapping(value = "/get-payment-redis/{reference_id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public  @ResponseBody PaymentResponse createPaymentRedis(@RequestHeader(value = X_OPENAPI_CLIENTID, required = false) String openApiClientId,
                                                        @RequestHeader(value = ACCEPT, defaultValue = MediaType.APPLICATION_JSON_VALUE) String accept,
                                                        @RequestHeader(value = CONTENT_TYPE, required = false) String contentType,
                                                        @RequestHeader(value = X_OPENAPI_TRANID, required = false) String openApiRqstId,
                                                        @PathVariable(value = REFERENCE_ID, required = true) String reference_id
    )throws ServiceException {
        log.info("Handling Get Payment Detail....delegating to service layer.");
        //TODO: Map header and parameter fields to request object before passing to delegator.
        return delegator.getPaymentRedisDetails(reference_id);
    }
}
