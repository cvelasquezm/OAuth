package com.mrc.oauth.authorization;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auth_server/v1")
public class AuthorizationController {

    @GetMapping("/getToken")
    public String getLeaderLookupMock(@RequestHeader("hubId") String hubId,
                                      @RequestHeader("project") String project,
                                      @RequestHeader("endPoint") String endPoint,
                                      @RequestHeader("xTimestamp") String xTimestamp)
    {
        //Secret Should be storage in ENVIRONMENT VARIABLES?
        //Secret Should be retrieve from a header?
        String secret = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";
        Date issuedAt = Date.from(Instant.now());
        Date expiryDate = Date.from(Instant.now().plusSeconds(30));

        return Jwts.builder()
                .setIssuer(hubId)
                .setSubject(project)
                .claim("endPoint", endPoint)
                .claim("xTimestamp", xTimestamp)
                .setIssuedAt(issuedAt)
                .setExpiration(expiryDate)
                .signWith(
                        SignatureAlgorithm.HS256, TextCodec.BASE64.decode(secret)
                )
                .compact();
    }

}