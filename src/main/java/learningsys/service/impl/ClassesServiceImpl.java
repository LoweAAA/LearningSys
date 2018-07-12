package learningsys.service.impl;

import learningsys.entity.Classes;
import learningsys.repository.ClassesDao;
import learningsys.service.ClassesService;
import learningsys.utils.AipNipClient;
import learningsys.utils.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassesServiceImpl implements ClassesService {

    private final ClassesDao classesDao;

    @Autowired
    public ClassesServiceImpl(ClassesDao classesDao) {
        this.classesDao = classesDao;
    }

    @Override
    public List<Classes> query(String className) {
        AipNipClient aipNipClient = new AipNipClient();
        if (className != null && !"".equals(className)) {
            List<String> strings = aipNipClient.appartString(className);
            List<Classes> classes = new ArrayList<Classes>();
            for (String string : strings) {
                List<Classes> tem = classesDao.findByClassnameLike("%" + string + "%");
                for (Classes aTem : tem) {
                    int flag = 0;
                    for (Classes aClass : classes) {
                        if (aTem.getId() == aClass.getId()) {
                            flag = 1;
                        }
                    }
                    if (flag == 0) {
                        classes.add(aTem);
                    }
                }
            }
            return classes;
        } else {
            return classesDao.findByClassnameLike("%" + className + "%");
        }
    }

    @Override
    public Classes getClass(int classId) throws Exception {
        return classesDao.findById(classId).get();
    }

    @Override
    public boolean save(String className, String classUrl, int price, int userId, String classDetail) {
        Classes classes = new Classes();
        classes.setClassurl(classUrl);
        classes.setClassteacher(userId);
        classes.setClassprice(price);
        classes.setClassname(className);
        classes.setClassdetail(classDetail);
        if (classesDao.findByClassurl(classUrl).size() > 0) {
            return false;
        } else {
            classesDao.save(classes);
            return true;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(int classId, int userId) throws Exception {
        int teacherId = classesDao.findById(classId).get().getClassteacher();
        if (teacherId == userId) {
            Upload upload = new Upload();
            String className = classesDao.findById(classId).get().getClassurl();
            System.out.println(className);
            System.out.println(className.substring(33, className.length()));
            upload.delete(className);
            classesDao.deleteByClassid(classId);
            return true;
        }
        return false;
    }

    @Override
    public List getTeacher(int userId) {
        return classesDao.findByClassteacher(userId);
    }
}
