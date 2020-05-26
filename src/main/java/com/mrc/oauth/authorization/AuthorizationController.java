package com.mrc.oauth.authorization;

import com.mrc.oauth.util.PropertiesManager;
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

    private final String SECRET_PROPERTY_KEYWORD = "secret";

    @GetMapping("/getToken")
    public String getAccessToken(@RequestHeader("clientId") String clientId,
                                 @RequestHeader("project") String project,
                                 @RequestHeader("endPoint") String endPoint,
                                 @RequestHeader("xTimestamp") String xTimestamp)
    {
        final String secret = PropertiesManager.INSTANCE.getProperty(SECRET_PROPERTY_KEYWORD);
        final Date issuedAt = Date.from(Instant.now());
        final Date expiryDate = Date.from(Instant.now().plusSeconds(30));

        return Jwts.builder()
                .setIssuer(clientId)
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