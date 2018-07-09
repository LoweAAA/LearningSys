package learningsystest.repositoty;

import learningsys.repository.ClassesDao;
import learningsys.repository.HistoriesDao;
import learningsystest.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ClassesDaoTest extends BaseTest {

    @Autowired
    private ClassesDao classesDao;

    @Test
    public void classesFindByClassnameLike() {
        System.out.println(classesDao.findByClassnameLike("%aaa%"));
    }
}
