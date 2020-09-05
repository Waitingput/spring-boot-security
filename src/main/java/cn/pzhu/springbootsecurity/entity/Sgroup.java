package cn.pzhu.springbootsecurity.entity;

import java.io.Serializable;

/**
 * 学生分组实体
 */
public class Sgroup implements Serializable {
    private Integer sgid;
    private Integer sid;
    private Integer gid;

    public Integer getSgid() {
        return sgid;
    }

    public void setSgid(Integer sgid) {
        this.sgid = sgid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    @Override
    public String toString() {
        return "Sgroup{" +
                "sgid=" + sgid +
                ", sid=" + sid +
                ", gid=" + gid +
                '}';
    }
}
