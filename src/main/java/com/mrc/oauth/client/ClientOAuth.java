package com.mrc.oauth.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ClientOAuth {

    private HeaderBuilder headerBuilder = HeaderBuilder.INSTANCE;
    private final String ENDPOINT_RESOURCE_SERVICE = "resource_server/v1/getResource";
    private final String ENDPOINT_AUTH_SERVICE = "auth_server/v1/getToken";
    private final String SERVER_RESOURCE_SERVICE = "http://localhost:8080/";
    private final String SERVER_AUTH_SERVICE = "http://localhost:8080/";
    private final RestClient restClient = new RestClient();
    private final String GET_METHOD = "GET";

    private String getResources(String token) throws Exception {
        final String uri = SERVER_RESOURCE_SERVICE.concat(ENDPOINT_RESOURCE_SERVICE);
        final Map<String, String> headers = headerBuilder.headerToken(token).build();
        final HttpResponse response = restClient.doRequest(uri, headers, GET_METHOD);

        return String.valueOf(response.body());
    }

    @GetMapping("/token")
    public String getToken() throws Exception {
        final String client = "client";
        final String project = "project";
        final String uri = SERVER_AUTH_SERVICE.concat(ENDPOINT_AUTH_SERVICE);
        final Map<String, String> headers = headerBuilder.headerOAuth(ENDPOINT_AUTH_SERVICE, client, project).build();
        final HttpResponse response = restClient.doRequest(uri, headers, GET_METHOD);

        return String.valueOf(response.body());
    }
}
