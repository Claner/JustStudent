package ark.clanner.juststudent.config;

import ark.clanner.juststudent.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Clanner on 2018/5/3.
 * 拦截器配置
 */
@Configuration
public class WebInterceptor implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/Admin/**")
                .excludePathPatterns("/Admin/login");
    }
}
