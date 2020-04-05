package com.mrc.oauth;

import java.net.http.HttpResponse;
import java.util.Map;

public class ClientOAuth {

    private HeaderBuilder headerBuilder;
    private String token;
    private final String ENDPOINT_RESOURCE_SERVICE = "resource_server/v1/getResource";
    private final String ENDPOINT_AUTH_SERVICE = "auth_server/v1/getToken";
    private final String SERVER_RESOURCE_SERVICE = "http://localhost:8090/";
    private final String SERVER_AUTH_SERVICE = "http://localhost:8090/";

    public void retrieveInformation() throws InterruptedException {
        token = getToken();
        String status = getResources();
        System.out.println(status);

    }

    private String getResources() throws InterruptedException {
        final String uri = SERVER_RESOURCE_SERVICE + ENDPOINT_RESOURCE_SERVICE;
        final Map<String, String> headers = headerBuilder.headerToken(token).build();
        final HttpResponse response = null;// TODO getResourceService()

        return String.valueOf(response.body());
    }

    private String getToken() throws InterruptedException {
        final String client = "client";
        final String secret = "secret";
        final String project = "project";
        final String uri = SERVER_AUTH_SERVICE + ENDPOINT_AUTH_SERVICE;
        final Map<String, String> headers = headerBuilder.headerOAuth(ENDPOINT_AUTH_SERVICE, secret, project).build();
        final HttpResponse response = null;// TODO getToken()

        return String.valueOf(response.body());
    }

}
