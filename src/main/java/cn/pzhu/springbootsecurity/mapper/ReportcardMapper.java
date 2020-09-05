package cn.pzhu.springbootsecurity.mapper;

import cn.pzhu.springbootsecurity.entity.Reportcard;
import cn.pzhu.springbootsecurity.entity.SReportcard;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 成绩mapper
 */
@Mapper
public interface ReportcardMapper {
    //添加成绩
    @Insert("INSERT INTO report_card (usual_grade,report_grade,sid,tcid) SELECT #{usual_grade},#{report_grade},#{sid},#{tcid} FROM DUAL WHERE NOT EXISTS(SELECT rcid FROM report_card WHERE sid=#{sid} AND tcid=#{tcid})")
    Integer insertReportcard(Reportcard reportcard);

    //删除成绩
    @Delete("DELETE rc FROM report_card rc WHERE rc,rcid=#{rcid} AND rc.tcid=#{tcid")
    Integer deleteReportcard(Integer rcid,Integer tcid);

    //修改成绩
    @Update("update report_card set usual_grade=#{usual_grade},report_grade=#{report_grade} where rcid=#{rcid}")
    Integer updateReportcard(Reportcard reportcard);

    //查询已有成绩
    @Select("SELECT rc.rcid,rc.usual_grade,rc.report_grade,rc.sid,rc.tcid FROM report_card rc WHERE rc.sid=#{sid} AND rc.tcid=#{tcid}")
    Reportcard findReportcardBySidTCid(Integer sid,Integer tcid);

    //查询课程所有已有学生成绩
    @Select("SELECT rc.rcid,rc.usual_grade,rc.report_grade,rc.sid,rc.tcid FROM report_card rc WHERE rc.tcid=#{tcid}")
    List<Reportcard> findReportCardsByTCid(Integer tcid);

    //成绩id查询
    @Select("SELECT rc.rcid,rc.usual_grade,rc.report_grade,rc.sid,rc.tcid FROM report_card rc WHERE rc.rcid=#{rcid}")
    Reportcard findReportCardByid(Integer rcid);


    //查询所有学生成绩
    @Select("SELECT sc.sid,rc.rcid FROM student_course sc LEFT JOIN  report_card rc ON sc.sid=rc.sid WHERE sc.tcid=#{tcid}")
    @Results(id = "reportcard",value = {
            @Result(column = "sid",property = "student",
                    many = @Many(select = "cn.pzhu.springbootsecurity.mapper.StudentMapper.findStudentById",fetchType = FetchType.EAGER)),
            @Result(column = "rcid",property = "reportcard",
                    many = @Many(select = "cn.pzhu.springbootsecurity.mapper.ReportcardMapper.findReportCardByid",fetchType = FetchType.EAGER))
    })
    List<SReportcard> findStudentReportCardsByTCid(Integer tcid);
}
