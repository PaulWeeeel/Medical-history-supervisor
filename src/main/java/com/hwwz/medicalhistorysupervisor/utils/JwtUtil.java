package com.hwwz.medicalhistorysupervisor.utils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 *@Author: PaulWell
 *@Description:
 *@Date: 22:50 2017/12/19
 */

@Component
public class JwtUtil {
    private static Logger log = LoggerFactory.getLogger(JwtUtil.class);

    private static String SECRET="qwe123asd456zxc789t";
    private static Long maxAge=15000l;


    //该方法使用HS256算法和Secret:bankgl生成signKey
    private static Key getKeyInstance() {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        return signingKey;
    }

    //使用HS256签名算法和生成的signingKey最终的Token,claims中是有效载荷
    public static String createJavaWebToken(Map<String, Object> claims) {
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+maxAge))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, getKeyInstance());
        return builder.compact();
    }

    //解析Token，同时也能验证Token，当验证失败返回null
    public static Map<String, Object> parserJavaWebToken(String jwt) {
        try {
            Map<String, Object> jwtClaims =
                    Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwt).getBody();
            return jwtClaims;
        }
        catch (ExpiredJwtException e) {
            log.error("json web token expired");
            return null;
        }
        catch (Exception e) {
            log.error("json web token verify failed");
            return null;
        }

    }
}
