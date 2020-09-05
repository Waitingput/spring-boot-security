package cn.pzhu.springbootsecurity.entity;

import java.util.List;

/**
 * 分组实体类
 */
public class Group {
    private Integer gid;
    private Integer gno;
    private Integer snu;
    private Integer gnu;
    private Integer tcid;
    private List<Student> students;
    private Student leader;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getGno() {
        return gno;
    }

    public void setGno(Integer gno) {
        this.gno = gno;
    }

    public Integer getSnu() {
        return snu;
    }

    public void setSnu(Integer snu) {
        this.snu = snu;
    }

    public Integer getGnu() {
        return gnu;
    }

    public void setGnu(Integer gnu) {
        this.gnu = gnu;
    }

    public Integer getTcid() {
        return tcid;
    }

    public void setTcid(Integer tcid) {
        this.tcid = tcid;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getLeader() {
        return leader;
    }

    public void setLeader(Student leader) {
        this.leader = leader;
    }

    @Override
    public String toString() {
        return "Group{" +
                "gid=" + gid +
                ", gno=" + gno +
                ", snu=" + snu +
                ", gnu=" + gnu +
                ", tcid=" + tcid +
                ", students=" + students +
                ", leader=" + leader +
                '}';
    }
}
