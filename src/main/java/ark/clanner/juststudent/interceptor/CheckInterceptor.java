package ark.clanner.juststudent.interceptor;

import ark.clanner.juststudent.utils.EncodeUtil;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Created by Clanner on 2018/5/3.
 */
public class CheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        PrintWriter out = null;
//        try {
//            out = response.getWriter();
//            if (checkSignature(signature, timestamp, nonce)) {
//                out.write(echostr);
//                return true;
//            }
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } finally {
//            out.close();
//        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }

//    public boolean checkSignature(String signature, String timestamp, String nonce) {
//        String[] str = new String[]{EncodeUtil.TOKEN, timestamp, nonce};
//        //排序
//        Arrays.sort(str);
//        //拼接字符串
//        StringBuffer buffer = new StringBuffer();
//        for (int i = 0; i < str.length; i++) {
//            buffer.append(str[i]);
//        }
//        //进行sha1加密
//        String temp = EncodeUtil.SHA1(buffer.toString());
//        //与微信提供的signature进行匹对
//        return signature.equals(temp);
//    }
}
