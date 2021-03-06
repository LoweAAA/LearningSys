package learningsys.service.impl;

import learningsys.entity.Favourites;
import learningsys.repository.FavouritesDao;
import learningsys.service.FavouritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class FavouritesServiceImpl implements FavouritesService {

    public static Integer operator = null;
    private final FavouritesDao favouritesDao;

    @Autowired
    public FavouritesServiceImpl(FavouritesDao favouritesDao) {
        this.favouritesDao = favouritesDao;
    }

    @Override
    public List query(int userId) {
        List favourites = favouritesDao.findByUserid(userId);
        favourites.sort((Comparator<Favourites>) (o1, o2) -> {
            if (o1.getTime().before(o2.getTime())) {
                return 1;
            }
            return -1;
        });
        return favourites;
    }

    @Override
    public boolean isFavourite(int userId, int classId) {
        Favourites favourite = favouritesDao.findByClassidAndUserid(classId, userId);
        return favourite != null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addAndDelete(int userId, int classId) throws Exception {
        Favourites favourite = favouritesDao.findByClassidAndUserid(classId, userId);
        if (favourite == null) {
            Favourites favourites = new Favourites();
            favourites.setUserid(userId);
            favourites.setClassid(classId);
            favourites.setTime(Timestamp.valueOf(LocalDateTime.now()));
            favouritesDao.save(favourites);
            operator = 0;
            return true;
        } else {
            favouritesDao.deleteById(favourite.getId());
            operator = 1;
            return true;
        }
    }
}
