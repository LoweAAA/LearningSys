package learningsys.repository;

import learningsys.entity.Classes;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassesDao extends CrudRepository<Classes, Integer> {

    List findByClassnameLike(String className);

    List findByClassurl(String classUrl);

    @Modifying
    @Query(value = "DELETE FROM Classes c WHERE c.id = ?1")
    Integer deleteByClassid(int classId);

    @Query(value = "FROM Classes c WHERE c.classteacher = ?1")
    List findByClassteacher(int userId);
}
