package com.nhoclahola.bt212_graphql.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

public class JwtUtils
{
    private static final String SECRET = "3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b";

    public static Key getSigningKey()
    {
        return new SecretKeySpec(SECRET.getBytes(), "HmacSHA256");
    }

    public static String generateToken(String username) throws JOSEException
    {
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("your-app")
                .expirationTime(new Date(new Date().getTime() + 3600 * 1000)) // 1 giờ
                .build();

        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        SignedJWT signedJWT = new SignedJWT(header, claims);
        signedJWT.sign(new MACSigner(getSigningKey().getEncoded()));

        return signedJWT.serialize();
    }

    public static boolean validateToken(String token)
    {
        try
        {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.verify(new MACVerifier(getSigningKey().getEncoded())) &&
                    new Date().before(signedJWT.getJWTClaimsSet().getExpirationTime());
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public static String getUsernameFromToken(String token)
    {
        try
        {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.getJWTClaimsSet().getSubject(); // Lấy "username" từ "sub"
        }
        catch (Exception e)
        {
            throw new RuntimeException("Invalid JWT token");
        }
    }

    public static long getExpiredTimeFromToken(String token)
    {
        try
        {
            // Phân tích token
            SignedJWT signedJWT = SignedJWT.parse(token);
            // Lấy giá trị "exp" từ JWT claims dưới dạng long
            return signedJWT.getJWTClaimsSet().getExpirationTime().getTime();
        }
        catch (Exception e)
        {
            throw new RuntimeException("Invalid JWT token", e);
        }
    }
}
