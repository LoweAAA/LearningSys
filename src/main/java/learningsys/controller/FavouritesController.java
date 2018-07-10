package learningsys.controller;

import io.swagger.annotations.ApiOperation;
import learningsys.entity.Classes;
import learningsys.entity.Favourites;
import learningsys.service.FavouritesService;
import learningsys.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/favourite")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FavouritesController {

    private final FavouritesService favouritesService;

    @Autowired
    public FavouritesController(FavouritesService favouritesService) {
        this.favouritesService = favouritesService;
    }

    @ApiOperation(value = "添加到收藏夹")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public ResponseUtil addFavourites(@RequestBody Classes rowClasses, HttpSession session) {
        Integer userId = null;
        try {
            userId = Integer.parseInt(session.getAttribute("userid").toString());
        } catch (Exception e) {
            return ResponseUtil.error("未登录，请登陆后再进行操作");
        }
        favouritesService.addFavourities(userId, rowClasses.getId());
        return ResponseUtil.success("收藏成功");
    }

    @ApiOperation(value = "查看收藏夹")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public ResponseUtil query(HttpSession session) {
        Integer userId = null;
        try {
            userId = Integer.parseInt(session.getAttribute("userid").toString());
        } catch (Exception e) {
            return ResponseUtil.error("未登录，请登陆后再进行操作");
        }
        return ResponseUtil.success().put("data", favouritesService.query(userId));
    }

    @ApiOperation(value = "删除收藏夹中某一记录")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public ResponseUtil delete(@RequestBody Favourites rowFavourites, HttpSession session) {
        Integer userId = null;
        try {
            userId = Integer.parseInt(session.getAttribute("userid").toString());
        } catch (Exception e) {
            return ResponseUtil.error("未登录，请登陆后再进行操作");
        }
        try {
            if (favouritesService.delete(userId, rowFavourites.getId())) {
                return ResponseUtil.success();
            }
        } catch (Exception e) {
            return ResponseUtil.error("收藏不存在");
        }
        return ResponseUtil.error("当前用户不匹配！");
    }
}
