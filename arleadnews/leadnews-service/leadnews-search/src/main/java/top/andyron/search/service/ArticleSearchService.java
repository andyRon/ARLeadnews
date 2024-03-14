package top.andyron.search.service;

import top.andyron.model.common.dtos.ResponseResult;
import top.andyron.model.search.dto.UserSearchDto;

import java.io.IOException;

public interface ArticleSearchService {

    /**
     * ES文章分页搜索
     */
    ResponseResult search(UserSearchDto userSearchDto) throws IOException;
}
