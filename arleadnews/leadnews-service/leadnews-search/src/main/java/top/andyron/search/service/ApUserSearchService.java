package top.andyron.search.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.andyron.model.common.dtos.ResponseResult;
import top.andyron.model.search.dto.HistorySearchDto;
import top.andyron.search.pojos.ApUserSearch;

public interface ApUserSearchService  {

    /**
     * 保存用户搜索记录
     * @param keyword
     * @param userId
     */
    public void insert(String keyword, Integer userId);

    /**
     * 查询搜索历史
     * @return
     */
    ResponseResult findUserSearch();

    /**
     * 删除历史记录
     * @param dto
     * @return
     */
    ResponseResult delUserSearch(HistorySearchDto dto);
}
