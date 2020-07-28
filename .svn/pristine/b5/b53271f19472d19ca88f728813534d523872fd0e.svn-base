package com.jfast.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jfast.vo.AccessToken;

public class JWTHelper {
    /**
     * 加密密钥
     */
    private static final String  SECRET = "session_secret";


    /**
     * 默认过期30min
     */
    public static final long EXPIRE_TIME =  60 * 60 * 1000;
    private static final String JWT_ISSUER = "JWT";
    private static final String ID_CLAIM = "serial_no";

    /**
     * 生成token
     *
     * @param id         用户id
     * @param expireTime 过期时间（毫秒ms）
     * @return java.lang.String
     */
    public static String sign(String serialNo, long expireTime) {
        try {
            Map<String, Object> headerClaims = new HashMap<String, Object>(2);
            headerClaims.put("alg", "HS256");
            headerClaims.put("typ", "JWT");
            long currentTimeMillis = System.currentTimeMillis();
            Date expireDate = new Date(currentTimeMillis + expireTime);
            // 附带username信息
            return JWT.create()
                    .withHeader(headerClaims)
                    .withIssuer(JWT_ISSUER)
                    .withClaim(ID_CLAIM, serialNo)
                    .withIssuedAt(new Date(currentTimeMillis))
                    .withExpiresAt(expireDate)
                    .sign(Algorithm.HMAC256(SECRET));
        } catch (UnsupportedEncodingException e) {
            return null;
        } catch (JWTCreationException exception) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 验证token
     *
     * @param token token值
     * @return AccessToken
     */
    public static AccessToken verify(String token) {
        AccessToken result = new AccessToken();
        result.setToken(token);
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).withIssuer(JWT_ISSUER).build();
            DecodedJWT jwt = verifier.verify(token);
            result.setVerify(Boolean.TRUE);
            result.setSerialNo(jwt.getClaim(ID_CLAIM).asString());
            result.setSignDate(jwt.getIssuedAt());
            result.setExpireDate(jwt.getExpiresAt());
            result.setExpire(new Date().compareTo(jwt.getExpiresAt()) > 0 ? false : true);
        } catch (TokenExpiredException e) {
            DecodedJWT jwt = JWT.decode(token);
            result.setVerify(Boolean.TRUE);
            result.setExpire(Boolean.FALSE);
            result.setSerialNo(jwt.getClaim(ID_CLAIM).asString());
            result.setSignDate(jwt.getIssuedAt());
            result.setExpireDate(jwt.getExpiresAt());
        } catch (JWTVerificationException e) {
            result.setVerify(Boolean.FALSE);
        } catch (Exception e) {
            result.setVerify(Boolean.FALSE);
        }
        return result;
    }

    /**
     * 验证token
     *
     * @param token token值
     * @return boolean
     */
    public static boolean verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
                    .withIssuer(JWT_ISSUER)
                    .build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 校验token
     *
     * @param token token值
     * @param id    用户的id
     * @return boolean
     */
    public static boolean verify(String token, Long id) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
                    .withIssuer(JWT_ISSUER)
                    .withClaim(ID_CLAIM, id)
                    .build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取用户id
     *
     * @param token token值
     * @return java.lang.Long
     */
    public static String getSerialNo(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(ID_CLAIM).asString();
        } catch (JWTDecodeException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 获取颁发时间
     *
     * @param token token值
     * @return java.util.Date
     */
    public static Date getIssuedDate(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getIssuedAt();
        } catch (JWTDecodeException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 获取过期时间
     *
     * @param token token值
     * @return java.util.Date
     */
    public static Date getExpireDate(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getExpiresAt();
        } catch (JWTDecodeException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 判断是否过期
     *
     * @param token token值
     * @return boolean
     */
    public static boolean isExpire(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getExpiresAt().compareTo(new Date()) <= 0 ? true : false;
        } catch (JWTDecodeException e) {
            return true;
        } catch (Exception e) {
            return true;
        }
    }
}
