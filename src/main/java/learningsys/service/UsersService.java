package learningsys.service;

import learningsys.model.Users;
import learningsys.repository.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;

public interface UsersService {

    public Users Confirm(String username,String password);
}
