package cn.pzhu.springbootsecurity.entity;

import java.io.Serializable;

/**
 * 学生考勤实体
 */
public class Scheck implements Serializable {
    private  Integer scid;
    private boolean checked;
    private Integer cwid;
    private Integer sid;

    public Integer getScid() {
        return scid;
    }

    public void setScid(Integer scid) {
        this.scid = scid;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Integer getCwid() {
        return cwid;
    }

    public void setCwid(Integer cwid) {
        this.cwid = cwid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "Scheck{" +
                "scid=" + scid +
                ", checked=" + checked +
                ", cwid=" + cwid +
                ", sid=" + sid +
                '}';
    }
}
