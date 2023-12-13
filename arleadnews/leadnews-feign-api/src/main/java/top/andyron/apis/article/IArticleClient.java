package top.andyron.apis.article;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.andyron.apis.article.fallback.IArticleClientFallback;
import top.andyron.model.article.dto.ArticleDto;
import top.andyron.model.common.dtos.ResponseResult;

/**
 * 所有文章对外接口定义在此
 * @author andyron
 **/
@FeignClient(value = "leadnews-article", fallback = IArticleClientFallback.class)  // 指定当前服务的名称
public interface IArticleClient {

    @PostMapping("/api/v1/article/save")
    public ResponseResult saveArticle(@RequestBody ArticleDto dto);
}
