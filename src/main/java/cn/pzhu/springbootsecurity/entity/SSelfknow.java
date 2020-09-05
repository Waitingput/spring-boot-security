package cn.pzhu.springbootsecurity.entity;

import java.io.Serializable;

/**
 * 学生自我评价
 */
public class SSelfknow implements Serializable {
    private Student student;
    private Selfknow selfknow;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Selfknow getSelfknow() {
        return selfknow;
    }

    public void setSelfknow(Selfknow selfknow) {
        this.selfknow = selfknow;
    }

    @Override
    public String toString() {
        return "SSelfknow{" +
                "student=" + student +
                ", selfknow=" + selfknow +
                '}';
    }
}
