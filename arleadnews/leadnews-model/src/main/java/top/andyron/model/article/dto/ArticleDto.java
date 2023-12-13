package top.andyron.model.article.dto;

import lombok.Data;
import top.andyron.model.article.pojos.ApArticle;

@Data
public class ArticleDto  extends ApArticle {

    /**
     * 文章内容
     */
    private String content;
}
