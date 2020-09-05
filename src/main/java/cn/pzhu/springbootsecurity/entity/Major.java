package cn.pzhu.springbootsecurity.entity;

import java.io.Serializable;

/**
 * 专业实体类
 */
public class Major implements Serializable {
    private Integer mid;
    private Integer grade;
    private String mname;
    private Dept dept;
    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Major{" +
                "mid=" + mid +
                ", grade=" + grade +
                ", mname='" + mname + '\'' +
                ", dept=" + dept +
                '}';
    }
}
