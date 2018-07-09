package learningsys.controller;

import learningsys.entity.Classes;
import learningsys.entity.Histories;
import learningsys.model.ReturnClass;
import learningsys.service.ClassesService;
import learningsys.service.HistoriesService;
import learningsys.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassesController {

    private final ClassesService classesService;
    private final HistoriesService historiesService;

    @Autowired
    public ClassesController(ClassesService classesService, HistoriesService historiesService) {
        this.classesService = classesService;
        this.historiesService = historiesService;
    }

    @RequestMapping("/query")
    public ResponseUtil query(@RequestParam String className) {
        List classes = classesService.query(className);
        return ResponseUtil.success().put("data", classes);
    }

    @RequestMapping("/get")
    public ResponseUtil get(@RequestParam int classId, HttpSession session) {
        try {
            Classes classes = classesService.getClass(classId);
            if (session.getAttribute("userid") != null) {
                try {
                    historiesService.addHistory(Integer.parseInt(session.getAttribute("userid").toString()), classId);
                } catch (Exception e) {
                    return ResponseUtil.error("系统异常");
                }
            }
            ReturnClass returnClass = new ReturnClass();
            returnClass.setId(classes.getId());
            returnClass.setClassname(classes.getClassname());
            returnClass.setClassdetail(classes.getClassdetail());
            returnClass.setClassprice(classes.getClassprice());
            returnClass.setClassteacher(classes.getClassteacher());
            returnClass.setClassurl(classes.getClassurl());
            returnClass.setRate(0);
            if (session.getAttribute("userid") != null) {
                try {
                    Histories histories = historiesService.get(Integer.parseInt(session.getAttribute("userid").toString()), classId);
                    returnClass.setRate(histories.getRate());
                } catch (Exception e) {
                    return ResponseUtil.error("系统异常");
                }
            }
            return ResponseUtil.success().put("data", returnClass);
        } catch (Exception e) {
            return ResponseUtil.error("未找到对应课程");
        }
    }
}
