package cn.pzhu.springbootsecurity.entity;

/**
 * 授课表
 */
public class Tcourse {
    private Integer tcid;
    private Course course;
    private Teacher teacher;
    private Class sclass;

    public Integer getTcid() {
        return tcid;
    }

    public void setTcid(Integer tcid) {
        this.tcid = tcid;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Class getSclass() {
        return sclass;
    }

    public void setSclass(Class sclass) {
        this.sclass = sclass;
    }

    @Override
    public String toString() {
        return "Tcourse{" +
                "tcid=" + tcid +
                ", course=" + course +
                ", teacher=" + teacher +
                ", sclass=" + sclass +
                '}';
    }
}
