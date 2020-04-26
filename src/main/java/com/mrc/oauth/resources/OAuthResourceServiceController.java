package com.mrc.oauth.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrc.oauth.util.PropertiesManager;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.DatatypeConverter;
import java.net.http.HttpResponse;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/resource_server/v1")
public class OAuthResourceServiceController {

    @GetMapping("/getResource")
    public Response getResource(@RequestHeader("Authorization") String token) throws Exception {
        final String SECRET = "secret";
        final String secret = PropertiesManager.INSTANCE.getProperty(SECRET);
        ObjectMapper objectMapper = new ObjectMapper();
        ResourceResponse resourceResponse;
        Response.ResponseBuilder responseBuilder;
        try {
            final Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                    .parseClaimsJws(token).getBody();

            resourceResponse = objectMapper.readValue(ResourceResponse.getSuccessResponse(), ResourceResponse.class);
            responseBuilder = Response.ok(resourceResponse);
        } catch (Exception e){
            resourceResponse = objectMapper.readValue(ResourceResponse.getForbiddenResponse(), ResourceResponse.class);
            responseBuilder = Response.status(Response.Status.FORBIDDEN).entity(resourceResponse);
        }
        return responseBuilder.build();
    }

}
