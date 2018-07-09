package learningsystest.repositoty;

import learningsys.repository.HistoriesDao;
import learningsystest.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class HistoriesDaoTest extends BaseTest {

    @Autowired
    private HistoriesDao historiesDao;

    @Test
    public void historiesFindByUseridAndClassid(){
        System.out.println(historiesDao.findByUseridAndClassid(1,2));
    }
}
