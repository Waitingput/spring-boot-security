package cn.pzhu.springbootsecurity.service;

import cn.pzhu.springbootsecurity.entity.*;
import cn.pzhu.springbootsecurity.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 教师service
 */
@Service("teacherService")
public class TeacherService {
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    GroupMapper groupMapper;
    @Autowired
    ProjectMapper projectMapper;
    @Autowired
    CheckworkMapper checkworkMapper;
    @Autowired
    ReportcardMapper reportcardMapper;

    public Teacher getTeacherInfo(Integer tid){
        Teacher teacher = teacherMapper.findTeacherById(tid);
        return teacher;
    }
    /*-------------------------------------------------------------个人信息*/

    public List<Tcourse> getTeacherCourses(Integer tid){
        List<Tcourse> stcours = courseMapper.findCoursesByTid(tid);
        return stcours;
    }
    public Tcourse getTCourseInfo(Integer tcid){
        Tcourse tcourse = courseMapper.findCourseByTCid(tcid);
        return tcourse;
    }
    /*-------------------------------------------------------------课程信息*/
    public Group getStudentGroup(Integer sid, Integer tcid){
       // Group group = groupMapper.findGroupBySidTCid(sid,tcid);
        return null;
    }
    public List<Group> getAllGroup(Integer tcid){
        List<Group> groups = groupMapper.findGroupsByTCid(tcid);
        return groups;
    }
    public List<Student> getStudentsOfGroup(Integer gid){
        List<Student> students = groupMapper.findStudentsOfGroupByGid(gid);
        return students;
    }
    public boolean addGroup(Group group){
        Integer row = groupMapper.insertGroup(group);
        return row > 0 ? true : false;
    }
    public boolean deleteGroup(Integer gid,Integer tcid){
        Integer row = groupMapper.deleteGroup(gid, tcid);
        return row > 0 ? true : false;
    }
    public boolean changeGroup(Group group){
        Integer row = groupMapper.updateGroup(group);
        return row > 0 ? true : false;
    }
    /*-------------------------------------------------------------分组信息*/

    public Project getStudentProject(Integer sid, Integer tcid){
       /* Project project = projectMapper.findStudentProjectBySidTCid(sid, tcid);
        return project;*/
       return null;
    }
    public List<Project> getAllProjects(Integer tcid){
        List<Project> projects = projectMapper.findProjectsByTCid(tcid);
        return projects;
    }
    public boolean addProject(Project project){
        Integer row = projectMapper.insertProject(project);
        return row > 0 ? true : false;
    }
    public boolean deleteProject(Integer pid,Integer tcid){
        Integer row = projectMapper.deleteProject(pid,tcid);
        return row > 0 ? true : false;
    }
    public boolean changeProject(Project project){
        Integer row = projectMapper.updateProject(project);
        return row > 0 ? true : false;
    }
    /*-------------------------------------------------------------课题信息*/



    /*-------------------------------------------------------------成果信息*/

    public List<Checkwork> getCheckworks(Integer tcid){
        List<Checkwork> checkworks = checkworkMapper.findCheckworksByTCid(tcid);
        return checkworks;
    }
    public Checkwork getCheckwork(Integer cwid){
        Checkwork checkwork = checkworkMapper.findCheckworkByCWid(cwid);
        return checkwork;
    }
    public boolean addCheckwork(Checkwork checkwork){
        Integer row = checkworkMapper.addCheckwork(checkwork);
        return row > 0 ? true : false;
    }
    public boolean deleteCheckwork(Integer cwid,Integer tcid){
        Integer row = checkworkMapper.deleteCheckwork(cwid, tcid);
        return row > 0 ? true : false;
    }
    public boolean changeChechwork(Checkwork checkwork){
        Integer row = checkworkMapper.updateCheckwork(checkwork);
        return row > 0 ? true : false;
    }
    public List<Student> getNOCheckedStudent(Integer cwid,Integer tcid){
        List<Student> students = checkworkMapper.findStudenOfNOChecked(cwid, tcid);
        return students;
    }

    /*-------------------------------------------------------------考勤信息*/

    public List<SReportcard> getReportcards(Integer tcid){
        List<SReportcard> reportcards = reportcardMapper.findStudentReportCardsByTCid(tcid);
        return reportcards;
    }
    public boolean addReportcard(Reportcard reportcard){
        Integer row = reportcardMapper.insertReportcard(reportcard);
        return row > 0 ? true : false;
    }
    public boolean deleteReportcard(Integer rcid,Integer tcid){
        Integer row = reportcardMapper.deleteReportcard(rcid, tcid);
        return row > 0 ? true : false;
    }
    public boolean changeReportcard(Reportcard reportcard){
        Integer row = reportcardMapper.updateReportcard(reportcard);
        return row > 0 ? true : false;
    }
    /*-------------------------------------------------------------成绩信息*/

    public List<SSelfknow> getStudentSelfknow(Integer tcid){
        List<SSelfknow> studentsSelfknow = teacherMapper.findStudentsSelfknow(tcid);
        return studentsSelfknow;
    }
    /*-------------------------------------------------------------评价查看信息*/

}
