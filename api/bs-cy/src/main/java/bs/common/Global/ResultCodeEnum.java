package bs.common.Global;

import lombok.Getter;

/**
 * @ClassName R
 * @Description 统一全局返回参数配置枚举
 * @Author Dear lin
 * @Date 17:05 2022/7/14
 * @Version 1.0
 **/
@Getter
public enum ResultCodeEnum {
    SUCCESS(true, 0, "成功"),
    UNKNOWN_ERROR(false, 201, "未知错误"),
    PARAM_ERROR(false, 202, "参数错误"),
    HTTP_CLIENT_ERROR(false, 203, "HttpClient错误"),
    NULL_POINT(false, 205, "空指针");

    // 响应是否成功
    private Boolean success;
    // 响应状态码
    private Integer code;
    // 响应信息
    private String message;

    ResultCodeEnum(boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
