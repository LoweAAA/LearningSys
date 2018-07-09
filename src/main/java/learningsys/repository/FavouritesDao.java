package learningsys.repository;

import learningsys.model.Favourites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouritesDao extends JpaRepository<Favourites,Integer> {
}
