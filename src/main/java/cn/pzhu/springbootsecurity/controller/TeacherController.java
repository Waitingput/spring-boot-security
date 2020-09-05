package cn.pzhu.springbootsecurity.controller;

import cn.pzhu.springbootsecurity.entity.*;
import cn.pzhu.springbootsecurity.service.TeacherService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 教师controller
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @GetMapping("/info/{uid}")
    public String getStudentInfo(@PathVariable("uid") Integer uid,
                                 Model model){
        Teacher teacher = teacherService.getTeacherInfo(uid);
        model.addAttribute("teacher",teacher);
        return "teacher/teacherInfo";
    }
    /*-------------------------------------------------------------个人信息*/


    //总课程获取
    @GetMapping("/courses/{uid}")
    public String getStudentCourses(@PathVariable("uid") Integer uid,
                                    Model model){
        List<Tcourse> tcourses = teacherService.getTeacherCourses(uid);
        model.addAttribute("tCourses", tcourses);
        return"teacher/courses";
    }
    //详细课程信息获取
    @GetMapping("/course/{tcid}")
    public String getStudentCourse(@PathVariable("tcid") Integer tcid,
                                   Model model,
                                   HttpSession session){
        Tcourse tcourseInfo = teacherService.getTCourseInfo(tcid);
        model.addAttribute("tCourse", tcourseInfo);
        session.setAttribute("tCourse", tcourseInfo);
        return "teacher/course/courseInfo";
    }
    /*-------------------------------------------------------------课程信息*/


    //分组信息获取
    @GetMapping("/course/groups/{tcid}")
    public String getStudentGroupInfo(@PathVariable("tcid") Integer tcid,
                                      HttpSession session,
                                      Model model){
        Integer sid = Integer.parseInt(session.getAttribute("uid").toString());
        List<Group> allGroup = teacherService.getAllGroup(tcid);
        model.addAttribute("allGroup",allGroup);
        return "teacher/course/groupsInfo";
    }
    //分组信息详情
    @GetMapping("/course/group/{gid}")
    public String getGroupInfo(@PathVariable("gid") Integer gid,
                               Model model){
        List<Student> students = teacherService.getStudentsOfGroup(gid);
        model.addAttribute("students",students);
        return "teacher/course/groupInfo";
    }
    //分组添加
    @PostMapping("/course/group")
    public String addGroup(Group group,
                           HttpSession session,
                           Model model){
        Integer tcid = ((Tcourse) session.getAttribute("tCourse")).getTcid();
        group.setTcid(tcid);
        boolean flag = teacherService.addGroup(group);
        return "redirect:/teacher/course/groups/"+tcid;
    }
    //删除分组
    @DeleteMapping("/course/group/{gid}")
    public String deleteGroup(@PathVariable("gid") Integer gid,
                              HttpSession session,
                              Model model){
        Integer tcid = ((Tcourse) session.getAttribute("tCourse")).getTcid();
        boolean flag = teacherService.deleteGroup(gid, tcid);
        return "redirect:/teacher/course/groups/"+tcid;
    }
    //更新分组
    @PutMapping("/course/group")
    public String changeGroup(Group group,
                              HttpSession session){
        boolean flag = teacherService.changeGroup(group);
        Integer tcid = ((Tcourse) session.getAttribute("tCourse")).getTcid();
        return "redirect:/teacher/course/groups/"+tcid;
    }

    /*-------------------------------------------------------------分组信息*/

    //课题信息获取
    @GetMapping("/course/projects/{tcid}")
    public String getProjects(@PathVariable("tcid") Integer tcid,
                              HttpSession session,
                              Model model){
        List<Project> projects = teacherService.getAllProjects(tcid);
        model.addAttribute("projects",projects);
        return "teacher/course/projects";
    }
    //课题添加
    @PostMapping("/course/project")
    public String addProject(Project project,
                           HttpSession session,
                           Model model){
        Integer tcid = ((Tcourse) session.getAttribute("tCourse")).getTcid();
        project.setTcid(tcid);
        boolean flag = teacherService.addProject(project);
        return "redirect:/teacher/course/projects/"+tcid;
    }
    //删除课题
    @DeleteMapping("/course/project/{pid}")
    public String deleteProject(@PathVariable("pid") Integer pid,
                              HttpSession session,
                              Model model){
        Integer tcid = ((Tcourse) session.getAttribute("tCourse")).getTcid();
        boolean flag = teacherService.deleteProject(pid, tcid);
        return "redirect:/teacher/course/projects/"+tcid;
    }
    //修改课题
    @PutMapping("/course/project")
    public String changeGroup(Project project,
                              HttpSession session){
        boolean flag = teacherService.changeProject(project);
        Integer tcid = ((Tcourse) session.getAttribute("tCourse")).getTcid();
        return "redirect:/teacher/course/projects/"+tcid;
    }

    /*-------------------------------------------------------------课题信息*/
    //获取所有学生评价信息
    @GetMapping("/course/self/{tcid}")
    public String getStudentsSelfknow(@PathVariable("tcid") Integer tcid,
                                      Model model){
        List<SSelfknow> studentsSelfknow = teacherService.getStudentSelfknow(tcid);
        model.addAttribute("studentsSelfknow",studentsSelfknow);
        return "teacher/course/selfknow";
    }

    /*-------------------------------------------------------------评价信息*/
    //获取考勤表
    @GetMapping("/course/checks/{tcid}")
    public String getCheckworks(@PathVariable("tcid") Integer tcid,
                                Model model){
        List<Checkwork> checkworks = teacherService.getCheckworks(tcid);
        model.addAttribute("checkworks",checkworks);
        return "teacher/course/check";
    }
    //考勤表添加
    @PostMapping("/course/check")
    @ResponseBody
    public Integer addCheckwork(@RequestBody Checkwork checkwork,
                             HttpSession session,
                             Model model){
        Integer tcid = ((Tcourse) session.getAttribute("tCourse")).getTcid();
        checkwork.setTcid(tcid);
        System.out.println(checkwork);
        boolean flag = teacherService.addCheckwork(checkwork);
        return flag ? 1 : 0;
    }
    //删除考勤表
    @DeleteMapping("/course/check/{cwid}")
    public String deleteCheckwork(@PathVariable("cwid") Integer cwid,
                                HttpSession session,
                                Model model){
        Integer tcid = ((Tcourse) session.getAttribute("tCourse")).getTcid();
        boolean flag = teacherService.deleteCheckwork(cwid, tcid);
        return "redirect:/teacher/course/checks/"+tcid;
    }
    //修改考勤表
    @PutMapping("/course/check")
    public String changeCheckwork(Checkwork checkwork,
                              HttpSession session){
        boolean flag = teacherService.changeChechwork(checkwork);
        Integer tcid = ((Tcourse) session.getAttribute("tCourse")).getTcid();
        return "redirect:/teacher/course/projects/"+tcid;
    }

    /*-------------------------------------------------------------考勤信息*/
    //获取成绩
    @GetMapping("/course/reportcard/{tcid}")
    public String getStudentReportcard(@PathVariable("tcid") Integer tcid,
                                Model model){
        List<SReportcard> reportcards = teacherService.getReportcards(tcid);
        model.addAttribute("reportcards",reportcards);
        return "teacher/course/reportcard";
    }
    //成绩添加
    @PostMapping("/course/reportcard")
    public String addStudentReportcard(Reportcard reportcard,
                               HttpSession session,
                               Model model){
        Integer tcid = ((Tcourse) session.getAttribute("tCourse")).getTcid();
        reportcard.setTcid(tcid);
        boolean flag = teacherService.addReportcard(reportcard);
        return "redirect:/teacher/course/reportcard/"+tcid;
    }
    //删除成绩
    @DeleteMapping("/course/reportcard{rcid}")
    public String deleteReportCard(@PathVariable("rcid") Integer rcid,
                                  HttpSession session,
                                  Model model){
        Integer tcid = ((Tcourse) session.getAttribute("tCourse")).getTcid();
        boolean flag = teacherService.deleteReportcard(rcid, tcid);
        return "redirect:/teacher/course/reportcard/"+tcid;
    }
    //修改成绩
    @PutMapping("/course/reportcard")
    public String changeReportCard(Reportcard reportcard,
                                  HttpSession session){
        boolean flag = teacherService.changeReportcard(reportcard);
        Integer tcid = ((Tcourse) session.getAttribute("tCourse")).getTcid();
        return "redirect:/teacher/course/reportcard/"+tcid;
    }

    /*-------------------------------------------------------------成绩信息*/
}
