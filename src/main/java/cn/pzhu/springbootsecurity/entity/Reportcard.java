package cn.pzhu.springbootsecurity.entity;

import java.io.Serializable;

/**
 * 成绩表
 */
public class Reportcard implements Serializable {
    private Integer rcid;
    private Integer usual_grade;
    private Integer report_grade;
    private Integer tcid;
    private Integer sid;

    public Integer getRcid() {
        return rcid;
    }

    public void setRcid(Integer rcid) {
        this.rcid = rcid;
    }

    public Integer getUsual_grade() {
        return usual_grade;
    }

    public void setUsual_grade(Integer usual_grade) {
        this.usual_grade = usual_grade;
    }

    public Integer getReport_grade() {
        return report_grade;
    }

    public void setReport_grade(Integer report_grade) {
        this.report_grade = report_grade;
    }

    public Integer getTcid() {
        return tcid;
    }

    public void setTcid(Integer tcid) {
        this.tcid = tcid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "Reportcard{" +
                "rcid=" + rcid +
                ", usual_grade=" + usual_grade +
                ", report_grade=" + report_grade +
                ", tcid=" + tcid +
                ", sid=" + sid +
                '}';
    }
}
