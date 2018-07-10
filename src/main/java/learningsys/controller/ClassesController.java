package learningsys.controller;

import io.swagger.annotations.ApiOperation;
import learningsys.entity.Classes;
import learningsys.entity.Histories;
import learningsys.model.ReturnClass;
import learningsys.service.ClassesService;
import learningsys.service.HistoriesService;
import learningsys.service.UsersService;
import learningsys.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/class")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ClassesController {

    private final ClassesService classesService;
    private final HistoriesService historiesService;
    private final UsersService usersService;

    @Autowired
    public ClassesController(ClassesService classesService, HistoriesService historiesService, UsersService usersService) {
        this.classesService = classesService;
        this.historiesService = historiesService;
        this.usersService = usersService;
    }

    @ApiOperation(value = "课程视频查询")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public ResponseUtil query(@RequestBody Classes rowClasses) {
        List classes = classesService.query(rowClasses.getClassname());
        return ResponseUtil.success().put("data", classes);
    }

    @ApiOperation(value = "课程视频观看")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public ResponseUtil get(@RequestBody Classes rowClasses, HttpSession session) {
        try {
            Classes classes = classesService.getClass(rowClasses.getId());
            if (session.getAttribute("userid") != null) {
                try {
                    historiesService.addHistory(Integer.parseInt(session.getAttribute("userid").toString()), rowClasses.getId());
                } catch (Exception e) {
                    return ResponseUtil.error("系统异常");
                }
            }
            ReturnClass returnClass = new ReturnClass();
            returnClass.setId(classes.getId());
            returnClass.setClassname(classes.getClassname());
            returnClass.setClassdetail(classes.getClassdetail());
            returnClass.setClassprice(classes.getClassprice());
            returnClass.setClassteacher(usersService.get(classes.getClassteacher()).getNickname());
            returnClass.setClassurl(classes.getClassurl());
            returnClass.setRate(0);
            if (session.getAttribute("userid") != null) {
                try {
                    Histories histories = historiesService.get(Integer.parseInt(session.getAttribute("userid").toString()), rowClasses.getId());
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
