package learningsystest.repositoty;


import learningsys.repository.TestRes;
import learningsystest.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class TestResTest extends BaseTest {

    @Autowired
    private TestRes testRes;


    @Test
    public void getList(){
        System.out.println(testRes.findAll().get(0).getTestInt());
    }

    @Test
    public void add(){
        learningsys.entity.Test test=new learningsys.entity.Test();
        testRes.save(test);
    }



}
