package cn.pzhu.springbootsecurity;

import cn.pzhu.springbootsecurity.entity.*;
import cn.pzhu.springbootsecurity.entity.Class;
import cn.pzhu.springbootsecurity.mapper.*;
import cn.pzhu.springbootsecurity.service.StudentService;
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
class StudentTests {
    @Autowired
    StudentService studentService;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    DeptMapper deptMapper;

    @Autowired
    ClassMapper classMapper;

    @Autowired
    GroupMapper groupMapper;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    CheckworkMapper checkworkMapper;

    //加密算法测试
    @Test
    void testBCrypt(){
       // String password = BCrypt.hashpw("$2a$10$A0hkRkZlwgPA3EeXysv/XOJaUVGEeqlzMWWWztZl/XsTUu25BLKMC", BCrypt.gensalt());
        boolean checkpw = BCrypt.checkpw("1", "$2a$10$A0hkRkZlwgPA3EeXysv/XOJaUVGEeqlzMWWWztZl/XsTUu25BLKMC");
        boolean checkpw1 = BCrypt.checkpw("1", "$2a$10$eobfvnf9LuK9aFevwsKPMeXSMMriSuI7wS2o/UkEqEYDlq/g1FPuC");
        boolean checkpw2 = BCrypt.checkpw("$2a$10$A0hkRkZlwgPA3EeXysv/XOJaUVGEeqlzMWWWztZl/XsTUu25BLKMC", "$2a$10$2/1pycyNRTSFvSMnnS/qheWpHBreBPPUoyl4Y.NnsTJ2YNOaPB7Ha");
        System.out.println(checkpw);
        System.out.println(checkpw1);
        System.out.println(checkpw2);
        Assert.assertThat(checkpw,is(true));
        //System.out.println(password);
    }
    //部门查询测试
    @Test
    void getDeptInfo(){
        Dept dept = deptMapper.findDeptById(1);
        Assert.assertThat(dept.getDid(),is(1));
        System.out.println(dept);
    }
    //班级查询测试
    @Test
    void getClassInfo(){
        Class aClass = classMapper.findClassById(1);
        Assert.assertThat(aClass.getCname(),is("一班"));
        System.out.println(aClass);
    }
    //学生信息查询测试
    @Test
    void getStudentInfo(){
        Student student = studentService.getStudentInfo(1);
        Assert.assertThat(student.getSno(),is("201710803001"));
        System.out.println(student);
    }
    @Test
    void getStudentCourses(){
        List<Tcourse> tCours = courseMapper.findCoursesBySid(1);
        for(Tcourse courses: tCours){
            System.out.println(courses);
        }
    }
    //课程查询测试
    @Test
    void getTCourse(){
        Tcourse tCourse = courseMapper.findCourseByTCid(1);
        Assert.assertThat(tCourse.getTcid(),is(1));
        System.out.println(tCourse);
    }
    //分组查询测试
    @Test
    void getGroupsnuBygid(){
        Group group = groupMapper.findGroupBySidTCid(1, 1);
        Assert.assertThat(group.getGid(),is(1));
        System.out.println(group);
    }

    @Test
    void getAllGroup(){
        List<Group> groups = groupMapper.findGroupsByTCid(1);
        for (Group group:groups) {
            System.out.println(group);
        }
    }
    @Test
    void getStudentsOfGroup(){
        List<Student> students = groupMapper.findStudentsOfGroupByGid(1);
        for (Student student:students){
            System.out.println(student);
        }
    }
    //添加分组测试
    @Test
    void addStudentGroup(){
        Sgroup sgroup = new Sgroup();
        sgroup.setSid(3);
        sgroup.setGid(1);
        Integer row = groupMapper.insertStudentGroup(sgroup);
        Assert.assertThat(row,is(3));
       System.out.println(row);
    }
    //退选分组测试
    @Test
    void deleteStudentGroup(){
        boolean flag = studentService.deleteStudentGroup(6, 1);
        Assert.assertThat(flag,is(true));
        System.out.println(flag);
    }
    //分组id查询测试
    @Test
    void findGroupByGid(){
        Group group = groupMapper.findGroupByGid(1);
        Assert.assertThat(group.getGid(),is(1));
        System.out.println(group);
    }
    //分组更新测试
    @Test
    void updateGroup(){
        Group group = new Group();
        Student student = new Student();
        student.setSid(1);
        group.setGid(2);
        group.setGno(2);
        group.setGnu(5);
        group.setLeader(student);
        Integer row = groupMapper.updateGroup(group);
        Assert.assertThat(row,is(2));
        System.out.println(row);
    }
    //
    @Test
    void getcheckworkBycwid(){
        //Checkwork checkwork = checkworkMapper.findCheckworkByCWid(1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();

        System.out.println(date);
    }
}
