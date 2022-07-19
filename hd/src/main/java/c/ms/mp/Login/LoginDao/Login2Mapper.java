package c.ms.mp.Login.LoginDao;

import c.ms.mp.Login.Entity.User;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface Login2Mapper extends BaseMapper<User> {

    @Insert("INSERT INTO tb_users (username,password,password_salt) VALUES (#{username},#{password},#{password_salt})")
    int saveUserInfo(@Param("username") String uname,@Param("password")String md5pas,@Param("password_salt")String salt);

    @Select("select * from user where id = #{id}")
    User selectById(Integer id);

    @Select("select * from news")
    List<HashMap<String, Object>> getSomeThing();

    @Insert("INSERT INTO user (name) VALUES ('xuanxuan')")
    int saveSecInfo(@Param("userInfo") User user);

    @Select("select user_id AS userId,username,password,password_salt as passWordSalt from tb_users where username = #{username}")
    User selectByUserName(String username);
}
