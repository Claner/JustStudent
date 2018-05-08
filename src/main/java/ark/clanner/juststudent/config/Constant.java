package ark.clanner.juststudent.config;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Clanner on 2018/5/8.
 */
public final class Constant {
    public static final ConcurrentHashMap<String, String> ADMINS = new ConcurrentHashMap<>();

    public static final String ADMIN = "admin_";

    public static final String JWT_KEY = "jwt_token";
    public static final String ISSUER = "JustStudent";
    public static final String START = "Clanner";
    public static final String END = "Wang";
}
