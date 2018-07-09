package learningsys.repository;

import learningsys.entity.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsDao extends CrudRepository<News,Integer> {


}
