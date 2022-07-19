package c.ms.mp.Login.LoginController;

import c.ms.mp.Global.CMSException;
import c.ms.mp.Global.R;
import c.ms.mp.Global.RedisCache;
import c.ms.mp.Login.Entity.User;
import c.ms.mp.Login.LoginDao.LoginMapper;
import c.ms.mp.Login.Service.LoginServiceImpl;
import com.alibaba.fastjson2.JSON;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
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
        int id = loginService.saveInfo(new User("wang2"));
        int id2 = loginService.saveSecInfo(new User("wang2"));
        HashMap<String, Object> put6 = new HashMap<>();
        HashMap<String, Object> put5 = new HashMap<>();
        put6.put("id", id);
        User user = l.selectById(id);
        put5.put("user", JSON.toJSON(user));
        hashMaps.add(put6);
        hashMaps.add(put5);
        return R.ok().data("ok", hashMaps);
    }

    @PostMapping("login")
    public R login(@RequestParam(required = false) String username, @RequestParam(required = false) String password) {
        Jedis jedis = RedisCache.getJedis(1);
        jedis.hset("-1000", "demo", "wangwang:01");
        //设置token2个小时过期 key 为token，value为用户有的权限
        jedis.setex("token", 60 * 60 * 2, "role");
        jedis.close();
        return R.ok().data("token", "role");
    }

    @PostMapping("loginOut")
    public R loginOut(@RequestParam(required = false) String token) {
        Jedis jedis = RedisCache.getJedis(1);

        jedis.hdel("-1000", "demo");
        jedis.close();
        //RedisCache.del(token);
        return null;
    }

    @PostMapping("/register")
    public String register(String username, String password) {
        //注册时对密码进行加密
        Md5Hash md5Hash = new Md5Hash(password);
        //加盐加密
        int salt = new Random().nextInt(90000) + 10000;  //10000-99999
        Md5Hash md5Hash1 = new Md5Hash(password, salt + "");
        //加盐加密+Hash次数
        Md5Hash md5Hash2 = new Md5Hash(password, salt + "", 1);
        //TODO 此处省略存储到数据库代码
        return "login";
    }
}
