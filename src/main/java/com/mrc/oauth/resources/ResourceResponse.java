package com.mrc.oauth.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResourceResponse {

    @JsonProperty(value = "status")
    private String status;
    @JsonProperty(value = "message")
    private String message;

    public static String getSuccessResponse(){
        return "{\"status\" : \"200\", \"message\" : \"Authorized access\"}";
    }
}
