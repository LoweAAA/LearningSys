package learningsys.controller;

import io.swagger.annotations.ApiOperation;
import learningsys.service.NewsService;
import learningsys.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @ApiOperation(value = "查看新闻")
    @RequestMapping("/query")
    public ResponseUtil query() {
        List news = newsService.query();
        return ResponseUtil.success().put("data", news).put("count", news.size());
    }
}
