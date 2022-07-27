package bs.common.Global;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常拦截器
 * @Author Dear lin
 * @Date 17:10 2022/7/14
 * @Version 1.0
 **/


@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * -------- 通用异常处理方法 --------
     **/
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        e.printStackTrace();
        return R.error();
        // 当无权人员访问时会抛出AuthorizationException异常
        // 通用异常结果
    }

    /**
     * -------- 指定异常处理方法 --------
     **/
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public R error(NullPointerException e) {
        e.printStackTrace();
        return R.setResult(ResultCodeEnum.NULL_POINT);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseBody
    public R error(IndexOutOfBoundsException e) {
        e.printStackTrace();
        return R.setResult(ResultCodeEnum.HTTP_CLIENT_ERROR);
    }

    /**
     * -------- 自定义定异常处理方法 --------
     **/
    @ExceptionHandler(CMSException.class)
    @ResponseBody
    public R error(CMSException e) {
        e.printStackTrace();
        return R.error().message(e.getMessage()).code(e.getCode());
    }


}
