package bs.modules.student.controller;

import bs.common.Global.R;
import bs.common.Global.RedisCache;
import bs.common.Global.ThreadPoolHelp;
import bs.common.utils.PageData;
import bs.common.utils.Result;
import bs.modules.student.entity.OwnerEntity;
import bs.modules.student.service.OwnerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName OwnerController
 * @Description 业主信息登记
 * @Author Dear lin
 * @Date 16:07 2022/7/29
 * @Version 1.0
 **/
@RestController
@RequestMapping("/owner")
@Api(tags = "业主信息登记")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    @Autowired
    private ThreadPoolHelp threadPoolHelp;
    @Autowired
    private RedisCache redisCache;

    @GetMapping("/getOwnerList")
    public Result<PageData<OwnerEntity>> getOwnerList(@RequestParam Map<String, Object> params) {
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
