package learningsys.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import learningsys.entity.Histories;
import learningsys.model.GetHistory;
import learningsys.service.HistoriesService;
import learningsys.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/history")
@CrossOrigin(origins = "*", maxAge = 3600)
public class HistoriesController {

    private final HistoriesService historiesService;

    @Autowired
    public HistoriesController(HistoriesService historiesService) {
        this.historiesService = historiesService;
    }

    @ApiOperation(value = "查看历史记录")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public ResponseUtil query(HttpSession session) {
        Integer userId = null;
        try {
            userId = Integer.parseInt(session.getAttribute("userid").toString());
        } catch (Exception e) {
            return ResponseUtil.error("未登录，请登陆后再进行操作");
        }
        return ResponseUtil.success().put("data", historiesService.query(userId));
    }

    @ApiOperation(value = "清空历史记录")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseUtil delete(HttpSession session) {
        Integer userId = null;
        try {
            userId = Integer.parseInt(session.getAttribute("userid").toString());
        } catch (Exception e) {
            return ResponseUtil.error("未登录，请登陆后再进行操作");
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
                return ResponseUtil.success("已登录登陆保存成功");
            } catch (Exception e) {
                return ResponseUtil.success("历史记录不存在");
            }
        }
        return ResponseUtil.success("未登录保存成功");
    }
}
