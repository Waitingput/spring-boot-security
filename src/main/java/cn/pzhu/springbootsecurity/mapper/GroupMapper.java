package cn.pzhu.springbootsecurity.mapper;

import cn.pzhu.springbootsecurity.entity.Group;
import cn.pzhu.springbootsecurity.entity.Sgroup;
import cn.pzhu.springbootsecurity.entity.Student;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 分组Mapper
 */
@Mapper
public interface GroupMapper {
    //分组已选人数查询
    @Select("SELECT COUNT(sgid) snu FROM student_group WHERE gid=#{gid}")
    Integer findGroupSnuByTCid(Integer gid);

    //分组ID查询
    @Select("SELECT g.gid,g.gno,g.gnu,g.tcid,g.sid FROM `group` g WHERE g.gid=#{gid}")
    @ResultMap(value = "group")
    Group findGroupByGid(Integer gid);

    //添加分组
    @Insert("INSERT INTO `group` (gno,gnu,tcid) SELECT #{gno},#{gnu},#{tcid} FROM DUAL WHERE NOT EXISTS(SELECT gno,tcid FROM `group` WHERE tcid=#{tcid} AND gno=#{gno})")
    Integer insertGroup(Group group);

    //修改分组信息
    @Update("update `group` g set gno=#{gno},gnu=#{gnu},sid=#{leader.sid} where gid=#{gid}")
    Integer updateGroup(Group group);

    //删除分组
    @Delete("DELETE g FROM `group` g WHERE g.gid=#{gid} AND g.tcid=#{tcid}")
    Integer deleteGroup(Integer gid,Integer tcid);

    //个人分组查询
    @Select("SELECT g.gid,g.gno,g.gnu,g.tcid,g.sid FROM `group` g WHERE g.tcid=#{tcid} AND g.gid=(SELECT gid FROM student_group sg WHERE sg.sid=#{sid})")
    @Results(id = "group",value = {
            @Result(id = true,column = "gid",property = "gid"),
            @Result(column = "gno",property = "gno"),
            @Result(column = "gnu",property = "gnu"),
            @Result(column = "tcid",property = "tcid"),
            @Result(column = "gid",property = "snu",
                    one = @One(select = "cn.pzhu.springbootsecurity.mapper.GroupMapper.findGroupSnuByTCid",fetchType = FetchType.EAGER)),
            @Result(column = "gid",property = "students",
                    many = @Many(select = "cn.pzhu.springbootsecurity.mapper.GroupMapper.findStudentsOfGroupByGid",fetchType = FetchType.LAZY)),
            @Result(column = "sid",property = "leader",
                    one = @One(select = "cn.pzhu.springbootsecurity.mapper.StudentMapper.findStudentById",fetchType = FetchType.LAZY))
    })
    Group findGroupBySidTCid(Integer sid, Integer tcid);

    //全部分组查询
    @Select("SELECT g.gid,g.gno,g.gnu,g.tcid,g.sid FROM `group` g WHERE g.tcid=#{tcid}")
    @ResultMap(value = "group")
    List<Group> findGroupsByTCid(Integer tcid);

    //分组成员查询
    @Select("SELECT s.sid,s.sno,s.sname,s.did,s.mid,s.cid FROM student s JOIN student_group sg ON sg.sid=s.sid AND sg.gid=#{gid}")
    @ResultMap(value = "cn.pzhu.springbootsecurity.mapper.StudentMapper.studentInfo")
    List<Student> findStudentsOfGroupByGid(Integer gid);

    //删除学生分组
    @Delete("DELETE sg FROM student_group sg WHERE sg.gid=#{gid} AND sg.sid=#{sid}")
    Integer deleteStudentGroupBySidGid(Integer sid,Integer gid);

    //添加学生分组
    @Insert("INSERT INTO student_group (gid,sid) SELECT #{gid},#{sid} FROM DUAL WHERE NOT EXISTS(SELECT gid,sid FROM student_group WHERE gid = #{gid} AND sid = #{sid})")
    Integer insertStudentGroup(Sgroup sgroup);

    //更新学生分组
    @Update("update student_group set gid=#{gid} where sid=#{sid}")
    Integer updateStudentGroup(Sgroup sgroup);
}
