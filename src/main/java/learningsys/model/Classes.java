package learningsys.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Classes {
    private int id;
    private String className;
    private String classUrl;
    private int classPrice;
    private int classTeacher;
    private String classDetail;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "className")
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Basic
    @Column(name = "classUrl")
    public String getClassUrl() {
        return classUrl;
    }

    public void setClassUrl(String classUrl) {
        this.classUrl = classUrl;
    }

    @Basic
    @Column(name = "classPrice")
    public int getClassPrice() {
        return classPrice;
    }

    public void setClassPrice(int classPrice) {
        this.classPrice = classPrice;
    }

    @Basic
    @Column(name = "classTeacher")
    public int getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(int classTeacher) {
        this.classTeacher = classTeacher;
    }

    @Basic
    @Column(name = "classDetail")
    public String getClassDetail() {
        return classDetail;
    }

    public void setClassDetail(String classDetail) {
        this.classDetail = classDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Classes classes = (Classes) o;

        if (id != classes.id) return false;
        if (classPrice != classes.classPrice) return false;
        if (classTeacher != classes.classTeacher) return false;
        if (className != null ? !className.equals(classes.className) : classes.className != null) return false;
        if (classUrl != null ? !classUrl.equals(classes.classUrl) : classes.classUrl != null) return false;
        if (classDetail != null ? !classDetail.equals(classes.classDetail) : classes.classDetail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + (classUrl != null ? classUrl.hashCode() : 0);
        result = 31 * result + classPrice;
        result = 31 * result + classTeacher;
        result = 31 * result + (classDetail != null ? classDetail.hashCode() : 0);
        return result;
    }
}
