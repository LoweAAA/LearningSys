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
    public ResponseUtil update(@RequestParam int classesid, @RequestParam double rate, HttpSession session) {
        Integer userId = null;
        try {
            userId = Integer.parseInt(session.getAttribute("userid").toString());
        } catch (Exception e) {
            return ResponseUtil.success();
        }
        Histories histories = historiesService.get(userId, classesid);
        historiesService.update(userId, histories.getId(), rate);
        return ResponseUtil.success();

    }
}
