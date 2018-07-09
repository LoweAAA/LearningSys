package learningsys.controller;

import learningsys.service.FavouritesService;
import learningsys.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/favourite")
public class FavouritesController {

    private final FavouritesService favouritesService;

    @Autowired
    public FavouritesController(FavouritesService favouritesService) {
        this.favouritesService = favouritesService;
    }

    @RequestMapping("/add")
    public ResponseUtil addFavourites(@RequestParam int classesId, HttpSession session) {
        Integer userId = null;
        try {
            userId = Integer.parseInt(session.getAttribute("userid").toString());
        } catch (Exception e) {
            return ResponseUtil.error("未登录，请登陆后再进行操作");
        }
        favouritesService.addFavourities(userId, classesId);
        return ResponseUtil.success("收藏成功");
    }

    @RequestMapping("/query")
    public ResponseUtil query(HttpSession session) {
        Integer userId = null;
        try {
            userId = Integer.parseInt(session.getAttribute("userid").toString());
        } catch (Exception e) {
            return ResponseUtil.error("未登录，请登陆后再进行操作");
        }
        return ResponseUtil.success().put("data", favouritesService.query(userId));
    }

    @RequestMapping("/delete")
    public ResponseUtil delete(@RequestParam int id, HttpSession session) {
        Integer userId = null;
        try {
            userId = Integer.parseInt(session.getAttribute("userid").toString());
        } catch (Exception e) {
            return ResponseUtil.error("未登录，请登陆后再进行操作");
        }
        try {
            if (favouritesService.delete(userId, id)) {
                return ResponseUtil.success();
            }
        } catch (Exception e) {
            return ResponseUtil.error("历史记录不存在");
        }
        return ResponseUtil.error("当前用户不匹配！");
    }
}
