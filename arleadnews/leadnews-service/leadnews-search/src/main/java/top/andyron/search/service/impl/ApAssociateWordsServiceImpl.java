package top.andyron.search.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import top.andyron.model.common.dtos.ResponseResult;
import top.andyron.model.common.enums.AppHttpCodeEnum;
import top.andyron.model.search.dto.UserSearchDto;
import top.andyron.search.mapper.ApAssociateWordsMapper;
import top.andyron.search.pojos.ApAssociateWords;
import top.andyron.search.service.ApAssociateWordsService;

import java.util.List;

/**
 * <p>
 * 联想词表 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
public class ApAssociateWordsServiceImpl  implements ApAssociateWordsService {


    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 搜索联想词
     * @param dto
     * @return
     */
    @Override
    public ResponseResult search(UserSearchDto dto) {
        // 1.检查参数
        if(StringUtils.isBlank(dto.getSearchWords())){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        // 2.分页检查
        if(dto.getPageSize() > 20){
            dto.setPageSize(20);
        }

        // 3.执行查询，模糊查询
        Query query = Query.query(Criteria.where("associateWords").regex(".*?\\" + dto.getSearchWords() + ".*"));
        query.limit(dto.getPageSize());
        List<ApAssociateWords> apAssociateWords = mongoTemplate.find(query, ApAssociateWords.class);
        return ResponseResult.okResult(apAssociateWords);
    }
}
