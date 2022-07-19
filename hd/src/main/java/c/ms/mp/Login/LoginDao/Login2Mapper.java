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

    @Insert("INSERT INTO user (name) VALUES ('wangwang')")
    int saveInfo(@Param("userInfo") User user);

    @Select("select * from user where id = #{id}")
    User selectById(Integer id);

    @Select("select * from news")
    List<HashMap<String, Object>> getSomeThing();

    @Insert("INSERT INTO user (name) VALUES ('xuanxuan')")
    int saveSecInfo(@Param("userInfo") User user);
}
