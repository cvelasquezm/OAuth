package com.mrc.oauth.client;

import java.net.http.HttpResponse;
import java.util.Map;

public class ClientOAuth {

    private HeaderBuilder headerBuilder;
    private String token;
    private final String ENDPOINT_RESOURCE_SERVICE = "resource_server/v1/getResource";
    private final String ENDPOINT_AUTH_SERVICE = "auth_server/v1/getToken";
    private final String SERVER_RESOURCE_SERVICE = "http://localhost:8090/";
    private final String SERVER_AUTH_SERVICE = "http://localhost:8090/";
    private RestClient restClient;

    public void retrieveInformation() {
        restClient = new RestClient();
    }

    private String getResources() {
        final String uri = SERVER_RESOURCE_SERVICE + ENDPOINT_RESOURCE_SERVICE;
        final Map<String, String> headers = headerBuilder.headerToken(token).build();
        final HttpResponse response = null;// TODO getResourceService()

        return String.valueOf(response.body());
    }

    private String getToken() throws Exception {
        final String client = "client";
        final String secret = "secret";
        final String project = "project";
        final String uri = SERVER_AUTH_SERVICE + ENDPOINT_AUTH_SERVICE;
        final Map<String, String> headers = headerBuilder.headerOAuth(ENDPOINT_AUTH_SERVICE, client, secret, project).build();
        final HttpResponse response = restClient.doRequest(uri, headers, "GET");

        return String.valueOf(response.body());
    }
}