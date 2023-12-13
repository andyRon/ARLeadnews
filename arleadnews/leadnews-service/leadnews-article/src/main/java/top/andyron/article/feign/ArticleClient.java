package top.andyron.article.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.andyron.apis.article.IArticleClient;
import top.andyron.article.service.ApArticleService;
import top.andyron.model.article.dto.ArticleDto;
import top.andyron.model.common.dtos.ResponseResult;

/**
 * @author andyron
 **/
@RestController
public class ArticleClient implements IArticleClient {
    @Autowired
    private ApArticleService apArticleService;
    @Override
    @PostMapping("/api/v1/article/save")
    public ResponseResult saveArticle(@RequestBody ArticleDto dto) {
        return apArticleService.saveArticle(dto);
    }
}
