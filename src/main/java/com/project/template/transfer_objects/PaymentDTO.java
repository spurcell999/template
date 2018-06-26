package com.project.template.transfer_objects;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;


@Data
public class PaymentDTO {
    private String paymentId;
    private String paymentReferenceId;
    private String senderAccount;
    private String receiverAccount;
    private String amount;
    private String partnerId;
    private String paymentType;
    private String network;
    private String networkProcessor;
    private String status;
    private Calendar createdTs;
    private String createdBy;
    private Calendar updatedTs;
    private String updatedBy;
}
