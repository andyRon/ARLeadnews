package top.andyron.article.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import top.andyron.article.mapper.ApArticleContentMapper;
import top.andyron.article.service.ApArticleService;
import top.andyron.article.service.ArticleFreemarkerService;
import top.andyron.common.constants.ArticleConstants;
import top.andyron.file.service.FileStorageService;
import top.andyron.model.article.pojos.ApArticle;
import top.andyron.model.article.pojos.ApArticleContent;
import top.andyron.model.search.vos.SearchArticleVo;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author andyron
 **/
@Service
public class ArticleFreemarkerServiceImpl implements ArticleFreemarkerService {

    @Autowired
    private Configuration configuration;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ApArticleService apArticleService;
    /**
     * 生成静态文件上传到minio中  // TODO 静态页面样式
     *
     * @param apArticle
     * @param content
     */
    @Async
    @Override
    public void buildArticleToMinIO(ApArticle apArticle, String content) {

        if (StringUtils.isNotBlank(content)) {
            Template template = null;
            StringWriter out = new StringWriter();
            try {
                template = configuration.getTemplate("article.ftl");
                Map<String, Object> contentDataModel = new HashMap<>();
                contentDataModel.put("content", JSONArray.parseArray(content));

                template.process(contentDataModel, out);
            } catch (Exception e) {
                e.printStackTrace();
            }


            //3 把html文件上传到minio中
            InputStream in = new ByteArrayInputStream(out.toString().getBytes());
            String path = fileStorageService.uploadHtmlFile("", apArticle.getId() + ".html", in);

            //4 修改ap_article表，保存static_url字段
            apArticleService.update(Wrappers.<ApArticle>lambdaUpdate()
                    .eq(ApArticle::getId, apArticle.getId())
                    .set(ApArticle::getStaticUrl, path));

            // 发送消息，创建es索引
            createArticleEsIndex(apArticle, content, path);
        }
    }

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    /**
     * 发送消息，创建es索引
     * @param apArticle
     * @param content
     * @param path
     */
    private void createArticleEsIndex(ApArticle apArticle, String content, String path) {
        SearchArticleVo vo = new SearchArticleVo();
        BeanUtils.copyProperties(apArticle,vo);
        vo.setContent(content);
        vo.setStaticUrl(path);

        kafkaTemplate.send(ArticleConstants.ARTICLE_ES_SYNC_TOPIC, JSON.toJSONString(vo));
    }
}
