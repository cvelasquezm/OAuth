package com.mrc.oauth.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class RestClient {

    public HttpResponse<String> doRequest(String url, Map<String, String> headers, String method) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().uri(URI.create(url));
        addHeaders(headers, requestBuilder);
        HttpRequest request = requestBuilder.build();
        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() != 200){
            throw new Exception("");
        }

        return response;
    }

    protected HttpRequest.Builder addHeaders(Map<String, String> headers, HttpRequest.Builder requestBuilder) {
        headers.forEach((k, v) -> {
            requestBuilder.setHeader(k, v);
        });
        return requestBuilder;
    }

}
