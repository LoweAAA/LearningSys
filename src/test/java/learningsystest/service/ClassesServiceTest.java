package learningsystest.service;

import learningsys.entity.Classes;
import learningsys.service.ClassesService;
import learningsystest.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class ClassesServiceTest extends BaseTest {


    @Autowired
    private ClassesService classesService;

    @Test
    public void query() {
        List<Classes> classes=classesService.query("零基础学习");
        for(int i=0;i<classes.size();i++){
            System.out.println(classes.get(i).getClassname());
        }

    }



    @Test
    public void save() {
    }
}