package top.andyron.apis.article.fallback;

import org.springframework.stereotype.Component;
import top.andyron.apis.article.IArticleClient;
import top.andyron.model.article.dto.ArticleDto;
import top.andyron.model.common.dtos.ResponseResult;
import top.andyron.model.common.enums.AppHttpCodeEnum;

/**
 * @author andyron
 **/
@Component
public class IArticleClientFallback implements IArticleClient {
    @Override
    public ResponseResult saveArticle(ArticleDto dto) {
        return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR, "获取数据失败");
    }
}
