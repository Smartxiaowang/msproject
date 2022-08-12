/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package bs.modules.security.controller;

import bs.common.Global.CMSException;
import bs.common.Global.R;
import bs.common.Global.RedisCache;
import bs.common.Global.ThreadPoolHelp;
import bs.common.utils.ErrorCode;
import bs.common.utils.IpUtils;
import bs.modules.job.utils.ValidatorUtils;
import bs.modules.log.entity.SysLogLoginEntity;
import bs.modules.log.enums.LoginOperationEnum;
import bs.modules.log.enums.LoginStatusEnum;
import bs.modules.log.service.SysLogLoginService;
import bs.modules.security.service.CaptchaService;
import bs.modules.security.service.SysUserTokenService;
import bs.modules.sys.dto.SysUserDTO;
import bs.modules.sys.enums.UserStatusEnum;
import bs.modules.sys.service.SysUserService;
import bs.modules.security.dto.LoginDTO;
import bs.modules.security.password.PasswordUtils;
import bs.modules.security.user.SecurityUser;
import bs.modules.security.user.UserDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.ExecutorService;

/*
 * @Description: 登录
 * @Author: Dear lin
 */
@RestController
@Api(tags = "登录管理")
public class LoginController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;
    @Autowired
    private CaptchaService captchaService;
    @Autowired
    private SysLogLoginService sysLogLoginService;
    @Autowired
    private ThreadPoolHelp threadPoolHelp;
    @Autowired
    private RedisCache redisCache;

    @GetMapping("captcha")
    @ApiOperation(value = "验证码", produces = "application/octet-stream")
    @ApiImplicitParam(paramType = "query", dataType = "string", name = "uuid", required = true)
    public void captcha(HttpServletResponse response, String uuid) throws IOException {
        //uuid不能为空
        //AssertUtils.isBlank(uuid, ErrorCode.IDENTIFIER_NOT_NULL);
		assert uuid != null;
        if (!uuid.equals(""))
            //生成验证码
            captchaService.create(response, uuid);
        else
            throw new CMSException(ErrorCode.CAPTCHA_NULL, "别测试了,求放过~~~");
    }

    /*
     * @Description: TODO
     * @Author: Dear lin
     * @Param: [request, login]
     * @Return: bs.common.Global.R
     */
    @PostMapping("login")
    @ApiOperation(value = "登录")
    public R login(HttpServletRequest request, @RequestBody LoginDTO login) {
        //效验数据
        ExecutorService executorService = threadPoolHelp.executorService();
        executorService.shutdownNow();
        Set<String> wang = redisCache.keys("wang");
        ValidatorUtils.validateEntity(login);

        //验证码是否正确
        boolean flag = captchaService.validate(login.getUuid(), login.getCaptcha());
        if (!flag) {
			return R.error().code(ErrorCode.CAPTCHA_ERROR).message("验证码错误");
        }
        //加一个账号登录冻结10分钟
        String s = RedisCache.get(login.getUsername());

        //用户信息
        SysUserDTO userDTO = sysUserService.getByUsername(login.getUsername());

        if (userDTO.getStatus() == UserStatusEnum.LOCKTENMIN.value()) {
            //账号登录次数过多停用十分钟
        }
        SysLogLoginEntity log = new SysLogLoginEntity();
        log.setOperation(LoginOperationEnum.LOGIN.value());
        log.setCreateDate(new Date());
        log.setIp(IpUtils.getIpAddr(request));
        log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
        log.setIp(IpUtils.getIpAddr(request));

        //用户不存在
        if (userDTO == null) {
            log.setStatus(LoginStatusEnum.FAIL.value());
            log.setCreatorName(login.getUsername());
            sysLogLoginService.save(log);
            throw new CMSException(ErrorCode.USER_PASSWORD_ERROR, "用户不存在");
        }

        //密码错误
        if (!PasswordUtils.matches(login.getPassword(), userDTO.getPassword())) {
            log.setStatus(LoginStatusEnum.FAIL.value());
            log.setCreator(userDTO.getId());
            log.setCreatorName(userDTO.getUsername());
            sysLogLoginService.save(log);

            throw new CMSException(ErrorCode.USER_PASSWORD_ERROR, "密码错误");
        }

        //账号停用
        if (userDTO.getStatus() == UserStatusEnum.DISABLE.value()) {
            log.setStatus(LoginStatusEnum.LOCK.value());
            log.setCreator(userDTO.getId());
            log.setCreatorName(userDTO.getUsername());
            sysLogLoginService.save(log);
            throw new CMSException(ErrorCode.USER_IS_LOCK, "账号停用");
        }

        //登录成功
        log.setStatus(LoginStatusEnum.SUCCESS.value());
        log.setCreator(userDTO.getId());
        log.setCreatorName(userDTO.getUsername());
        sysLogLoginService.save(log);

        return sysUserTokenService.createToken(userDTO.getId());

    }

    @PostMapping("logout")
    @ApiOperation(value = "退出")
    public R logout(HttpServletRequest request) {
        UserDetail user = SecurityUser.getUser();

        //退出
        sysUserTokenService.logout(user.getId());

        //用户信息
        SysLogLoginEntity log = new SysLogLoginEntity();
        log.setOperation(LoginOperationEnum.LOGOUT.value());
        log.setIp(IpUtils.getIpAddr(request));
        log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
        log.setIp(IpUtils.getIpAddr(request));
        log.setStatus(LoginStatusEnum.SUCCESS.value());
        log.setCreator(user.getId());
        log.setCreatorName(user.getUsername());
        log.setCreateDate(new Date());
        sysLogLoginService.save(log);

        return R.ok();
    }

}