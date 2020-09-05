package cn.pzhu.springbootsecurity.entity;

import java.io.Serializable;

/**
 * 学生课题实体
 */
public class Sproject implements Serializable {
    private Integer spid;
    private Integer pid;
    private Integer sid;

    public Integer getSpid() {
        return spid;
    }

    public void setSpid(Integer spid) {
        this.spid = spid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "Sproject{" +
                "spid=" + spid +
                ", pid=" + pid +
                ", sid=" + sid +
                '}';
    }
}
