package learningsys.service;

import learningsys.entity.Favourites;

import java.util.List;

public interface FavouritesService {

    List query(int userId);

    boolean addFavourities(int userId, int classId);

    boolean delete(int userId, int id) throws Exception;
}
