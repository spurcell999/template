package com.project.template.exception;

import com.google.common.base.CaseFormat;
import com.project.template.api.rest.response.ErrorResponse;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.lang.*;
import java.util.ArrayList;
import java.util.List;


@Data
public class ServiceException extends RuntimeException{

    private static final String SYSTEM_SOURCE = "SYSTEM";
    private static final String SYSTEM_ERROR = "SYSTEM_ERROR";
    private static final String INTERNAL_EXCEPTION_DESCRIPTION = "Internal exception occurred.";
    private static final String FALSE = "false";
    private HttpStatus status;
    private List<Error> errorList;

    public ServiceException(String message, HttpStatus status, List<Error> errorList) {
        super(message);
        this.status = status;
        this.errorList = errorList;
    }

    public ServiceException(String message, Throwable cause, HttpStatus status, List<Error> errorList) {
        super(message, cause);
        this.status = status;
        this.errorList = errorList;
    }

    public ServiceException(Throwable cause, HttpStatus status, List<Error> errorList) {
        super(cause);
        this.status = status;
        this.errorList = errorList;
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, HttpStatus status, List<Error> errorList) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
        this.errorList = errorList;
    }

    public ServiceException() {
    }

    public static List<Error> getSystemError() {
        List<Error> errorList = new ArrayList<>(1);
        Error error = new Error();
        error.setSource(SYSTEM_SOURCE);
        error.setReason_code(SYSTEM_ERROR);
        error.setDescription(INTERNAL_EXCEPTION_DESCRIPTION);
        error.setRecoverable(FALSE);
        errorList.add(error);
        return errorList;
    }

    public static List<Error> getInvalidValueError(String source, String errorCodeValue) {
        List<Error> errorList = new ArrayList<>(1);
        Error error = new Error();
        error.setSource(source);
        error.setReason_code("INVALID_VALUE");
        error.setDescription("Input Value is not valid");
        error.setRecoverable(FALSE);
        Detail detail = new Detail();
        ArrayList<Detail> detailList = new ArrayList<Detail>();
        detail.setName("ErrorDetailCode");
        detail.setValue(errorCodeValue);
        detailList.add(detail);
        error.setDetails(detailList);
        errorList.add(error);
        return errorList;
    }

    public ErrorResponse generateErrorList(List<FieldError> fieldErrors, String objectName) {
        ErrorResponse response = new ErrorResponse();
        ArrayList<Error> errorList = new ArrayList<>(fieldErrors.size());
         fieldErrors.forEach(fieldError -> {
            Error error = new Error();
            error.setRequest_id("rqst_123434343");
            error.setSource(formatField(fieldError.getField()));
            error.setReason_code("INVALID_VALUE");
            error.setDescription(formatField(fieldError.getDefaultMessage()));
            error.setRecoverable("false");
            ArrayList<Detail> detailList = new ArrayList<>();
            Detail detail = new Detail();
            detail.setName("ErrorDetailCode");
            detail.setValue("099999999");
            detailList.add(detail);
            error.setDetails(detailList);
            errorList.add(error);

        });

        response.setErrorList(errorList);

        return response;
    }

    public String formatField(String field) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field);
    }

}
