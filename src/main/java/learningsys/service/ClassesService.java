package learningsys.service;

import learningsys.entity.Classes;

import java.util.List;

public interface ClassesService {

    List query(String className);

    Classes getClass(int classId) throws Exception;
}
