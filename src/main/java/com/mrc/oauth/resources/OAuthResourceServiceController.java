package com.mrc.oauth.resources;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.DatatypeConverter;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/resource_server/v1")
public class OAuthResourceServiceController {

    @GetMapping("/getResource")
    public String getLeaderLookupMock(@RequestHeader("Authorization") String token) throws Exception {
        //Secret Should be storage in ENVIRONMENT VARIABLES?
        //Secret Should be retrieve from a header?
        String secret = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                    .parseClaimsJws(token).getBody();
            return "Success";
        } catch (Exception e){
            return "Forbidden";
        }
    }

}
