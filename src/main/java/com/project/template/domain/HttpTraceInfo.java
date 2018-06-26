package com.project.template.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.io.Serializable;
import java.sql.Clob;


@Entity
@Data
public class HttpTraceInfo implements Serializable{
    @Id
    private String session;
    private String openApiRequestId;
    private String partnerId;
    private String httpRequest;
    private String httpResponse;
    private String traceInfo;
    private int status;
}
