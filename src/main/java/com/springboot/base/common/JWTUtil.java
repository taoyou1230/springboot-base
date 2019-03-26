package com.springboot.base.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * Author: Damon.CF
 * Company: UBIOT.CN
 * Date: 2018/12/29$
 * Description:
 */
public class JWTUtil {
    private static final long EXPIRE_TIME = 1 * 60 * 60 * 1000;

    public static Integer getUserId(String token, Integer userId, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("userId", userId)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("userId").asInt();
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asInt();
        } catch (Exception e) {
            return null;
        }
    }

    public static String getToken(Integer userId, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                    .withClaim("userId", userId)
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }
}
