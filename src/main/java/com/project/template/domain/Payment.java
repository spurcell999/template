package com.project.template.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Calendar;


@Entity
@Data
public class Payment implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;
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
