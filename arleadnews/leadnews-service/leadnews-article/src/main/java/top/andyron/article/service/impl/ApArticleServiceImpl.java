package top.andyron.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.andyron.article.mapper.ApArticleMapper;
import top.andyron.article.service.ApArticleService;
import top.andyron.common.constants.ArticleConstants;
import top.andyron.model.article.dto.ArticleHomeDto;
import top.andyron.model.article.pojos.ApArticle;
import top.andyron.model.common.dtos.ResponseResult;

import java.util.Date;
import java.util.List;

/**
 * @author andyron
 **/
@Service
@Transactional
@Slf4j
public class ApArticleServiceImpl extends ServiceImpl<ApArticleMapper, ApArticle> implements ApArticleService {

    @Autowired
    private ApArticleMapper apArticleMapper;

    /**
     * 单页最大加载的数字
     */
    private final static short MAX_PAGE_SIZE = 50;

    /**
     * 根据参数加载文章列表
     *
     * @param loadType 1 加载更多 2 加载最新
     * @param dto
     * @return
     */
    @Override
    public ResponseResult load(Short loadType, ArticleHomeDto dto) {
        // 1 校验参数
        Integer size = dto.getSize();
        if (size == null || size == 0) {
            size = 10;
        }
        size = Math.min(size, MAX_PAGE_SIZE);
        dto.setSize(size);

        if (!loadType.equals(ArticleConstants.LOADTYPE_LOAD_MORE) && !loadType.equals(ArticleConstants.LOADTYPE_LOAD_NEW)) {
            loadType = ArticleConstants.LOADTYPE_LOAD_MORE;
        }
        if (StringUtils.isEmpty(dto.getTag())) {
            dto.setTag(ArticleConstants.DEFAULT_TAG);
        }
        if (dto.getMaxBehotTime() == null) {
            dto.setMaxBehotTime(new Date());
        }
        if (dto.getMinBehotTime() == null) {
            dto.setMinBehotTime(new Date());
        }
        // 2 查询数据
        List<ApArticle> apArticles = apArticleMapper.loadArticleList(dto, loadType);
        // 3 结果封装
        return ResponseResult.okResult(apArticles);
    }
}
