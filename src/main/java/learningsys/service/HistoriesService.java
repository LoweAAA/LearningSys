package learningsys.service;

import learningsys.entity.Histories;

import java.util.List;

public interface HistoriesService {

    boolean addHistory(int userId, int classesId) throws Exception;

    Histories get(int userId, int classesId);

    List query(int userId);

    boolean delete(int userId);

    boolean update(int userId, int id, double rate) throws Exception;
}
