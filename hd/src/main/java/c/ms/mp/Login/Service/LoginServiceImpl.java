package c.ms.mp.Login.Service;

import c.ms.mp.Global.CMSException;
import c.ms.mp.Global.RedisCache;
import c.ms.mp.Global.ThreadPoolHelp;
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

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;

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

    /*
     * @Description: 注册用户
     * @Author: Dear lin
     * @Date: 2022/7/19 16:06
     * @Param: [uname, md5pas, salt]
     * @Return: int
     */
    @Transactional(rollbackFor = Exception.class)
    public int saveInfo(String uname, String md5pas, String salt) {
        try {
            return loginMapper.saveUserInfo(uname, md5pas, salt);
        }catch (Exception e) {
            return -1;
        }

    }

    public void checkLogin(String username, String Password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, Password);
        subject.login(token);
    }

    public List<HashMap<String, Object>> getSomeThing() {
        return loginMapper.getSomeThing();
    }

    @DS("slave2")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public int saveSecInfo(User wang2) {
        int a = 0 / 0;
        return loginMapper.saveSecInfo(wang2);
    }

    /*
     * @Description: 登录的时候检查数据库中是否有此人
     * @Author: Dear lin
     * @Date: 2022/7/19 16:06
     * @Param: [username]
     * @Return: c.ms.mp.Login.Entity.User
     */
    public User getUserByName(String username) {
        User u = loginMapper.selectByUserName(username);
        if (u == null) {
            throw new CMSException(-1, "用户不存在");
        }
        return u;
    }

    /*
     * @Description: 登出
     * @Author: Dear lin
     * @Date: 2022/7/19 16:05
     * @Param: [token]
     * @Return: void
     */
    public void loginOut(String token) {
        ExecutorService fixed = ThreadPoolHelp.getInstance("fixed");
        assert fixed != null;
        fixed.submit(() -> {
            RedisCache.del(token);
        });
    }
    /*
     * @Description: 保存token
     * @Author: Dear lin
     * @Date: 2022/7/19 16:05
     * @Param: [token]
     * @Return: void
     */
    public void saveToken(int token) {
        ThreadPoolHelp.getInstance("fixed").submit(()->{
            RedisCache.setex(String.valueOf(token), 60 * 60 * 2, "role");
        });

    }
}
