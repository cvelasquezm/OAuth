package com.mrc.oauth.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrc.oauth.exceptions.InvalidTokenException;
import com.mrc.oauth.util.PropertiesManager;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.DatatypeConverter;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/resource_server/v1")
public class OAuthResourceServiceController {

    @GetMapping("/getResource")
    @ResponseBody
    public ResourceResponse getResource(@RequestHeader("Authorization") String token) throws Exception {
        final String INVALID_TOKEN = "Invalid token";
        final String SECRET_PROPERTY_KEYWORD = "secret";
        final String secret = PropertiesManager.INSTANCE.getProperty(SECRET_PROPERTY_KEYWORD);
        final ObjectMapper objectMapper = new ObjectMapper();

        try {
            Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                    .parseClaimsJws(token).getBody();
            return objectMapper.readValue(ResourceResponse.getSuccessResponse(), ResourceResponse.class);
        } catch (Exception e){
            throw new InvalidTokenException(INVALID_TOKEN);
        }
    }

}
