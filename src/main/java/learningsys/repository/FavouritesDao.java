package learningsys.repository;

import learningsys.entity.Favourites;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouritesDao extends CrudRepository<Favourites, Integer> {

    List findByUserid(int userId);

    Favourites findByClassidAndUserid(int classId,int userId);
}
