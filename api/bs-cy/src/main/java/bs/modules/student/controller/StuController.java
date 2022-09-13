package bs.modules.student.controller;

import bs.common.Global.R;
import bs.common.Global.RedisCache;
import bs.common.Global.ThreadPoolHelp;
import bs.common.utils.PageData;
import bs.common.utils.Result;
import bs.modules.security.user.SecurityUser;
import bs.modules.security.user.UserDetail;
import bs.modules.student.entity.OwnerEntity;
import bs.modules.student.service.OwnerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName StuController
 * @Description 学生模块
 * @Version 1.0
 **/
@RestController
@RequestMapping("/stu")
public class StuController {
    @Autowired
    private OwnerService ownerService;

    @Autowired
    private ThreadPoolHelp threadPoolHelp;
    @Autowired
    private RedisCache redisCache;

    @GetMapping("/getBodyTemperatureList")
    //获取体温列表
    public Result<PageData<OwnerEntity>> getBodyTemperatureList(@RequestParam Map<String, Object> params) {
        //登录的用户信息
        UserDetail user = SecurityUser.getUser();
        Long id = user.getId();
        Long deptId = user.getDeptId();
        assert deptId != null;


        PageData<OwnerEntity> page = ownerService.page(params);
        return new Result<PageData<OwnerEntity>>().ok(page);
    }

    @PostMapping("/saveOwnerInfo")
    public R saveOwnerInfo() {
        return R.ok();
    }

    @PostMapping("/updOwnerInfo")
    public R updOwnerInfo() {
        return R.ok();
    }

    @DeleteMapping("/delOwnerInfo")
    public R delOwnerInfo() {
        return R.ok();
    }
}
