package cn.pzhu.springbootsecurity.entity;

import java.io.Serializable;

/**
 * 学生实体类
 */
public class Student implements Serializable {
    private Integer sid;
    private String sno;
    private String sname;
    private Dept dept;
    private Major major;
    private Class sclass;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public Class getSclass() {
        return sclass;
    }

    public void setSclass(Class sclass) {
        this.sclass = sclass;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", sno='" + sno + '\'' +
                ", sname='" + sname + '\'' +
                ", dept=" + dept +
                ", major=" + major +
                ", sclass=" + sclass +
                '}';
    }
}
