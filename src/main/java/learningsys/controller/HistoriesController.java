package learningsys.controller;

import io.swagger.annotations.ApiOperation;
import learningsys.entity.Histories;
import learningsys.service.HistoriesService;
import learningsys.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/history")
public class HistoriesController {

    private final HistoriesService historiesService;

    @Autowired
    public HistoriesController(HistoriesService historiesService) {
        this.historiesService = historiesService;
    }

    @ApiOperation(value = "查看历史记录")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
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
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
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
    public ResponseUtil update(@RequestParam int classesId, @RequestParam double rate, HttpSession session) {
        if (session.getAttribute("userid") != null) {
            Integer userId = Integer.parseInt(session.getAttribute("userid").toString());
            Histories histories = historiesService.get(userId, classesId);
            try {
                historiesService.update(userId, histories.getId(), rate);
                return ResponseUtil.success("已登录登陆保存成功");
            } catch (Exception e) {
                return ResponseUtil.success("历史记录不存在");
            }
        }
        return ResponseUtil.success("未登录保存成功");
    }
}
