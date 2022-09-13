package bs.modules.student.entity;

import bs.common.utils.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName OwnerEntity
 * @Description TODO
 * @Author Dear lin
 * @Date 16:59 2022/7/29
 * @Version 1.0
 **/
@TableName("owner")
@Data
public class OwnerEntity extends BaseEntity {
    private String ownerName;
    private String ownerPlace;
    private String ownerHouseNum;
    private String ownerSex;
    private String ownerCar;
}
