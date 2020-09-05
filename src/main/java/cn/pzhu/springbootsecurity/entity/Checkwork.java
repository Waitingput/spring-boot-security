package cn.pzhu.springbootsecurity.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 考勤实体
 */
public class Checkwork implements Serializable {
    private Integer cwid;
    private Integer tcid;
    private String date;
    private Date begin;
    private Date end;

    public Integer getCwid() {
        return cwid;
    }

    public void setCwid(Integer cwid) {
        this.cwid = cwid;
    }

    public Integer getTcid() {
        return tcid;
    }

    public void setTcid(Integer tcid) {
        this.tcid = tcid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Checkwork{" +
                "cwid=" + cwid +
                ", tcid=" + tcid +
                ", date='" + date + '\'' +
                ", begin=" + begin +
                ", end=" + end +
                '}';
    }
}
