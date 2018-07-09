package learningsys.service.impl;

import learningsys.entity.News;
import learningsys.repository.NewsDao;
import learningsys.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsDao newsDao;

    @Autowired
    public NewsServiceImpl(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    @Override
    public List query() {
        Iterable<News> news = newsDao.findAll();
        List list = new ArrayList();
        news.forEach(news1 -> list.add(news1));
        return list;
    }
}
