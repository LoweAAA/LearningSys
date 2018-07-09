package learningsys.repository;

import learningsys.model.Histories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriesDao extends JpaRepository<Histories,Integer> {
}
