package cn.pzhu.springbootsecurity.mapper;

import cn.pzhu.springbootsecurity.entity.Projectreport;
import cn.pzhu.springbootsecurity.entity.Selfknow;
import cn.pzhu.springbootsecurity.entity.Student;
import cn.pzhu.springbootsecurity.entity.Tcourse;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

/**
 * 学生mapper
 */
@Mapper
public interface StudentMapper {
    //id获取
    @Select("select sid from student where sno = #{sno}")
    Integer findStudentByNo(String sno);

    //id查询
    @Select("select * from student where sid = #{sid}")
    @Results(id = "studentInfo",value = {
            @Result(id = true,column = "sid",property = "sid"),
            @Result(column = "sno",property = "sno"),
            @Result(column = "sname",property = "sname"),
            @Result(column = "did",property = "dept",
                    one = @One(select = "cn.pzhu.springbootsecurity.mapper.DeptMapper.findDeptById",fetchType = FetchType.LAZY)),
            @Result(column = "mid",property = "major",
                    one = @One(select = "cn.pzhu.springbootsecurity.mapper.MajorMapper.findMajorById",fetchType = FetchType.LAZY)),
            @Result(column = "cid",property = "sclass",
                    one = @One(select = "cn.pzhu.springbootsecurity.mapper.ClassMapper.findClassById",fetchType = FetchType.LAZY))
    })
    Student findStudentById(Integer sid);

    //自我评查询
    @Select("SELECT sk.skid,sk.content,sk.tcid,sk.sid FROM self_know sk WHERE sk.sid=#{sid} AND sk.tcid=#{tcid}")
    Selfknow findSelfknowBySidTCid(Integer sid,Integer tcid);

    //自我评价添加
    @Insert("INSERT INTO self_know (content,tcid,sid) SELECT #{content},#{tcid},#{sid} FROM DUAL WHERE NOT EXISTS(SELECT tcid,sid FROM self_know WHERE tcid=#{tcid} AND sid=#{sid})")
    Integer addSelfknow(Selfknow selfknow);

    //自我评价删除
    @Delete("DELETE sf FROM self_know sf WHERE sf.skid=#{skid} AND sid=#{sid}")
    Integer deleteSelfknow(Integer skid, Integer sid);

    //自我评价修改
    @Update("update self_know set content=#{content} where skid=#{skid} AND sid=#{sid}")
    Integer updateSelfknow(Selfknow selfknow);

    //自我评价添加/修改
    @Insert("INSERT INTO self_know (skid,content,tcid,sid)VALUE(#{selfknow},#{content},#{tcid},#{sid}) ON DUPLICATE KEY UPDATE content=#{content}")
    Integer addAndupdateSelfknow(Selfknow selfknow);

    //报告查询
    @Select("SELECT pr.prid,pr.worldurl,pr.zipurl,pr.tcid,pr.sid FROM project_report pr WHERE sid=#{sid} AND tcid=#{tcid}")
    Projectreport findProjectreportByTCidSid(Integer tcid,Integer sid);

    //报告上传
    @Insert("INSERT INTO project_report (worldurl,zipurl,tcid,sid) SELECT #{worldurl},#{zipurl},#{tcid},#{sid} FROM DUAL WHERE NOT EXISTS(SELECT tcid,sid FROM project_report WHERE sid=#{sid} AND tcid=#{tcid})")
    Integer addProjectreport(Projectreport projectreport);

    //报告word更新
    @Update("UPDATE project_report SET worldurl=#{worldurl} WHERE tcid=#{tcid} AND sid=#{sid}")
    Integer updateWordReport(Projectreport projectreport);

    //报告zip更新
    @Update("UPDATE project_report SET zipurl=#{zipurl} WHERE tcid=#{tcid} AND sid=#{sid}")
    Integer updateZipReport(Projectreport projectreport);
}
