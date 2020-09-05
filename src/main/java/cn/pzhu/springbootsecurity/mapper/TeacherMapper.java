package cn.pzhu.springbootsecurity.mapper;

import cn.pzhu.springbootsecurity.entity.SSelfknow;
import cn.pzhu.springbootsecurity.entity.Selfknow;
import cn.pzhu.springbootsecurity.entity.Teacher;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 教师mapper
 */
@Mapper
public interface TeacherMapper {
    //Teacher id获取
    @Select("select tid from teacher where tno = #{tno}")
    Integer findTeacherIdByNo(String tno);

    //Teacher Id查询
    @Select("select * from teacher where tid = #{tid}")
    @Results(id = "teacherInfo",value = {
            @Result(id = true,column = "tid",property = "tid"),
            @Result(column = "tno",property = "tno"),
            @Result(column = "tname",property = "tname"),
            @Result(column = "tid",property = "dept",
                    one = @One(select = "cn.pzhu.springbootsecurity.mapper.DeptMapper.findDeptById",fetchType = FetchType.LAZY)),
    })
    Teacher findTeacherById(Integer tid);

    //自我评价id查询
    @Select("SELECT sk.skid,sk.content,sk.tcid,sk.sid FROM self_know sk WHERE sk.skid=#{skid}")
    Selfknow findStudentSelfKnowById(Integer skid);

    //学生自我评价查询
    @Select("SELECT sc.sid,sk.skid FROM student_course sc LEFT JOIN  self_know sk  ON sc.sid=sk.sid WHERE sc.tcid=#{tcid}")
    @Results(id = "selfknows",value = {
            @Result(column = "sid",property = "student",
                    many = @Many(select = "cn.pzhu.springbootsecurity.mapper.StudentMapper.findStudentById",fetchType = FetchType.EAGER)),
            @Result(column = "skid",property = "selfknow",
                    many = @Many(select = "cn.pzhu.springbootsecurity.mapper.TeacherMapper.findStudentSelfKnowById",fetchType = FetchType.EAGER))
    })
    List<SSelfknow> findStudentsSelfknow(Integer tcid);
}
