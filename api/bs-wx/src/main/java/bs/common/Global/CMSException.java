package bs.common.Global;

import lombok.Data;

/**
 * @ClassName CMSException
 * @Description 全局异常
 * @Author Dear lin
 * @Date 17:09 2022/7/14
 * @Version 1.0
 **/
@Data
public class CMSException extends RuntimeException {
    private Integer code;

    public CMSException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public CMSException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "CMSException{" + "code=" + code + ", message=" + this.getMessage() + '}';
    }
}