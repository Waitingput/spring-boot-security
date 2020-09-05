package cn.pzhu.springbootsecurity.mapper;

import cn.pzhu.springbootsecurity.entity.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * deptMapper
 */
@Mapper
public interface DeptMapper {
    @Select("select * from dept where did = #{did}")
    Dept findDeptById(Integer did);
}
