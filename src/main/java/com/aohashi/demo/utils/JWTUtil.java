package com.aohashi.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {

    // 过期时间 5*60
    private static final long EXPIRE_TIME = 10*24*60*60*1000;

    //生成签名
    public  static String createToken(String username,String password) {
        try {

            Algorithm algorithm = Algorithm.HMAC256(password);
            Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
            String token = JWT.create()
                              .withClaim("username",username)
                              .withIssuer("SERVICE") //前面是谁生成，如服务器
                              .withSubject("AUTH")
                              .withAudience("WebSite")
                              .withExpiresAt(date)
                              .sign(algorithm);
            return token;
        }catch (JWTCreationException e){
            e.printStackTrace();
        }
        return null;
    }


    public static boolean verify(String token,String username,String password){
        try{
            Algorithm algorithm = Algorithm.HMAC256(password);
            JWTVerifier verifier = JWT.require(algorithm)
                                      .withClaim("username",username)
                                      .withIssuer("SERVICE")
                                      .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }catch (JWTVerificationException e){
            return false;
        }
    }

    public static String getUsername(String token){
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        }catch (JWTDecodeException e){
            return null;
        }
    }





}
