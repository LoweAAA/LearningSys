package learningsys.service.impl;

import learningsys.entity.Histories;
import learningsys.repository.HistoriesDao;
import learningsys.service.HistoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class HistoriesServiceImpl implements HistoriesService {

    private final HistoriesDao historiesDao;

    @Autowired
    public HistoriesServiceImpl(HistoriesDao historiesDao) {
        this.historiesDao = historiesDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addHistory(int userId, int classesId, double rate) throws Exception {
        List historiesList = historiesDao.findByUserid(userId);
        if (historiesList.size() < 20) {
            for (Object aHistoriesList : historiesList) {
                Histories histories = (Histories) aHistoriesList;
                if (histories.getClassid() == classesId) {
                    histories.setRate(rate);
                    histories.setTime(Timestamp.valueOf(LocalDateTime.now()));
                    historiesDao.save(histories);
                    break;
                }
            }
        } else {
            historiesList.sort((Comparator<Histories>) (o1, o2) -> Integer.compare(o1.getTime().compareTo(o2.getTime()), 0));
            historiesDao.delete((Histories) historiesList.get(0));
            Histories histories = new Histories();
            histories.setUserid(userId);
            histories.setClassid(classesId);
            histories.setRate(rate);
            histories.setTime(Timestamp.valueOf(LocalDateTime.now()));
            historiesDao.save(histories);
        }
        return true;
    }

    @Override
    public Histories get(int userId, int classesId) throws Exception {
        return historiesDao.findByUseridAndClassid(userId, classesId);
    }

    @Override
    public List query(int userId) {
        return historiesDao.findByUserid(userId);
    }
}
