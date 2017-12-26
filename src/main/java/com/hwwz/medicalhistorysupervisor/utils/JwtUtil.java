package com.hwwz.medicalhistorysupervisor.utils;

import com.hwwz.medicalhistorysupervisor.properties.JwtSetting;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private Logger log = LoggerFactory.getLogger(JwtUtil.class);

    @Autowired
    private JwtSetting jwtSetting;

    //该方法使用HS256算法和Secret:bankgl生成signKey
    private Key getKeyInstance() {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSetting.getSECRET());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        return signingKey;
    }

    //使用HS256签名算法和生成的signingKey最终的Token,claims中是有效载荷
    public String createJavaWebToken(Map<String, Object> claims) {
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+jwtSetting.getMaxAge()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, getKeyInstance());
        return builder.compact();
    }

    //解析Token，同时也能验证Token，当验证失败返回null
    public Map<String, Object> parserJavaWebToken(String jwt) {
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
