package bs.common.Global;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName MyInterceptor
 * @Description token 拦截器
 * @Author Dear lin
 * @Date 16:22 2022/7/19
 * @Version 1.0
 **/
@Component
public class MyInterceptor implements HandlerInterceptor {
    private static Logger log = LoggerFactory.getLogger(MyInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token"); // 从 http 请求头中取出 token
        if (token == null || token.equals("")) {
            response.getWriter().write("{code:-1,msg:'token is null'}");
            log.error("拦截没有token的请求接口为{},该请求人的ip地址为{}，该请求人的ip2地址为{}",request.getRequestURI(),request.getRemoteAddr(),request.getLocalAddr());
            return false;
        }
        String s = RedisCache.get(token);
        if (s == null) {
            response.getWriter().write("{code:-1,msg:'token is timeout'}");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

