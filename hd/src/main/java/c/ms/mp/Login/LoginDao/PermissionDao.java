package c.ms.mp.Login.LoginDao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/*
 * @Description: 根据用户明查询权限
 * @Author: Dear lin
 * @Date: 2022/7/15 20:22
 */
@Mapper
public interface PermissionDao {
    Set<String> getPermissionByUsername(String username);
}
