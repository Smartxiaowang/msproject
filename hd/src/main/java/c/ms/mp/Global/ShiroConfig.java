package c.ms.mp.Global;
import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
/**
 * @ClassName ShiroConfig
 * @Description TODO
 * @Author Dear lin
 * @Date 19:43 2022/7/15
 * @Version 1.0
 **/
public class ShiroConfig {
    //Shiro的方言
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
    //自定义Realm
    @Bean
    public MyRealm getMyRealm(){
        MyRealm myRealm=new MyRealm();
        return myRealm;
    }
    //SecurityManager安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(MyRealm myRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //securityManager要完成校验，需要realm
        securityManager.setRealm(myRealm);
        return securityManager;
    }
    //过滤器
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean filter=new ShiroFilterFactoryBean();
        filter.setSecurityManager(defaultWebSecurityManager);
        //设置shiro的拦截规则
        //anon 匿名用户可访问   authc  认证用户可访问
        //user 使用RemeberMe的用户可访问  perms  对应权限可访问
        //role  对应的角色可访问
        Map<String,String> filterMap=new HashMap<>();
        filterMap.put("/","anon");
        filterMap.put("/login.html","anon");
        filterMap.put("/register.html","anon");
        filterMap.put("/user/login","anon");
        filterMap.put("/user/register","anon");
        filterMap.put("/static/**","anon");
        filterMap.put("/**","authc");
        filter.setFilterChainDefinitionMap(filterMap);

        filter.setLoginUrl("/login.html");
        //设置未授权页面跳转到登录页面
        filter.setUnauthorizedUrl("/login.html");
        return filter;
    }
}
