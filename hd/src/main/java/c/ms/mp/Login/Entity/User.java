package c.ms.mp.Login.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;

/**
 * @ClassName User
 * @Description TODO
 * @Author Dear lin
 * @Date 17:00 2022/7/15
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@TableName("tb_users")
public class User implements Serializable {
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;
    @TableField("username")
    private String userName;

    @TableField("password")
    private String passWord;

    @TableField("password_salt")
    private String passWordSalt;

}
