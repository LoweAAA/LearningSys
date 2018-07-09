package learningsys.service.impl;

import learningsys.model.Users;
import learningsys.repository.UsersDao;
import learningsys.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;

public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersDao usersDao;

    @Override
    public Users Confirm(String username, String password) {



        return null;
    }
}
