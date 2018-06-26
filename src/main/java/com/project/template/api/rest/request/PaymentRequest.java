package com.project.template.api.rest.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.project.template.validators.PaymentAccountConstraint;
import com.project.template.validators.PaymentConstraint;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
@Data
@ApiModel(description = "Payment Request Payload",value = "payment_request")
@JacksonXmlRootElement(localName = "payment_request")
@XmlAccessorType(XmlAccessType.FIELD)
@PaymentAccountConstraint
@PaymentConstraint
public class PaymentRequest {

    @NotNull(message = "Payment Reference ID Is Mandatory.")
    @ApiModelProperty(required = true, example = "REF_111111111",notes = "Unique Payment Reference ID")
    private String paymentReferenceId;

    @Size(max = 19, min = 16, message = "Sender Account should be of minimum 16 and maximum 19.")
    @NotNull(message = "Sender Account is Mandatory.")
    @CreditCardNumber(message = "Sender Account is not valid.")
    private String senderAccount;

    @NotNull(message = "Receiver Account is Mandatory.")
    @Size(max = 19, min = 16, message = "Receiver Account should be of minimum 16 and maximum 19.")
    @CreditCardNumber(message = "Receiver Account is not valid.")
    //@LuhnCheck(startIndex= , endIndex=, checkDigitIndex=, ignoreNonDigitCharacters=)
    private String receiverAccount;

    @NotNull(message = "Amount is Mandatory.")
    //@Positive(message = "Amount need to be a positive value. Can not be 0 or negative.")
    private String amount;

    private String paymentType;

}
