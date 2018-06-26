package com.project.template.api.rest.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Calendar;


@Data
@JacksonXmlRootElement(localName = "payment_response")
@ApiModel(description = "Payment Response Payload",value = "payment_response")
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentResponse {
    private String paymentReferenceId;
    private String senderAccount;
    private String receiverAccount;
    private String amount;
    private String paymentType;
    private String network;
    private String networkProcessor;
    private String status;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy hh:mm:ss")
    private Calendar createdTs;
}
