package cn.pzhu.springbootsecurity.mapper;

import cn.pzhu.springbootsecurity.entity.Major;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 专业Mapper
 */
@Mapper
public interface MajorMapper {
    @Select("select * from major where mid = #{mid}")
    Major findMajorById(Integer mid);
}
