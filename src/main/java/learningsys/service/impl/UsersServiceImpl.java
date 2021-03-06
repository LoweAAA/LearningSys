package learningsys.service.impl;

import learningsys.entity.Users;
import learningsys.repository.UsersDao;
import learningsys.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersDao usersDao;

    @Override
    public Users Confirm(String username, String password) {
        try {
            Users users = usersDao.findUsersByUsername(username).get(0);
            if (users.getPassword().equals(password)) {
                return users;
            }
        } catch (Exception e) {
            System.out.println("账号不存在");
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void Register(String username, String password, String email, byte role) {
        Users users = new Users();
        users.setEmail(email);
        users.setUsername(username);
        users.setPassword(password);
        users.setRole(role);
        users.setNickname(username);
        usersDao.save(users);
    }

    @Override
    public Users get(int id) throws Exception{
        return usersDao.findById(id).get();
    }


}
