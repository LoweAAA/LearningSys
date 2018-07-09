package learningsys.model;

import javax.persistence.*;

@Entity
@Table(name="test")
public class Test {
    private int testInt;

    @Id
    @Column(name = "test_int")
    public int getTestInt() {
        return testInt;
    }

    public void setTestInt(int testInt) {
        this.testInt = testInt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Test test = (Test) o;

        if (testInt != test.testInt) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return testInt;
    }
}
