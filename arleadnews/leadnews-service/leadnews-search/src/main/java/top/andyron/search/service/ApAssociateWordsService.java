package top.andyron.search.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.andyron.model.common.dtos.ResponseResult;
import top.andyron.model.search.dto.UserSearchDto;
import top.andyron.search.pojos.ApAssociateWords;

/**
 * <p>
 * 联想词表 服务类
 * </p>
 *
 * @author itheima
 */
public interface ApAssociateWordsService {

    /**
     * 搜索联想词
     * @param dto
     * @return
     */
    ResponseResult search(UserSearchDto dto);
}
