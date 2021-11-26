package br.com.bandtec.projetopi.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JWTConfig {

//    // EXPIRATION_TIME = 10 dias
//    static final long EXPIRATION_TIME = 860_000_000;
//    static final String SECRET = "checkBandtec";
//
//    public static String token(String id) {
//        String JWT = Jwts.builder()
//                .setSubject(id.toString())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .signWith(SignatureAlgorithm.HS512, SECRET)
//                .compact();
//        return JWT;
//    }

}
