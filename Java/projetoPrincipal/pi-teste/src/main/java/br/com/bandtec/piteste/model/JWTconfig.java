package br.com.bandtec.piteste.model;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JWTconfig {

    static final long EXPIRATION_TIME = 86400000;
    static final String SECRET = "CheckBandtec";

    public static String generateToken(Integer id) {
        String JWT = Jwts.builder()
                .setSubject(id.toString())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return JWT;
    }

    public static Integer parseToken(String token) {
        Integer id = null;
        if (token != null) {
            id = Integer.parseInt(Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject());
        }
        return id;
    }

}
