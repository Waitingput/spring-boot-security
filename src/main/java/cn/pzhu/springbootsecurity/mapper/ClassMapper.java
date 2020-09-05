package cn.pzhu.springbootsecurity.mapper;

import cn.pzhu.springbootsecurity.entity.Class;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 班级Mapper
 */
@Mapper
public interface ClassMapper {
    @Select("select * from class where cid = #{cid}")
    Class findClassById(Integer cid);
}
