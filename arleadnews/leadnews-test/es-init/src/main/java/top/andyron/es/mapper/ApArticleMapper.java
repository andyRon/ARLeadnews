package top.andyron.es.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.andyron.es.pojo.SearchArticleVo;
import top.andyron.model.article.pojos.ApArticle;

import java.util.List;

/**
 * @author andyron
 **/
@Mapper
public interface ApArticleMapper extends BaseMapper<ApArticle> {

    public List<SearchArticleVo> loadArticleList();

}
