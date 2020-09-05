package cn.pzhu.springbootsecurity.entity;

import java.io.Serializable;

public class Project implements Serializable {
    private Integer pid;
    private Integer pno;
    private String pname;
    private Integer phight;
    private Integer tcid;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getPno() {
        return pno;
    }

    public void setPno(Integer pno) {
        this.pno = pno;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getPhight() {
        return phight;
    }

    public void setPhight(Integer phight) {
        this.phight = phight;
    }

    public Integer getTcid() {
        return tcid;
    }

    public void setTcid(Integer tcid) {
        this.tcid = tcid;
    }

    @Override
    public String toString() {
        return "Project{" +
                "pid=" + pid +
                ", pno=" + pno +
                ", pname='" + pname + '\'' +
                ", phight=" + phight +
                ", tcid=" + tcid +
                '}';
    }
}
