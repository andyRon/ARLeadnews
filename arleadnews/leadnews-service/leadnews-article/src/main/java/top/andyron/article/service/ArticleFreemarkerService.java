package top.andyron.article.service;

import top.andyron.model.article.pojos.ApArticle;

/**
 * @author andyron
 **/
public interface ArticleFreemarkerService {
    /**
     * 生成静态文件上传到minio中
     * @param apArticle
     * @param content
     */
    public void buildArticleToMinIO(ApArticle apArticle, String content);
}
