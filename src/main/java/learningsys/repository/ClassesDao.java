package learningsys.repository;

import learningsys.entity.Classes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassesDao extends CrudRepository<Classes, Integer> {

    List findByClassnameLike(String className);

    List findByClassurl(String classUrl);

}
