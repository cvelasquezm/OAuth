package com.mrc.oauth.util;

public class ApplicationConstant {

    public static final String ENDPOINT_RESOURCE_SERVICE = PropertiesManager.INSTANCE.getProperty("resource.service.endpoint");
    public static final String ENDPOINT_AUTH_SERVICE = PropertiesManager.INSTANCE.getProperty("resource.auth.endpoint");
    public static final String SERVER_RESOURCE_SERVICE = PropertiesManager.INSTANCE.getProperty("resource.service.server");
    public static final String SERVER_AUTH_SERVICE = PropertiesManager.INSTANCE.getProperty("resource.auth.server");
    public static final String CLIENT = PropertiesManager.INSTANCE.getProperty("clientId");
    public static final String PROJECT = PropertiesManager.INSTANCE.getProperty("project");

}
