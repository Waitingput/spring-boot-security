package cn.pzhu.springbootsecurity.mapper;

import cn.pzhu.springbootsecurity.entity.Group;
import cn.pzhu.springbootsecurity.entity.Project;
import cn.pzhu.springbootsecurity.entity.Sproject;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 题目Mapper
 */
@Mapper
public interface ProjectMapper {
    //题目已选人数查询
    @Select("SELECT COUNT(spid) snu FROM student_project WHERE pid=#{pid}")
    Integer findProjectSnuByPid(Integer pid);

    //所有题目查询
    @Select("SELECT p.pid,p.pno,p.pname,p.phight,p.tcid FROM project p WHERE tcid=#{tcid}")
    List<Project> findProjectsByTCid(Integer tcid);

    //增加课题
    @Insert("INSERT INTO project (pno,pname,phight,tcid) SELECT #{pno},#{pname},#{phight},#{tcid} FROM DUAL WHERE NOT EXISTS(SELECT pid FROM project WHERE pno=#{pno} and tcid=#{tcid})")
    Integer insertProject(Project project);

    //课题删除
    @Delete("DELETE p FROM project p WHERE p.pid=#{pid} AND tcid=#{tcid}")
    Integer deleteProject(Integer pid,Integer tcid);

    //课题修改
    @Update("update project p set pno=#{pno},pname=#{pname},phight=#{phight} where pid=#{pid}")
    Integer updateProject(Project project);

    //学生已选题目查询
    @Select("SELECT p.pid,p.pno,p.pname,p.phight,p.tcid FROM project p WHERE p.tcid=#{tcid} AND p.pid=(SELECT pid FROM student_project sp WHERE sp.sid=#{sid})")
    Project findStudentProjectBySidTCid(Integer sid,Integer tcid);

    //学生选题添加
    @Insert("INSERT INTO student_project (pid,sid) SELECT #{pid},#{sid} FROM DUAL WHERE NOT EXISTS(SELECT pid,sid FROM student_project WHERE pid = #{pid} AND sid = #{sid})")
    Integer addStudentProject(Sproject sproject);

    //学生题目删除
    @Delete("DELETE sp FROM student_project sp WHERE sp.sid=#{sid} AND sp.pid=#{pid}")
    Integer deleteStudentPeoject(Integer sid,Integer pid);



}
