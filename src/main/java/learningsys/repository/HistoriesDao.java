package learningsys.repository;

import learningsys.entity.Histories;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoriesDao extends CrudRepository<Histories, Integer> {

    List<Histories> findByUserid(int userId);

    Histories findByUseridAndClassid(int userId, int classId);

    @Modifying
    @Query(value = "DELETE FROM Histories h WHERE h.userid = ?1")
    Integer deleteByUserid(int userId);
}
