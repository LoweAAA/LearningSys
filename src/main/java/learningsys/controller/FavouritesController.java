package learningsys.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import learningsys.entity.Favourites;
import learningsys.model.GetFavouriteId;
import learningsys.model.ReturnFavourite;
import learningsys.service.ClassesService;
import learningsys.service.FavouritesService;
import learningsys.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/favourite")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FavouritesController {

    private final FavouritesService favouritesService;
    private final ClassesService classesService;

    @Autowired
    public FavouritesController(FavouritesService favouritesService, ClassesService classesService) {
        this.favouritesService = favouritesService;
        this.classesService = classesService;
    }

    @ApiOperation(value = "查看收藏夹")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtil query(HttpSession session) {
        Integer userId = null;
        try {
            userId = Integer.parseInt(session.getAttribute("userid").toString());
        } catch (Exception e) {
            return ResponseUtil.error(202, "未登录，请登陆后再进行操作");
        }
        List favourites = favouritesService.query(userId);
        List<ReturnFavourite> favouriteList = new ArrayList<>();
        for (Object aFavourite : favourites) {
            ReturnFavourite returnFavourite = new ReturnFavourite();
            Favourites favourite = (Favourites) aFavourite;
            returnFavourite.setId(favourite.getId());
            returnFavourite.setTime(favourite.getTime());
            try {
                returnFavourite.setClassName(classesService.getClass(favourite.getClassid()).getClassname());
                returnFavourite.setClassDetail(classesService.getClass(favourite.getClassid()).getClassdetail());
            } catch (Exception e) {
                return ResponseUtil.error("课程已删除");
            }
            returnFavourite.setClassId(favourite.getClassid());
            favouriteList.add(returnFavourite);
        }
        return ResponseUtil.success().put("data", favouriteList);
    }

    @ApiOperation(value = "保存或删除收藏夹中某一记录")
    @RequestMapping(value = "/addanddelete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtil addAndDelete(@ApiParam(value = "收藏的课程id") @RequestBody GetFavouriteId rowFavourites, HttpSession session) {
        Integer userId = null;
        try {
            userId = Integer.parseInt(session.getAttribute("userid").toString());
        } catch (Exception e) {
            return ResponseUtil.error(202, "未登录，请登陆后再进行操作");
        }
        try {
            if (favouritesService.addAndDelete(userId, rowFavourites.getId())) {
                return ResponseUtil.success("操作成功");
            }
        } catch (Exception e) {
            return ResponseUtil.error("系统异常");
        }
        return ResponseUtil.error("当前用户不匹配！");
    }
}
