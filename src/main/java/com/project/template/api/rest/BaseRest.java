package com.project.template.api.rest;

/**
 *
 */
public class BaseRest {
    private static final int OPENAPI_CLIENT_ID_SIZE = 250;
    private static final int OPENAPI_CLIENT_ID_TRIMMED_SIZE = 48;
    private static final int OPEN_API_REQST_ID_MAX_SIZE = 22;
    public static final String X_OPENAPI_CLIENTID = "x-openapi-clientid";
    public static final String X_OPENAPI_TRANID = "x-openapi-transid";
    public static final String X_RQST_ID = "x-rqst-id";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String ACCEPT ="Accept";
    public static final String PARTNER_ID = "partnerId";
    public static final String REFERENCE_ID = "reference_id";
}
