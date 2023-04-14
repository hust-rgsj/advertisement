package com.ad.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import java.util.Date;
import java.util.Map;


public class JwtUtils {
    private  static  String signKey = "ad";
    private  static  Long expire = 43200000L;

    /**
     * 生成JWT令牌
     * @param claims JWT第二部分负载 payload 中存储的内容
     * @return
     */
    public static  String generateJwt(Map<String, Object> claims){
        String jwt = Jwts.builder().
                addClaims(claims)//自定义信息（有效载荷）
                .signWith(SignatureAlgorithm.HS256,signKey)//签名算法（头部）
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
        return jwt;
    }



    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */

    public static Claims parseJwt(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)//指定签名密钥
                .parseClaimsJws(jwt)//指定令牌Token
                .getBody();
        return claims;
    }
}
