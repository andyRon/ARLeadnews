package top.andyron.search.controller.v1;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.andyron.model.common.dtos.ResponseResult;
import top.andyron.model.search.dto.UserSearchDto;
import top.andyron.search.service.ArticleSearchService;

import java.io.IOException;

@Api(tags = "搜索")
@RestController
@RequestMapping("/api/v1/article/search")
public class ArticleSearchController{
    @Autowired
    private ArticleSearchService articleSearchService;
    @PostMapping("/search")
    public ResponseResult search(@RequestBody UserSearchDto userSearchDto) throws IOException {
        return articleSearchService.search(userSearchDto);
    }
}
