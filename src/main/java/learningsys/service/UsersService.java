package learningsys.service;

import learningsys.entity.Users;

public interface UsersService {

    public Users Confirm(String username, String password);
    public void Register(String username,
                         String password,
                        String email,
                         byte role);
    Users get(int id) throws Exception;
}
