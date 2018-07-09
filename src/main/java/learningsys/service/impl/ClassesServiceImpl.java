package learningsys.service.impl;

import learningsys.entity.Classes;
import learningsys.repository.ClassesDao;
import learningsys.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassesServiceImpl implements ClassesService {

    private final ClassesDao classesDao;

    @Autowired
    public ClassesServiceImpl(ClassesDao classesDao) {
        this.classesDao = classesDao;
    }

    @Override
    public List query(String className) {
        return classesDao.findByClassnameLike("%" + className + "%");
    }

    @Override
    public Classes getClass(int classId) throws Exception {
        return classesDao.findById(classId).get();
    }
}
