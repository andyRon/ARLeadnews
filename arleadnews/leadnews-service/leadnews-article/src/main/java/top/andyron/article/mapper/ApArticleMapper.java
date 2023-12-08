package top.andyron.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.andyron.model.article.dto.ArticleHomeDto;
import top.andyron.model.article.pojos.ApArticle;

import java.util.List;

/**
 * @author andyron
 **/
@Mapper
public interface ApArticleMapper extends BaseMapper<ApArticle> {

    /**
     * 加载文章列表
     * @param dto
     * @param type 1 加载更多 2 加载最新
     * @return
     */
    public List<ApArticle> loadArticleList(ArticleHomeDto dto, Short type);
}
