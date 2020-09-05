package cn.pzhu.springbootsecurity.entity;

import java.io.Serializable;

/**
 * 学生&成绩
 */
public class SReportcard implements Serializable {
    private Student student;
    private Reportcard reportcard;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Reportcard getReportcard() {
        return reportcard;
    }

    public void setReportcard(Reportcard reportcard) {
        this.reportcard = reportcard;
    }

    @Override
    public String toString() {
        return "SReportcard{" +
                "student=" + student +
                ", reportcard=" + reportcard +
                '}';
    }
}
