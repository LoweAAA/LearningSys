package learningsys.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import learningsys.entity.Classes;
import learningsys.entity.Histories;
import learningsys.model.GetClass;
import learningsys.model.GetClassId;
import learningsys.model.GetClassName;
import learningsys.model.ReturnClass;
import learningsys.service.ClassesService;
import learningsys.service.FavouritesService;
import learningsys.service.HistoriesService;
import learningsys.service.UsersService;
import learningsys.utils.ResponseUtil;
import learningsys.utils.Upload;
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
    private final FavouritesService favouritesService;
    private final Upload upload;

    @Autowired
    public ClassesController(ClassesService classesService, HistoriesService historiesService, UsersService usersService, FavouritesService favouritesService, Upload upload) {
        this.classesService = classesService;
        this.historiesService = historiesService;
        this.usersService = usersService;
        this.favouritesService = favouritesService;
        this.upload = upload;
    }

    @ApiOperation(value = "课程视频查询")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtil query(@ApiParam(value = "查询的课程名") @RequestBody GetClassName rowClasses) {
        List classes = classesService.query(rowClasses.getClassName());
        if (classes.isEmpty()) {
            return ResponseUtil.error(203, "未找到相应课程");
        }
        return ResponseUtil.success().put("data", classes);
    }

    @ApiOperation(value = "课程视频观看")
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtil get(@ApiParam(value = "观看的课程id") @RequestBody GetClassId rowClasses, HttpSession session) {
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
                    returnClass.setFavourite(favouritesService.isFavourite(Integer.parseInt(session.getAttribute("userid").toString()), rowClasses.getId()));
                    Histories histories = historiesService.get(Integer.parseInt(session.getAttribute("userid").toString()), rowClasses.getId());
                    returnClass.setRate(histories.getRate());
                    historiesService.update(Integer.parseInt(session.getAttribute("userid").toString()), rowClasses.getId(), 0);
                } catch (Exception e) {
                    return ResponseUtil.error("系统异常");
                }
            }
            return ResponseUtil.success().put("data", returnClass);
        } catch (Exception e) {
            return ResponseUtil.error("未找到对应课程");
        }
    }

    @ApiOperation(value = "视频上传")
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseUtil upload() {
        return ResponseUtil.success().put("token", upload.getUpToken());
    }

    @ApiOperation(value = "保存记录")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseUtil save(@RequestBody GetClass getClass) {
        if (classesService.save(getClass.getName(), getClass.getLessonUrl(), 0, getClass.getUserId(), getClass.getDesc())) {
            return ResponseUtil.success("保存成功");
        } else {
            return ResponseUtil.error("视频已存在");
        }
    }

    @ApiOperation(value = "删除视频")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseUtil delete(@RequestBody GetClassId getClassId, HttpSession session) {
        Integer userId = null;
        try {
            userId = Integer.parseInt(session.getAttribute("userid").toString());
        } catch (Exception e) {
            return ResponseUtil.error(202, "未登录，请登陆后再进行操作");
        }
        try {
            classesService.delete(getClassId.getId(), userId);
            return ResponseUtil.success("删除成功");
        } catch (Exception e) {
            return ResponseUtil.error("删除失败");
        }
    }

    @ApiOperation(value = "查找某位老师的视频")
    @RequestMapping(value = "/getteacher", method = RequestMethod.POST)
    public ResponseUtil getteacher(HttpSession session) {
        Integer userId = null;
        try {
            userId = Integer.parseInt(session.getAttribute("userid").toString());
        } catch (Exception e) {
            return ResponseUtil.error(202, "未登录，请登陆后再进行操作");
        }
        List classes = classesService.getTeacher(userId);
        return ResponseUtil.success().put("data", classes);
    }
}
