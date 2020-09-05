package cn.pzhu.springbootsecurity.mapper;

import cn.pzhu.springbootsecurity.entity.Checkwork;
import cn.pzhu.springbootsecurity.entity.Scheck;
import cn.pzhu.springbootsecurity.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 考勤mapper
 */
@Mapper
public interface CheckworkMapper {
    //id查询
    @Select("SELECT cw.cwid,cw.tcid,cw.date,cw.begin,cw.end FROM check_work cw WHERE cw.cwid=#{cwid}")
    Checkwork findCheckworkByCWid(Integer cwid);

    //获取课程考勤表
    @Select("SELECT cw.cwid,cw.tcid,cw.date,cw.begin,cw.end FROM check_work cw WHERE cw.tcid=#{tcid}")
    List<Checkwork> findCheckworksByTCid(Integer tcid);

    //添加考勤表
    @Insert("INSERT INTO check_work (tcid,date,begin,end) SELECT #{tcid},#{date},#{begin},#{end} FROM DUAL WHERE NOT EXISTS(SELECT tcid,date FROM check_work WHERE tcid=#{tcid} AND date=#{date})")
    Integer addCheckwork(Checkwork checkwork);

    //删除考情表
    @Delete("DELETE cw FROM check_work cw WHERE cw.cwid=#{cwid} AND cw.tcid=#{tcid}")
    Integer deleteCheckwork(Integer cwid,Integer tcid);

    //修改考勤表
    @Update("update check_work set date=#{date},begin=#{begin},end=#{end} where cwid=#{cwid}")
    Integer updateCheckwork(Checkwork checkwork);

    //学生考勤表查看
    @Select("SELECT sc.scid,sc.checked,sc.cwid,sc.sid FROM student_check sc WHERE sc.cwid=#{cwid} AND sid=#{sid}")
    Scheck findStudentCheckBySidCWid(Integer sid, Integer cwid);

    //学生考勤
    @Insert("INSERT INTO student_check (checked,cwid,sid) SELECT #{checked},#{cwid},#{sid} FROM DUAL WHERE NOT EXISTS(SELECT checked,cwid,sid FROM student_check WHERE cwid=#{cwid} AND sid=#{sid})")
    Integer addStudentCheck(Scheck scheck);

    //未考勤学生查看
    @Select("SELECT s.sid,s.sno,s.sname,s.did,s.mid,s.cid FROM student s JOIN (SELECT sc.sid FROM student_course sc LEFT JOIN (SELECT cwid,sid FROM student_check WHERE cwid=#{cwid}) sk ON sc.sid=sk.sid WHERE tcid=#{tcid} AND sk.cwid IS NULL) s1 ON s.sid=s1.sid")
    @ResultMap(value = "cn.pzhu.springbootsecurity.mapper.StudentMapper.studentInfo")
    List<Student> findStudenOfNOChecked(Integer cwid,Integer tcid);

}
