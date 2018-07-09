package learningsys.repository;

import learningsys.model.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersDao extends CrudRepository<Users,Integer> {

    List<Users> findUsersByUsername(String username);


}
