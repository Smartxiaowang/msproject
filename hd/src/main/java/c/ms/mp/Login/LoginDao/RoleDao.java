package c.ms.mp.Login.LoginDao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/*
 * @Description:根据用户名查询角色
 * @Author: Dear lin
 * @Date: 2022/7/15 20:22
 */
@Mapper
public interface RoleDao {
    Set<String> getRoleNamesByUsername(String username);
}
