package learningsys.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import learningsys.entity.Histories;
import learningsys.model.GetHistory;
import learningsys.model.ReturnHistory;
import learningsys.service.ClassesService;
import learningsys.service.HistoriesService;
import learningsys.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/history")
@CrossOrigin(origins = "*", maxAge = 3600)
public class HistoriesController {

    private final HistoriesService historiesService;
    private final ClassesService classesService;

    @Autowired
    public HistoriesController(HistoriesService historiesService, ClassesService classesService) {
        this.historiesService = historiesService;
        this.classesService = classesService;
    }

    @ApiOperation(value = "查看历史记录")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public ResponseUtil query(HttpSession session) {
        Integer userId = null;
        try {
            userId = Integer.parseInt(session.getAttribute("userid").toString());
        } catch (Exception e) {
            return ResponseUtil.error(202, "未登录，请登陆后再进行操作");
        }
        List favourites = historiesService.query(userId);
        List<ReturnHistory> historyList = new ArrayList<>();
        for (Object aHistory : favourites) {
            ReturnHistory returnFavourite = new ReturnHistory();
            Histories historie = (Histories) aHistory;
            returnFavourite.setId(historie.getId());
            returnFavourite.setTime(historie.getTime());
            try {
                returnFavourite.setClassName(classesService.getClass(historie.getClassid()).getClassname());
                returnFavourite.setClassDetail(classesService.getClass(historie.getClassid()).getClassdetail());
            } catch (Exception e) {
                return ResponseUtil.error("课程已删除");
            }
            returnFavourite.setClassId(historie.getClassid());
            historyList.add(returnFavourite);
        }
        return ResponseUtil.success().put("data", historyList);
    }

    @ApiOperation(value = "清空历史记录")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseUtil delete(HttpSession session) {
        Integer userId = null;
        try {
            userId = Integer.parseInt(session.getAttribute("userid").toString());
        } catch (Exception e) {
            return ResponseUtil.error(202, "未登录，请登陆后再进行操作");
        }
        historiesService.delete(userId);
        return ResponseUtil.success("清空成功");
    }

    @ApiOperation(value = "修改历史记录（视频观看时间保存）")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseUtil update(@ApiParam(value = "当前视频id号和观看进度rate") @RequestBody GetHistory rowHistories, HttpSession session) {
        if (session.getAttribute("userid") != null) {
            Integer userId = Integer.parseInt(session.getAttribute("userid").toString());
            Histories histories = historiesService.get(userId, rowHistories.getClassId());
            try {
                historiesService.update(userId, histories.getId(), rowHistories.getRate());
                return ResponseUtil.success();
            } catch (Exception e) {
                return ResponseUtil.success("历史记录不存在");
            }
        }
        return ResponseUtil.success("未登录保存成功");
    }
}
