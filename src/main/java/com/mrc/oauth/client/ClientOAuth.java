package com.mrc.oauth.client;

import com.mrc.oauth.util.PropertiesManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.Map;

import static com.mrc.oauth.util.ApplicationConstant.CLIENT;
import static com.mrc.oauth.util.ApplicationConstant.ENDPOINT_AUTH_SERVICE;
import static com.mrc.oauth.util.ApplicationConstant.ENDPOINT_RESOURCE_SERVICE;
import static com.mrc.oauth.util.ApplicationConstant.PROJECT;
import static com.mrc.oauth.util.ApplicationConstant.SERVER_AUTH_SERVICE;
import static com.mrc.oauth.util.ApplicationConstant.SERVER_RESOURCE_SERVICE;

@RestController
@RequestMapping("/api")
public class ClientOAuth {

    private HeaderBuilder headerBuilder = HeaderBuilder.INSTANCE;

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
        final String uri_auth = SERVER_AUTH_SERVICE.concat(ENDPOINT_AUTH_SERVICE);
        final String uri_resource = SERVER_RESOURCE_SERVICE.concat(ENDPOINT_RESOURCE_SERVICE);

        final Map<String, String> headers = headerBuilder.headerOAuth(ENDPOINT_AUTH_SERVICE, CLIENT, PROJECT).build();
        final HttpResponse response_auth = restClient.doRequest(uri_auth, headers, GET_METHOD);

        final Map<String, String> headers_resource = headerBuilder.headerToken(String.valueOf(response_auth.body())).build();
        final HttpResponse response_resource = restClient.doRequest(uri_resource, headers_resource, GET_METHOD);

        return String.valueOf(response_resource);
    }
}
