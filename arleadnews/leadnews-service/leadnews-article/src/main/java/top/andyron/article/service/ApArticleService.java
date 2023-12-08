package top.andyron.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.andyron.model.article.dto.ArticleHomeDto;
import top.andyron.model.article.pojos.ApArticle;
import top.andyron.model.common.dtos.ResponseResult;

/**
 * @author andyron
 **/
public interface ApArticleService extends IService<ApArticle> {

    /**
     * 根据参数加载文章列表
     * @param loadType  1 加载更多 2 加载最新
     * @param dto
     * @return
     */
    public ResponseResult load(Short loadType, ArticleHomeDto dto);
}
