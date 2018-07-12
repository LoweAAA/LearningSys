package learningsys.service;

import learningsys.entity.Classes;

import java.util.List;

public interface ClassesService {

    List<Classes> query(String className);

    Classes getClass(int classId) throws Exception;

    boolean save(String className,String classUrl,int price,int userId,String classDetail);
}
