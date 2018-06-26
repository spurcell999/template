package com.project.template.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.ArrayList;


@Data
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {
    public String source;
    public String reason_code;
    public String description;
    public String recoverable;
    public String request_id;
    public ArrayList<Detail> details;
}
