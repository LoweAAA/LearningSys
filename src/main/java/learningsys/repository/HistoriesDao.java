package learningsys.repository;

import learningsys.entity.Histories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoriesDao extends CrudRepository<Histories, Integer> {

    List<Histories> findByUserid(int userId);

    Histories findByUseridAndClassid(int userId, int classId);
}
