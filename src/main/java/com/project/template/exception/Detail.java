package com.project.template.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


@Data
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Detail {
    public String name;
    public String value;
}
