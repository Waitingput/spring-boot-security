package cn.pzhu.springbootsecurity.service;

import cn.pzhu.springbootsecurity.entity.*;
import cn.pzhu.springbootsecurity.mapper.*;
import cn.pzhu.springbootsecurity.util.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 学生service
 */
@Service("studentService")
public class StudentService {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    GroupMapper groupMapper;
    @Autowired
    ProjectMapper projectMapper;
    @Autowired
    CheckworkMapper checkworkMapper;
    @Autowired
    FileIO fileIO;

    public Student getStudentInfo(Integer sid){
        Student student = studentMapper.findStudentById(sid);
        return  student;
    }
    /*-------------------------------------------------------------------基本信息管理*/
    public List<Tcourse> getStudentCourses(Integer sid){
        List<Tcourse> stcours = courseMapper.findCoursesBySid(sid);
        return stcours;
    }
    public Tcourse getTCourseInfo(Integer tcid){
        Tcourse tcourse = courseMapper.findCourseByTCid(tcid);
        return tcourse;
    }
    /*-------------------------------------------------------------------课程管理*/
    public Group getStudentGroup(Integer sid,Integer tcid){
        Group group = groupMapper.findGroupBySidTCid(sid,tcid);
        return group;
    }
    public List<Group> getAllGroup(Integer tcid){
        List<Group> groups = groupMapper.findGroupsByTCid(tcid);
        return groups;
    }
    public List<Student> getStudentsOfGroup(Integer gid){
        List<Student> students = groupMapper.findStudentsOfGroupByGid(gid);
        return students;
    }
    public boolean deleteStudentGroup(Integer sid,Integer gid){
        Group group = groupMapper.findGroupByGid(gid);
        boolean flag = false;
        //不是组长
        if(sid!=group.getLeader().getSid()){
            //退出组
            Integer row = groupMapper.deleteStudentGroupBySidGid(sid, gid);
            flag = row > 0 ? true : false;
        }else {
            //是组长且存在组员
            if(group.getSnu()>1){
                return  false;
            }else {
                //没有组员
                Student student = new Student();
                student.setSid(0);
                group.setLeader(student);
                //初始化组长
                groupMapper.updateGroup(group);
                //退出组
                Integer row = groupMapper.deleteStudentGroupBySidGid(sid, gid);
                flag = row > 0 ? true : false;
            }
        }
        return flag;
    }
    public boolean addStudentGroup(Sgroup sgroup,Integer tcid){
        boolean flag = false;
        Group group = groupMapper.findGroupBySidTCid(sgroup.getSid(), tcid);
        //判断本课程是否存在分组
        if(group != null){
            return  flag;
        }else {
            Group group1 = groupMapper.findGroupByGid(sgroup.getGid());
            //判断是否存在组长
            if(group1.getSnu()==0){
                Student student = new Student();
                student.setSid(sgroup.getSid());
                group1.setLeader(student);
                groupMapper.updateGroup(group1);
            }
            Integer row = groupMapper.insertStudentGroup(sgroup);
            flag = row > 0 ? true : false;
        }
       return flag;
    }
    /*------------------------------------------------------------------------分组管理*/
    public Project getStudentProject(Integer sid,Integer tcid){
        Project project = projectMapper.findStudentProjectBySidTCid(sid, tcid);
        return project;
    }
    public List<Project> getAllProjects(Integer tcid){
        List<Project> projects = projectMapper.findProjectsByTCid(tcid);
        return projects;
    }
    public boolean addStudentProject(Sproject sproject){
        Integer row = projectMapper.addStudentProject(sproject);
        return row > 0 ? true : false;
    }
    public boolean deleteStudentProject(Integer sid,Integer pid){
        Integer row = projectMapper.deleteStudentPeoject(sid, pid);
        return row > 0 ? true : false;
    }
    /*------------------------------------------------------------------------选题管理*/

    public Projectreport getStudentProjectreport(Integer tcid,Integer sid){
        Projectreport projectreport = studentMapper.findProjectreportByTCidSid(tcid, sid);
        return projectreport;
    }

    public boolean uploadStudentProjectreport(String fileType,Integer tcid, Integer sid, MultipartFile upload){
        //根据类型判断上传路径
        String filepath = fileType.equals("world") ? "E:/test/world/" : "E:/test/zip/";
        //文件上传
        Map resultMap = fileIO.fileUpload(filepath,upload);
        //文件上传正常
        boolean flag = Boolean.parseBoolean(resultMap.get("flag").toString());
        //获取上传路径
        String name =resultMap.get("name").toString();
        //文件路径写入数据库
        if (flag){
            Projectreport projectreport = new Projectreport();
            projectreport.setTcid(tcid);
            projectreport.setSid(sid);
            if (fileType.equals("world")){
                projectreport.setWorldurl(name);
            }else {
                projectreport.setZipurl(name);
            }
            Integer row = studentMapper.addProjectreport(projectreport);
        }
        return flag;
    }


    /*------------------------------------------------------------------------报告管理*/

    public Selfknow getStudentSelfknow(Integer sid,Integer tcid){
        Selfknow selfknow = studentMapper.findSelfknowBySidTCid(sid, tcid);
        return selfknow;
    }
    public boolean addStudentSelfknow(Selfknow selfknow){
        Selfknow selfknow1 = studentMapper.findSelfknowBySidTCid(selfknow.getSid(), selfknow.getTcid());
        if(selfknow1 != null){
            return false;
        }
        Integer row = studentMapper.addSelfknow(selfknow);
        return row > 0 ? true : false;
    }
    public boolean deleteStudentSelfknow(Integer skid,Integer sid){
        Integer row = studentMapper.deleteSelfknow(skid, sid);
        return row > 0 ? true : false;
    }
    public boolean changeStudentSelfknow(Selfknow selfknow){
        Integer row = studentMapper.updateSelfknow(selfknow);
        return row > 0 ? true : false;
    }
    /*------------------------------------------------------------------------评价管理*/

    public List<Checkwork> getCheckworks(Integer tcid){
        List<Checkwork> checkworks = checkworkMapper.findCheckworksByTCid(tcid);
        return checkworks;
    }
    public Checkwork getCheckwork(Integer cwid){
        Checkwork checkwork = checkworkMapper.findCheckworkByCWid(cwid);
        return checkwork;
    }
    public Scheck getStudentCheck(Integer sid,Integer cwid){
        Scheck scheck = checkworkMapper.findStudentCheckBySidCWid(sid,cwid);
        return scheck;
    }
    public boolean addStudentCheck(Scheck scheck){
        Integer row = checkworkMapper.addStudentCheck(scheck);
        return row > 0 ? true : false;
    }
    //考勤加查询
    public Scheck studentCheck(Scheck scheck){
        Scheck studentCheck = this.getStudentCheck(scheck.getSid(),scheck.getCwid());
        if (studentCheck == null){
            boolean flag = this.addStudentCheck(scheck);
            return this.getStudentCheck(scheck.getSid(),scheck.getCwid());
        }
        return studentCheck;
    }
    /*------------------------------------------------------------------------签到管理*/
}

