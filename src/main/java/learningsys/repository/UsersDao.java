package learningsys.repository;

import learningsys.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersDao extends JpaRepository<Users,Integer> {


    @Query(value="select city from users  where usern=?1",nativeQuery = true)
    public List<Users> getByUsername(String username);

}
