package learningsystest.repositoty;

import learningsys.model.Users;
import learningsys.repository.UsersDao;
import learningsystest.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UsersDaoTest extends BaseTest {

    @Autowired
    private UsersDao usersDao;

    @Test
    public void getByUsername() {
        String username = "123456";
        Users users = usersDao.findUsersByUsername(username).get(0);
        System.out.println(users.getEmail());
    }
}
