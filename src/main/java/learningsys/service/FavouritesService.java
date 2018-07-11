package learningsys.service;

import java.util.List;

public interface FavouritesService {

    List query(int userId);

    boolean addFavourities(int userId, int classId);

    boolean delete(int userId, int id) throws Exception;

    boolean isFavourite(int userId, int classId);

    boolean addAndDelete(int userId, int classId) throws Exception;
}
