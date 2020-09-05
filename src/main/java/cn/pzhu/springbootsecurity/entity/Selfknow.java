package cn.pzhu.springbootsecurity.entity;

import java.io.Serializable;

/**
 * 自我评价实体
 */
public class Selfknow implements Serializable {
    private Integer skid;
    private String content;
    private Integer tcid;
    private Integer sid;

    public Integer getSkid() {
        return skid;
    }

    public void setSkid(Integer skid) {
        this.skid = skid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        return "Selfknow{" +
                "skid=" + skid +
                ", content='" + content + '\'' +
                ", tcid=" + tcid +
                ", sid=" + sid +
                '}';
    }
}
