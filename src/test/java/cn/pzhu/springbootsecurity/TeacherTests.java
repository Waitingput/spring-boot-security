package cn.pzhu.springbootsecurity;

import cn.pzhu.springbootsecurity.entity.Class;
import cn.pzhu.springbootsecurity.entity.*;
import cn.pzhu.springbootsecurity.mapper.*;
import cn.pzhu.springbootsecurity.service.StudentService;
import cn.pzhu.springbootsecurity.service.TeacherService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;
@SpringBootTest
class TeacherTests {
    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    GroupMapper groupMapper;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    CheckworkMapper checkworkMapper;
    @Autowired
    ReportcardMapper reportcardMapper;

    @Autowired
    TeacherService teacherService;

    @Test
    public void testgetTeacherInfo(){
        Teacher teacher = teacherMapper.findTeacherById(1);
        System.out.println(teacher);
    }
    @Test
    public void testgetReportcardByTCid(){
        List<SReportcard> sReportcards = reportcardMapper.findStudentReportCardsByTCid(1);
        System.out.println(sReportcards);
    }
    @Test
    public void testNochecked(){
        List<Student> students = checkworkMapper.findStudenOfNOChecked(1, 1);
        for(Student student : students){
            System.out.println(student);
        }
    }
    @Test
    public void testfindCoursInfo(){
        Tcourse tcourse = courseMapper.findCourseByTCid(5);
        System.out.println(tcourse);
    }
    //添加分组测试
    @Test
    public void testinsertGroup(){
        Group group = new Group();
        group.setGnu(4);
        group.setTcid(2);
        group.setSnu(4);
        boolean flag = teacherService.addGroup(group);
        Assert.assertThat(flag,is(true));
    }
    //删除分组测试
    public void deleteGroup(){
        boolean flag = teacherService.deleteGroup(1, 2);
        Assert.assertThat(flag,is(true));
    }
    //课题添加测试
    @Test
    public void testinsertProject(){
        Project project = new Project();
        project.setTcid(3);
        project.setPhight(4);
        project.setPname("软件测试");
        project.setPno(4);
        boolean flag = teacherService.addProject(project);
        Assert.assertThat(flag,is(true));
    }
    //删除课题测试
    @Test
    public void testdeleteProject(){
        boolean flag = teacherService.deleteProject(1, 2);
        Assert.assertThat(flag,is(true));
    }
    //添加考勤表测试
    @Test
    public void testinsertCheck(){
        Checkwork checkwork = new Checkwork();
        Date begin = new Date();
        Date end = new Date();
        checkwork.setTcid(2);
        checkwork.setBegin(begin);
        checkwork.setEnd(end);
        boolean flag = teacherService.addCheckwork(checkwork);
        Assert.assertThat(flag,is(true));
    }
    //删除考勤表测试
    @Test
    public void testdeleteCheck(){
        boolean flag = teacherService.deleteCheckwork(1, 2);
        Assert.assertThat(flag,is(true));
    }

}

