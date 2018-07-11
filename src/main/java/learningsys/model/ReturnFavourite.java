package learningsys.model;

import java.sql.Timestamp;

public class ReturnFavourite {

    private int id;
    private int classId;
    private String className;
    private Timestamp time;
    private String classDetail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getClassDetail() {
        return classDetail;
    }

    public void setClassDetail(String classDetail) {
        this.classDetail = classDetail;
    }
}
