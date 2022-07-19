package c.ms.mp.Login.Service;

import c.ms.mp.Login.Entity.User;
import c.ms.mp.Login.LoginDao.Login2Mapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName LoginServiceImpl
 * @Description 登录所有逻辑的impl
 * @Author Dear lin
 * @Date 20:18 2022/7/15
 * @Version 1.0
 **/
@Service
@DS("master")
public class LoginServiceImpl {

    @Autowired
    private Login2Mapper loginMapper;

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    public int saveInfo(User u) {
        return loginMapper.saveInfo(u);
    }

    public void checkLogin(String username, String Password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, Password);
        subject.login(token);
    }
    public List<HashMap<String,Object>> getSomeThing() {
        return loginMapper.getSomeThing();
    }
    @DS("slave2")
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    public int saveSecInfo(User wang2)  {
        int a = 0/0;
        return loginMapper.saveSecInfo(wang2);
    }
}
