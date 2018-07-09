package learningsys.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Classes {
    private int id;
    private String classname;
    private String classurl;
    private int classprice;
    private int classteacher;
    private String classdetail;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "classname")
    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    @Basic
    @Column(name = "classurl")
    public String getClassurl() {
        return classurl;
    }

    public void setClassurl(String classurl) {
        this.classurl = classurl;
    }

    @Basic
    @Column(name = "classprice")
    public int getClassprice() {
        return classprice;
    }

    public void setClassprice(int classprice) {
        this.classprice = classprice;
    }

    @Basic
    @Column(name = "classteacher")
    public int getClassteacher() {
        return classteacher;
    }

    public void setClassteacher(int classteacher) {
        this.classteacher = classteacher;
    }

    @Basic
    @Column(name = "classdetail")
    public String getClassdetail() {
        return classdetail;
    }

    public void setClassdetail(String classdetail) {
        this.classdetail = classdetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Classes classes = (Classes) o;

        if (id != classes.id) return false;
        if (classprice != classes.classprice) return false;
        if (classteacher != classes.classteacher) return false;
        if (classname != null ? !classname.equals(classes.classname) : classes.classname != null) return false;
        if (classurl != null ? !classurl.equals(classes.classurl) : classes.classurl != null) return false;
        if (classdetail != null ? !classdetail.equals(classes.classdetail) : classes.classdetail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (classname != null ? classname.hashCode() : 0);
        result = 31 * result + (classurl != null ? classurl.hashCode() : 0);
        result = 31 * result + classprice;
        result = 31 * result + classteacher;
        result = 31 * result + (classdetail != null ? classdetail.hashCode() : 0);
        return result;
    }
}
