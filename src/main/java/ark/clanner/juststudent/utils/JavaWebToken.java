package ark.clanner.juststudent.utils;


import ark.clanner.juststudent.config.Constant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * Created by Clanner on 2018/4/26.
 * jwt工具类
 * 有时间把subject存到redis
 */
public class JavaWebToken {

    //管理员key
    public static final String ADMIN_KEY = "Clanner_admin_key";
    //普通用户key
    public static final String USER_KEY = "Clanner_user_key";

    /**
     * @param id        用户id
     * @param issuer    签发者
     * @param subject   接收者
     * @param ttlMillis 过期时间
     * @return
     */
    public static String createJWT(String id, String issuer, String subject, long ttlMillis, String key) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    //解析验证
    public static boolean parseJWT(String jwt, String key) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(key))
                .parseClaimsJws(jwt).getBody();
        switch (key) {
            case ADMIN_KEY:
                return claims.getSubject().equals(Constant.ADMINS.get(Constant.ADMIN + claims.getId()));
            case USER_KEY://暂时不做普通用户，直接返回true
                return true;
            default:
                return false;
        }
    }
}
