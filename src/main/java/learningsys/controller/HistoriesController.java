package learningsys.controller;

import learningsys.entity.Histories;
import learningsys.service.HistoriesService;
import learningsys.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/query")
    public ResponseUtil query(HttpSession session) {
        Integer userId = null;
        try {
            userId = Integer.parseInt(session.getAttribute("userid").toString());
        } catch (Exception e) {
            return ResponseUtil.error("未登录，请登陆后再进行操作");
        }
        return ResponseUtil.success().put("data", historiesService.query(userId));
    }

    @RequestMapping("/delete")
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

    @RequestMapping("/update")
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