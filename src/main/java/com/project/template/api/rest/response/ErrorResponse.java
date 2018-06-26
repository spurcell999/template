package com.project.template.api.rest.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.project.template.exception.Error;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;


@JacksonXmlRootElement(localName = "Errors")
@ApiModel(description = "Error Model",value = "Errors")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private List<Error> errorList;
}
