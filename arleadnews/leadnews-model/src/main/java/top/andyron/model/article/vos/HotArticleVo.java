package top.andyron.model.article.vos;

import lombok.Data;
import top.andyron.model.article.pojos.ApArticle;

@Data
public class HotArticleVo extends ApArticle {
    /**
     * 文章分值
     */
    private Integer score;
}
