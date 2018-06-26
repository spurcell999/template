package com.project.template.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.Calendar;


@Data
@RedisHash(value = "Payment")
public class RedisPayment implements Serializable{

    @Id  Long paymentId;
    @Indexed String paymentReferenceId;
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
