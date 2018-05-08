package ark.clanner.juststudent.interceptor;

import ark.clanner.juststudent.config.Constant;
import ark.clanner.juststudent.utils.JavaWebToken;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Clanner on 2018/5/5.
 * 登陆拦截器
 */
public class LoginInterceptor implements HandlerInterceptor{
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals(Constant.JWT_KEY)){
                return JavaWebToken.parseJWT(c.getValue(),JavaWebToken.ADMIN_KEY);
            }
        }
        return false;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
