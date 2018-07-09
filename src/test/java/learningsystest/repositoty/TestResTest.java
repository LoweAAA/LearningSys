package learningsystest.repositoty;


import learningsys.Application;
import learningsys.repository.TestRes;
import learningsystest.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


public class TestResTest extends BaseTest {

    @Autowired
    private TestRes testRes;


    @Test
    public void getList(){
        System.out.println(testRes.findAll().get(0).getTestInt());
    }

    @Test
    public void add(){
        learningsys.model.Test test=new learningsys.model.Test();
        testRes.save(test);
    }



}
