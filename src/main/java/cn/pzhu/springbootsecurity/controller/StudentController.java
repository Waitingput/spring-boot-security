package cn.pzhu.springbootsecurity.controller;

import cn.pzhu.springbootsecurity.entity.*;
import cn.pzhu.springbootsecurity.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 学生controller
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    //个人信息获取
    @GetMapping("/info/{uid}")
    public String getStudentInfo(@PathVariable("uid") Integer uid,
                                 Model model){
        Student student = studentService.getStudentInfo(uid);
        model.addAttribute("student",student);
        return "student/studentInfo";
    }
    /*-------------------------------------------------------------个人信息*/
    //总课程获取
    @GetMapping("/courses/{uid}")
    public String getStudentCourses(@PathVariable("uid") Integer uid,
                                   Model model){
        List<Tcourse> tcourses = studentService.getStudentCourses(uid);
        model.addAttribute("tCourses", tcourses);
        return"student/courses";
    }
    //详细课程信息获取
    @GetMapping("/course/{tcid}")
    public String getStudentCourse(@PathVariable("tcid") Integer tcid,
                                   Model model,
                                   HttpSession session){
        Tcourse tcourseInfo = studentService.getTCourseInfo(tcid);
        model.addAttribute("tCourse", tcourseInfo);
        session.setAttribute("tCourse", tcourseInfo);
        return "student/course/courseInfo";
    }
    /*-------------------------------------------------------------课程信息*/

    //分组信息获取
    @GetMapping("/course/groups/{tcid}")
    public String getStudentGroupInfo(@PathVariable("tcid") Integer tcid,
                                      HttpSession session,
                                      Model model){
        Integer sid = Integer.parseInt(session.getAttribute("uid").toString());
        Group studentGroup = studentService.getStudentGroup(sid, tcid);
        List<Group> allGroup = studentService.getAllGroup(tcid);
        model.addAttribute("studentGroup",studentGroup);
        model.addAttribute("allGroup",allGroup);
        return "student/course/groupsInfo";
    }
    //分组信息详情
    @GetMapping("/course/group/{gid}")
    public String getGroupInfo(@PathVariable("gid") Integer gid,
                               Model model){
        List<Student> students = studentService.getStudentsOfGroup(gid);
        model.addAttribute("students",students);
        return "student/course/groupInfo";
    }
    //退出分组
    @DeleteMapping("/course/group/{gid}")
    public String deleteGroup(@PathVariable("gid") Integer gid,
                              HttpSession session){
        Integer sid = Integer.parseInt(session.getAttribute("uid").toString());
        Integer tcid = ((Tcourse) session.getAttribute("tCourse")).getTcid();
        boolean flag = studentService.deleteStudentGroup(sid,gid);
        return "redirect:/student/course/groups/"+tcid;
    }
    //加入分组
    @PostMapping("/course/group")
    public String addGroup(Sgroup sgroup,
                           HttpSession session){
        Integer tcid = ((Tcourse) session.getAttribute("tCourse")).getTcid();
        boolean flag = studentService.addStudentGroup(sgroup,tcid);
        return "redirect:/student/course/groups/"+tcid;
    }
    /*-------------------------------------------------------------分组信息*/

    //课题信息获取
    @GetMapping("/course/projects/{tcid}")
    public String getProjects(@PathVariable("tcid") Integer tcid,
                              HttpSession session,
                              Model model){
        Integer sid = Integer.parseInt(session.getAttribute("uid").toString());
        List<Project> projects = studentService.getAllProjects(tcid);
        Project studentProject = studentService.getStudentProject(sid, tcid);
        model.addAttribute("projects",projects);
        model.addAttribute("studentProject",studentProject);
        return "student/course/projects";
    }
    //选择课题
    @PostMapping("/course/project")
    public String addStudentProject(Sproject sproject,
                                    Model model,
                                    HttpSession session){
        boolean flag = studentService.addStudentProject(sproject);
        if (!flag){
            model.addAttribute("msg","选题失败");
        }
        Integer tcid = ((Tcourse) session.getAttribute("tCourse")).getTcid();
        return "redirect:/student/course/projects/"+tcid;
    }
    //退选课题
    @DeleteMapping("/course/project/{pid}")
    public String deleteStudentProject(@PathVariable("pid") Integer pid,
                                       HttpSession session){
        Integer sid = Integer.parseInt(session.getAttribute("uid").toString());
        Integer tcid = ((Tcourse) session.getAttribute("tCourse")).getTcid();
        boolean flag = studentService.deleteStudentProject(sid, pid);
        return "redirect:/student/course/projects/"+tcid;
    }
    /*-------------------------------------------------------------课题信息*/
    //获取报告
    @GetMapping("/course/report/{tcid}")
    public String getStudentReportInfo(@PathVariable("tcid") Integer tcid,
                                       HttpSession session,
                                       Model model){
        Integer sid = Integer.parseInt(session.getAttribute("uid").toString());
        Projectreport projectreport = studentService.getStudentProjectreport(tcid, sid);
        model.addAttribute("projectreport",projectreport);
        return "student/course/report";
    }
    //提交报告
    @PostMapping("/course/report/{fileType}")
    public String uploadStudentWorldReport(@PathVariable("fileType") String fileTpye,
                                           MultipartFile upload,
                                           HttpSession session,
                                           Model model){
        Integer sid = Integer.parseInt(session.getAttribute("uid").toString());
        Integer tcid = ((Tcourse) session.getAttribute("tCourse")).getTcid();
        System.out.println(fileTpye);
        boolean flag = studentService.uploadStudentProjectreport(fileTpye, tcid, sid, upload);
        model.addAttribute("flag",flag);
        return "redirect:/student/course/report/"+tcid;
    }




    /*-------------------------------------------------------------成果信息*/

    //获取自我评价
    @GetMapping("/course/selfknow/{tcid}")
    public String getSelfknow(@PathVariable("tcid") Integer tcid,
                              HttpSession session,
                              Model model){
        Integer sid = Integer.parseInt(session.getAttribute("uid").toString());
        Selfknow selfknow = studentService.getStudentSelfknow(sid, tcid);
        model.addAttribute("selfknow",selfknow);
        return "student/course/selfknow";
    }
    //自我评价添加
    @PostMapping("/course/selfknow")
    public String addSelfknow(Selfknow selfknow){
        boolean flag = studentService.addStudentSelfknow(selfknow);
        return "redirect:/student/course/selfknow/"+selfknow.getTcid();
    }
    //自我评价修改
    @PutMapping("/course/selfknow")
    public String changeSelfknow(Selfknow selfknow){
        boolean flag = studentService.changeStudentSelfknow(selfknow);
        return "redirect:/student/course/selfknow/"+selfknow.getTcid();
    }
    //自我评价删除
    @DeleteMapping("/course/selfknow/{skid}")
    public String deleteSelfknow(@PathVariable("skid") Integer skid,
                                 HttpSession session){
        Integer sid = Integer.parseInt(session.getAttribute("uid").toString());
        Integer tcid = ((Tcourse) session.getAttribute("tCourse")).getTcid();
        boolean flag = studentService.deleteStudentSelfknow(skid, sid);
        return "redirect:/student/course/selfknow/"+tcid;
    }
    /*-------------------------------------------------------------评价信息*/


    //获取考勤表
    @GetMapping("/course/check/{tcid}")
    public String getCheckworks(@PathVariable("tcid") Integer tcid,
                                Model model){
        List<Checkwork> checkworks = studentService.getCheckworks(tcid);
        model.addAttribute("checkworks",checkworks);
        return "student/course/check";
    }
    //考勤
    @PostMapping("/course/check")
    @ResponseBody
    public Scheck studentCheck(@RequestBody Scheck scheck){
        Scheck studentCheck = studentService.studentCheck(scheck);
        System.out.println("return check");
        return studentCheck;
    }
    /*-------------------------------------------------------------考勤信息*/

}
