package learningsys.service.impl;

import learningsys.entity.Classes;
import learningsys.repository.ClassesDao;
import learningsys.service.ClassesService;
import learningsys.utils.AipNipClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


        AipNipClient aipNipClient=new AipNipClient();

        List<String> strings=aipNipClient.appartString(className);
        List<Classes> classes=new ArrayList<Classes>();

        for(int i=0;i<strings.size();i++){

            List<Classes> tem=classesDao.findByClassnameLike("%" + strings.get(i) + "%");

            for(int j=0;j<tem.size();j++){
                int flag=0;
                for(int k=0;k<classes.size();k++){
                    if(tem.get(j).getId()==classes.get(k).getId()){
                        flag=1;
                    }
                }
                if(flag==0){
                    classes.add(tem.get(j));
                }
            }

        }
        return classes;
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
}
