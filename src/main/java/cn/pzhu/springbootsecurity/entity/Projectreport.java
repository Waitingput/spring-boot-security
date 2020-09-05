package cn.pzhu.springbootsecurity.entity;

import java.io.Serializable;

/**
 * 报告实体
 */
public class Projectreport implements Serializable {
    private Integer prid;
    private String worldurl;
    private String zipurl;
    private Integer tcid;
    private Integer sid;

    public Integer getPrid() {
        return prid;
    }

    public void setPrid(Integer prid) {
        this.prid = prid;
    }

    public String getWorldurl() {
        return worldurl;
    }

    public void setWorldurl(String worldurl) {
        this.worldurl = worldurl;
    }

    public String getZipurl() {
        return zipurl;
    }

    public void setZipurl(String zipurl) {
        this.zipurl = zipurl;
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
        return "Projectreport{" +
                "prid=" + prid +
                ", worldurl='" + worldurl + '\'' +
                ", zipurl='" + zipurl + '\'' +
                ", tcid=" + tcid +
                ", sid=" + sid +
                '}';
    }
}
