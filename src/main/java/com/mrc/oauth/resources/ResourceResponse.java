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

    public static String getFailureResponse(){
        return "{\"status\" : \"500\", \"message\" : \"An error has occurred\"}";
    }

    public static String getForbiddenResponse(){
        return "{\"status\" : \"403\", \"message\" : \"Prohibited access\"}";
    }
}
