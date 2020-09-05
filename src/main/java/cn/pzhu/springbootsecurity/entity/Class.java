package cn.pzhu.springbootsecurity.entity;

import java.io.Serializable;

/**
 * 班级实体类
 */
public class Class implements Serializable {
    private Integer cid;
    private String cname;
    private Major major;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "Class{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", major=" + major +
                '}';
    }
}
