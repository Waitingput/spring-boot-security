package cn.pzhu.springbootsecurity.mapper;

import cn.pzhu.springbootsecurity.entity.Role;
import cn.pzhu.springbootsecurity.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 用户Mapper
 */
@Mapper
public interface UserMapper {

    @Select("select * from user where username = #{username}")
    User lodaUserByUsername(String username);

    @Select("select * from role r,user_role ur where r.id=ur.rid and ur.uid=#{userId}")
    List<Role> getUserRolesByUserId(Integer userId);

    @Update("update user set password = #{password} where username = #{username}")
    boolean updateUserPassword(User user);
}
