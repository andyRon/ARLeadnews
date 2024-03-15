package top.andyron.search.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.andyron.search.interceptor.AppTokenInterceptor;

/**
 * @author andyron
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加自定义的拦截器，拦截所有请求
        registry.addInterceptor(new AppTokenInterceptor()).addPathPatterns("/**");
    }
}
