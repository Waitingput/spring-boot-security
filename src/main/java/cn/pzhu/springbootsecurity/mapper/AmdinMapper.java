package cn.pzhu.springbootsecurity.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 管理员Mapper
 */
@Mapper
public interface AmdinMapper {
    @Select("select aid from amdin where ano = #{ano}")
    Integer findAmdinIdByNo(String ano);
}
