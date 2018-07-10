package learningsys.model;

public class ReturnClass {
    private int id;
    private String classname;
    private String classurl;
    private int classprice;
    private String classteacher;
    private String classdetail;
    private double rate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getClassurl() {
        return classurl;
    }

    public void setClassurl(String classurl) {
        this.classurl = classurl;
    }

    public int getClassprice() {
        return classprice;
    }

    public void setClassprice(int classprice) {
        this.classprice = classprice;
    }

    public String getClassteacher() {
        return classteacher;
    }

    public void setClassteacher(String classteacher) {
        this.classteacher = classteacher;
    }

    public String getClassdetail() {
        return classdetail;
    }

    public void setClassdetail(String classdetail) {
        this.classdetail = classdetail;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
