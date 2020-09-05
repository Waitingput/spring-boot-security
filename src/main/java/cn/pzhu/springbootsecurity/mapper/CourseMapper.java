package cn.pzhu.springbootsecurity.mapper;

import cn.pzhu.springbootsecurity.entity.Course;
import cn.pzhu.springbootsecurity.entity.Tcourse;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 课程Mapper
 */
@Mapper
public interface CourseMapper {
    //所有课程查询
    @Select("select * frome course")
    List<Course> findAll();

    //课程id查询
    @Select("select cid,cname from course where cid = #{cid}")
    Course findCourseById(Integer cid);



    //学生总课程获取
    @Select("SELECT tc.tcid,tc.courseid,tc.tid,tc.classid FROM teacher_course tc JOIN student_course sc ON sc.tcid=tc.tcid AND sc.sid=#{sid}")
    @Results(id = "Tcourse",value = {
            @Result(id = true,column = "tcid",property = "tcid"),
            @Result(column = "tid",property = "teacher",
                    one = @One(select = "cn.pzhu.springbootsecurity.mapper.TeacherMapper.findTeacherById",fetchType = FetchType.LAZY)),
            @Result(column = "courseid",property = "course",
                    one = @One(select = "cn.pzhu.springbootsecurity.mapper.CourseMapper.findCourseById",fetchType = FetchType.LAZY)),
            @Result(column = "classid",property = "sclass",
                    one = @One(select = "cn.pzhu.springbootsecurity.mapper.ClassMapper.findClassById",fetchType = FetchType.LAZY))
    })
    List<Tcourse> findCoursesBySid(Integer sid);

    //单门课程详细信息
    @Select("SELECT tc.tcid,tc.courseid,tc.tid,tc.classid FROM teacher_course tc WHERE tc.tcid=#{tcid}")
    @ResultMap(value = "Tcourse")
    Tcourse findCourseByTCid(Integer tcid);

    //教师总课程查询
    @Select("SELECT tc.tcid,tc.courseid,tc.tid,tc.classid FROM teacher_course tc WHERE tc.tid=#{tid}")
    @ResultMap(value = "Tcourse")
    List<Tcourse> findCoursesByTid(Integer tid);

}
