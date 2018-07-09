package learningsys.service;

import learningsys.entity.Histories;

import java.util.List;

public interface HistoriesService {

    boolean addHistory(int userId,int classesId,double rate) throws Exception;

    Histories get(int userId,int classesId) throws Exception;

    List query(int userId);
}
