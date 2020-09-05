package cn.pzhu.springbootsecurity.entity;

import java.io.Serializable;

/**
 * 教师实体
 */
public class Teacher implements Serializable {
    private Integer tid;
    private String tno;
    private String tname;
    private Dept dept;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tid=" + tid +
                ", tno='" + tno + '\'' +
                ", tname='" + tname + '\'' +
                ", dept=" + dept +
                '}';
    }
}
