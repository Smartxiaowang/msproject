package c.ms.mp.Global;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName MyMvcConfig
 * @Description TODO
 * @Author Dear lin
 * @Date 16:24 2022/7/19
 * @Version 1.0
 **/
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .excludePathPatterns("/login")
                .excludePathPatterns("/loginOut")
                .addPathPatterns("/**")
        ;
    }
}

