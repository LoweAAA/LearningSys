package learningsys.service;

import learningsys.model.Users;

public interface UsersService {

    public Users Confirm(String username, String password);
}
