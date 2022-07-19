package c.ms.mp.Login.LoginController;

import c.ms.mp.Global.CMSException;
import c.ms.mp.Global.R;
import c.ms.mp.Global.RedisCache;
import c.ms.mp.Global.SaltUntil;
import c.ms.mp.Login.Entity.User;
import c.ms.mp.Login.LoginDao.LoginMapper;
import c.ms.mp.Login.Service.LoginServiceImpl;
import com.alibaba.fastjson2.JSON;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author Dear lin
 * @Date 15:22 2022/7/15
 * @Version 1.0
 **/

@RestController
public class LoginController {

    @Resource
    private LoginMapper l;

    @Autowired
    private LoginServiceImpl loginService;

    @GetMapping("/helloworld")
    public R hello() {
        ArrayList<HashMap<String, Object>> hashMaps = new ArrayList<>();
        HashMap<String, Object> put = new HashMap<>();
        HashMap<String, Object> put2 = new HashMap<>();
        HashMap<String, Object> put3 = new HashMap<>();
        HashMap<String, Object> put4 = new HashMap<>();
        put.put("ok", "abc1");
        put2.put("ok", "abc2");
        List<HashMap<String, Object>> someThing = loginService.getSomeThing();
        put3.put("some", someThing);
        hashMaps.add(put);
        hashMaps.add(put3);
        hashMaps.add(put2);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletableFuture.supplyAsync(() -> RedisCache.get("wang2"), executorService).whenComplete((v, e) -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            if (e == null) {
                put4.put("redis", JSON.toJSON(v));
            } else {
                put4.put("redis", "redis get error");
            }
            hashMaps.add(put4);
        });
        executorService.shutdown();

        return R.ok().data("ok", hashMaps);
    }

    @PostMapping("login")
    public R login(@RequestParam String username, @RequestParam String password) {
        User user = loginService.getUserByName(username);
        Md5Hash md5Hash = new Md5Hash(password, user.getPassWordSalt());
        if (md5Hash.toString().equals(user.getPassWord())) {
            int token = user.hashCode();
            loginService.saveToken(token);
            return R.ok().data("token", token);
        }
        //设置token2个小时过期 key 为token，value为用户有的权限

        return R.error();
    }

    @PostMapping("loginOut")
    public R loginOut(@RequestParam(required = false) String token) {
        loginService.loginOut(token);
        return R.ok();
    }

    @PostMapping("/register")
    public R register(String username, String password) {
        //注册时对密码进行加密
        String salt = SaltUntil.generateShortUUID();
        Md5Hash md5Hash = new Md5Hash(password, salt);
        int i = loginService.saveInfo(username, md5Hash.toString(), salt);
        if (i < 0) {
            return R.error().message("此用户名已经存在");
        }
        return R.ok();
    }


}
